package fr.n7.stl.block.ast.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * Implementation of the Abstract Syntax Tree node for a function call expression.
 * @author Marc Pantel
 *
 */
public class FunctionCallImpl implements FunctionCall {

	private Expression function;
	private List<Expression> parameters;
	private List<Type> paramTypes = new LinkedList<>();

	public FunctionCallImpl(Expression _function) {
		this.function = _function;
		this.parameters = new LinkedList<>();
	}

	public FunctionCallImpl(Expression _function, List<Expression> _parameters) {
		this.function = _function;
		this.parameters = _parameters;
        for (Expression e : parameters) {
            paramTypes.add(e.getType());
        }
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
		this.paramTypes.add(_parameter.getType());
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
        List<Type> _paramAux;

		if (this.parameters != null) {
			for (Expression _parameter : this.parameters) {
				_fragment.append(_parameter.getCode(_factory));
			}
		}

		//TODO: A function MUST know its parameters

        _paramAux = new LinkedList<>();
        for (Expression p : parameters)
        {
            _paramAux.add(p.getType());
        }
        if (this.function instanceof ObjectAllocation) {
            ((ObjectAllocationImpl) this.function).setParameters(_paramAux);
        }

		//TODO: Function's code puts function's address in ST
		_fragment.append(this.function.getCode(_factory));

		return _fragment;
	}

	public List<Type> getParamTypes() {
        return this.paramTypes;
    }

}
