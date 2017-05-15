package fr.n7.stl.block.ast;

import java.util.List;

import static fr.n7.stl.block.ast.AtomicType.ErrorType;

/**
 * @author Matthieu Perrier
 *
 */
public interface FunctionDeclaration extends Declaration, Labellable {

    /**
     * Synthesized semantics attribute for the type of the returned variable.
     * @return Type of the returned variable.
     */
    SignatureDeclaration getSignature();

	/**
	 * Synthesized semantics attribute for the type of the returned variable.
	 * @return Type of the returned variable.
	 */
	Type getValueType();

	/**
	 * Synthesized semantics attribute for the type of the parameters declarations.
	 * @return List of parameters declarations.
	 */
	List<ParameterDeclaration> getParameters();

	/**
	 * Synthesized semantics attribute for the body of the method.
	 * @return body of the method.
	 */
	Block getBody();

}