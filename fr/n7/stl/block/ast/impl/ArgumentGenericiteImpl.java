package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArgumentGenericite;
import fr.n7.stl.block.ast.Type;

import java.util.List;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 15/05/2017.
 */
public class ArgumentGenericiteImpl implements ArgumentGenericite {
    private Type type;
    private List<ArgumentGenericite> instanceGenericite;


    ArgumentGenericiteImpl(Type _type, List<ArgumentGenericite> _instance){
        this.type = _type;
        this.instanceGenericite = _instance;
    }

    @Override
    public boolean equalsTo(Type _other) {
        return false;
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
}
