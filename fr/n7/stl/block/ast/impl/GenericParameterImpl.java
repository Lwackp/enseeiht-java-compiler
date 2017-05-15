package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.GenericParameter;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 03/05/2017.
 */
public class GenericParameterImpl implements GenericParameter {

    String name;

    GenericParameterImpl(String _identificateur){
        this.name = _identificateur;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //TODO getType Generic
    @Override
    public Type getType() {
        return new GenericParameterTypeImpl(this.name);
    }

    @Override
    public Type getValueType() {
        return null;
    }

    @Override
    public Register getRegister() {
        return null;
    }

    @Override
    public int getOffset() {
        return 0;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean checkType() {
        return true;
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        return null;
    }
}
