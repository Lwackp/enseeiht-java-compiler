package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface SignatureDeclaration extends Declaration {

	/**
	 * Synthesized semantics attribute for the type of the declared function.
	 * @return Type of the declared variable.
	 */
    Type getType();

}
