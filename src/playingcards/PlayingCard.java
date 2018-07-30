package playingcards;

import cards.Card;
import cards.CardDeck;
import graphics.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayingCard extends Card implements Renderable {
    Rank rank;
    Suit suit;
    private double renderX, renderY;

    public PlayingCard(int width, int height, Rank rank, Suit suit, BufferedImage front, BufferedImage back, CardDeck deck) {
        super(width, height, deck);
        this.rank = rank;
        this.suit = suit;
        this.front = front;
        this.back = back;
        this.x = deck.stack.getX();
        this.y = deck.stack.getY();
    }

    @Override
    public void draw(Graphics g) {

        if(renderX - x != 0) {
            renderX -= (renderX - x) * 0.2;
            renderY -= (renderY - y) * 0.2;
        }
        g.drawImage(getImage(),
                (int) renderX,
                (int) renderY,
                null);
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
}
