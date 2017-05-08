package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Factory to create Abstract Syntax Tree nodes for common expressions in programming languages.
 * @author Marc Pantel
 *
 */
public interface ExpressionFactory {

	/**
	 * Create a node for expressions in the Abstract Syntax Tree.
	 * @param _expressions Abstract Syntax Tree for the left parameter of the binary operation.
	 * @return Abstract Syntax Tree node for expressions.
	 */
	Expression createExpressions(List<Expression> _expressions);

	/**
	 * Create a node for a binary expression in the Abstract Syntax Tree.
	 * @param _left Abstract Syntax Tree for the left parameter of the binary operation.
	 * @param _operator Enum value for the binary operator.
	 * @param _right Abstract Syntax Tree for the right parameter of the binary operation.
	 * @return Abstract Syntax Tree node for the binary operation.
	 */
    Expression createBinaryExpression(Expression _left, BinaryOperator _operator, Expression _right);

	/**
	 * Create a node for an unary expression in the Abstract Syntax Tree.
	 * @param _operator Enum value for the unary operator.
	 * @param _parameter Abstract Syntax Tree for the parameter of the unary operation.
	 * @return Abstract Syntax Tree node for the unary operation.
	 */
    Expression createUnaryExpression(UnaryOperator _operator, Expression _parameter);
	
	/**
	 * Create a node for an integer value expression in the Abstract Syntax Tree.
	 * @param _texte Textual representation of the value of the Abstract Syntax Tree IntegerValue node.
	 * @return Abstract Syntax Tree node for the integer constant.
	 */
    Value createIntegerValue(String _texte);
	
	/**
	 * Create a node for a boolean value expression in the Abstract Syntax Tree.
	 * @param _value Boolean value for the Abstract Syntax Tree BooleanValue node.
	 * @return Abstract Syntax Tree node for the boolean constant.
	 */
    Value createBooleanValue(boolean _value);

	/**
	 * Create a node for a floating value expression in the Abstract Syntax Tree.
	 * @param _value Floating value for the Abstract Syntax Tree FloatingValue node.
	 * @return Abstract Syntax Tree node for the floating constant.
	 */
	Value createFloatingValue(String _value);


	/**
	 * Create a node for a char value expression in the Abstract Syntax Tree.
	 * @param _value Char value for the Abstract Syntax Tree CharValue node.
	 * @return Abstract Syntax Tree node for the char constant.
	 */
	Value createCharValue(String _value);


	/**
	 * Create a node for a string value expression in the Abstract Syntax Tree.
	 * @param _value String value for the Abstract Syntax Tree StringValue node.
	 * @return Abstract Syntax Tree node for the string constant.
	 */
	Value createStringValue(String _value);
	
	/**
	 * Create a node for a variable use expression in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.	 
	 * @param _declaration Abstract Syntax Tree node for the declaration of the variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
    Expression createVariableUse(VariableDeclaration _declaration);

	/**
	 * Create a node for a parameter use expression in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 * @param _declaration Abstract Syntax Tree node for the declaration of the variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
	Expression createParameterUse(ParameterDeclaration _declaration);

	/**
	 * Create a node for a class element use expression in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 * @param _declaration Abstract Syntax Tree node for the declaration of the variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
	Expression createClassElementUse(ClassElement _declaration);

	/**
	 * Create a node for a this use expression in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
	Expression createClassThisUse();
	
	/**
	 * Create a node for a variable assignment in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.	 
	 * @param _declaration Abstract Syntax Tree node for the declaration of the variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
    Assignable createVariableAssignment(VariableDeclaration _declaration);

	/**
	 * Create a node for a class element assignment in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 * @param _declaration Abstract Syntax Tree node for the declaration of the class element.
	 * @return Abstract Syntax Tree node for the access to a class element.
	 */
	Assignable createClassElementAssignment(ClassElement _declaration);

	/**
	 * Create a node for a parameter assignment in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 * @param _declaration Abstract Syntax Tree node for the declaration of the parameter.
	 * @return Abstract Syntax Tree node for the access to a parameter.
	 */
	Assignable createParameterAssignment(ParameterDeclaration _declaration);

	/**
	 * Create an assignment node in the Abstract Syntax Tree.
	 * @param _declaration Variable Declaration node in the Abstract Syntax Tree corresponding to the assigned variable.
	 * @param _value Abstract Syntax Tree for the expression whose value is assigned to the variable.
	 * @return An Assignment node in the Abstract Syntax Tree.
	 */
	Expression createAssignment(Declaration _declaration, Expression _value);

	/**
	 * Create an assignment node in the Abstract Syntax Tree.
	 * @param _assignable Expression node in the Abstract Syntax Tree corresponding to the assignable part.
	 * @param _value Abstract Syntax Tree for the expression whose value is assigned to the assignable part.
	 * @return An Assignment node in the Abstract Syntax Tree.
	 */
	Expression createAssignment(Expression _assignable, Expression _value);
	
	/**
	 * Create a node for a function call expression in the Abstract Syntax Tree.
	 * @param _called Abstract Syntax Tree node for the called function.
	 * @return Abstract Syntax Tree node for the call of the _called function.
	 */
    FunctionCall createFunctionCall(Expression _called);
	
	/**
	 * Create a node for an access to an element in an array expression in the Abstract Syntax Tree.
	 * @param _array Abstract Syntax Tree node for the array.
	 * @param _index Abstract Syntax Tree node for the index in the array.
	 * @return Abstract Syntax Tree node for the access of the _index cell in the _array.
	 */
    Expression createArrayAccess(Expression _array, Expression _index);
	
	/**
	 * Create a node for an assignment to an element in an array expression in the Abstract Syntax Tree.
	 * @param _array Abstract Syntax Tree node for the array.
	 * @param _index Abstract Syntax Tree node for the index in the array.
	 * @return Abstract Syntax Tree node for the access of the _index cell in the _array.
	 */
    Assignable createArrayAssignment(Assignable _array, Expression _index);

	/**
	 * Create a node for an access to the pointed value in an expression in the Abstract Syntax Tree.
	 * @param _type Abstract Syntax Tree node for the pointer.
	 * @return Abstract Syntax Tree node for the access of the content of the _pointer.
	 */
    Expression createArrayAllocation(Type _type, Expression _size);

	/**
	 * Create a node for an access to a field in a record expression in the Abstract Syntax Tree.
	 * with resolving the reference to the Field Declaration with the Symbol Table.
	 * @param _record Abstract Syntax Tree node for the record.
	 * @param _field Abstract Syntax Tree node for the field in the record.
	 * @return Abstract Syntax Tree node for the access of the _field in the _record.
	 */
    Expression createFieldAccess(Expression _record, FieldDeclaration _field);
	
	/**
	 * Create a node for an assignment to a field in a record expression in the Abstract Syntax Tree.
	 * with resolving the reference to the Field Declaration with the Symbol Table.
	 * @param _record Abstract Syntax Tree node for the record.
	 * @param _field Abstract Syntax Tree node for the field in the record.
	 * @return Abstract Syntax Tree node for the access of the _field in the _record.
	 */
    Assignable createFieldAssignment(Assignable _record, FieldDeclaration _field);

	/**
	 * Create a node for an access to a field in a record expression in the Abstract Syntax Tree.
	 * without resolving the reference to the field name with the Symbol Table.
	 * @param _record Abstract Syntax Tree node for the record.
	 * @param _name Name of the field in the record.
	 * @return Abstract Syntax Tree node for the access of the _name in the _record.
	 */
    Expression createFieldAccess(Expression _record, String _name);

	/**
	 * Create a node for an assignment to a field in a record expression in the Abstract Syntax Tree.
	 * without resolving the reference to the field name with the Symbol Table.
	 * @param _record Abstract Syntax Tree node for the record.
	 * @param _name Name of the field in the record.
	 * @return Abstract Syntax Tree node for the access of the _name in the _record.
	 */
    Assignable createFieldAssignment(Assignable _record, String _name);

	/**
	 * Create a node for an access to the pointed value in an expression in the Abstract Syntax Tree.
	 * @param _pointer Abstract Syntax Tree node for the pointer.
	 * @return Abstract Syntax Tree node for the access of the content of the _pointer.
	 */
    Expression createPointerAccess(Expression _pointer);
	
	/**
	 * Create a node for an assignment to the pointed value in an expression in the Abstract Syntax Tree.
	 * @param _pointer Abstract Syntax Tree node for the pointer.
	 * @return Abstract Syntax Tree node for the access of the content of the _pointer.
	 */
    Assignable createPointerAssignment(Assignable _pointer);

	/**
	 * Create a node for an access to the pointed value in an expression in the Abstract Syntax Tree.
	 * @param _type Abstract Syntax Tree node for the object.
	 * @return Abstract Syntax Tree node for the access of the content of the object.
	 */
    Expression createObjectAllocation(Type _type);

	/**
	 * Create a node for an access to the pointed value in an expression in the Abstract Syntax Tree.
	 * @param _variable Abstract Syntax Tree node for the pointer.
	 * @return Abstract Syntax Tree node for the access of the content of the _pointer.
	 */
    Expression createAddressAccess(Expression _variable);

	/**
	 * Create a node for an access to the function in an expression in the Abstract Syntax Tree.
	 * @param _variable Abstract Syntax Tree node for the function.
	 * @return Abstract Syntax Tree node for the access of the content of the function.
	 */
	Expression createFunctionAccess(Expression _variable);

	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 * @param _variable Abstract Syntax Tree node for the accessed function.
	 * @param _parameters Abstract Syntax Tree node for the parameters of the accessed function.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	Expression createFunctionAccess(Expression _variable, List<Expression> _parameters);

	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 * @param _type Abstract Syntax Tree node for the accessed Type.
	 * @param _id Abstract Syntax Tree node for the name of the accessed attribute.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	Expression createStaticCallOrAccess(String _type, String _id);

	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 * @param _affectation Abstract Syntax Tree node for the accessed function.
	 * @param _suiteAffectation Abstract Syntax Tree node for the value of the accessed function.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	Expression createAffectation(Expression _affectation, Expression _suiteAffectation);
		
	/**
	 * Create a node for a type conversion of an expression in the Abstract Syntax Tree.
	 * @param _target Abstract Syntax Tree for the expression whose value is converted.
	 * @param _type Abstract Syntax Tree for the type toward which the expression value is converted.
	 * @return Abstract Syntax Tree node for the conversion of the value of a target expression to a type.
	 */
    Expression createTypeConversion(Expression _target, Type _type);

	/**
	 * Create a node for a sequence of expressions in the Abstract Syntax Tree.
	 * @return Abstract Syntax Tree node for the creation of a sequence of values.
	 */
    Sequence createSequence();

	/**
	 * Create a node for a sequence of expressions in the Abstract Syntax Tree.
	 * @param _values Abstract Syntax Tree nodes for the values in the collection.
	 * @return Abstract Syntax Tree node for the creation of a collection of values.
	 */
    Sequence createSequence(Iterable<Expression> _values);
}
