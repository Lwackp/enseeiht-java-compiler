package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Created by thibault on 09/05/17.
 */
public interface ConstructorType extends Type {

    ClassDeclaration getClassDeclaration();

    void setClassDeclaration(ClassDeclaration _classDeclaration);

    List<Type> getParameters();
}
