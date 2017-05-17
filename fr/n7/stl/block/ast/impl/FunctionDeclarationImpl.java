package fr.n7.stl.block.ast.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import static fr.n7.stl.block.ast.AtomicType.ErrorType;

/**
 * Implementation of the Abstract Syntax Tree node for a method declaration instruction.
 * @author Matthieu Perrier
 *
 */
public class FunctionDeclarationImpl implements FunctionDeclaration {

    private SignatureDeclaration signature;
    private FunctionBody body;
    private String label;

    private Register register;
    private int offset;

    /**
     * Creates a method declaration instruction node for the Abstract Syntax Tree.
     * @param _name Name of the declared method.
     * @param _returnedType Type returned by the declared method.
     * @param _parameters List of parameters declarations of the declared method.
     * @param _body Body of the declared method
     */
    public FunctionDeclarationImpl(String _name, Type _returnedType, List<ParameterDeclaration> _parameters,
                                   FunctionBody _body) {
        this.signature = new SignatureDeclarationImpl(_name, _returnedType, _parameters);
        this.body = _body;
    }

    /**
     * Creates a method declaration instruction node for the Abstract Syntax Tree.
     * @param _signature Signature of the declared method
     * @param _body Body of the declared method
     */
    public FunctionDeclarationImpl(SignatureDeclaration _signature, FunctionBody _body) {
        this.signature = _signature;
        this.body = _body;
    }

    @Override
    public String getName() {
        return this.signature.toString();
    }

    /**
     * Synthesized semantics attribute for the type of the declared variable.
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getType() {
        return this.signature.getType();
    }

    /**
     * Synthesized semantics attribute for the register used to compute the address of the variable.
     *
     * @return Register used to compute the address where the declared variable will be stored.
     */
    @Override
    public Register getRegister() {
        return this.signature.getRegister();
    }

    /**
     * Synthesized semantics attribute for the offset used to compute the address of the variable.
     *
     * @return Offset used to compute the address where the declared variable will be stored.
     */
    @Override
    public int getOffset() {
        return this.signature.getOffset();
    }

    @Override
    public boolean checkType() {
        return (this.signature.checkType());
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        this.register = _register;
        this.offset = _offset;

        int _paramssize = 0;
        if (this.signature.getValueType() instanceof ConstructorType) {
            _paramssize += this.getValueType().length();
        }

        //List<ParameterDeclaration> reversedParameters = new LinkedList<>(this.getParameters());
        //Collections.reverse(reversedParameters);

        for (ParameterDeclaration _parameter : this.getParameters()) {
            _paramssize += _parameter.allocateMemory(Register.LB, -1*_paramssize);
        }

        // 3 because of CALL instruction inner behaviour
        this.body.allocateMemory(Register.LB, 3);
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _fragment = _factory.createFragment();

        //By default there is always the pointed object
        int _paramssize = 1;

        for (ParameterDeclaration _parameter : this.signature.getParameters()) {
            _paramssize += _parameter.getType().length();
        }

        this.body.setParametersSize(_paramssize);

        if (this.signature.getValueType() instanceof ConstructorType) {
            _fragment.add(_factory.createLoad(Register.LB, -1, 1));
        }

        _fragment.append(this.body.getCode(_factory));

        if (this.signature.getValueType() == AtomicType.VoidType) {
            _fragment.add(_factory.createReturn(0, _paramssize));
        } else if (this.signature.getValueType() instanceof ConstructorType) {
            ConstructorType _classType = (ConstructorType)this.getValueType();
            _fragment.add(_factory.createLoad(_classType.getClassDeclaration().getRegister(),
                                              _classType.getClassDeclaration().getOffset(),
                                              1));
            _fragment.add(_factory.createLoad(Register.LB, -1, 1));
            _fragment.add(_factory.createStoreI(1));
            _fragment.add(_factory.createReturn(_classType.length(), _paramssize));
        }

        this.label = "function_" + this.signature.getName() + _factory.createLabelNumber();
        _fragment.addPrefix(this.label);

        return _fragment;
    }

    /**
     * Synthesized semantics attribute for the type of the returned variable.
     *
     * @return Type of the returned variable.
     */
    @Override
    public SignatureDeclaration getSignature() {
        return this.signature;
    }

    @Override
    public Type getValueType() {
        return this.signature.getValueType();
    }

    @Override
    public List<ParameterDeclaration> getParameters() {
        return this.signature.getParameters();
    }

    @Override
    public Block getBody() {
        return this.body;
    }

    /**
     * Synthesized semantics attribute for the label of the method.
     *
     * @return label of the method.
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.signature).append(" ");

        _local.append(this.body);

        return _local.toString();
    }
}