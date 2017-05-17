package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassDeclaration;
import fr.n7.stl.block.ast.ConstructorType;
import fr.n7.stl.block.ast.Type;

import java.util.LinkedList;
import java.util.List;

import static fr.n7.stl.block.ast.AtomicType.ErrorType;

/**
 * Created by thibault on 09/05/17.
 */
public class ConstructorTypeImpl extends FunctionTypeImpl implements ConstructorType {
    private ClassDeclaration classDeclaration;

    public ConstructorTypeImpl() {}

    public ConstructorTypeImpl(ClassDeclaration _classDeclaration) {
        this();
        this.classDeclaration = _classDeclaration;
        this.parameters = new LinkedList<>();
    }

    public ConstructorTypeImpl(ClassDeclaration _classDeclaration, Iterable<Type> _parameters) {
        this();
        this.classDeclaration = _classDeclaration;
        this.parameters = new LinkedList<>();
        for (Type _type : _parameters) {
            this.parameters.add(_type);
        }
    }

    /* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
    @Override
    public boolean equalsTo(Type _other) {
        return _other instanceof ConstructorType;
    }

    /* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
    @Override
    public boolean compatibleWith(Type _other) {
        return this.equalsTo(_other);
    }

    /**
     * Builds a new type that results from the merging of self and _other according to the compatibleWith relation.
     * Compute the least common type (least upper bound) of two types according to the compatibleWith relation.
     * TA.merge(TB).compatibleWith(TA) and TA.merge(TB).compatibleWith(TB).
     *
     * @param _other The other type.
     * @return A type that is the least upper bound of self and _other according to compatibleWith.
     */
    @Override
    public Type merge(Type _other) {
        if (this.compatibleWith(_other)) {
            return this;
        }
        return ErrorType;
    }

    /**
     * Compute the size in TAM words needed to store a value of the _self type.
     *
     * @return Number of TAM words needed to store a value of the _self type.
     */
    @Override
    public int length() {
        return this.classDeclaration.getType().length();
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    @Override
    public String toString() {
        return "";
    }

    @Override
    public ClassDeclaration getClassDeclaration() {
        return this.classDeclaration;
    }

    @Override
    public void setClassDeclaration(ClassDeclaration _classDeclaration) {
        this.classDeclaration = _classDeclaration;
    }

    public List<Type> getParameters() {
        return this.parameters;
    }
}
