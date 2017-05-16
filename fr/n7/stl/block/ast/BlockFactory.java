package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Combines the various factories for the Bloc language.
 * @author Marc Pantel
 *
 */
public interface BlockFactory extends ExpressionFactory, InstructionFactory, TypeFactory, SymbolTableFactory {
}
