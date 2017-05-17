package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.WildCard;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 17/05/2017.
 */
public class WildCardImpl implements WildCard {

    @Override
    public boolean equalsTo(Type _other) {
        return true;
    }

    @Override
    public boolean compatibleWith(Type _other) {
        return true;
    }

    @Override
    public Type merge(Type _other) {
        return _other;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public String toString(){
        return "?";
    }
}
