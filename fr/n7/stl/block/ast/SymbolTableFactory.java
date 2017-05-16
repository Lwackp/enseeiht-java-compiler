package fr.n7.stl.block.ast;

import fr.n7.stl.util.SymbolTable;

import java.util.List;

/**
 * Factory to create Symbol Table.
 * @author Thibault Meunier
 *
 */
public interface SymbolTableFactory {

	/**
	 * Add Inherited Class Elements to the SymbolTable
	 * @param _s SymbolTable to be modified
	 * @param _elements All class elements to be added
	 * @return The SymbolTable with all elements added
	 */
    SymbolTable addInheritedElements(SymbolTable _s, List<ClassElement> _elements);
}
