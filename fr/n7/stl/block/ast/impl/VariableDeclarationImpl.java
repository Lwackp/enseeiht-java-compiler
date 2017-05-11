package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;


/**
 * Implementation of the Abstract Syntax Tree node for a variable declaration instruction.
 * @author Thibault Meunier
 *
 */
public class VariableDeclarationImpl implements VariableDeclaration {

	public enum SpecialValue implements Expression {
		NoValue;

		/**
		 * Synthesized Semantics attribute to compute the type of an expression.
		 *
		 * @return Synthesized Type of the expression.
		 */
		@Override
		public Type getType() {
			return AtomicType.VoidType;
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
			Fragment _fragment = _factory.createFragment();
			_fragment.add(_factory.createPush(1));
			_fragment.add(Library.MVoid);
			return _fragment;
		}
	}

	private String name;
	private Type type;
	private Expression value;
	private Register register;
	private int offset;

	/**
	 * Creates a variable declaration instruction node for the Abstract Syntax Tree.
	 * @param _name Name of the declared variable.
	 * @param _type Type of the declared variable.
	 */
	public VariableDeclarationImpl(String _name, Type _type) {
		this(_name, _type, SpecialValue.NoValue);
	}

	/**
	 * Creates a variable declaration instruction node for the Abstract Syntax Tree.
	 * @param _name Name of the declared variable.
	 * @param _type Type of the declared variable.
	 * @param _value Value of the declared variable.
	 */
	public VariableDeclarationImpl(String _name, Type _type, Expression _value) {
		this.name = _name;
		this.type = _type;
		this.value = _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String _result = this.type + " " + this.name;

		if (value != SpecialValue.NoValue) {
			_result += " = " + this.value;
		}

		return _result + ";";
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.VariableDeclaration#getType()
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
		return this.value.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.VariableDeclaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.VariableDeclaration#getRegister()
	 */
	@Override
	public Register getRegister() {
		return this.register;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.VariableDeclaration#getOffset()
	 */
	@Override
	public int getOffset() {
		return this.offset;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return this.value == SpecialValue.NoValue || this.value.getType().compatibleWith(this.type);
	}

	@Override
	public int allocateMemory(Register _register, int _offset) {
		this.register = _register;
		this.offset = _offset;
		return this.type.length();
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();
		
		fragment.append(this.value.getCode(_factory));

		return fragment;
	}

}
