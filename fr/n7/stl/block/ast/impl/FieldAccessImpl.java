package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import sun.util.resources.cldr.de.CalendarData_de_LI;

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
			if (_dec instanceof FunctionDeclaration) {
				return ((FunctionDeclaration) _dec).getValueType();
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
				//Load method's address from virtual method table (may have problem on functions coming from interface)
				_fragment.add(_factory.createLoadL(_field.getOffset()));
				_fragment.add(Library.IAdd);
				//Load function address
				_fragment.add(_factory.createLoadI(1));

				//Because CallI doesn't work, load the address twice
				_fragment.add(_factory.createLoad(Register.ST, -1, 1));
				_fragment.add(_factory.createCallI());
				FunctionDeclaration _functionDeclaration = (FunctionDeclaration) _declaration;
//				//_fragment.add(_factory.createLoad(_functionDeclaration.));
//				_fragment.add(_factory.createCall(((FunctionDeclaration) _declaration).getLabel(), Register.LB));
			} else {
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
			if (_recordType instanceof ClassType) {
				return ((ClassType) _recordType).getElement(this.name);
			} else if (_recordType instanceof InterfaceType) {
				return ((VariableUseImpl)this.record).getDeclaration();
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
}
