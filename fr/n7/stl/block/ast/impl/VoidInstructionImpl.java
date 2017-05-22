package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.VoidInstruction;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 22/05/17.
 */
public class VoidInstructionImpl implements VoidInstruction {

    Expression expression;

    public VoidInstructionImpl(Expression _expression) {
        this.expression = _expression;
    }

    /**
     * Synthesized Semantics attribute to check that an instruction if well typed.
     *
     * @return Synthesized True if the instruction is well typed, False if not.
     */
    @Override
    public boolean checkType() {
        return this.expression.getType().equalsTo(AtomicType.VoidType);
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
        return expression.getCode(_factory);
    }

    @Override
    public String toString() {
        return this.expression.toString() + ";";
    }
}
