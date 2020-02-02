package controller;

import model.PlayerType;
import model.Board;
import model.BoardPosition;

import java.util.ArrayList;

public class MovementPattern {

    public static ArrayList<BoardPosition> getPossibleMoves(BoardPosition currentPosition, Board board) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        int[][] offsets;
        switch (currentPosition.getPiece().getType()) {
            case PAWN:
                if (currentPosition.getPiece().getOwner() == PlayerType.WHITE) {
                    if (currentPosition.getY() == 6) {
                        offsets = new int[][]{{-1, -1},{0, -1},{1, -1},{0, -2}};
                    } else {
                        offsets = new int[][]{{-1, -1},{0, -1},{1, -1}};
                    }
                } else {
                    if (currentPosition.getY() == 1) {
                        offsets = new int[][]{{-1, 1},{0, 1},{1, 1},{0, 2}};
                    } else {
                        offsets = new int[][]{{-1, 1},{0, 1},{1, 1}};
                    }
                }
                for (int i = 0; i < offsets.length; i++) {
                    int possibleX = currentPosition.getX() + offsets[i][0];
                    int possibleY = currentPosition.getY() + offsets[i][1];
                    if (possibleX < 0 || possibleX > 7 || possibleY < 0 || possibleY > 7) {
                        continue;
                    }
                    BoardPosition possibleMove = board.getBoardPosition(possibleX, possibleY);
                    if (i % 2 == 0) { // Both diagonal cases
                        if (possibleMove.getPiece() != null && possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                            possibleMoves.add(possibleMove);
                        }
                    } else {
                        if (possibleMove.getPiece() == null) {
                            possibleMoves.add(possibleMove);
                        }
                    }
                }
                break;
            case ROOK:
                possibleMoves.addAll(getAllPossibleOrthogonalMoves(currentPosition, board));
                break;
            case KNIGHT:
                offsets = new int[][]{{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
                for (int[] offset : offsets) {
                    int possibleX = currentPosition.getX() + offset[0];
                    int possibleY = currentPosition.getY() + offset[1];
                    if (possibleX < 0 || possibleX > 7 || possibleY < 0 || possibleY > 7) {
                        continue;
                    }
                    BoardPosition possibleMove = board.getBoardPosition(possibleX, possibleY);
                    if (possibleMove.getPiece() == null || possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                        possibleMoves.add(possibleMove);
                    }
                }
                break;
            case BISHOP:
                possibleMoves.addAll(getAllPossibleDiagonalMoves(currentPosition, board));
                break;
            case QUEEN:
                possibleMoves.addAll(getAllPossibleOrthogonalMoves(currentPosition, board));
                possibleMoves.addAll(getAllPossibleDiagonalMoves(currentPosition, board));
                break;
            case KING:
                offsets = new int[][]{{0, -1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};
                for (int i = 0; i < offsets.length; i++) {
                    int possibleX = currentPosition.getX() + offsets[i][0];
                    int possibleY = currentPosition.getY() + offsets[i][1];
                    if (possibleX < 0 || possibleX > 7 || possibleY < 0 || possibleY > 7) {
                        continue;
                    }
                    BoardPosition possibleMove = board.getBoardPosition(possibleX, possibleY);
                    if (possibleMove.getPiece() == null || possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                        possibleMoves.add(possibleMove);
                    }
                }
                break;
        }
        return possibleMoves;
    }

    private static ArrayList<BoardPosition> getAllPossibleOrthogonalMoves(BoardPosition currentPosition, Board board) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        possibleMoves.addAll(getPossibleHorizontalMoves(currentPosition, board, 1));
        possibleMoves.addAll(getPossibleHorizontalMoves(currentPosition, board, -1));
        possibleMoves.addAll(getPossibleVerticalMoves(currentPosition, board, 1));
        possibleMoves.addAll(getPossibleVerticalMoves(currentPosition, board, -1));
        return possibleMoves;
    }

    private static ArrayList<BoardPosition> getPossibleHorizontalMoves(BoardPosition currentPosition, Board board, int direction) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        for (int possibleX = currentPosition.getX() + direction; possibleX >= 0 && possibleX <= 7; possibleX += direction) {
            BoardPosition possibleMove = board.getBoardPosition(possibleX, currentPosition.getY());
            if (possibleMove.getPiece() == null) {
                possibleMoves.add(possibleMove);
            } else if (possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                possibleMoves.add(possibleMove);
                break;
            } else {
                break;
            }
        }
        return possibleMoves;
    }

    private static ArrayList<BoardPosition> getPossibleVerticalMoves(BoardPosition currentPosition, Board board, int direction) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        for (int possibleY = currentPosition.getY() + direction; possibleY >= 0 && possibleY <= 7; possibleY += direction) {
            BoardPosition possibleMove = board.getBoardPosition(currentPosition.getX(), possibleY);
            if (possibleMove.getPiece() == null) {
                possibleMoves.add(possibleMove);
            } else if (possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                possibleMoves.add(possibleMove);
                break;
            } else {
                break;
            }
        }
        return possibleMoves;
    }

    private static ArrayList<BoardPosition> getAllPossibleDiagonalMoves(BoardPosition currentPosition, Board board) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        possibleMoves.addAll(getPossibleDiagonalMoves(currentPosition, board, -1, -1));
        possibleMoves.addAll(getPossibleDiagonalMoves(currentPosition, board, 1, -1));
        possibleMoves.addAll(getPossibleDiagonalMoves(currentPosition, board, 1, 1));
        possibleMoves.addAll(getPossibleDiagonalMoves(currentPosition, board, -1, 1));
        return possibleMoves;
    }

    private static ArrayList<BoardPosition> getPossibleDiagonalMoves(BoardPosition currentPosition, Board board, int xOffsetDirection, int yOffsetDirection) {
        ArrayList<BoardPosition> possibleMoves = new ArrayList<>();
        for (int offset = 1; offset < 8; offset++) {
            int possibleX = currentPosition.getX() + (offset * xOffsetDirection);
            int possibleY = currentPosition.getY() + (offset * yOffsetDirection);
            if (possibleX < 0 || possibleX > 7 || possibleY < 0 || possibleY > 7) {
                break;
            }
            BoardPosition possibleMove = board.getBoardPosition(possibleX, possibleY);
            if (possibleMove.getPiece() == null) {
                possibleMoves.add(possibleMove);
            } else if (possibleMove.getPiece().getOwner() != currentPosition.getPiece().getOwner()) {
                possibleMoves.add(possibleMove);
                break;
            } else {
                break;
            }
        }
        return possibleMoves;
    }
}
