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
		//this.signature = new SignatureDeclarationImpl(_name, _returnedType, _body);
		this.body = _body;
	}
	
	/**
	 * Creates a method declaration instruction node for the Abstract Syntax Tree.
	 * @param _name Signature of the declared method
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParameterDeclaration> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignatureDeclaration getSignature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBody() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
    @Override
    public String toString() {
        StringBuilder _local = new StringBuilder();

        _local.append(access).append(" ");
        if (b_static) {
            _local.append(NonAccessModifier.Static).append(" ");
        }
        if (b_final) {
            _local.append(NonAccessModifier.Final).append(" ");
        }

        _local.append(this.name);

        if (this.body != null) {
            _local.append("(");
            boolean first = true;
            for (ParameterDeclaration _parameter : this.parameters) {
                if (!first) {
                    _local.append(", ");
                }
                _local.append(_parameter);
                first = false;
            }
            _local.append(") ").append(this.body);

            return _local.toString();
        } else {
            return _local + ";";
        }
	}
	*/
}
