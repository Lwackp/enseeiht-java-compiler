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
		Fragment _fragment = _factory.createFragment();

		_fragment.append(this.value.getCode(_factory));

		if (this.type instanceof InterfaceType) {
			if (this.value == SpecialValue.NoValue) {
				_fragment.add(_factory.createPop(0, 1));
			}
			_fragment.add(_factory.createLoadL(2));
			_fragment.add(Library.MAlloc);
			if (this.value != SpecialValue.NoValue) {
				//Duplicate object address
				_fragment.add(_factory.createLoad(Register.ST, -2, 1));

				if (this.value.getType() instanceof ClassType) {
					//Load current virtual method table address
					_fragment.add(_factory.createLoadI(1));
					_fragment.add(_factory.createLoadL(
							((ClassType) this.value.getType()).getDeclaration()
									.getInterfaceOffset(((InterfaceType) this.type).getDeclaration())
					));
					_fragment.add(Library.IAdd);
					//Load Interface Virtual Method Table
					_fragment.add(_factory.createLoadI(1));


					//Duplicate object address
					_fragment.add(_factory.createLoad(Register.ST, -3, 1));
				} else {
					_fragment.add(_factory.createLoadI(2));
				}
				//Load address where it should be stored
				_fragment.add(_factory.createLoad(Register.ST, -3, 1));
				//Size of pointer to object and to its virtual method table
				_fragment.add(_factory.createStoreI(2));
				_fragment.add(_factory.createPop(1, 1));
			}
		}

		return _fragment;
	}

}
