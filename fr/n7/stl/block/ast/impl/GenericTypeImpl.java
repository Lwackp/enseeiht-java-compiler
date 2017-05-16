package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassType;
import fr.n7.stl.block.ast.GenericType;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.VariableDeclaration;

import java.util.LinkedList;
import java.util.List;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 15/05/2017.
 */
public class GenericTypeImpl implements GenericType {

    Type type;
    private List<GenericType> arguments;

    public GenericTypeImpl(Type _type, List<GenericType> _args){
        this.type = _type;
        this.arguments = _args;
    }

    @Override
    public boolean equalsTo(Type _other) {
        return false;
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return true;
    }

    @Override
    public Type merge(Type _other) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();
        _local.append(this.type.toString());
        if (!arguments.isEmpty()) {
            _local.append("<");

            boolean first = true;
            for (GenericType _arg : this.arguments) {
                if (!first) {
                    _local.append(",");
                }
                _local.append(_arg);
                first = false;
            }
            _local.append(">");
        }
        return _local.toString();
    }

    @Override
    public List<VariableDeclaration> getAttributes() {
        if (this.type instanceof ClassType) {
            return ((ClassType) this.type).getAttributes();
        }
        return new LinkedList<>();
    }

    @Override
    public ClassType getClassType() {
        if (this.type instanceof ClassType) {
            return (ClassType) this.type;
        }
        return null;
    }
}
