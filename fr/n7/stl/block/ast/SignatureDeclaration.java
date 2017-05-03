package fr.n7.stl.block.ast;

import java.util.List;

/**
 * @author Marc Pantel
 *
 */
public interface SignatureDeclaration extends Declaration {

	/**
	 * Synthesized semantics attribute for the type of the returned variable.
	 * @return Type of the returned variable.
	 */
	Type getReturnedType();

	/**
	 * Synthesized semantics attribute for the type of the parameters declarations.
	 * @return List of parameters declarations.
	 */
	List<ParameterDeclaration> getParameters();
}