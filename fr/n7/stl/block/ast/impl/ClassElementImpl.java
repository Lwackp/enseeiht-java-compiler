package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;

import java.util.LinkedList;
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
    AccessModifier access = AccessModifier.Public;

    public ClassElementImpl(ClassElement element, ElementModifier[] modifiers) {
        this(((ClassElementImpl)element).name, ((ClassElementImpl)element).type, ((ClassElementImpl)element).parameters, ((ClassElementImpl)element).body);
        this.setModifiers(modifiers);
    }

    public ClassElementImpl(String name, Type type, List<ParameterDeclaration> parameters, Block body) {
        this.name = name;
        this.parameters = new LinkedList<>(parameters);
        this.type = type;
        this.body = body;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(access).append(" ");
        if (b_static) {
            _local.append(NonAccessModifier.Static).append(" ");
        }
        if (b_final) {
            _local.append(NonAccessModifier.Final).append(" ");
        }

        _local.append(this.name);

        if (this.body != null) {
            _local.append("(");
            boolean first = true;
            for (ParameterDeclaration _parameter : this.parameters) {
                if (!first) {
                    _local.append(", ");
                }
                _local.append(_parameter);
                first = false;
            }
            _local.append(") ").append(this.body);

            return _local.toString();
        } else {
            return _local + ";";
        }
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
        return this.name;
    }

    @Override
    public Type getType() {
        return null;
    }

    public void setModifiers(ElementModifier[] modifiers) {
        for (ElementModifier modifier: modifiers){
            if (modifier instanceof AccessModifier) {
                this.access = (AccessModifier) modifier;
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
