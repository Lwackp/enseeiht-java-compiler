package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

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
	private FieldDeclaration field;

	public FieldAccessImpl(Expression _record, String _name) {
		this.record = _record;
		this.name = _name;
	}

	public FieldAccessImpl(Expression _record, FieldDeclaration _field) {
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
		if (_declaration instanceof FunctionDeclaration) {
			return ((FunctionDeclaration) _declaration).getReturnedType();
		}
		return _declaration.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = _factory.createFragment();

		Declaration _field = this.getField();

		if (_field instanceof FunctionDeclaration) {
			_fragment.add(_factory.createCall(((FunctionDeclaration) _field).getLabel(), Register.LB));
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
				return ((ClassType) _recordType).getElement(this.name).getDeclaration();
			}
		}
		return field;
	}

	protected int inOffset() {
		int precLength = 0;
		boolean dejaVu = false;
		if (this.record.getType() instanceof RecordType) {
			List<FieldDeclaration> fields = ((RecordType)this.record.getType()).getFields();
			Collections.reverse(fields);
			for (FieldDeclaration f : fields) {
				dejaVu |= this.getField().getName().equals(f.getName());

				if (!dejaVu) {
					precLength += f.getType().length();
				}
			}
		} else if (this.record.getType() instanceof ClassType) {
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
