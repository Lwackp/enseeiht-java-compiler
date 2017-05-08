package fr.n7.stl.block.ast.impl;

import java.util.List;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
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

    //private Register register;

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
        return this.signature.getName();
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

    @Override
    public boolean checkType() {
        return (this.signature.checkType());
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        // +3 because of CALL instruction inner behaviour
        this.body.allocateMemory(_register, _offset+3);
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _fragment = _factory.createFragment();

        int _paramssize = 0;
        for (ParameterDeclaration _parameter : this.signature.getParameters()) {
            int _paramsize = _parameter.getType().length();
            _fragment.add(_factory.createLoad(Register.LB, -1*_paramsize, _paramsize));
            _paramssize += _paramsize;
        }

        this.body.setParametersSize(_paramssize);

        _fragment.append(this.body.getCode(_factory));

        //TODO: Return instructions must know parameters's size
        if (this.signature.getReturnedType() == AtomicType.VoidType
         || this.signature.getReturnedType() == ConstructorType) {
            _fragment.add(_factory.createReturn(0, 0));
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
        return this.getSignature();
    }

    @Override
    public Type getReturnedType() {
        return this.signature.getReturnedType();
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