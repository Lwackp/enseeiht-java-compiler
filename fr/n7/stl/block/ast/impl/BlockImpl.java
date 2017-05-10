package fr.n7.stl.block.ast.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.impl.FragmentImpl;

/**
 * Implementation of the Abstract Syntax Tree node for an instruction block.
 * @author Thibault Meunier
 *
 */
public class BlockImpl implements Block {

	/**
	 * Sequence of instructions contained in a block.
	 */
	List<Instruction> instructions;

	/**
	 * Hierarchical structure of blocks.
	 * Link to the container block.
	 * 
	 */
	private Optional<Block> context;
	
	/**
	 * Subset of instructions corresponding to variable declarations in the same order.
	 */
	private List<VariableDeclaration> variables;

	/**
	 * Subset of instructions corresponding to constant declarations in the same order.
	 */	
	private List<ConstantDeclaration> constants;

	/**
	 * Subset of instructions corresponding to type declarations in the same order.
	 */
	private List<TypeDeclaration> types;

	int offset;

	/**
	 * Constructor for a block contained in a _context block.
	 * @param _context Englobing block.
	 */
	public BlockImpl(Block _context) {
		this.instructions = new LinkedList<>();
		this.variables = new LinkedList<>();
		this.constants = new LinkedList<>();
		this.types = new LinkedList<>();
		if (_context == null) {
			this.context = Optional.empty();
		} else {
			this.context = Optional.of(_context);
		}
	}
	
	/**
	 * Constructor for a block root of the block hierarchy.
	 */
	public BlockImpl() {
		this.instructions = new LinkedList<>();
		this.variables = new LinkedList<>();
		this.constants = new LinkedList<>();
		this.types = new LinkedList<>();
		this.context = Optional.empty();
	}

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 *
	 * @return Type of the declared variable.
	 */
	@Override
	public Type getType() {
		Type _type = AtomicType.VoidType;
		boolean first = true;

		for (Instruction _instruction : this.instructions) {
			if (_instruction instanceof ReturnImpl) {
				Type _typeInstruction = ((ReturnImpl) _instruction).getType();
				if (!first) {
					Type _merge = _typeInstruction.merge(_type);
				}
				_type = _typeInstruction;
				first = false;
			}
		}

		return _type;
	}

	/* (non-Javadoc)
         * @see fr.n7.block.ast.Block#add(fr.n7.block.ast.Instruction)
         */
	@Override
	public void add(Instruction _instruction) {
		this.instructions.add(_instruction);
		if (_instruction instanceof Declaration) {
			this.register((Declaration)_instruction);
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Scope#register(fr.n7.stl.block.ast.Declaration)
	 */
	public void register(Declaration _declaration) {
		if (_declaration instanceof VariableDeclaration) {
			this.variables.add((VariableDeclaration)_declaration);
		} else {
			if (_declaration instanceof ConstantDeclaration) {
				this.constants.add((ConstantDeclaration) _declaration);
			} else {
				if (_declaration instanceof TypeDeclaration) {
					this.types.add((TypeDeclaration) _declaration);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#addAll(java.lang.Iterable)
	 */
	@Override
	public void addAll(Iterable<Instruction> _instructions) {
		for (Instruction i : _instructions) {
			this.instructions.add(i);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _local = new StringBuilder();
		for (Instruction _instruction : this.instructions) {
			_local.append(_instruction);
		}
		return "{\n" + _local + "}\n" ;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#checkType()
	 */
	@Override
	public boolean checkType() {
		boolean _result = true;
		for (Instruction _instruction : this.instructions) {
			_result = _result && _instruction.checkType();
		}
		return _result;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		int _length = _offset;
		for (Instruction i : this.instructions) {
			_length += i.allocateMemory(_register, _length);
		}
		this.offset = _length - _offset;
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();
		for (Instruction i : this.instructions) {
			fragment.append(i.getCode(_factory));
		}
		fragment.add(_factory.createPop(0, this.offset));
		return fragment;
	}

}
