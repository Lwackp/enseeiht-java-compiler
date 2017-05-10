package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the Abstract Syntax Tree node for an instruction block.
 * @author Thibault Meunier
 *
 */
public class FunctionBodyImpl implements FunctionBody {

	private Block body;
	private int parametersSize;

	public FunctionBodyImpl(Block _body) {
		this.body = _body;
	}

	@Override
	public void setParametersSize(int _paramssize) {
		this.parametersSize = _paramssize;
	}

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 *
	 * @return Type of the declared variable.
	 */
	@Override
	public Type getType() {
		return this.body.getType();
	}

	/**
	 * Add an instruction at then end of a block.
	 *
	 * @param _instruction Instruction node in the AST added to the block node.
	 */
	@Override
	public void add(Instruction _instruction) {
		this.body.add(_instruction);
	}

	/**
	 * Add a sequence of instruction in a block.
	 *
	 * @param _instructions Sequence of instruction nodes in the AST added to the block node.
	 */
	@Override
	public void addAll(Iterable<Instruction> _instructions) {
		this.body.addAll(_instructions);
	}

	/**
	 * Synthesized Semantics attribute to check that an instruction if well typed.
	 *
	 * @return Synthesized True if the instruction is well typed, False if not.
	 */
	@Override
	public boolean checkType() {
		return this.body.checkType();
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
		return this.body.allocateMemory(_register, _offset);
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
		Fragment fragment = _factory.createFragment();
		for (Instruction i : ((BlockImpl)this.body).instructions) {
			if (i instanceof Return) {
				((Return)i).setParametersSize(this.parametersSize);
			}
			fragment.append(i.getCode(_factory));
		}
		fragment.add(_factory.createPop(0, ((BlockImpl)this.body).offset));
		return fragment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.body.toString();
	}

}
