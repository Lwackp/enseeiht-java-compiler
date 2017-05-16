package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 15/05/2017.
 */
public interface GenericType extends Type {

    List<VariableDeclaration> getAttributes();

    ClassType getClassType();

}
