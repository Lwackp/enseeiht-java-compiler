package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.TypeDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a type declaration.
 * @author Marc Pantel
 *
 */
public class TypeDeclarationImpl implements TypeDeclaration {

	private String name;
	private Type type;

	public TypeDeclarationImpl(String _name, Type _type) {
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
	 * @see fr.n7.stl.block.ast.TypeDeclaration#getType()
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
		return this.getType();
	}

	/**
	 * Synthesized semantics attribute for the register used to compute the address of the variable.
	 *
	 * @return Register used to compute the address where the declared variable will be stored.
	 */
	@Override
	public Register getRegister() {
		return null;
	}

	/**
	 * Synthesized semantics attribute for the offset used to compute the address of the variable.
	 *
	 * @return Offset used to compute the address where the declared variable will be stored.
	 */
	@Override
	public int getOffset() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		return _factory.createFragment();
	}

}
