package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ParameterDeclaration;
import fr.n7.stl.block.ast.Type;

/**
 * Created by Thibault Meunier on 02/05/17.
 */
public class ParameterDeclarationImpl implements ParameterDeclaration {

    private String name;
    private Type type;

    ParameterDeclarationImpl(String _name, Type _type) {
        this.name = _name;
        this.type = _type;
    }

    /**
     * Provide the identifier (i.e. name) given to the declaration.
     *
     * @return Name of the declaration.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Synthesized semantics attribute for the type of the declared variable.
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getType() {
        return this.type;
    }
}
