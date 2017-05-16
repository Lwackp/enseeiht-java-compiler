package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.GenericParameter;
import fr.n7.stl.block.ast.GenericType;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.List;

/**
 * Project: enseeiht-java-compiler
 * Created by Sacha on 03/05/2017.
 */
public class GenericParameterImpl implements GenericParameter {

    private String name;
    private List<GenericType> inheritance;

    GenericParameterImpl(String _identificateur, List<GenericType> _inheritance){
        this.name = _identificateur;
        this.inheritance = _inheritance;
    }

    @Override
    public String getName() {
        return this.name;
    }


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
        StringBuilder _local = new StringBuilder();
        _local.append(this.name);
        if (!this.inheritance.isEmpty()){
            boolean first = true;
            _local.append(" extends ");
            for (GenericType _type: this.inheritance
                 ) {
                if (!first){
                    _local.append(", ");
                }
                _local.append(_type.toString());

            }
        }
        return _local.toString() ;
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

    @Override
    public List<GenericType> getInheritance() {
        return this.inheritance;
    }
}

