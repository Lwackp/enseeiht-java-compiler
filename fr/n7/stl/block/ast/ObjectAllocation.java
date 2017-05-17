package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Created by thibault on 28/03/17.
 */
public interface ObjectAllocation extends Expression {
    public void setParameters(List<Type> _parameters);
}
