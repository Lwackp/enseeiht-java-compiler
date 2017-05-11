package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

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
    private String label;

    private Type classType;

    private Register register;
    private int offset;

    public ClassDeclarationImpl(String _name, ClassElement _element) {
        this.name = _name;
        this.elements = new LinkedList<>();
        this.elements.add(_element);
        this.classType = new ClassTypeImpl(this.name, this.getElements());
    }

    public ClassDeclarationImpl(String _name, Object _generics, InheritanceDeclaration _inheritance, List<InheritanceDeclaration> _interfaces, List<ClassElement> _elements) {
        this.name = _name;
        this.generics = _generics;
        this.inheritance = _inheritance;
        this.interfaces = new LinkedList<>(_interfaces);
        this.elements = new LinkedList<>(_elements);
        this.classType = new ClassTypeImpl(this.name, this.getElements());
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
        return this.classType;
    }

    /**
     * Synthesized semantics attribute for the real type of the declared variable. (like getClass() in Java)
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getValueType() {
        return this.getType();
    }

    /**
     * Synthesized semantics attribute for the register used to compute the address of the variable.
     *
     * @return Register used to compute the address where the declared variable will be stored.
     */
    @Override
    public Register getRegister() {
        return this.register;
    }

    /**
     * Synthesized semantics attribute for the offset used to compute the address of the variable.
     *
     * @return Offset used to compute the address where the declared variable will be stored.
     */
    @Override
    public int getOffset() {
        return this.offset;
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

        if (this.interfaces != null && !this.interfaces.isEmpty()) {
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

        return "public class " + _local + "\n" ;
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
        this.register = _register;
        this.offset = _offset;

        int _length = offset;
        for (ClassElement _element : this.elements) {
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                if (((FunctionDeclaration)(_element.getDeclaration())).getValueType() instanceof ConstructorType) {
                    ((ConstructorType)((FunctionDeclaration)(_element.getDeclaration())).getValueType())
                            .setClassDeclaration(this);
                }
            }
            _length += _element.allocateMemory(Register.LB, _length);
        }

        return 1;
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

        for (ClassElement _element : this.getStaticElements()) {
            if (!(_element.getDeclaration() instanceof FunctionDeclaration)) {
                _fragment.append(_element.getCode(_factory));
                _fragment.add(_factory.createLoad(Register.LB, -1, 1));
                _fragment.add(_factory.createLoadL(_element.getOffset()));
                _fragment.add(Library.IAdd);
                _fragment.add(_factory.createStoreI(_element.getType().length()));
            }
        }
        _fragment.add(_factory.createReturn(0, 0));
        this.label = "class_" + this.name + _factory.createLabelNumber();
        _fragment.addPrefix(this.label);

        //TODO: Inheritance

        //TODO: Sort element regarding final, static, public, ...
        for (ClassElement _element : this.elements) {
            _fragment.append(_element.getCode(_factory));
        }

        return _fragment;
    }

    /**
     * Synthesized semantics attribute for the label of the object.
     *
     * @return label of the object.
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public List<ClassElement> getStaticElements() {
        List<ClassElement> _staticElements = new LinkedList<>();
        for (ClassElement _element : this.getElements()) {
            if (_element.isStatic()) {
                _staticElements.add(_element);
            }
        }
        return _staticElements;
    }

    @Override
    public List<FunctionDeclaration> getFunctions() {
        List<FunctionDeclaration> _functions = new LinkedList<>();
        for (ClassElement _element : this.getElements()) {
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                _functions.add((FunctionDeclaration) _element);
            }
        }
        return _functions;
    }
}
