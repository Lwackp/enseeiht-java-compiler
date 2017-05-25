package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

/**
 * Created by thibault on 05/05/17.
 */
public class ClassTypeImpl implements ClassType {

    private ClassDeclaration declaration;

    public ClassTypeImpl(ClassDeclaration _declaration) {
        this.declaration = _declaration;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return this.declaration.getName();
    }

    /**
     * Check if two types are equals.
     * This must be an equivalence relation.
     *
     * @param _other The other type (with respect to self).
     * @return True if the type is equals with the type _other, False otherwise.
     */
    @Override
    public boolean equalsTo(Type _other) {
        return _other instanceof ClassType
                && ((ClassType) _other).getDeclaration().equals(this.declaration);
    }

    /**
     * Check if two types are compatibles. A subtype is compatibleWith a supertype,
     * but a supertype is not compatible with a subtype.
     * This must be a partial order relation.
     * Check that the self type is compatible with the _other type, i.e. all values of self can be
     * used as a value of _other.
     *
     * @param _other The other type
     * @return True if the types are compatibles, False otherwise.
     */
    //TODO Heritage
    @Override
    public boolean compatibleWith(Type _other) {
        return this.equalsTo(_other);
    }

    /**
     * Builds a new type that results from the merging of self and _other according to the compatibleWith relation.
     * Compute the least common type (least upper bound) of two types according to the compatibleWith relation.
     * TA.merge(TB).compatibleWith(TA) and TA.merge(TB).compatibleWith(TB).
     *
     * @param _other The other type.
     * @return A type that is the least upper bound of self and _other according to compatibleWith.
     */
    @Override
    public Type merge(Type _other) {
        return null;
    }

    /**
     * Compute the size in TAM words needed to store a value of the _self type.
     *
     * @return Number of TAM words needed to store a value of the _self type.
     */
    @Override
    public int length() {
        return 1;
    }

    @Override
    public ClassElement getElement(String _name) {
        for (ClassElement _element : this.declaration.getElements()) {
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
    public ClassElement getElement(String _name, List<Type> _parameters) {
        List<ClassElement> _wellNamedElements = new LinkedList<>();
        Type _returnedType;

        for (ClassElement _element : this.declaration.getElements()) {
            String _elementName = _element.getName();
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                _elementName = ((FunctionDeclaration) _element.getDeclaration()).getSignature().getName();

                if (_elementName.equals(_name)) {
                    _wellNamedElements.add(_element);
                }
            }
        }

        if (_wellNamedElements.size() <= 0) {
            return null;
        }
        _returnedType = _wellNamedElements.get(0).getValueType();

        List<ClassElement> _compatibleElements = new LinkedList<>();
        for (ClassElement _element : _wellNamedElements) {
            List<ParameterDeclaration> _signatureParams = new LinkedList<>(((FunctionDeclaration)_element.getDeclaration()).getSignature().getParameters());

            if (_signatureParams.size() != _parameters.size()) {
                continue;
            }

            List<Type> _signatureParamTypes = new LinkedList<>();

            for (ParameterDeclaration _param : _signatureParams) {
                _signatureParamTypes.add(_param.getType());
            }

            Boolean _compatible;
            if (_signatureParamTypes.equals(_parameters)) {
                return (ClassElement) _element;
            } else {
                int _indParam = 0;
                _compatible = true;
                for (_indParam = 0; _indParam < _parameters.size(); _indParam++) {
                    _compatible &= _parameters.get(_indParam).compatibleWith(_signatureParamTypes.get(_indParam));
                }
                if (_compatible) {
                    _compatibleElements.add(_element);
                }
            }
        }

        if (_compatibleElements.size() > 0) {
            return (ClassElement) _compatibleElements.get(0);
        }
        return null;
    }

    @Override
    public List<FunctionDeclaration> getConstructors() {
        List<FunctionDeclaration> _constructors = new LinkedList<>();
        for (ClassElement _element : this.declaration.getElements()) {
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                if (_element.getDeclaration().getValueType() instanceof ConstructorType) {
                    _constructors.add((FunctionDeclaration) _element.getDeclaration());
                }
            }
        }
        return _constructors;
    }

    @Override
    public FunctionDeclaration getConstructor(List<Type> _parameters) {
        List<FunctionDeclaration> _constructors = new LinkedList<>();

        for (ClassElement _element : this.declaration.getElements()) {
            if (_element.getDeclaration() instanceof FunctionDeclaration) {
                if ((_element.getDeclaration()).getValueType() instanceof ConstructorType) {
                    _constructors.add((FunctionDeclaration) _element.getDeclaration());
                }
            }
        }

        int _indParam;
        Type _t;
        FunctionType _wConst = new FunctionTypeImpl(new ConstructorTypeImpl(this.declaration), _parameters);
        for (FunctionDeclaration _currentConst : _constructors) {
            if (_currentConst.getType().equalsTo(_wConst)) {
                return _currentConst;
            }
        }
        return null;
    }

    @Override
    public List<VariableDeclaration> getAttributes() {
        List<VariableDeclaration> _attributes = new LinkedList<>();

        for (ClassElement _element : this.declaration.getElements()) {
            if (_element.getDeclaration() instanceof VariableDeclaration) {
                _attributes.add((VariableDeclaration) _element.getDeclaration());
            }
        }
        return _attributes;
    }

    @Override
    public ClassDeclaration getDeclaration() {
        return this.declaration;
    }
}
