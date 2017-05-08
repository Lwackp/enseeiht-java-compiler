package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassType;
import fr.n7.stl.block.ast.Type;

/**
 * Created by thibault on 05/05/17.
 */
public class ClassTypeImpl implements ClassType {

    private String name;

    public ClassTypeImpl(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Check if two types are equals.
     * This must be an equivalence relation.
     *
     * @param _other The other type (with respect to self).
     * @return True if the type is equals with the type _other, False otherwise.
     */
    @Override
    public boolean equalsTo(Type _other) {
        return false;
    }

    /**
     * Check if two types are compatibles. A subtype is compatibleWith a supertype,
     * but a supertype is not compatible with a subtype.
     * This must be a partial order relation.
     * Check that the self type is compatible with the _other type, i.e. all values of self can be
     * used as a value of _other.
     *
     * @param _other The other type
     * @return True if the types are compatibles, False otherwise.
     */
    @Override
    public boolean compatibleWith(Type _other) {
        return false;
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
        return null;
    }

    /**
     * Compute the size in TAM words needed to store a value of the _self type.
     *
     * @return Number of TAM words needed to store a value of the _self type.
     */
    @Override
    public int length() {
        return 0;
    }
}
