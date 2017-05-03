package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassDeclaration;
import fr.n7.stl.block.ast.InterfaceDeclaration;
import fr.n7.stl.block.ast.Program;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thibault on 02/05/17.
 */
public class ProgramImpl implements Program {

    private List<InterfaceDeclaration> interfaces;
    private List<ClassDeclaration> classes;
    private ClassDeclaration main;

    private int offset;

    public ProgramImpl(List<InterfaceDeclaration> _interfaces, List<ClassDeclaration> _classes, ClassDeclaration _main) {
        this.interfaces = new LinkedList<>(_interfaces);
        this.classes = new LinkedList<>(_classes);
        this.main = _main;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        for (InterfaceDeclaration _interface : this.interfaces) {
            _local.append(_interface);
        }
        for (ClassDeclaration _class : this.classes) {
            _local.append(_class);
        }

        _local.append(this.main);

        return "{\n" + _local + "}\n" ;
    }

    /**
      * Synthesized Semantics attribute to check that an instruction if well typed.
      *
      * @return Synthesized True if the instruction is well typed, False if not.
      */
    @Override
    public boolean checkType() {
        boolean _result = true;

        for (InterfaceDeclaration _interface : this.interfaces) {
            _result = _result && _interface.checkType();
        }
        for (ClassDeclaration _class : this.classes) {
            _result = _result && _class.checkType();
        }
        return _result && this.main.checkType();
    }

    /**
     * Inherited Semantics attribute to allocate memory for the variables declared in the instruction.
     * Synthesized Semantics attribute that compute the size of the allocated memory.
     *
     * @param _register Inherited Register associated to the address of the variables.
     * @param _offset   Inherited Current offset for the address of the variables.
     * @return Synthesized Size of the memory allocated to the variables.
     */
    @Override
    public int allocateMemory(Register _register, int _offset) {
        int _length = _offset;
        for (InterfaceDeclaration _interface : this.interfaces) {
            _length += _interface.allocateMemory(_register, _length);
        }
        for (ClassDeclaration _class : this.classes) {
            _length += _class.allocateMemory(_register, _length);
        }
        _length += main.allocateMemory(_register, _length);
        this.offset = _length - _offset;
        return 0;
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

        for (InterfaceDeclaration _interface : this.interfaces) {
            _fragment.append(_interface.getCode(_factory));
        }
        for (ClassDeclaration _class : this.classes) {
            _fragment.append(_class.getCode(_factory));
        }

        _fragment.append(this.main.getCode(_factory));

        _fragment.add(_factory.createPop(0, this.offset));
        return _fragment;
    }
}
