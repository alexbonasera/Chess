package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardPosition {

    private ChessPiece piece;
    private final Rectangle rect = new Rectangle(50, 50, new Color(Color.LIGHTGREEN.getRed(), Color.LIGHTGREEN.getGreen(), Color.LIGHTGREEN.getBlue(), .5));
    private final int x;
    private final int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
