package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.GenericParameter;
import fr.n7.stl.block.ast.GenericParameterType;
import fr.n7.stl.block.ast.GenericType;
import fr.n7.stl.block.ast.Type;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 10/05/2017.
 */
public class GenericParameterTypeImpl implements GenericParameterType {

    private GenericParameter declaration;

    public GenericParameterTypeImpl(GenericParameter _declaration) {
        this.declaration = _declaration;
    }

    
    @Override
    public boolean equalsTo(Type _other) {
        boolean _res = true;

        for (GenericType _type : this.declaration.getInheritance()) {
            _res &= _other.compatibleWith(_type);
        }

        return _res;
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return this.equalsTo(_other);
    }

    @Override
    public Type merge(Type _other) {
        return null;
    }

    @Override
    public int length() {
        return 1;
    }

    @Override
    public String toString() {
        return this.declaration.getName();
    }
}
