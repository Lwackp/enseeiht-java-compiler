package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public enum BooleanValue implements Value {
	
	/**
	 * Represents the True value.
	 */
	True,
	/**
	 * Represents the False value.
	 */
	False;

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return AtomicType.BooleanType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		switch (this) {
		case False: return "false";
		case True: return "true";
		default: throw new IllegalArgumentException( "The default case should never be triggered.");
		
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _code = _factory.createFragment();
		switch (this) {
			case True : {
				_code.add(_factory.createLoadL(1));
				break;
			}
			case False : {
				_code.add(_factory.createLoadL(0));
				break;
			}
		}
		return _code;
	}
	
}
