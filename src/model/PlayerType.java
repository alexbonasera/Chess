package model;

public enum PlayerType {
    WHITE, BLACK;

    public static PlayerType otherPlayer(PlayerType type) {
        return (type == WHITE ? BLACK : WHITE);
    }
}
