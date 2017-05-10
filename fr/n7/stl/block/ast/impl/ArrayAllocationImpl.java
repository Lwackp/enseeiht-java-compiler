package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArrayAllocation;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 28/03/17.
 */
public class ArrayAllocationImpl implements ArrayAllocation {

    private Type type;
    private Expression size;

    public ArrayAllocationImpl(Type _type, Expression _size) {
        this.type = _type;
        this.size = _size;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.type);
        _local.append("[");
        _local.append(this.size);
        _local.append("]");

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

        _fragment.append(size.getCode(_factory));
        _fragment.add(_factory.createLoadL(this.type.length()));
        _fragment.add(Library.IMul);
        _fragment.add(Library.MAlloc);

        return _fragment;
    }
}
