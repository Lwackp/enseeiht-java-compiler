package fr.n7.stl.block.ast;

import java.util.List;

import static fr.n7.stl.block.ast.AtomicType.ErrorType;

/**
 * @author Matthieu Perrier
 *
 */
public interface FunctionDeclaration extends Declaration, Instruction {


    public static Type ConstructorType = new Type() {
        /**
         * Check if two types are equals.
         * This must be an equivalence relation.
         *
         * @param _other The other type (with respect to self).
         * @return True if the type is equals with the type _other, False otherwise.
         */
        @Override
        public boolean equalsTo(Type _other) {
            return this == _other;
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
            return 0;
        }

        /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
        @Override
        public String toString() {
            return "";
        }
    };

	/**
	 * Synthesized semantics attribute for the type of the returned variable.
	 * @return Type of the returned variable.
	 */
	Type getReturnedType();

	/**
	 * Synthesized semantics attribute for the type of the parameters declarations.
	 * @return List of parameters declarations.
	 */
	List<ParameterDeclaration> getParameters();

	/**
	 * Synthesized semantics attribute for the body of the method.
	 * @return body of the method.
	 */
	Block getBody();

	/**
	 * Synthesized semantics attribute for the offset used to compute the address of the variable.
	 * @return Offset used to compute the address where the declared variable will be stored.
	 */
	//int getOffset();

}