package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;

import java.util.List;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 02/05/2017.
 */
public class ClassElementImpl implements ClassElement {
    boolean b_static = false;
    boolean b_final = false;
    String name;
    Type type;
    List<ParameterDeclaration> parameters;
    Block body;
    AccessModifier acces = AccessModifier.Public;

    public ClassElementImpl(ClassElement element, ElementModifier[] modifiers) {
        this.setModifiers(modifiers);
    }

    public ClassElementImpl(String name, Type type, List<ParameterDeclaration> parameters, Block body) {
        this.name = name;
        this.parameters = parameters;
        this.type = type;
        this.body = body;
    }

    public ClassElementImpl(VariableDeclaration element, ElementModifier[] modifiers) {
        this.setModifiers(modifiers);
        this.name = element.getName();
        this.type = element.getType();

    }

    //TODO le type "Object" n'est pas encore définis
    public ClassElementImpl(Object element, ElementModifier[] modifiers) {
        this.setModifiers(modifiers);
    }

    public ClassElementImpl(String name, Type type) {
        this.name = name;
        this.type = type;

    }

    public ClassElementImpl(String name, Type type, List<ParameterDeclaration> params) {
        this.parameters = params;
        this.type = type;
        this.name = name;
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
