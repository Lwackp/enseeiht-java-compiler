package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thibault on 02/05/17.
 */
public class InterfaceDeclarationImpl implements InterfaceDeclaration {

    private String name;
    private List<GenericParameter> generics;
    private List<InheritanceDeclaration<InterfaceDeclaration>> inheritance;
    private List<ClassElement> elements;

    private String label;

    private Register register;
    private int offset;
    public InterfaceDeclarationImpl(String _name,  List<GenericParameter> _generics, List<InheritanceDeclaration<InterfaceDeclaration>> _inheritance,
                                    List<ClassElement> _elements) {
        this.name = _name;
        this.generics = _generics;
        this.inheritance = new LinkedList<>(_inheritance);
        this.elements = new LinkedList<>(_elements);
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(this.name).append(" ");

        if (this.generics != null && !this.generics.isEmpty()) {
            _local.append("<");
            boolean first2 = true;
            for (GenericParameter _param: this.generics){
                if (!first2) {
                    _local.append(", ");
                }
                _local.append(_param);
                first2 = false;
            }
            _local.append("> ");

        }

        if (this.inheritance != null && !this.inheritance.isEmpty()) {
            _local.append("extends ");
            boolean first = true;
            for (InheritanceDeclaration _interface : this.inheritance) {
                if (!first) {
                    _local.append(", ");
                }
                _local.append(_interface);
                first = false;
            }
            _local.append(" ");
        }

        _local.append("{\n");
        for (ClassElement _element : this.elements) {
            _local.append(_element).append("\n");
        }
        _local.append("}");

        return "interface " + _local + "\n" ;
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
        return new InterfaceTypeImpl(this);
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

    /**
     * Synthesized Semantics attribute to check that an instruction if well typed.
     *
     * @return Synthesized True if the instruction is well typed, False if not.
     */
    @Override
    public boolean checkType() {
        //TODO: Inheritance checkType
        return true;
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

        int _length = 0;
        for (ClassElement _element : this.elements) {
            _element.allocateMemory(Register.LB, _length);
            if (!(_element.getDeclaration() instanceof SignatureDeclaration)) {
                _length += 1;
            }
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
        this.label = "interface_" + this.name + _factory.createLabelNumber();
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
    public List<ClassElement> getElements() {
        List<ClassElement> _elements = new ArrayList<>();

        for (InheritanceDeclaration<InterfaceDeclaration> _interface : this.inheritance) {
            for (ClassElement _element : _interface.getDeclaration().getElements()) {
                boolean _unique = true;
                for (ClassElement _other : _elements) {
                    if (_element.getDeclaration() instanceof SignatureDeclaration) {
                        _unique &= !((SignatureDeclaration) _element.getDeclaration()).equalsTo(_other);
                    }
                }
                if (_unique) {
                    _elements.add(_element);
                }
            }
        }

        for (ClassElement _element : this.elements) {
            boolean _unique = true;
            for (ClassElement _other : _elements) {
                if (_element.getDeclaration() instanceof SignatureDeclaration) {
                    _unique &= !((SignatureDeclaration) _element.getDeclaration()).equalsTo(_other);
                }
            }
            if (_unique) {
                _elements.add(_element);
            }
        }

        return _elements;
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
    public List<ClassElement> getNonStaticElements() {
        List<ClassElement> _nonStaticElements = new LinkedList<>();
        for (ClassElement _element : this.getElements()) {
            if (!_element.isStatic()) {
                _nonStaticElements.add(_element);
            }
        }
        return _nonStaticElements;
    }

    @Override
    public List<SignatureDeclaration> getFunctions() {
        List<SignatureDeclaration> _functions = new LinkedList<>();
        for (ClassElement _element : this.getElements()) {
            if (_element.getType() instanceof FunctionType) {
                _functions.add((SignatureDeclaration) _element.getDeclaration());
            }
        }
        return _functions;
    }

    @Override
    public boolean isImplementedBy(List<ClassElement> _elements) {
        boolean _return = true;
        for (SignatureDeclaration _interfaceElement : this.getFunctions()) {
            boolean _implemented = false;
            for (ClassElement _element : _elements) {
                if (_element.getDeclaration() instanceof FunctionDeclaration) {
                    if (((FunctionDeclaration) _element.getDeclaration()).getSignature().toString()
                            .equals(_interfaceElement.toString())) {
                        _implemented = true;
                    }
                }
            }
            _return &= _implemented;
        }
        return _return;
    }

	@Override
	public boolean contains(SignatureDeclaration _function) {
		for (SignatureDeclaration _sd : this.getFunctions()) {
			if (_sd.equalsTo(_function)) {
				return true;
			}
		}
		return false;
	}

    @Override
    public ClassElement getElement(String _name) {
        for (ClassElement _element : this.getElements()) {
            String _elementName = _element.getName();
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                _elementName = ((FunctionDeclaration) _element.getDeclaration()).getSignature().getName();
            }
            if (_elementName.equals(_name)) {
                return _element;
            }
        }
        return null;
    }

    @Override
    public List<InterfaceDeclaration> getInheritedInterfaces() {
        List<InterfaceDeclaration> _interfaces = new LinkedList<>();

        if (this.inheritance != null) {
            for (InheritanceDeclaration<InterfaceDeclaration> _interface : this.inheritance) {
                for (InterfaceDeclaration _inherited : _interface.getDeclaration().getInheritedInterfaces()) {
                    if (!_interfaces.contains(_inherited)) {
                        _interfaces.add(_inherited);
                    }
                }
                if (!_interfaces.contains(_interface)) {
                    _interfaces.add(_interface.getDeclaration());
                }
            }
        }
        return _interfaces;
    }
}
