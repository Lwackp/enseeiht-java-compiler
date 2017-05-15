package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Factory to create nodes for types.
 * @author Marc Pantel
 *
 */
public interface TypeFactory {

	/**
	 * Create an Abstract Syntax Tree node for the integer type.
	 * @return Abstract Syntax Tree node for the integer type.
	 */
    Type createIntegerType();
	
	/**
	 * Create an Abstract Syntax Tree node for the boolean type.
	 * @return Abstract Syntax Tree node for the boolean type.
	 */
    Type createBooleanType();
	
	/**
	 * Create an Abstract Syntax Tree node for the float type.
	 * @return Abstract Syntax Tree node for the float type.
	 */
    Type createFloatingType();
	
	/**
	 * Create an Abstract Syntax Tree node for the char type.
	 * @return Abstract Syntax Tree node for the char type.
	 */
    Type createCharType();

	/**
	 * Create an Abstract Syntax Tree node for the String type.
	 * @return Abstract Syntax Tree node for the String type.
	 */
    Type createStringType();
	
	/** 
	 * Create an Abstract Syntax Tree node for an array type.
	 * @param _element Abstract Syntax Tree for the type of the elements in the array type.
	 * @return Abstract Syntax Tree node for an array type.
	 */
    Type createArrayType(Type _element);
	
	/**
	 * Create an Abstract Syntax Tree node for a pointer type.
	 * @param _element Abstract Syntax Tree for the type of the target element in the pointer type.
	 * @return Abstract Syntax Tree node for a pointer type.
	 */
    Type createPointerType(Type _element);
	
	/**
	 * Create an Abstract Syntax Tree node for a function type.
	 * @param _result Abstract Syntax Tree for the type of the result in the function type.
	 * @param _parameters Abstract Syntax Trees for the types of the sequence of parameters in the function type.
	 * @return Abstract Syntax Tree node for a function type.
	 */
    Type createFunctionType(Type _result, Iterable<Type> _parameters);
	
	/**
	 * Create an Abstract Syntax Tree node for a named type (e.g. in a type definition).
	 * @param _declaration Abstract Syntax Tree for the target type in the named type.
	 * @return Abstract Syntax Tree node for a named type.
	 */
    Type createNamedType(TypeDeclaration _declaration);

	/**
	 * Create an Abstract Syntax Tree node for a Generic Type
	 * @param _type Abstract Syntax Tree for the target type in the generic type.
	 * @param _args Abstract Syntax Tree for the arguments
	 * @return Abstract Syntax Tree node for a generic type.
	 */
	GenericType createGenericType(Type _type, List<GenericType> _args);
}
