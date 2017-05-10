package fr.n7.stl.block.ast;

import java.lang.Iterable;
import java.util.List;
import java.util.function.Function;

/**
 * Factory to create Abstract Syntax Tree nodes for common instructions in programming languages.
 * @author Marc Pantel
 *
 */
public interface InstructionFactory {

	/**
	 * Create a root Program node in the Abstract Syntax Tree.
	 * @param _interfaces Abstract Syntax Tree of the interfaces contained in the program.
	 * @param _classes Abstract Syntax Tree of the classes contained in the program.
	 * @param _main Abstract Syntax Tree of the main method contained in the program.
	 * @return A Program node in the Abstract Syntax Tree.
	 */
	Program createProgram(List<InterfaceDeclaration> _interfaces, List<ClassDeclaration> _classes, ClassDeclaration _main);

	/**
	 * Create a root Block node in the Abstract Syntax Tree.
	 * @return A Block node in the Abstract Syntax Tree.
	 * To be used to create the root of the instruction part in an Abstract Syntax Tree.
	 * Then the branches corresponding to the instruction in the block will be added using the add method
	 * from the Block interface.
	 */
    Block createBlock();
	 
	/**
	 * Create a root Block node in the Abstract Syntax Tree.
	 * @param _content Abstract Syntax Tree of the instructions contained in the block.
	 * @return A Block node in the Abstract Syntax Tree.
	 * To be used when the Abstract Syntax Tree of the branches are already available.
	 */
    Block createBlock(Iterable<Instruction> _content);
	
	/**
	 * Create an embedded Block node in the Abstract Syntax Tree.
	 * @param _context Abstract Syntax Tree of the containing block.
	 * @return A Block node in the Abstract Syntax Tree.
	 */
    Block createBlock(Block _context);
	
	/**
	 * Create an embedded Block node in the Abstract Syntax Tree.
	 * @param _context Abstract Syntax Tree of the containing block.
	 * @param _content Abstract Syntax Tree of the instructions contained in the block.
	 * @return A Block node in the Abstract Syntax Tree.
	 */
    Block createBlock(Block _context, Iterable<Instruction> _content);

	/**
	 * Create an Function Body node in the Abstract Syntax Tree.
	 * @param _body Abstract Syntax Tree of the function body.
	 * @return A Function body node in the Abstract Syntax Tree.
	 */
	FunctionBody createFunctionBody(Block _body);
	
	/**
	 * Create a constant declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared constant.
	 * @param _type Abstract Syntax Tree for the type of the declared constant.
	 * @param _value Abstract Syntax Tree for the initial value of the declared constant.
	 * @return A Constant Declaration node in the Abstract Syntax Tree.
	 */
    ConstantDeclaration createConstantDeclaration(String _name, Type _type, Expression _value);
	
	/**
	 * Create a variable declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared variable.
	 * @param _type Abstract Syntax Tree for the type of the declared variable.
	 * @return A Variable Declaration node in the Abstract Syntax Tree.
	 */
    VariableDeclaration createVariableDeclaration(String _name, Type _type);

	/**
	 * Create a variable declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared variable.
	 * @param _type Abstract Syntax Tree for the type of the declared variable.
	 * @param _value Abstract Syntax Tree for the initial value of the declared variable.
	 * @return A Variable Declaration node in the Abstract Syntax Tree.
	 */
	VariableDeclaration createVariableDeclaration(String _name, Type _type, Expression _value);

    //TODO: Change Object to generics' type
	/**
	 * Create a interface declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared interface.
	 * @param _generics Abstract Syntax Tree for the generics of the declared interface.
	 * @param _inheritance Abstract Syntax Tree for the inherited interfaces of the declared interface.
	 * @param _elements Abstract Syntax Tree for the elements of the declared interface.
	 * @return An InterfaceDeclaration node in the Abstract Syntax Tree.
	 */
	InterfaceDeclaration createInterfaceDeclaration(String _name,  List<GenericParameter> _generics,
													List<InheritanceDeclaration> _inheritance, List<ClassElement> _elements);

	//TODO: Change Object to generics' type
	/**
	 * Create a class declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared class.
	 * @param _generics Abstract Syntax Tree for the generics of the declared class.
	 * @param _inheritance Abstract Syntax Tree for the inherited class of the declared class.
	 * @param _interfaces Abstract Syntax Tree for the interfaces of the declared class.
	 * @param _elements Abstract Syntax Tree for the elements of the declared class.
	 * @return An ClassDeclaration node in the Abstract Syntax Tree.
	 */
	ClassDeclaration createClassDeclaration(String _name,  List<GenericParameter> _generics,
											InheritanceDeclaration _inheritance, List<InheritanceDeclaration> _interfaces, List<ClassElement> _elements);

	/**
	 * Create a class declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name        Name of the declared class.
	 * @param _element    Abstract Syntax Tree for the element of the declared class.
	 * @return An ClassDeclaration node in the Abstract Syntax Tree.
	 */
	ClassDeclaration createClassDeclaration(String _name, ClassElement _element);

	/**
	 * Create a parameter declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared parameter.
	 * @param _type Abstract Syntax Tree for the type of the declared parameter.
	 * @return An ParameterDeclaration node in the Abstract Syntax Tree.
	 */
	ParameterDeclaration createParameterDeclaration(String _name, Type _type);


	/**
	 * Create an inherited type declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared inherited type.
	 * @param _type Abstract Syntax Tree for the generics of the declared inherited type.
	 * @return An InheritanceDeclaration node in the Abstract Syntax Tree.
	 */
	InheritanceDeclaration createInheritanceDeclaration(String _name, Object _type);

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 * @param _element Element of the declared class element.
	 * @param _modifiers Abstract Syntax Tree for the modifier of the declared class element.
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	ClassElement createClassElement(ClassElement _element, ElementModifier... _modifiers);

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 * @param _element Declaration of the declared class element.
	 * @param _modifiers Abstract Syntax Tree for the modifier of the declared class element.
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	ClassElement createClassElement(Declaration _element, ElementModifier... _modifiers);

	/**
     * Create a class element declaration node in the Abstract Syntax Tree.
     * @param _name Name of the declared class element.
     * @param _type Abstract Syntax Tree for the generics of the declared class element.
     * @param _params
     * @return An ClassElement node in the Abstract Syntax Tree.
     */
    SignatureDeclaration createSignature(String _name, Type _type, List<ParameterDeclaration> _params);

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared class element.
	 * @param _type Abstract Syntax Tree for the generics of the declared class element.
	 * @param _params
	 * @param _body
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	FunctionDeclaration createFunctionDeclaration(String _name, Type _type, List<ParameterDeclaration> _params,
												  FunctionBody _body);

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 * @param _signature Name of the declared class element.
	 * @param _body
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	FunctionDeclaration createFunctionDeclaration(SignatureDeclaration _signature, FunctionBody _body);

	/**
	 * Create a type declaration node in the Abstract Syntax Tree.
	 * @param _name Name of the declared type.
	 * @param _type Abstract Syntax Tree for the type associated to the name.
	 * @return A Type Declaration node in the Abstract Syntax Tree.
	 */
    TypeDeclaration createTypeDeclaration(String _name, Type _type);
	
	/**
	 * Create a conditional node in the Abstract Syntax Tree.
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the conditional when the Conditional node is executed.
	 *                   This node is assigned to the Condition branch of the conditional node.
	 * @param _then TAMInstruction node in the Abstract Syntax Tree that is executed if the condition value is true.
	 *              This node is assigned to the Then branch of the Conditional node.
	 * @param _else TAMInstruction node in the Abstract Syntax Tree that is executed if the condition value is false.
	 *              This node is assigned to the Else branch of the Conditional node.
	 * @return A Conditional node in the Abstract Syntax Tree with both Then and Else branches.
	 */
    Instruction createConditional(Expression _condition, Block _then, Block _else);
	
	/**
	 * Create a conditional node in the Abstract Syntax Tree with only the then part.
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the conditional when the Conditional node is executed.
	 *                   This node is assigned to the Condition branch of the Conditional node.
	 * @param _then TAMInstruction node in the Abstract Syntax Tree that is evaluated if the condition value is true.
	 *              This node is assigned to the Then branch of the conditional node.
	 * @return A Conditional node in the Abstract Syntax Tree with only a Then branch.
	 */
    Instruction createConditional(Expression _condition, Block _then);

    /**
	 * Create a conditional node in the Abstract Syntax Tree with only the then part.
	 * @param _identificateur Abstract Syntax Tree for the generics of the declared generic parameter.
	 * @return A Conditional node in the Abstract Syntax Tree with only a Then branch.
	 */
    GenericParameter createGenericParameter(String _identificateur);
	
	/**
	 * Create a repetition node in the Abstract Syntax Tree.
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the repetition when the Repetition node is executed.
	 *                   This node is assigned to the Condition branch of the Repetition node.
	 * @param _body TAMInstruction node in the Abstract Syntax Tree that is evaluated if the condition value is true
	 *              before repeating the repetition.
	 * @return A Repetition node in the Abstract Syntax Tree.
	 */
    Instruction createRepetition(Expression _condition, Block _body);

	/**
	 * Create a return node in the Abstract Syntax Tree.
	 * @param _return Expression node in the Abstract Syntax Tree whose value is returned.
	 * @return A Return node in the Abstract Syntax Tree.
	 */
	Instruction createReturn(Expression _return);

	/**
	 * Create a return node in the Abstract Syntax Tree.
	 * @return A Return node in the Abstract Syntax Tree.
	 */
	Instruction createReturnThis();

	/**
	 * Create a printer node in the Abstract Syntax Tree.
	 * @param _value Expression node in the Abstract Syntax Tree whose value is printed when the Printer node is executed.
	 *               This node is assigned to the Value branch of the Printer node.
	 * @return A Printer node in the Abstract Syntax Tree.
	 */
    Instruction createPrinter(Expression _value);

	/**
	 * Create a void instruction node in the Abstract Syntax Tree.
	 * @param _expression Expression node in the Abstract Syntax Tree whose expression is executed.
	 * @return A Void Instruction node in the Abstract Syntax Tree.
	 */
	Instruction createVoidInstruction(Expression _expression);

}
