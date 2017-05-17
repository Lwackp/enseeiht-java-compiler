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
    private List<GenericParameter> generics;
    private InheritanceDeclaration<ClassDeclaration> inheritance;
    private List<InheritanceDeclaration<InterfaceDeclaration>> interfaces = new LinkedList<InheritanceDeclaration<InterfaceDeclaration>>();
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
        this.classType = new ClassTypeImpl(this);
    }

    public ClassDeclarationImpl(String _name, List<GenericParameter> _generics,InheritanceDeclaration<ClassDeclaration> _inheritance,
                                List<InheritanceDeclaration<InterfaceDeclaration>> _interfaces,
                                List<ClassElement> _elements) {
        this.name = _name;
        this.generics = _generics;
        this.inheritance = _inheritance;
        if (_interfaces != null) {
            this.interfaces = new LinkedList<InheritanceDeclaration<InterfaceDeclaration>>(_interfaces);
        }
        this.elements = this.sortElements(_elements);
        this.classType = new ClassTypeImpl(this);
    }

    /** Sort elements in the following order : 
     * 			public methods
     * 			public attributes
     * 			protected methods
     * 			protected attributes
     * 			private methods
     * 			private attributes
     */
    private LinkedList<ClassElement> sortElements(List<ClassElement> lce) {
    	LinkedList<ClassElement> res = new LinkedList<>();
    	LinkedList<ClassElement> publicmethods = new LinkedList<>();
    	LinkedList<ClassElement> publicattributes = new LinkedList<>();
    	LinkedList<ClassElement> protectedmethods = new LinkedList<>();
    	LinkedList<ClassElement> protectedattributes = new LinkedList<>();
    	LinkedList<ClassElement> privatemethods = new LinkedList<>();
    	LinkedList<ClassElement> privateattributes = new LinkedList<>();

    	for (ClassElement ce : lce) {
    		switch (ce.getAccessModifier()) {
			case Public:
				if (ce instanceof VariableDeclaration) {
					publicattributes.add(ce);
				} else {
					publicmethods.add(ce);
				}
				break;
			case Protected:
				if (ce instanceof VariableDeclaration) {
					protectedattributes.add(ce);
				} else {
					protectedmethods.add(ce);
				}
				break;
			case Private:
				if (ce instanceof VariableDeclaration) {
					privateattributes.add(ce);
				} else {
					privatemethods.add(ce);
				}
				break;
			default:
				throw new RuntimeException("Modifier " + ce.getAccessModifier() + " is not defined.");
			}
    	}
    	res.addAll(publicmethods);
    	res.addAll(publicattributes);
    	res.addAll(protectedmethods);
    	res.addAll(protectedattributes);
    	res.addAll(privatemethods);
    	res.addAll(privateattributes);
    	return res;
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
        if (this.inheritance != null) {
            _local.append("extends ").append(this.inheritance).append(" ");
        }

        if (this.interfaces != null && !this.interfaces.isEmpty()) {
            _local.append("implements ");
            boolean first = true;
            for (InheritanceDeclaration _interface : this.interfaces) {
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
    	LinkedList<ClassElement> res = new LinkedList<>();
    	// Récupration des éléments hérités
    	if (this.inheritance != null) {
    		res.addAll(this.inheritance.getDeclaration().getHeritableElements());
    	}
    	// Implement new interfaces
    	for (InterfaceDeclaration i : this.getNewInterfaces()) {
    		res.addAll(i.getElements());
    	}
    	
    	// Ajout des éléments propre à la classe. Override si nécessaire
    	for (ClassElement ce : this.elements) {
    		List<Integer> _indices = indicesOf(res, ce);
    		if (_indices.isEmpty()) {
    			// the element doesn't exist, add it at the end
    			res.add(ce);
    		} else {
    			// if the element already exists, override it. Many times if it implements differents interfaces
    			for (int i : _indices) {
    				res.set(i, ce);
    			}
    		}
    	}

    	return res;
	}
    
    /* Returns -1 if not found */
    private List<Integer> indicesOf(List<ClassElement> l, ClassElement ce) {
    	List<Integer> _res = new LinkedList<Integer>();
    	for (int i = 0; i<l.size() ; i++) {
    		if (conflictualDeclaration(ce,l.get(i))) {
    			_res.add(i);
    		}
    	}
    	return _res;
    }
    
    private static boolean conflictualDeclaration(ClassElement ce1, ClassElement ce2) {
    	if (ce1.getDeclaration().getClass().equals(ce2.getDeclaration().getClass())) {
    		// Meme type de déclaration (variable, const, fonction, ...)
    		if (ce1.getDeclaration().getName().equals(ce2.getDeclaration().getName())) {
    			// Noms identiques
    			if (ce1.getDeclaration().getType().equalsTo(ce2.getDeclaration().getType())) {
    				// Type identique
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
	@Override
	public List<ClassElement> getPrivateElements() {
		LinkedList<ClassElement> resu = new LinkedList<>();
		for (ClassElement e : this.getElements()) {
			if (e.getAccessModifier() == AccessModifier.Private) {
				resu.add(e);
			}
		}
		return resu;
	}
    
	@Override
	public List<ClassElement> getProtectedElements() {
		LinkedList<ClassElement> resu = new LinkedList<>();
		for (ClassElement e : this.getElements()) {
			if (e.getAccessModifier() == AccessModifier.Protected) {
				resu.add(e);
			}
		}
		return resu;
	}
    
	@Override
	public List<ClassElement> getPublicElements() {
		LinkedList<ClassElement> resu = new LinkedList<>();
		for (ClassElement e : this.getElements()) {
			if (e.getAccessModifier() == AccessModifier.Public) {
				resu.add(e);
			}
		}
		return resu;
	}
	
	@Override
	public List<ClassElement> getHeritableElements() {
		LinkedList<ClassElement> resu = new LinkedList<>();
		resu.addAll(this.getPublicElements());
		resu.addAll(this.getProtectedElements());
		return resu;
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

        int _staticLength = 0;
        for (ClassElement _element : this.getStaticElements()) {
            _staticLength += _element.allocateMemory(Register.LB, _staticLength);
        }

        //1 is Virtual Method table size
        int _length = 1;
        for (ClassElement _element : this.getNonStaticElements()) {
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

        //TODO: Sort element regarding final, static, public, ...
        for (ClassElement _element : this.elements) {
            _fragment.append(_element.getCode(_factory));
        }

        _fragment.append(this.getVirtualMethodTableCode(_factory));

        //TODO: Inheritance

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

    //TODO: separation between function, attributes, static, inheritance, ...
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
    public List<FunctionDeclaration> getFunctions() {
        List<FunctionDeclaration> _functions = new LinkedList<>();
        for (ClassElement _element : this.getElements()) {
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                FunctionDeclaration _fDeclaration = (FunctionDeclaration)(_element.getDeclaration());
                if (!(_fDeclaration.getValueType() instanceof ConstructorType)) {
                    _functions.add(_fDeclaration);
                }
            }
        }
        return _functions;
    }

    private List<InterfaceDeclaration> getNewInterfaces() {
    	List<InterfaceDeclaration> _heritedInterfaces;
    	List<InterfaceDeclaration> _newInterfaces = new LinkedList<InterfaceDeclaration>();
    	if (this.inheritance == null) {
    		_heritedInterfaces = new LinkedList<>();
    	} else {
    		_heritedInterfaces = this.inheritance.getDeclaration().getInterfaces();
    	}
    	
    	for (InheritanceDeclaration<InterfaceDeclaration> _interface : this.interfaces) {
    		if (_heritedInterfaces.contains(_interface.getDeclaration())) {
    			_newInterfaces.add(_interface.getDeclaration());
    		}
    	}
        return _heritedInterfaces;
    }
    
	@Override
	public List<InterfaceDeclaration> getImplementedInterfaces(FunctionDeclaration _fd) {
		List<InterfaceDeclaration> _res = new LinkedList<InterfaceDeclaration>();
		for (InterfaceDeclaration i : this.getInterfaces()) {
			if (i.contains(_fd.getSignature())) {
				_res.add(i);
			}
		}
		return _res;
	}
    
    @Override
    public List<InterfaceDeclaration> getInterfaces() {
    	List<InterfaceDeclaration> _interfaces;
    	if (this.inheritance == null) {
    		_interfaces = new LinkedList<>();
    	} else {
    		_interfaces = this.inheritance.getDeclaration().getInterfaces();
    	}
    	
    	for (InheritanceDeclaration<InterfaceDeclaration> _interface : this.interfaces) {
    		if (!_interfaces.contains(_interface.getDeclaration())) {
    			_interfaces.add(_interface.getDeclaration());
    		}
    	}
        
        return _interfaces;
    }

    @Override
    public int getVirtualMethodTableLength() {
        int _length = 0;
        for (ClassElement _element : this.getStaticElements()) {
            _length += _element.getType().length();
        }
        _length += this.getFunctions().size();
        _length += this.getInterfaces().size();
        return _length;
    }

    @Override
    public List<VariableDeclaration> getAttributes() {
        List<VariableDeclaration> _attributes = new LinkedList<>();

        for (ClassElement _element : this.getElements()) {
            if (_element.getDeclaration() instanceof VariableDeclaration) {
                _attributes.add((VariableDeclaration) _element.getDeclaration());
            }
        }
        return _attributes;
    }

    @Override
    public Fragment getVirtualMethodTableCode(TAMFactory _factory) {
        Fragment _virtualMethodTable = _factory.createFragment();
        for (ClassElement _element : this.getStaticElements()) {
            if (!(_element.getDeclaration() instanceof FunctionDeclaration)) {
                _virtualMethodTable.append(_element.getCode(_factory));
            }
        }
        for (FunctionDeclaration _function : this.getFunctions()) {
            _virtualMethodTable.add(_factory.createLoadA(_function.getLabel()));
        }
        for (InterfaceDeclaration _interface : this.getInterfaces()) {
            //_virtualMethodTable.add();
        }
        _virtualMethodTable.add(_factory.createLoad(Register.LB, -1, 1));
        _virtualMethodTable.add(_factory.createStoreI(this.getVirtualMethodTableLength()));
        _virtualMethodTable.add(_factory.createReturn(0, 0));
        this.label = "class_" + this.name + "_static_" + _factory.createLabelNumber();
        _virtualMethodTable.addPrefix(this.label);

        return _virtualMethodTable;
    }

    @Override
    public boolean checkGenericsParameter(List<GenericType> _params){

        if (_params.size() == this.generics.size()){
            for (int i = 0; i < _params.size(); i++) {
                for (GenericType _type: this.generics.get(i).getInheritance()) {
                    if (!_params.get(i).compatibleWith(_type)){
                        return false;
                    }
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

}
