package cards;




import java.awt.image.BufferedImage;

public abstract class  Card{
    protected int x, y;
    protected int width;
    protected int height;
    protected boolean facingUp;
    protected BufferedImage front, back;
    CardDeck deck;

    public Card(int width, int height, CardDeck deck) {
        this.width = width;
        this.height = height;
        this.deck = deck;


    }
    protected BufferedImage getImage() {
        return facingUp ? front : back;
    }

    public void flip() {
        facingUp = !facingUp;
    }

    void setX(int x) {
        deck.putOnTop(this);
        this.x = x;
    }

    void setY(int y) {
        deck.putOnTop(this);
        this.y = y;
    }

    public boolean isFacingUp() {
        return facingUp;
    }

}
