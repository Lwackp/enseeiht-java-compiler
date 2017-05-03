package fr.n7.stl.block.ast;

import java.util.List;

/**
 * @author Marc Pantel
 *
 */
public interface SignatureDeclaration extends Declaration {

	/**
	 * Synthesized semantics attribute for the name of the signature.
	 * @return name of the signature
	 */
	String getName();

	/**
	 * Synthesized semantics attribute for the type of the returned variable.
	 * @return Type of the returned variable.
	 */
	Type getReturnedType();

	/**
	 * Synthesized Semantics attribute to check that an instruction if well typed.
	 * @return Synthesized True if the instruction is well typed, False if not.
	 */
	boolean checkType();

	/**
	 * Synthesized semantics attribute for the type of the parameters declarations.
	 * @return List of parameters declarations.
	 */
	List<ParameterDeclaration> getParameters();
}