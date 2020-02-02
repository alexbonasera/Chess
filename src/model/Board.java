package model;

public class Board {

    private final BoardPosition[][] boardPositions = new BoardPosition[8][8];

    public Board() {
        for (int x = 0; x < 8; x++) {
            boardPositions[x] = new BoardPosition[8];
            for (int y = 0; y < 8; y++) {
                boardPositions[x][y] = new BoardPosition(x, y);
            }
        }
    }

    public Board(ChessPiece[][] chessPieces) {
        this();
        for (int y = 0; y < chessPieces.length; y++) {   // loop through each row
            for (int x = 0; x < chessPieces[y].length; x++) {   // loop through each column
                setChessPiece(x, y, chessPieces[y][x]);
            }
        }
    }

    public BoardPosition[][] getBoardPositions() {
        return boardPositions;
    }

    public BoardPosition getBoardPosition(int x, int y) {
        return boardPositions[x][y];
    }

    public ChessPiece getChessPiece(int x, int y) {
        return boardPositions[x][y].getPiece();
    }

    public void setChessPiece(int x, int y, ChessPiece chessPiece) {
        boardPositions[x][y].setPiece(chessPiece);
    }
}
