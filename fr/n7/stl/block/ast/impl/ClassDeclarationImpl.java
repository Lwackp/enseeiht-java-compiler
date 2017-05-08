package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thibault Meunier on 02/05/17.
 */
public class ClassDeclarationImpl implements ClassDeclaration {

    private String name;
    private Object generics;
    private InheritanceDeclaration inheritance;
    private List<InheritanceDeclaration> interfaces;
    private List<ClassElement> elements;
    private ClassThisUse thisElement = new ClassThisUseImpl();

    public ClassDeclarationImpl(String _name, ClassElement _element) {
        this.name = _name;
        this.elements = new LinkedList<>();
        this.elements.add(_element);
    }

    public ClassDeclarationImpl(String _name, Object _generics, InheritanceDeclaration _inheritance, List<InheritanceDeclaration> _interfaces, List<ClassElement> _elements) {
        this.name = _name;
        this.generics = _generics;
        this.inheritance = _inheritance;
        this.interfaces = new LinkedList<>(_interfaces);
        this.elements = new LinkedList<>(_elements);
    }

    /**
     * Provide the identifier (i.e. name) given to the declaration.
     *
     * @return Name of the declaration.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Synthesized semantics attribute for the type of the declared variable.
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getType() {
        return new ClassTypeImpl(this.name, this.getElements());
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.name);

        if (this.generics != null) {
            _local.append(this.generics);
        }
        if (this.inheritance != null) {
            _local.append(" extends ").append(this.inheritance);
        }

        if (this.interfaces != null) {
            _local.append(" implements ");
            boolean first = true;
            for (InheritanceDeclaration _interface : this.interfaces) {
                if (!first) {
                    _local.append(", ");
                }
                _local.append(_interface);
                first = false;
            }
        }

        _local.append(" {\n");
        for (ClassElement _element : this.elements) {
            _local.append(_element).append("\n");
        }
        _local.append("}");

        return "public static class " + _local + "\n" ;
    }

    /**
     * Synthesized Semantics attribute to check that an instruction if well typed.
     *
     * @return Synthesized True if the instruction is well typed, False if not.
     */
    @Override
    public boolean checkType() {
        boolean _result = true;

        for (ClassElement _element : this.elements) {
            //_result = _result && _element.checkType();
        }
        return _result;
    }

    /**
     * Synthesized semantics attribute for the elements of the declared class.
     *
     * @return Elements of the declared class.
     */
    @Override
    public List<ClassElement> getElements() {
        return new LinkedList<>(elements);
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
        for (ClassElement _element : this.elements) {
            _element.allocateMemory(_register, _offset);
        }

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

        //TODO: Inheritance

        //TODO: Sort element regarding final, static, public, ...
        for (ClassElement _element : this.elements) {
            _fragment.append(_element.getCode(_factory));
        }

        return _fragment;
    }
}
