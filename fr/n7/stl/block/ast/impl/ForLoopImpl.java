package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Project: enseeiht-java-compiler
 * Created by sacha on 17/05/2017.
 */
public class ForLoopImpl implements Instruction {

    private Expression init;
    private Expression condition;
    private Instruction next;
    private Block body;

    private RepetitionImpl loop;


    public ForLoopImpl(Expression _init, Expression _condition, Instruction _next, Block
            _body) {
        this.init = _init;
        this.condition = _condition;
        this.next = _next;
        this.body = _body;
        this.loop = new RepetitionImpl(_condition, _body, _next);

    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "for (" + this.init + "; " + this.condition + "; " + this.next + ") "+
                this.body;
    }

    @Override
    public boolean checkType() {
        return false;
    }

    @Override
    public int allocateMemory(Register _register, int _offset) {
        return 0;
    }

    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _fragment = this.init.getCode(_factory);
        _fragment.append(this.loop.getCode(_factory));
        return _fragment;

    }
}
