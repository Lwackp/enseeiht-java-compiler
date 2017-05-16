package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
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

        if (this.declaration instanceof ClassElementImpl) {
            ClassElementImpl _c_decl = (ClassElementImpl) this.declaration;
            this.b_static = _c_decl.b_static;
            this.b_final = _c_decl.b_final;
            this.access = _c_decl.access;
            this.declaration = ((ClassElementImpl) this.declaration).declaration;
        }

        this.setModifiers(_modifiers);
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

    /**
     * Synthesized semantics attribute for the real type of the declared variable. (like getClass() in Java)
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getValueType() {
        return this.getType();
    }

    /**
     * Synthesized semantics attribute for the register used to compute the address of the variable.
     *
     * @return Register used to compute the address where the declared variable will be stored.
     */
    @Override
    public Register getRegister() {
        return this.declaration.getRegister();
    }

    /**
     * Synthesized semantics attribute for the offset used to compute the address of the variable.
     *
     * @return Offset used to compute the address where the declared variable will be stored.
     */
    @Override
    public int getOffset() {
        return this.declaration.getOffset();
    }

    public void setModifiers(ElementModifier[] modifiers) {
        for (ElementModifier modifier: modifiers){
            if (modifier instanceof AccessModifier) {
                this.access = (AccessModifier) modifier;
            }
            else if (modifier == NonAccessModifier.Final) {
                this.b_final = true;
            }
            else if (modifier == NonAccessModifier.Static) {
                this.b_static = true;
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
        int _length = this.declaration.allocateMemory(_register, _offset);
        if (this.b_static) {
            return _length;
        }
        return 0;
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

    @Override
    public boolean isStatic() {
        return this.b_static;
    }

    @Override
    public boolean isFinal() {
        return this.b_final;
    }

	@Override
	public AccessModifier getAccessModifier() {
		return this.access;
	}
}
