package fr.n7.stl.block.ast.impl;

import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.ParameterDeclaration;
import fr.n7.stl.block.ast.SignatureDeclaration;
import fr.n7.stl.block.ast.Type;

public class SignatureDeclarationImpl implements SignatureDeclaration {

    private String name;
    private Type type;
    private List<ParameterDeclaration> parameters;

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

    @Override
    public Type getReturnedType() {
        return this.type;
    }

    @Override
    public List<ParameterDeclaration> getParameters() {
        return this.parameters;
    }

    @Override
    public boolean checkType() {
        return false;
    }

}