package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thibault on 28/03/17.
 */
public class ObjectAllocationImpl implements ObjectAllocation {

    private Type type;
    private List<Type> parameters;

    public ObjectAllocationImpl(Type _type) {
        this.type = _type;
        this.parameters = new LinkedList<>();
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
        return this.type;
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
        List<VariableDeclaration> _attributes = null;
        if (this.type instanceof ClassType) {
            _attributes = ((ClassType) this.type).getAttributes();
        } else if (this.type instanceof GenericType) {
            _attributes = ((GenericType) this.type).getAttributes();
        }
        for (VariableDeclaration _v : _attributes) {
            _attributesSize += _v.getType().length();
        }
        _attributesSize += 1; //Size of virtual method table

        _fragment.add(_factory.createLoadL(_attributesSize));
        _fragment.add(Library.MAlloc);

        ClassType _ctype = null;
        if (this.type instanceof ClassType) {
            _ctype = (ClassType) this.type;
        } else if (this.type instanceof GenericType) {
            _ctype = ((GenericType) this.type).getClassType();
        }
        //TODO: Constructor matching parameters
        List<FunctionDeclaration> _constructors = _ctype.getConstructors();
        FunctionDeclaration _constructor;
        _constructor = _ctype.getConstructor(this.parameters);

        if (_constructor == null || _constructors.isEmpty()) {
            //Initialize Object with default constructor == virtual method table linking
            _fragment.add(_factory.createLoad(_ctype.getDeclaration().getRegister(),
                    _ctype.getDeclaration().getOffset(), 1));
            _fragment.add(_factory.createLoad(Register.ST, -2, 1));
            _fragment.add(_factory.createStoreI(1));
        } else {
            _fragment.add(_factory.createCall(_constructor.getLabel(), Register.LB));
        }

        return _fragment;
    }

    public void setParameters(List<Type> _parameters) {
        this.parameters = _parameters;
    }
}
