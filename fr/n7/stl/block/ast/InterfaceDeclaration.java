package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Register;

/**
 * @author Marc Pantel
 *
 */
public interface InterfaceDeclaration extends Declaration {

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 * @return Type of the declared variable.
	 */
    Type getType();

}
