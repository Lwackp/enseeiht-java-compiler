package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ClassElement;
import fr.n7.stl.block.ast.InheritanceDeclaration;
import fr.n7.stl.block.ast.InterfaceDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thibault on 02/05/17.
 */
public class InterfaceDeclarationImpl implements InterfaceDeclaration {

    private String name;
    private Object generics;
    private List<InheritanceDeclaration> inheritance;
    private List<ClassElement> elements;

    public InterfaceDeclarationImpl(String _name, Object _generics, List<InheritanceDeclaration> _inheritance,
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

        _local.append(this.name);

        if (this.generics != null) {
            _local.append(this.generics);
        }

        if (this.inheritance != null) {
            _local.append(" extends ");
            boolean first = true;
            for (InheritanceDeclaration _interface : this.inheritance) {
                if (!first) {
                    _local.append(", ");
                }
                _local.append(_interface);
                first = false;
            }
        }

        _local.append(" {");
        for (ClassElement _element : this.elements) {
            _local.append(_element);
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
        return null;
    }

    /**
     * Synthesized Semantics attribute to check that an instruction if well typed.
     *
     * @return Synthesized True if the instruction is well typed, False if not.
     */
    @Override
    public boolean checkType() {
        return false;
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
