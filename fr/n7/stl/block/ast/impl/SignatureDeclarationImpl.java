package fr.n7.stl.block.ast.impl;

import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.ParameterDeclaration;
import fr.n7.stl.block.ast.SignatureDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

public class SignatureDeclarationImpl implements SignatureDeclaration {

    private String name;
    private Type type;
    private List<ParameterDeclaration> parameters;

    private Register register;
    private int offset;

    public SignatureDeclarationImpl(String _name, Type _returnedType, List<ParameterDeclaration> _parameters) {
        this.name = _name;
        this.type = _returnedType;
        this.parameters = new LinkedList<>(_parameters);
    }

    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.type).append(" ");
        _local.append(this.name);

        _local.append("(");
        boolean first = true;
        for (ParameterDeclaration _parameter : this.getParameters()) {
            if (!first) {
                _local.append(", ");
            }
            _local.append(_parameter);
            first = false;
        }
        _local.append(")");

        return _local.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Synthesized semantics attribute for the type of the declared variable.
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getType() {
        List<Type> _params = new LinkedList<>();
        for (ParameterDeclaration _p : this.parameters) {
            _params.add(_p.getType());
        }
        return new FunctionTypeImpl(this.type, _params);
    }

    /**
     * Synthesized semantics attribute for the register used to compute the address of the variable.
     *
     * @return Register used to compute the address where the declared variable will be stored.
     */
    @Override
    public Register getRegister() {
        return this.register;
    }

    /**
     * Synthesized semantics attribute for the offset used to compute the address of the variable.
     *
     * @return Offset used to compute the address where the declared variable will be stored.
     */
    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public Type getValueType() {
        return this.type;
    }

    @Override
    public List<ParameterDeclaration> getParameters() {
        return this.parameters;
    }

    @Override
    public boolean checkType() {
        //TODO Override checktype
        return true;
    }

    /**
     * Inherited Semantics attribute to allocate memory for the variables declared in the instruction.
     * Synthesized Semantics attribute that compute the size of the allocated memory.
     *
     * @param _register Inherited Register associated to the address of the variables.
     * @param _offset   Inherited Current offset for the address of the variables.
     * @return Synthesized Size of the memory allocated to the variables.
     */
    @Override
    public int allocateMemory(Register _register, int _offset) {
        this.register = _register;
        this.offset = _offset;
        return 0;
    }

    /**
     * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
     * Synthesized Semantics attribute that provide the generated TAM code.
     *
     * @param _factory Inherited Factory to build AST nodes for TAM code.
     * @return Synthesized AST for the generated TAM code.
     */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        return _factory.createFragment();
    }

}