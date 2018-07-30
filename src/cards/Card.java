package cards;




import java.awt.image.BufferedImage;

public abstract class  Card{
    protected int xPos, yPos;
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
    public BufferedImage getImage() {
        return facingUp ? front : back;
    }

    public void flip() {
        facingUp = !facingUp;
    }

    public void setxPos(int xPos) {
        deck.putOnTop(this);
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        deck.putOnTop(this);
        this.yPos = yPos;
    }

    public boolean isFacingUp() {
        return facingUp;
    }

    public BufferedImage getFront() {
        return front;
    }

    public BufferedImage getBack() {
        return back;
    }
}
