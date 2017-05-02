package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 02/05/2017.
 */
public class ClassElementImpl implements ClassElement {
    boolean b_static = false;
    boolean b_final = false;
    String name;
    Type type;
    Object parameters;
    Block[] body;
    AccessModifier acces = AccessModifier.Public;

    public ClassElementImpl(ClassElement element, ElementModifier[] modifiers) {
        this.setModifiers(modifiers);
    }

    public ClassElementImpl(String name, Type type, Object parameters, Block[] body) {
        this.name = name;
        this.parameters = parameters;
        this.type = type;
        this.body = body;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    public void setModifiers(ElementModifier[] modifiers) {
        for (ElementModifier modifier: modifiers){
            if (modifier instanceof AccessModifier) {
                this.acces = (AccessModifier) modifier;
            }
            else if (modifier == NonAccessModifier.Final) {
                b_final = true;
            }
            else if (modifier == NonAccessModifier.Static) {
                b_static = true;
            }
        }
    }
}
