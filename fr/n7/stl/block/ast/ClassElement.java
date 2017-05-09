package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public interface ClassElement extends Declaration {

	/**
	 * Synthesized semantics attribute for the declaration of the declared class element.
	 * @return Declaration of the declared class element.
	 */
	Declaration getDeclaration();

	boolean isStatic();

	boolean isFinal();

}
