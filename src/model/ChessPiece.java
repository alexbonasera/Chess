package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ChessPiece {

    private final PlayerType owner;
    private final PieceType type;
    private final Node view;
    private boolean hasMoved = false;

    public ChessPiece(PlayerType owner, PieceType type) {
        this.owner = owner;
        this.type = type;
        this.view = createView();
    }

    public Node createView() {
        Image image = new Image(String.format("res%s%s_%s.png", File.separator, type.toString().toLowerCase(), owner.toString().toLowerCase()));
        ImageView view =  new ImageView(image);
        view.setFitWidth(50);
        view.setFitHeight(50);
        return view;
    }

    public PlayerType getOwner() {
        return owner;
    }

    public PieceType getType() {
        return type;
    }

    public void setHasMoved() {
        hasMoved = true;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public Node getView() {
        return view;
    }
}
