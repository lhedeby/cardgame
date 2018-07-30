package playingcards;

import War.GameState;
import cards.Card;
import cards.CardDeck;
import graphics.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayingCard extends Card implements Renderable {
    Rank rank;
    Suit suit;
    double renderX, renderY;
    double delay = 0;


    public PlayingCard(int width, int height, Rank rank, Suit suit, BufferedImage front, BufferedImage back, CardDeck deck) {
        super(width, height, deck);
        this.rank = rank;
        this.suit = suit;
        this.front = front;
        this.back = back;



    }

    @Override
    public void draw(Graphics g) {
        if(delay > 0) {
            delay--;
        } else if(renderX - xPos != 0) {
            renderX -= (renderX - xPos) * 0.2;
            renderY -= (renderY - yPos) * 0.2;
        }
        g.drawImage(this.getImage(),
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
