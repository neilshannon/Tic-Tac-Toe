package com.ntsdev.game;

public class AI {

    private final CellState computerState = CellState.X;
    private final CellState playerState = CellState.O;

    /**
     * The computer makes a move
     * @param board the current game board
     * @return the board with the computer's move applied
     */
    public Board makeMove(Board board){
        //see if computer can win with this turn, or if player can win, move to that space
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(isWinningMove(i,j,board,computerState)){
                    board.makeMove(i,j,computerState);
                    return board;
                }
                else if(isWinningMove(i,j,board,playerState)){
                    board.makeMove(i,j,computerState);
                    return board;
                }
            }
        }
        //nobody's going to win on this move, so try to get a corner or the center
        if(couldPlayCenter(board)) return board;
        if(couldPlayCorner(board)) return board;

        //just pick somewhere
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if(board.cellAvailable(i,j)){
                    board.makeMove(i,j,computerState);
                    return board;
                }
            }
        }

        return board; //shouldn't happen
    }

    /**
     * Checks if the prospective move is a winner
     * @param x x coordinate of the move
     * @param y y coordinate of the move
     * @param board the game board
     * @param stateToCheck state to place on the board at x,y
     * @return true if this move will cause the player's state to win
     */
    public boolean isWinningMove(int x, int y, Board board, CellState stateToCheck){
        boolean win = false;
        Board copy = board.copy();
        if(copy.cellAvailable(x, y)) {
            copy.setState(x, y, stateToCheck);
            win = copy.checkWin(stateToCheck);
        }
        return win;
    }


    private boolean couldPlayCorner(Board board){
        boolean couldPlay = false;
        if(board.cellAvailable(0,0)){
            board.makeMove(0,0,computerState);
            couldPlay = true;
        }
        else if(board.cellAvailable(0,2)){
            board.makeMove(0,2,computerState);
            couldPlay = true;
        }
        else if(board.cellAvailable(2,2)){
            board.makeMove(2,2,computerState);
            couldPlay = true;
        }
        else if(board.cellAvailable(2,0)){
            board.makeMove(2,0,computerState);
            couldPlay = true;
        }
        return couldPlay;
    }

    private boolean couldPlayCenter(Board board){
        boolean couldPlay = false;
        if(board.cellAvailable(1,1)){
            board.makeMove(1,1,computerState);
            couldPlay = true;
        }
        return couldPlay;
    }

}
