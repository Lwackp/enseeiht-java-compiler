package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.GenericType;
import fr.n7.stl.block.ast.InheritanceDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.List;

/**
 * Created by thibault on 02/05/17.
 */
public class InheritanceDeclarationImpl<T extends Declaration> implements InheritanceDeclaration {

    private T declaration;
    private List<GenericType> generics;

    public InheritanceDeclarationImpl(T _declaration, List<GenericType> _generics) {
        this.declaration = _declaration;
        this.generics = _generics;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.declaration.getName());
        if (this.generics != null) {
            _local.append(" " + this.generics);
        }

        return _local.toString() ;
    }

    /**
     * Synthesized semantics attribute for the register used to compute the address of the variable.
     *
     * @return Register used to compute the address where the declared variable will be stored.
     */
    public Register getRegister() {
        return null;
    }

    /**
     * Synthesized semantics attribute for the offset used to compute the address of the variable.
     *
     * @return Offset used to compute the address where the declared variable will be stored.
     */
    public int getOffset() {
        return 0;
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
        return null;
    }

    @Override
    public T getDeclaration() {
        return this.declaration;
    }
}
