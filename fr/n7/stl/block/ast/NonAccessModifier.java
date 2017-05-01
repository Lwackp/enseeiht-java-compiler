package fr.n7.stl.block.ast;

/**
 * Binary operators in the Bloc language.
 * @author Marc Pantel
 *
 */
public enum NonAccessModifier implements ElementModifier {
	
	/**
	 * Access modifier
	 */
	Static,
	Final,
	Abstract;

	@Override
	public String toString() {
		switch (this) {
		case Static: return "static";
		case Final: return "final";
		case Abstract: return "abstract";
		default: throw new IllegalArgumentException( "The default case should never be triggered.");		
		}
	}
}
