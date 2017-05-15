package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassDeclaration;
import fr.n7.stl.block.ast.ClassStaticUse;
import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by Thibault Meunier
 */
public class ClassStaticUseImpl implements ClassStaticUse {

    ClassDeclaration declaration;

    public ClassStaticUseImpl(ClassDeclaration _declaration) {
        this.declaration = _declaration;
    }

    @Override
    public Declaration getDeclaration() {
        return this.declaration;
    }

    /**
     * Synthesized Semantics attribute to compute the type of an expression.
     *
     * @return Synthesized Type of the expression.
     */
    @Override
    public Type getType() {
        return this.declaration.getType();
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
        Fragment _fragment = _factory.createFragment();

        _fragment.add(_factory.createLoad(this.declaration.getRegister(),
                this.declaration.getOffset(), this.getType().length()));

        return _fragment;
    }

    @Override
    public String toString() {
        return this.declaration.getName();
    }
}
