package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardView {

    private static final int SQUARE_SIZE = 50;

    private Group table;
    private GridPane board;
    private StackPane[][] boardCells;

    public BoardView() {
        board = new GridPane();
        boardCells = new StackPane[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rect = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                rect.setFill((i + j) % 2 == 0 ? Color.BROWN : Color.SANDYBROWN);
                StackPane cell = new StackPane(rect);

                boardCells[i][j] = cell;
                board.add(cell, i, j);
            }
        }
        table = new Group();
        table.getChildren().add(board);
    }

    public StackPane[][] getSpaces() {
        return boardCells;
    }

    public Node getNode() {
        return table;
    }
}
