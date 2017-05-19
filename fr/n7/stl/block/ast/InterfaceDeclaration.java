package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.List;

/**
 * @author Marc Pantel
 *
 */
public interface InterfaceDeclaration extends Declaration, Labellable {

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 * @return Type of the declared variable.
	 */
    Type getType();

	/**
	 * Synthesized Semantics attribute to check that an instruction if well typed.
	 * @return Synthesized True if the instruction is well typed, False if not.
	 */
	boolean checkType();

	/**
	 * Inherited Semantics attribute to allocate memory for the variables declared in the instruction.
	 * Synthesized Semantics attribute that compute the size of the allocated memory.
	 * @param _register Inherited Register associated to the address of the variables.
	 * @param _offset Inherited Current offset for the address of the variables.
	 * @return Synthesized Size of the memory allocated to the variables.
	 */
	int allocateMemory(Register _register, int _offset);

	/**
	 * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
	 * Synthesized Semantics attribute that provide the generated TAM code.
	 * @param _factory Inherited Factory to build AST nodes for TAM code.
	 * @return Synthesized AST for the generated TAM code.
	 */
	Fragment getCode(TAMFactory _factory);

	List<ClassElement> getElements();

	List<ClassElement> getStaticElements();

    List<ClassElement> getNonStaticElements();

    List<SignatureDeclaration> getFunctions();

	boolean isImplementedBy(List<ClassElement> _elements);

	boolean contains(SignatureDeclaration function);

    ClassElement getElement(String _name);
}
