package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a field in a record.
 * @author Marc Pantel
 *
 */
public class FieldDeclarationImpl implements FieldDeclaration {

	private String name;
	private Type type;
	private Register register;
	private int offset;

	public FieldDeclarationImpl(String _name, Type _type) {
		this.name = _name;
		this.type = _type;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Declaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.FieldDeclaration#getType()
	 */
	@Override
	public Type getType() {
		return this.type;
	}

	/**
	 * Synthesized semantics attribute for the real type of the declared variable. (like getClass() in Java)
	 *
	 * @return Type of the declared variable.
	 */
	@Override
	public Type getValueType() {
		return VariableDeclarationImpl.SpecialValue.NoValue.getType();
	}

	/**
	 * Synthesized semantics attribute for the register used to compute the address of the variable.
	 *
	 * @return Register used to compute the address where the declared variable will be stored.
	 */
	@Override
	public Register getRegister() {
		return this.register;
	}

	/**
	 * Synthesized semantics attribute for the offset used to compute the address of the variable.
	 *
	 * @return Offset used to compute the address where the declared variable will be stored.
	 */
	@Override
	public int getOffset() {
		return this.offset;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.type + " " + this.name + ";";
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
		this.register = _register;
		this.offset = _offset;
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
}
