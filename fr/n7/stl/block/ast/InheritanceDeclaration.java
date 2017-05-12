package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface InheritanceDeclaration<T extends Declaration> extends Declaration {

    Declaration getDeclaration();
}
