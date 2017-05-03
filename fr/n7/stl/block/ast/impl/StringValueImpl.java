package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.CharValue;
import fr.n7.stl.block.ast.StringValue;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for an integer constant expression.
 * @author Marc Pantel
 *
 */
public class StringValueImpl implements StringValue {

	private String value;

	/**
	 * Builds an integer value expression implementation from a textual representation of the integer.
	 * @param _text Textual representation of the integer value.
	 */
	public StringValueImpl(String _text) {
		value = _text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + this.value;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return AtomicType.StringType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		return null;
	}

}
