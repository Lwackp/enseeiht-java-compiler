package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface Declaration extends Instruction {
	
	/**
	 * Provide the identifier (i.e. name) given to the declaration.
	 * @return Name of the declaration.
	 */
    String getName();

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 * @return Type of the declared variable.
	 */
	Type getType();

}
