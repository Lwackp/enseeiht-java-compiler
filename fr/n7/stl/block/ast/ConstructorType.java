package fr.n7.stl.block.ast;

/**
 * Created by thibault on 09/05/17.
 */
public interface ConstructorType extends Type {

    ClassDeclaration getClassDeclaration();

    void setClassDeclaration(ClassDeclaration _classDeclaration);

}
