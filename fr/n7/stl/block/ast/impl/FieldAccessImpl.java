package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the Abstract Syntax Tree node for accessing a field in a record.
 * @author Marc Pantel
 *
 */
public class FieldAccessImpl implements Expression {

	protected Expression record;
	private String name;
	private Declaration field;
	private List<Type> parameters;

	public FieldAccessImpl(Expression _record, String _name) {
		this.record = _record;
		this.name = _name;
	}

	public FieldAccessImpl(Expression _record, Declaration _field) {
		this.record = _record;
		this.field = _field;
		this.name = this.field.getName();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.record + "." + this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		Declaration _declaration = this.getField();
		if (_declaration instanceof ClassElement) {
			Declaration _dec = ((ClassElement) _declaration).getDeclaration();
			if (_dec instanceof FunctionDeclaration || _dec instanceof SignatureDeclaration) {
				return _dec.getValueType();
			}
		}
//		if (_declaration instanceof VariableDeclaration) {
//			return ((VariableDeclaration) _declaration).getValueType();
//		}
		return _declaration.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = _factory.createFragment();

		Declaration _field = this.getField();

		if (_field instanceof ClassElement) {
			ClassElement _element = (ClassElement)_field;
			_fragment.append(this.record.getCode(_factory));

			Declaration _declaration = ((ClassElement) _field).getDeclaration();
			if (_declaration instanceof FunctionDeclaration) {
				//Record adress is a parameter for the function, so need to be loaded twice
				_fragment.append(this.record.getCode(_factory));
				//Load virtual method table address
				_fragment.add(_factory.createLoadI(1));
				//Load virtual method table of interface
				//Check if function is an implementation of interface inside the class
				VariableDeclaration _var = (VariableDeclaration)(((VariableUseImpl) (this.record)).getDeclaration());
				ClassDeclaration _class;
				if (_var.getType() instanceof ClassType) {
					_class = ((ClassType) _var.getType()).getDeclaration();
				} else if (_var.getType() instanceof GenericType) {
					_class = ((GenericType) _var.getType()).getClassType().getDeclaration();
				} else {
					//Error
					_class = null;
				}
				if (!_class.getImplementedInterfaces((FunctionDeclaration) _declaration).isEmpty()) {
					_fragment.add(_factory.createLoadI(1));
				}
				//Load method's address from virtual method table (may have problem on functions coming from interface)
				_fragment.add(_factory.createLoadL(_field.getOffset()));
				_fragment.add(Library.IAdd);
				//Load function address
				_fragment.add(_factory.createLoadI(1));

				//Because CallI doesn't work, load the address twice
				//_fragment.add(_factory.createLoad(Register.ST, -1, 1));
				_fragment.add(_factory.createCallI());
			} else if (_declaration instanceof SignatureDeclaration) {
				//Load Interface object
				_fragment.add(_factory.createLoadI(2));

				//Load Virtual method table address
				_fragment.add(_factory.createLoad(Register.ST, -2, 1));
				//Load method's address from virtual method table (may have problem on functions coming from interface)
				_fragment.add(_factory.createLoadL(_field.getOffset()));
				_fragment.add(Library.IAdd);
				//Load function address
				_fragment.add(_factory.createLoadI(1));

				//Because CallI doesn't work, load the address twice
				_fragment.add(_factory.createCallI());
			}
			else {
				_fragment.add(_factory.createLoadL(_declaration.getOffset()));
				_fragment.add(Library.IAdd);
				_fragment.add(_factory.createLoadI(_declaration.getType().length()));
			}
		} else {
			int length = this.getField().getType().length();
			_fragment.append(this.record.getCode(_factory));
			_fragment.add(_factory.createPop(0, this.inOffset()));
			_fragment.add(_factory.createPop(length, this.record.getType().length() - length - this.inOffset()));
		}

		return _fragment;
	}

	protected Declaration getField() {
		if (this.field == null) {
			Type _recordType = this.record.getType();

			if (this.parameters != null) {
				if (_recordType instanceof ClassType) {
					return ((ClassType) _recordType).getElement(this.name, this.parameters);
				} else if (_recordType instanceof InterfaceType) {
					return ((InterfaceType) _recordType).getDeclaration().getElement(this.name, this.parameters);
				} else if (_recordType instanceof GenericType) {
					return ((GenericType) _recordType).getClassType().getElement(this.name, this.parameters);
				} else if (_recordType instanceof GenericParameterType) {
					throw new RuntimeException("Call for method is not possible on Generic parameters");
				}
			}
			if (_recordType instanceof ClassType) {
				return ((ClassType) _recordType).getElement(this.name);
			} else if (_recordType instanceof InterfaceType) {
				return ((InterfaceType) _recordType).getDeclaration().getElement(this.name);
			} else if (_recordType instanceof GenericType) {
				return ((GenericType) _recordType).getClassType().getElement(this.name);
			} else if (_recordType instanceof GenericParameterType) {
				throw new RuntimeException("Call for method is not possible on Generic parameters");
			}
		}
		return field;
	}

	protected int inOffset() {
		int precLength = 0;
		boolean dejaVu = false;
		if (this.record.getType() instanceof ClassType) {
			List<VariableDeclaration> _attributes = ((ClassType)this.record.getType()).getAttributes();
			Collections.reverse(_attributes);
			for (VariableDeclaration f : _attributes) {
				dejaVu |= this.getField().getName().equals(f.getName());

				if (!dejaVu) {
					precLength += f.getType().length();
				}
			}
		}
		return precLength;
	}

	public void setParameters(List<Type> _parameters) {
		this.parameters = _parameters;
	}
}
