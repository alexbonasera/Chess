package controller;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import model.*;
import view.BoardView;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
    private final BoardView boardView;
    private final Board board;
    private BoardPosition selectedPosition;
    private ArrayList<BoardPosition> possibleMoves;
    private PlayerType currentTurn = PlayerType.WHITE;
    private final List<ChessPiece> whiteGraveyard;
    private final List<ChessPiece> blackGraveyard;

    public BoardState() {
        boardView = new BoardView();
//        board = new Board();
        board = new Board(StartType.STANDARD_START.getBoardState());
        whiteGraveyard = new ArrayList<>();
        blackGraveyard = new ArrayList<>();
        for (BoardPosition[] rows : board.getBoardPositions()) {
            for (BoardPosition boardPosition : rows) {
                if (boardPosition != null && boardPosition.getPiece() != null) {
                    boardView.getSpaces()[boardPosition.getX()][boardPosition.getY()].getChildren().add(boardPosition.getPiece().getView());
                }
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final BoardPosition boardPosition = board.getBoardPosition(x, y);
                final StackPane pane = boardView.getSpaces()[x][y];
                pane.setOnMouseClicked(event -> {
                    if (boardPosition.getPiece() != null && boardPosition.getPiece().getOwner() == currentTurn) { // If you click on your own piece
                        if (selectedPosition != null) { // If a piece has already been selected this turn
                            // Remove visual aid for previous selection
                            for (BoardPosition bp : possibleMoves) {
                                boardView.getSpaces()[bp.getX()][bp.getY()].getChildren().remove(bp.getRect());
                            }
                        }
                        selectedPosition = boardPosition;
                        possibleMoves = MovementPattern.getPossibleMoves(selectedPosition, board);
                        // Add visual aid for new selection
                        for (BoardPosition bp : possibleMoves) {
                            boardView.getSpaces()[bp.getX()][bp.getY()].getChildren().add(bp.getRect());
                        }
                    } else if (selectedPosition != null) { // If a piece has already been selected this turn
                        if (possibleMoves.contains(boardPosition)) { // If the clicked position is a possible move for the piece
                            movePiece(selectedPosition.getPiece(), selectedPosition.getX(), selectedPosition.getY(), boardPosition.getX(), boardPosition.getY());
                            selectedPosition = null;
                            currentTurn = PlayerType.otherPlayer(currentTurn);
                            // Remove visual aid
                            for (BoardPosition bp : possibleMoves) {
                                boardView.getSpaces()[bp.getX()][bp.getY()].getChildren().remove(bp.getRect());
                            }
                            possibleMoves = null;
                        }
                    }
                });
            }
        }
    }

    public void setPiece(ChessPiece chessPiece, int x, int y) {
        board.setChessPiece(x, y, chessPiece);
        boardView.getSpaces()[x][y].getChildren().add(chessPiece.getView());
    }

    public void movePiece(ChessPiece chessPiece, int oldX, int oldY, int newX, int newY) {
        chessPiece.setHasMoved();
        if (board.getChessPiece(newX, newY) != null) {
            if (board.getChessPiece(newX, newY).getOwner() == PlayerType.WHITE) {
                whiteGraveyard.add(board.getChessPiece(newX, newY));
            } else {
                blackGraveyard.add(board.getChessPiece(newX, newY));
            }
            boardView.getSpaces()[newX][newY].getChildren().remove(board.getChessPiece(newX, newY).getView());
        }
        board.setChessPiece(oldX, oldY, null);
        board.setChessPiece(newX, newY, chessPiece);

        boardView.getSpaces()[oldX][oldY].getChildren().remove(chessPiece.getView());
        boardView.getSpaces()[newX][newY].getChildren().add(chessPiece.getView());
    }

    public Node getView() {
        return boardView.getNode();
    }
}
