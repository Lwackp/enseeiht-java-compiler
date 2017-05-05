package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;

import java.util.List;

/**
 * Implementation of the factory for building Abstract Syntax Tree node for the Bloc language.
 * @author Marc Pantel
 *
 */
public class BlockFactoryImpl implements BlockFactory {

	/**
	 * 
	 */
	public BlockFactoryImpl() {
	}

	/**
	 * Create a node for expressions in the Abstract Syntax Tree.
	 *
	 * @param _expressions Abstract Syntax Tree for the left parameter of the binary operation.
	 * @return Abstract Syntax Tree node for expressions.
	 */
	@Override
	public Expression createExpressions(List<Expression> _expressions) {
		return null;
	}

	/* (non-Javadoc)
         * @see fr.n7.block.ast.ASTFactory#createBinaryExpression(fr.n7.block.ast.Expression, fr.n7.block.ast.Expression)
         */
	@Override
	public Expression createBinaryExpression(Expression _left, BinaryOperator _operator, Expression _right) {
		return new BinaryExpressionImpl(_left,_operator,_right);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createIntegerValue()
	 */
	@Override
	public Value createIntegerValue(String _texte) {
		return new IntegerValueImpl(_texte);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createBlock()
	 */
	@Override
	public Block createBlock(Block _context) {
		return new BlockImpl(_context);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createVariableDeclaration(java.lang.String, fr.n7.block.ast.Type, fr.n7.block.ast.Expression)
	 */
	@Override
	public VariableDeclaration createVariableDeclaration(String _name, Type _type, Expression _initialValue) {
		return new VariableDeclarationImpl(_name,_type,_initialValue);
	}

	/**
	 * Create a interface declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name        Name of the declared interface.
	 * @param _generics    Abstract Syntax Tree for the generics of the declared interface.
	 * @param _inheritance Abstract Syntax Tree for the inherited interfaces of the declared interface.
	 * @param _elements    Abstract Syntax Tree for the elements of the declared interface.
	 * @return An InterfaceDeclaration node in the Abstract Syntax Tree.
	 */
	@Override
	public InterfaceDeclaration createInterfaceDeclaration(String _name, Object _generics, List<InheritanceDeclaration> _inheritance, List<ClassElement> _elements) {
		return new InterfaceDeclarationImpl(_name, _generics, _inheritance, _elements);
	}

	/**
	 * Create a class declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name        Name of the declared class.
	 * @param _generics    Abstract Syntax Tree for the generics of the declared class.
	 * @param _inheritance Abstract Syntax Tree for the inherited class of the declared class.
	 * @param _interfaces  Abstract Syntax Tree for the interfaces of the declared class.
	 * @param _elements    Abstract Syntax Tree for the elements of the declared class.
	 * @return An ClassDeclaration node in the Abstract Syntax Tree.
	 */
	@Override
	public ClassDeclaration createClassDeclaration(String _name, Object _generics, InheritanceDeclaration _inheritance, List<InheritanceDeclaration> _interfaces, List<ClassElement> _elements) {
		return new ClassDeclarationImpl(_name, _generics, _inheritance, _interfaces, _elements);
	}

	/**
	 * Create a class declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name    Name of the declared class.
	 * @param _element Abstract Syntax Tree for the element of the declared class.
	 * @return An ClassDeclaration node in the Abstract Syntax Tree.
	 */
	@Override
	public ClassDeclaration createClassDeclaration(String _name, ClassElement _element) {
		return new ClassDeclarationImpl(_name, _element);
	}

	/**
	 * Create a parameter declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name Name of the declared parameter.
	 * @param _type Abstract Syntax Tree for the type of the declared parameter.
	 * @return An ParameterDeclaration node in the Abstract Syntax Tree.
	 */
	@Override
	public ParameterDeclaration createParameterDeclaration(String _name, Type _type) {
		return new ParameterDeclarationImpl(_name, _type);
	}

	/**
	 * Create an inherited type declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name Name of the declared inherited type.
	 * @param _type Abstract Syntax Tree for the generics of the declared inherited type.
	 * @return An InheritanceDeclaration node in the Abstract Syntax Tree.
	 */
	@Override
	public InheritanceDeclaration createInheritanceDeclaration(String _name, Object _type) {
		return new InheritanceDeclarationImpl(_name, _type);
	}

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 *
	 * @param _element   Element of the declared class element.
	 * @param _modifiers Abstract Syntax Tree for the modifier of the declared class element.
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	@Override
	public ClassElement createClassElement(ClassElement _element, ElementModifier... _modifiers) {
		return new ClassElementImpl(_element, _modifiers);
	}

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 *
	 * @param _element   Declaration of the declared class element.
	 * @param _modifiers Abstract Syntax Tree for the modifier of the declared class element.
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	@Override
	public ClassElement createClassElement(Declaration _element, ElementModifier... _modifiers) {
		return new ClassElementImpl(_element, _modifiers);
	}

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name   Name of the declared class element.
	 * @param _type   Abstract Syntax Tree for the generics of the declared class element.
	 * @param _params
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	@Override
	public SignatureDeclaration createSignature(String _name, Type _type, List<ParameterDeclaration> _params) {
		return new SignatureDeclarationImpl(_name, _type, _params);
	}

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name   Name of the declared class element.
	 * @param _type   Abstract Syntax Tree for the generics of the declared class element.
	 * @param _params
	 * @param _body
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	@Override
	public FunctionDeclaration createFunctionDeclaration(String _name, Type _type, List<ParameterDeclaration> _params, Block _body) {
		return new FunctionDeclarationImpl(_name, _type, _params, _body);
	}

	/**
	 * Create a class element declaration node in the Abstract Syntax Tree.
	 *
	 * @param _signature Name of the declared class element.
	 * @param _body
	 * @return An ClassElement node in the Abstract Syntax Tree.
	 */
	@Override
	public FunctionDeclaration createFunctionDeclaration(SignatureDeclaration _signature, Block _body) {
		return new FunctionDeclarationImpl(_signature, _body);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createIntegerType()
	 */
	@Override
	public Type createIntegerType() {
		return AtomicType.IntegerType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createUnaryExpression(fr.n7.stl.block.ast.UnaryOperator, fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Expression createUnaryExpression(UnaryOperator _operator, Expression _parameter) {
		return new UnaryExpressionImpl(_operator,_parameter);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createParameterUse(fr.n7.stl.block.ast.ParameterDeclaration)
	 */
	@Override
	public Expression createParameterUse(ParameterDeclaration _declaration) {
		return new ParameterUseImpl(_declaration);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createClassElementUse(fr.n7.stl.block.ast.ClassElement)
	 */
	@Override
	public Expression createClassElementUse(ClassElement _declaration) {
		return new ClassElementUseImpl(_declaration);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createVariableUse(fr.n7.stl.block.ast.VariableDeclaration)
	 */
	@Override
	public Expression createVariableUse(VariableDeclaration _declaration) {
		return new VariableUseImpl(_declaration);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createVariableAssignment(fr.n7.stl.block.ast.VariableDeclaration)
	 */
	@Override
	public Assignable createVariableAssignment(VariableDeclaration _declaration) {
		return new VariableAssignmentImpl(_declaration);
	}

	/**
	 * Create a node for a class element assignment in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 *
	 * @param _declaration Abstract Syntax Tree node for the declaration of the class element.
	 * @return Abstract Syntax Tree node for the access to a class element.
	 */
	@Override
	public Assignable createClassElementAssignment(ClassElement _declaration) {
		return new ClassElementAssignmentImpl(_declaration);
	}

	/**
	 * Create a node for a parameter assignment in the Abstract Syntax Tree.
	 * with resolving the reference with the Symbol Table.
	 *
	 * @param _declaration Abstract Syntax Tree node for the declaration of the parameter.
	 * @return Abstract Syntax Tree node for the access to a parameter.
	 */
	@Override
	public Assignable createParameterAssignment(ParameterDeclaration _declaration) {
		return new ParameterAssignmentImpl(_declaration);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createConditional(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.Block, fr.n7.stl.block.ast.Block)
	 */
	@Override
	public Instruction createConditional(Expression _condition, Block _then, Block _else) {
		return new ConditionalImpl(_condition,_then,_else);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createRepetition(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.Block)
	 */
	@Override
	public Instruction createRepetition(Expression _condition, Block _body) {
		return new RepetitionImpl(_condition,_body);
	}

	/**
	 * Create a return node in the Abstract Syntax Tree.
	 *
	 * @param _return Expression node in the Abstract Syntax Tree whose value is returned.
	 * @return A Return node in the Abstract Syntax Tree.
	 */
	@Override
	public Instruction createReturn(Expression _return) {
		return new ReturnImpl(_return);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createBooleanType()
	 */
	@Override
	public Type createBooleanType() {
		return AtomicType.BooleanType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createPrinter(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Instruction createPrinter(Expression _value) {
		return new PrinterImpl(_value);
	}

	/**
	 * Create a void instruction node in the Abstract Syntax Tree.
	 *
	 * @param _expression Expression node in the Abstract Syntax Tree whose expression is executed.
	 * @return A Void Instruction node in the Abstract Syntax Tree.
	 */
	@Override
	public Instruction createVoidInstruction(Expression _expression) {
		if (_expression instanceof Instruction) {
			return (Instruction) _expression;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createAssignment(fr.n7.stl.block.ast.Declaration, fr.n7.stl
	 * .block.ast.Expression)
	 */
	@Override
	public Expression createAssignment(Declaration _declaration, Expression _value) {
		return new AssignmentImpl(_declaration,_value);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#ExpressionFactory(fr.n7.stl.block.ast.VariableDeclaration, fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Expression createAssignment(Expression _assignable, Expression _value) {
		return new AssignmentImpl(_assignable,_value);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createBooleanValue(boolean)
	 */
	@Override
	public Value createBooleanValue(boolean _value) {
		if (_value) {
			return BooleanValue.True;
		} else {
			return BooleanValue.False;
		}
	}

	/**
	 * Create a node for a floating value expression in the Abstract Syntax Tree.
	 *
	 * @param _value Floating value for the Abstract Syntax Tree FloatingValue node.
	 * @return Abstract Syntax Tree node for the floating constant.
	 */
	@Override
	public Value createFloatingValue(String _value) {
		return null;
	}

	/**
	 * Create a node for a char value expression in the Abstract Syntax Tree.
	 *
	 * @param _value Char value for the Abstract Syntax Tree CharValue node.
	 * @return Abstract Syntax Tree node for the char constant.
	 */
	@Override
	public Value createCharValue(String _value) {
		return new CharValueImpl(_value);
	}

	/**
	 * Create a node for a string value expression in the Abstract Syntax Tree.
	 *
	 * @param _value String value for the Abstract Syntax Tree StringValue node.
	 * @return Abstract Syntax Tree node for the string constant.
	 */
	@Override
	public Value createStringValue(String _value) {
		return new StringValueImpl(_value);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createFunctionCall(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public FunctionCall createFunctionCall(Expression _function) {
		return new FunctionCallImpl(_function);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createArrayAccess(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Expression createArrayAccess(Expression _array, Expression _index) {
		return new ArrayAccessImpl(_array,_index);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createArrayAssignment(fr.n7.stl.block.ast.Assignable, fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Assignable createArrayAssignment(Assignable _array, Expression _index) {
		return new ArrayAssignmentImpl(_array,_index);
	}

	/* (non-Javadoc)
         * @see fr.n7.stl.block.ast.ExpressionFactory#createArrayAllocation(fr.n7.stl.block.ast.Assignable)
         */
	@Override
	public Expression createArrayAllocation(Type _type, Expression _size) {
		return new ArrayAllocationImpl(_type, _size);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createFieldAccess(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.FieldDeclaration)
	 */
	@Override
	public Expression createFieldAccess(Expression _record, FieldDeclaration _field) {
		return new FieldAccessImpl(_record,_field);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createFieldAssignment(fr.n7.stl.block.ast.Assignable, fr.n7.stl.block.ast.FieldDeclaration)
	 */
	@Override
	public Assignable createFieldAssignment(Assignable _record, FieldDeclaration _field) {
		return new FieldAssignmentImpl(_record,_field);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createFieldAccess(fr.n7.stl.block.ast.Expression, java.lang.String)
	 */
	@Override
	public Expression createFieldAccess(Expression _record, String _name) {
		return new FieldAccessImpl(_record, _name);
	}
	
	@Override
	public Assignable createFieldAssignment(Assignable _record, String _name) {
		return new FieldAssignmentImpl(_record, _name);
	}

	/**
	 * Create a root Program node in the Abstract Syntax Tree.
	 *
	 * @param _interfaces Abstract Syntax Tree of the interfaces contained in the program.
	 * @param _classes    Abstract Syntax Tree of the classes contained in the program.
	 * @param _main       Abstract Syntax Tree of the main method contained in the program.
	 * @return A Program node in the Abstract Syntax Tree.
	 */
	@Override
	public Program createProgram(List<InterfaceDeclaration> _interfaces, List<ClassDeclaration> _classes,
								 ClassDeclaration _main) {
		return new ProgramImpl(_interfaces, _classes, _main);
	}

	/* (non-Javadoc)
         * @see fr.n7.stl.block.ast.InstructionFactory#createBlock()
         */
	@Override
	public Block createBlock() {
		return createBlock((Block)null);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createBlock(java.lang.Iterable)
	 */
	@Override
	public Block createBlock(Iterable<Instruction> _content) {
		Block _local = createBlock();
		_local.addAll(_content);
		return _local;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createBlock(fr.n7.stl.block.ast.Block, java.lang.Iterable)
	 */
	@Override
	public Block createBlock(Block _context, Iterable<Instruction> _content) {
		Block _local = createBlock(_context);
		_local.addAll(_content);
		return _local;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createConditional(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.Block)
	 */
	@Override
	public Instruction createConditional(Expression _condition, Block _then) {
		return new ConditionalImpl(_condition,_then);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createRecordType(java.lang.Iterable)
	 */
	@Override
	public RecordType createRecordType(String _name, Iterable<FieldDeclaration> _fields) {
		return new RecordTypeImpl(_name, _fields);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createRecordType()
	 */
	@Override
	public RecordType createRecordType(String _name) {
		return new RecordTypeImpl(_name);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createConstantDeclaration(java.lang.String, fr.n7.stl.block.ast.Type, fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public ConstantDeclaration createConstantDeclaration(String _name, Type _type, Expression _value) {
		return new ConstantDeclarationImpl(_name,_type,_value);
	}

	/**
	 * Create a variable declaration node in the Abstract Syntax Tree.
	 *
	 * @param _name Name of the declared variable.
	 * @param _type Abstract Syntax Tree for the type of the declared variable.
	 * @return A Variable Declaration node in the Abstract Syntax Tree.
	 */
	@Override
	public VariableDeclaration createVariableDeclaration(String _name, Type _type) {
		return new VariableDeclarationImpl(_name, _type);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.InstructionFactory#createTypeDeclaration(java.lang.String, fr.n7.stl.block.ast.Type)
	 */
	@Override
	public TypeDeclaration createTypeDeclaration(String _name, Type _type) {
		return new TypeDeclarationImpl(_name,_type);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createArrayType(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type createArrayType(Type _element) {
		return new ArrayTypeImpl(_element);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createPointerType(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type createPointerType(Type _element) {
		return new PointerTypeImpl(_element);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createFunctionType(fr.n7.stl.block.ast.Type, java.lang.Iterable)
	 */
	@Override
	public Type createFunctionType(Type _result, Iterable<Type> _parameters) {
		return new FunctionTypeImpl(_result,_parameters);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createTypeConversion(fr.n7.stl.block.ast.Expression, fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Expression createTypeConversion(Expression _target, Type _type) {
		return new TypeConversionImpl(_target,_type);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createCollection()
	 */
	@Override
	public Sequence createSequence() {
		return new SequenceImpl();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createCollection(java.lang.Iterable)
	 */
	@Override
	public Sequence createSequence(Iterable<Expression> _values) {
		return new SequenceImpl(_values);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createNamedType(fr.n7.stl.block.ast.TypeDeclaration)
	 */
	@Override
	public Type createNamedType(TypeDeclaration _declaration) {
		return new NamedTypeImpl(_declaration);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createPointerAccess(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public Expression createPointerAccess(Expression _pointer) {
		return new PointerAccessImpl(_pointer);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createPointerAssignment(fr.n7.stl.block.ast.Assignable)
	 */
	@Override
	public Assignable createPointerAssignment(Assignable _pointer) {
		return new PointerAssignmentImpl(_pointer);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ExpressionFactory#createPointerAssignment(fr.n7.stl.block.ast.Assignable)
	 */
	@Override
	public Expression createObjectAllocation(Type _type) {
		return new ObjectAllocationImpl(_type);
	}

	public Expression createAddressAccess(Expression _assignable)  {
		return new AddressAccessImpl(_assignable);
	}

	/**
	 * Create a node for an access to the function in an expression in the Abstract Syntax Tree.
	 *
	 * @param _variable Abstract Syntax Tree node for the function.
	 * @return Abstract Syntax Tree node for the access of the content of the function.
	 */
	@Override
	public Expression createFunctionAccess(Expression _variable) {
		return new FunctionCallImpl(_variable);
	}

	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 *
	 * @param _variable   Abstract Syntax Tree node for the accessed function.
	 * @param _parameters Abstract Syntax Tree node for the paramters of the accessed function.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	@Override
	public Expression createFunctionAccess(Expression _variable, List<Expression> _parameters) {
		return new FunctionCallImpl(_variable, _parameters);
	}

	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 *
	 * @param _type Abstract Syntax Tree node for the accessed Type.
	 * @param _id   Abstract Syntax Tree node for the name of the accessed attribute.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	@Override
	public Expression createStaticCallOrAccess(String _type, String _id) {
		return null;
	}

	//TODO: affectation = suiteAffectation
	/**
	 * Create a node for an access to the accessed function in an expression in the Abstract Syntax Tree.
	 *
	 * @param _affectation      Abstract Syntax Tree node for the accessed function.
	 * @param _suiteAffectation Abstract Syntax Tree node for the value of the accessed function.
	 * @return Abstract Syntax Tree node for the access of the content of the accessed function.
	 */
	@Override
	public Expression createAffectation(Expression _affectation, Expression _suiteAffectation) {
		return _affectation;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createFloatingType()
	 */
	@Override
	public Type createFloatingType() {
		return AtomicType.FloatingType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createCharType()
	 */
	@Override
	public Type createCharType() {
		return AtomicType.CharacterType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.TypeFactory#createStringType()
	 */
	@Override
	public Type createStringType() {
		return AtomicType.StringType;
	}

}
