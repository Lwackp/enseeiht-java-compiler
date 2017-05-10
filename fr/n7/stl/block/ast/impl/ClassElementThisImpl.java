package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 05/05/17.
 */
public class ClassElementThisImpl extends ClassElementImpl {

    public ClassElementThisImpl(Declaration _declaration, ElementModifier[] _modifiers) {
        super(_declaration, _modifiers);
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "this";
    }

    @Override
    public String getName() {
        return "this";
    }

    @Override
    public Type getType() {
        return this.declaration.getType();
    }
}
