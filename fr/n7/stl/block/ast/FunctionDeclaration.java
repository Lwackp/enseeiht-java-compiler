package fr.n7.stl.block.ast;

import java.util.List;

/**
 * @author Matthieu Perrier
 *
 */
public interface FunctionDeclaration extends Declaration, Instruction {

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

	/**
	 * Synthesized semantics attribute for the signature of the method.
	 * @return signature of the method.
	 */
	SignatureDeclaration getSignature();

	/**
	 * Synthesized semantics attribute for the body of the method.
	 * @return body of the method.
	 */
	Block getBody();

	/**
	 * Synthesized semantics attribute for the offset used to compute the address of the variable.
	 * @return Offset used to compute the address where the declared variable will be stored.
	 */
	//int getOffset();

}