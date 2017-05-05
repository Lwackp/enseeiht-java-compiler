package fr.n7.stl.block.ast;

/**
 * Created by thibault on 05/05/17.
 */
public interface Return extends Instruction {

    /**
     * Synthesized semantics attribute for the type of the returned variable.
     *
     * @return Type of the returned variable.
     */
    Type getType();
}
