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

    /**
     * Set Parameters's size of the Function body node
     * @param _paramssize Parameters's Size node in the AST added to the Function Body node.
     */
    void setParametersSize(int _paramssize);

}
