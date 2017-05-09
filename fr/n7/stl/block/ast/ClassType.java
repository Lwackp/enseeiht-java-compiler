package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Created by thibault on 05/05/17.
 */
public interface ClassType extends Type {

    ClassElement getElement(String _name);

    FunctionDeclaration getConstructor();

    List<VariableDeclaration> getAttributes();
}
