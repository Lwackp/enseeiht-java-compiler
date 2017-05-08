package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.ClassElement;
import fr.n7.stl.block.ast.ClassElementUse;

/**
 * Created by thibault on 05/05/17.
 */
public class ClassElementAssignmentImpl extends ClassElementUseImpl implements Assignable {

    public ClassElementAssignmentImpl(ClassElement _declaration) {
        super(_declaration);
    }

}
