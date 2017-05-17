package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a printer instruction.
 * @author Matthieu Perrier
 *
 */

public class PrinterlnImpl extends PrinterImpl implements Instruction {

	public PrinterlnImpl(Expression _value) {
		super(_value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "println" + this.parameter + ";";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = super.getCode(_factory);
		
		_fragment.add(_factory.createLoadL("\"\n\""));
		_fragment.add(Library.SOut);

		return _fragment;
	}

}
