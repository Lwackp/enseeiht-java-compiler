package fr.n7.stl.block.ast.impl;

import java.util.List;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FunctionDeclaration;
import fr.n7.stl.block.ast.ParameterDeclaration;
import fr.n7.stl.block.ast.SignatureDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import static fr.n7.stl.block.ast.AtomicType.ErrorType;

/**
 * Implementation of the Abstract Syntax Tree node for a method declaration instruction.
 * @author Matthieu Perrier
 *
 */
public class FunctionDeclarationImpl implements FunctionDeclaration {

    private SignatureDeclaration signature;
    private Block body;

    //private Register register;

    /**
     * Creates a method declaration instruction node for the Abstract Syntax Tree.
     * @param _name Name of the declared method.
     * @param _returnedType Type returned by the declared method.
     * @param _parameters List of parameters declarations of the declared method.
     * @param _body Body of the declared method
     */
    public FunctionDeclarationImpl(String _name, Type _returnedType, List<ParameterDeclaration> _parameters, Block _body) {
        this.signature = new SignatureDeclarationImpl(_name, _returnedType, _parameters);
        this.body = _body;
    }

    /**
     * Creates a method declaration instruction node for the Abstract Syntax Tree.
     * @param _signature Signature of the declared method
     * @param _body Body of the declared method
     */
    public FunctionDeclarationImpl(SignatureDeclaration _signature, Block _body) {
        this.signature = _signature;
        this.body = _body;
    }

    @Override
    public String getName() {
        return this.signature.getName();
    }

    /**
     * Synthesized semantics attribute for the type of the declared variable.
     *
     * @return Type of the declared variable.
     */
    @Override
    public Type getType() {
        return this.signature.getType();
    }

    @Override
    public boolean checkType() {
        return (this.signature.checkType());
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type getReturnedType() {
        return this.signature.getReturnedType();
    }

    @Override
    public List<ParameterDeclaration> getParameters() {
        return this.signature.getParameters();
    }

	@Override
	public SignatureDeclaration getSignature() {
		return this.signature;
	}

    @Override
    public Block getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();


        /*
        _local.append(this.signature).append(" ");

        _local.append(this.body);

        return _local.toString();
        */

        _local.append(this.getName());

        _local.append("(");
        boolean first = true;
        for (ParameterDeclaration _parameter : this.getParameters()) {
        	if (!first) {
        		_local.append(", ");
        	}
        	_local.append(_parameter);
        	first = false;
        }
        _local.append(")");
        
        _local.append(this.body);
        
        _local.append("\n");
        return _local.toString();

	}
}
