package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.GenericParameterType;
import fr.n7.stl.block.ast.Type;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 10/05/2017.
 */
public class GenericParameterTypeImpl implements GenericParameterType {

    String identificateur;

    public GenericParameterTypeImpl(String name) {
        this.identificateur = name;
    }

    //TODO tres sale, à modifier
    @Override
    public boolean equalsTo(Type _other) {
        return this.identificateur == _other.toString();
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return false;
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
        return this.identificateur;
    }
}
