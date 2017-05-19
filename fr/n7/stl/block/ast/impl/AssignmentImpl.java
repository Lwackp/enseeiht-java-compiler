package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for an array type.
 * @author Marc Pantel
 *
 */
public class AssignmentImpl implements Expression, Instruction {

	private Declaration declaration;
	private Expression value;
	private String name;
	private Expression assignable;

	/**
	 * Create an assignment instruction implementation from the assigned variable declaration
	 * and the assigned value.
	 * @param _declaration Assigned variable declaration.
	 * @param _value Assigned value.
	 */
	public AssignmentImpl(Declaration _declaration, Expression _value) {
		this.declaration = _declaration;
		this.value = _value;
	}

    public AssignmentImpl(String _name, Expression _value) {
		this.name = _name;
		this.value = _value;
	}

	/**
	 * Create an assignment instruction implementation from the assignable expression
	 * and the assigned value.
	 * @param _assignable Expression that can be assigned a value.
	 * @param _value Value assigned to the expression.
	 */
	public AssignmentImpl(Expression _assignable, Expression _value) {
		this.assignable = _assignable;
		this.value = _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ((this.declaration != null)
				?this.declaration.getName()
				:this.assignable) /*:this.name)*/ + " = " + this.value.toString() + ";";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return this.value.getType().compatibleWith(
				(this.declaration != null) ? this.declaration.getType() : this.assignable.getType());
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		return 0;
	}

	/**
	 * Synthesized Semantics attribute to compute the type of an expression.
	 *
	 * @return Synthesized Type of the expression.
	 */
	@Override
	public Type getType() {
		return AtomicType.VoidType;
	}

	/* (non-Javadoc)
         * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
         */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = _factory.createFragment();

		_fragment.append(this.value.getCode(_factory));
		if (this.declaration == null) {
			_fragment.append(this.assignable.getCode(_factory));
			_fragment.add(_factory.createStoreI(this.value.getType().length()));
		} else {
			//Loading object address == this
			//TODO: Not valid for static method
			if (this.declaration instanceof ClassElement) {
				_fragment.add(_factory.createLoad(Register.LB, -1, 1));
				//Charge taille de ce qu'il y a avant
				_fragment.add(_factory.createLoadL(this.declaration.getOffset()));
				_fragment.add(Library.IAdd);
			} else {
				_fragment.add(_factory.createLoadA(this.declaration.getRegister(), this.declaration.getOffset()));
			}

			_fragment.add(_factory.createStoreI(this.value.getType().length()));

			System.err.println("-----------------------" + this.declaration.getType() + " - " + this.value.getType());
			if (this.declaration.getType() instanceof InterfaceType) {
				if (this.declaration instanceof ClassElement) {
					_fragment.add(_factory.createLoad(Register.LB, -1, 1));
					//Charge taille de ce qu'il y a avant
					_fragment.add(_factory.createLoadL(this.declaration.getOffset()));
					_fragment.add(Library.IAdd);
				} else {
					_fragment.add(_factory.createLoadA(this.declaration.getRegister(), this.declaration.getOffset()));
				}
				//Load Element Address
				_fragment.add(_factory.createLoadI(1));
				//Load current virtual method table address
				_fragment.add(_factory.createLoadI(1));
				//TODO: How do Interface to Interface assignment is managed?
				if (this.value.getType() instanceof ClassType) {
					_fragment.add(_factory.createLoadL(
					((ClassType) this.value.getType()).getDeclaration()
							.getInterfaceOffset(((InterfaceType) this.declaration.getType()).getDeclaration())
					));
				}
				_fragment.add(Library.IAdd);
				//Load Interface Virtual Method Table
				_fragment.add(_factory.createLoadI(1));

				_fragment.add(_factory.createLoad(Register.ST, -2, 1));
				_fragment.add(_factory.createStoreI(1));
			}
		}

		return _fragment;
	}

}
