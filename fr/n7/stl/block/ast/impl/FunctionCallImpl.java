package fr.n7.stl.block.ast.impl;

import java.util.Iterator;
import java.util.List;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a function call expression.
 * @author Marc Pantel
 *
 */
public class FunctionCallImpl implements FunctionCall {

	private Expression function;
	private List<Expression> parameters;

	public FunctionCallImpl(Expression _function) {
		this.function = _function;
	}

	public FunctionCallImpl(Expression _function, List<Expression> _parameters) {
		this(_function);
		this.parameters = _parameters;
	}

	@Override
	public String toString() {
		StringBuilder _result = new StringBuilder(function + "(");

		if (this.parameters != null) {
			Iterator<Expression> _iter = this.parameters.iterator();
			if (_iter.hasNext()) {
				_result.append(_iter.next());
			}
			while (_iter.hasNext()) {
				_result.append(" ,").append(_iter.next());
			}
		}
		return  _result + ")";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.FunctionCall#add(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public void add(Expression _parameter) {
		this.parameters.add(_parameter);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return this.function.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = _factory.createFragment();

		if (this.parameters != null) {
			for (Expression _parameter : this.parameters) {
				_fragment.append(_parameter.getCode(_factory));
			}
		}

		_fragment.append(this.function.getCode(_factory));

		return _fragment;
	}

}
