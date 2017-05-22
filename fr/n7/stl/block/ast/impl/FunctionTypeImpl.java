package fr.n7.stl.block.ast.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.FunctionType;
import fr.n7.stl.block.ast.Type;

/**
 * Implementation of the Abstract Syntax Tree node for a function type.
 * @author Marc Pantel
 *
 */
public class FunctionTypeImpl implements FunctionType {

	private Type result;
	protected List<Type> parameters;

	public FunctionTypeImpl(Type _result, Iterable<Type> _parameters) {
		this.result = _result;
		this.parameters = new LinkedList<>();
		for (Type _type : _parameters) {
			this.parameters.add(_type);
		}
	}

	/*
	NOT to be used - useful only in ConstructorTypeImpl class
	 */
	public FunctionTypeImpl() {}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof FunctionType) {
			FunctionTypeImpl _otherType = (FunctionTypeImpl) _other;
			boolean _res = (this.result.equalsTo(_otherType.result))
						&& (this.parameters.size() == _otherType.parameters.size());

			for (int i = 0; i < this.parameters.size() && _res; i++) {
				_res = this.parameters.get(i).equalsTo(_otherType.parameters.get(i));
			}

			return _res;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof FunctionTypeImpl) {
			FunctionTypeImpl _otherType = (FunctionTypeImpl) _other;
			boolean _res = (this.result.compatibleWith(_otherType.result))
					&& (this.parameters.size() == _otherType.parameters.size());

			for (int i = 0; i < this.parameters.size(); i++) {
				_res &= this.parameters.get(i).compatibleWith(_otherType.parameters.get(i));
			}

			return _res;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		throw new SemanticsUndefinedException( "merge is undefined in FunctionTypeImpl.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		return this.result.length();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _result = new StringBuilder("(");
		Iterator<Type> _iter = this.parameters.iterator();
		if (_iter.hasNext()) {
			_result.append(_iter.next());
			while (_iter.hasNext()) {
				_result.append(" ,").append(_iter.next());
			}
		}
		return ") -> " + this.result;
	}

}
