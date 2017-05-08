package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.ParameterDeclaration;
import fr.n7.stl.block.ast.ParameterUse;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.impl.ParameterDeclarationImpl;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 05/05/17.
 */
public class ParameterUseImpl implements ParameterUse {

    ParameterDeclaration declaration;

    public ParameterUseImpl(ParameterDeclaration _declaration) {
        this.declaration = _declaration;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return this.declaration.getName();
    }

    /**
     * Synthesized Semantics attribute to compute the type of an expression.
     *
     * @return Synthesized Type of the expression.
     */
    @Override
    public Type getType() {
        return declaration.getType();
    }

    /**
     * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
     * Synthesized Semantics attribute that provide the generated TAM code.
     *
     * @param _factory Inherited Factory to build AST nodes for TAM code.
     * @return Synthesized AST for the generated TAM code.
     */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        return null;
    }

    @Override
    public Declaration getDeclaration() {
        return declaration;
    }
}
