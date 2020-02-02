package model;

/**
 * Created by bonaa23 on 11/2/2017.
 */
public enum StartType {

    STANDARD_START(new ChessPiece[][]{
        {
            new ChessPiece(PlayerType.BLACK, PieceType.ROOK), new ChessPiece(PlayerType.BLACK, PieceType.KNIGHT),
            new ChessPiece(PlayerType.BLACK, PieceType.BISHOP), new ChessPiece(PlayerType.BLACK, PieceType.KING),
            new ChessPiece(PlayerType.BLACK, PieceType.QUEEN), new ChessPiece(PlayerType.BLACK, PieceType.BISHOP),
            new ChessPiece(PlayerType.BLACK, PieceType.KNIGHT), new ChessPiece(PlayerType.BLACK, PieceType.ROOK)
        }, {
            new ChessPiece(PlayerType.BLACK, PieceType.PAWN), new ChessPiece(PlayerType.BLACK, PieceType.PAWN),
            new ChessPiece(PlayerType.BLACK, PieceType.PAWN), new ChessPiece(PlayerType.BLACK, PieceType.PAWN),
            new ChessPiece(PlayerType.BLACK, PieceType.PAWN), new ChessPiece(PlayerType.BLACK, PieceType.PAWN),
            new ChessPiece(PlayerType.BLACK, PieceType.PAWN), new ChessPiece(PlayerType.BLACK, PieceType.PAWN)
        }, {}, {}, {}, {}, { // 4 empty rows
            new ChessPiece(PlayerType.WHITE, PieceType.PAWN), new ChessPiece(PlayerType.WHITE, PieceType.PAWN),
            new ChessPiece(PlayerType.WHITE, PieceType.PAWN), new ChessPiece(PlayerType.WHITE, PieceType.PAWN),
            new ChessPiece(PlayerType.WHITE, PieceType.PAWN), new ChessPiece(PlayerType.WHITE, PieceType.PAWN),
            new ChessPiece(PlayerType.WHITE, PieceType.PAWN), new ChessPiece(PlayerType.WHITE, PieceType.PAWN)
        }, {
            new ChessPiece(PlayerType.WHITE, PieceType.ROOK), new ChessPiece(PlayerType.WHITE, PieceType.KNIGHT),
            new ChessPiece(PlayerType.WHITE, PieceType.BISHOP), new ChessPiece(PlayerType.WHITE, PieceType.KING),
            new ChessPiece(PlayerType.WHITE, PieceType.QUEEN), new ChessPiece(PlayerType.WHITE, PieceType.BISHOP),
            new ChessPiece(PlayerType.WHITE, PieceType.KNIGHT), new ChessPiece(PlayerType.WHITE, PieceType.ROOK)
        }
    });

    private final ChessPiece[][] boardState;

    StartType(ChessPiece[][] boardState) {
        this.boardState = boardState;
    }

    public ChessPiece[][] getBoardState() {
        return boardState;
    }
}
