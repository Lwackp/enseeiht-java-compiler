package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 28/03/17.
 */
public class ObjectAllocationImpl implements ObjectAllocation {

    private Type type;

    public ObjectAllocationImpl(Type _type) {
        this.type = _type;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.type);

        return "new " + _local ;
    }

    /**
     * Synthesized Semantics attribute to compute the type of an expression.
     *
     * @return Synthesized Type of the expression.
     */
    @Override
    public Type getType() {
        return new ArrayTypeImpl(this.type);
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

        int _attributesSize = 0;
        for (VariableDeclaration _v : ((ClassType)this.type).getAttributes()) {
            _attributesSize += _v.getType().length();
        }

        _fragment.add(_factory.createLoadL(_attributesSize));
        _fragment.add(Library.MAlloc);

        return _fragment;
    }
}
