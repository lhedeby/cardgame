package playingcards;

public enum Suit {
    HEARTS(0),
    SPADES(1),
    DIAMONDS(2),
    CLUBS(3);

    private final int id;

    Suit(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
