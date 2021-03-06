package com.ntsdev.game;

/**
 * A cell on the Tic-Tac-Toe game board
 */
public class Cell {
    private CellState state = CellState.BLANK;

    public CellState getState() {
        return this.state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if (this.state == CellState.BLANK) {
            return "_";
        } else {
            return this.state.name();
        }
    }
}
