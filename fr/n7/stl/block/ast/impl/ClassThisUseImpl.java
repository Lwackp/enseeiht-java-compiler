package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassElement;
import fr.n7.stl.block.ast.ClassThisUse;
import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 05/05/17.
 */
public class ClassThisUseImpl implements ClassThisUse {

    public ClassThisUseImpl() {
    }

    @Override
    public Declaration getDeclaration() {
        return null;
    }

    /**
     * Synthesized Semantics attribute to compute the type of an expression.
     *
     * @return Synthesized Type of the expression.
     */
    @Override
    public Type getType() {
        return null;
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
};
