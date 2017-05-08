package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

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
        return this.declaration.getType();
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

    /**
     * Synthesized Semantics attribute to check that an instruction if well typed.
     *
     * @return Synthesized True if the instruction is well typed, False if not.
     */
    @Override
    public boolean checkType() {
        return true;
    }

    /**
     * Inherited Semantics attribute to allocate memory for the variables declared in the instruction.
     * Synthesized Semantics attribute that compute the size of the allocated memory.
     *
     * @param _register Inherited Register associated to the address of the variables.
     * @param _offset   Inherited Current offset for the address of the variables.
     * @return Synthesized Size of the memory allocated to the variables.
     */
    @Override
    public int allocateMemory(Register _register, int _offset) {
        return this.declaration.allocateMemory(_register, _offset);
    }

    /**
     * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
     * Synthesized Semantics attribute that provide the generated TAM code.
     *
     * @param _factory Inherited Factory to build AST nodes for TAM code.
     * @return Synthesized AST for the generated TAM code.
     */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        return declaration.getCode(_factory);
    }

    /**
     * Synthesized semantics attribute for the declaration of the declared class element.
     *
     * @return Declaration of the declared class element.
     */
    @Override
    public Declaration getDeclaration() {
        return this.declaration;
    }
}
