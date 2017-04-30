package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface ParameterDeclaration extends Declaration {

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 * @return Type of the declared variable.
	 */
    Type getType();

}
