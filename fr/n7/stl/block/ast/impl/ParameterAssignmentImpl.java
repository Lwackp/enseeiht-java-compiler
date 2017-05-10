package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.ParameterDeclaration;

/**
 * Created by thibault on 05/05/17.
 */
public class ParameterAssignmentImpl extends ParameterUseImpl implements Assignable {

    public ParameterAssignmentImpl(ParameterDeclaration _declaration) {
        super(_declaration);
    }
}
