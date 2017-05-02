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
    private Object main;

    private int offset;

    public ProgramImpl(List<InterfaceDeclaration> _interfaces, List<ClassDeclaration> _classes, Object _main) {
        this.interfaces = new LinkedList<>(_interfaces);
        this.classes = new LinkedList<>(_classes);
        this.main = _main;
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
        //TODO: Main Class
        //return _result && this.main.checkType();
        _result = false;

        return _result;
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
            _length += _interface.allocateMemory(_register, _length);;
        }
        for (ClassDeclaration _class : this.classes) {
            _length += _class.allocateMemory(_register, _length);;
        }
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
        return null;
    }
}
