package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a printer instruction.
 * @author Marc Pantel
 *
 */
public class PrinterImpl implements Instruction {

	protected Expression parameter;

	public PrinterImpl(Expression _value) {
		this.parameter = _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "print(" + this.parameter + ");";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return this.parameter.getType() instanceof AtomicType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _fragment = _factory.createFragment();

		_fragment.append(this.parameter.getCode(_factory));

		Type _parameterType = this.parameter.getType();

		if (_parameterType.compatibleWith(AtomicType.IntegerType)) {
			_fragment.add(Library.IOut);
		} else if (_parameterType.compatibleWith(AtomicType.StringType)) {
			_fragment.add(Library.SOut);
		} else if (_parameterType.compatibleWith(AtomicType.StringType)) {
			_fragment.add(Library.COut);
		}

		return _fragment;
	}

}
