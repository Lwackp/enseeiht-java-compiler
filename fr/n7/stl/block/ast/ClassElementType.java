package fr.n7.stl.block.ast;

/**
 * Binary operators in the Bloc language.
 * @author Marc Pantel
 *
 */
public enum ClassElementType {
	
	/**
	 * Access modifier
	 */
	Attribute,
	Method;

	@Override
	public String toString() {
		switch (this) {
		case Attribute: return "attribute";
		case Method: return "method";
		default: throw new IllegalArgumentException( "The default case should never be triggered.");
		}
	}
}
