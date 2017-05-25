package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Created by thibault on 05/05/17.
 */
public interface ClassType extends Type {

    ClassElement getElement(String _name);

    ClassElement getElement(String _name, List<Type> _parameters);

    List<FunctionDeclaration> getConstructors();

    FunctionDeclaration getConstructor(List<Type> _parameters);

    List<VariableDeclaration> getAttributes();

    ClassDeclaration getDeclaration();
}
