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
    Declaration declaration;
    AccessModifier access = AccessModifier.Public;

    public ClassElementImpl(Declaration _declaration, ElementModifier[] _modifiers) {
        this.declaration = _declaration;
        this.setModifiers(_modifiers);
    }

    public ClassElementImpl(ClassElement element, ElementModifier[] _modifiers) {
        this(((ClassElementImpl)element).declaration, _modifiers);
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

        _local.append(this.declaration);

        return _local.toString();
    }

    @Override
    public String getName() {
        return this.declaration.getName();
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
