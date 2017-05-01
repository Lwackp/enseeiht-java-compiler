package fr.n7.stl.block.ast;

/**
 * Binary operators in the Bloc language.
 * @author Marc Pantel
 *
 */
public enum AccessModifier implements ElementModifier {
	
	/**
	 * Access modifier
	 */
	Public,
	Protected,
	Private;

	@Override
	public String toString() {
		switch (this) {
		case Public: return "public";
		case Protected: return "protected";
		case Private: return "private";
		default: throw new IllegalArgumentException( "The default case should never be triggered.");		
		}
	}
}
