package fr.n7.stl.block.ast;

/**
 * Created by thibault on 08/05/17.
 */
public interface FunctionBody extends Block {

    /**
     * Set Parameters's size of the Function body node
     * @param _paramssize Parameters's Size node in the AST added to the Function Body node.
     */
    void setParametersSize(int _paramssize);

}
