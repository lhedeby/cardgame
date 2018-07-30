package playingcards;


import cards.Card;
import cards.CardDeck;
import cards.CardStack;
import graphics.Renderable;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;


public class PlayingCardDeck extends CardDeck implements Renderable {

    BufferedImage cardImage;
    BufferedImage cardback;

    int width, height;
    public PlayingCardDeck() {
        this.width = 73;
        this.height = 98;
        cards = new ArrayList<>();

        stack = new CardStack(50, 300);
        try {
            cardImage = ImageIO.read(new File(".\\src\\playingcards\\res\\cards.png"));
            cardback = ImageIO.read(new File(".\\src\\playingcards\\res\\cardback.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int subX = 0;
        int subY = 0;
        int[] order = {2,1,3,0};
        int cardWidth = 73;
        int cardHeight = 98;
        for(Suit s : Suit.values()) {

            for(Rank r : Rank.values()) {
                BufferedImage front = cardImage.getSubimage(subX*cardWidth,order[subY]*cardHeight, cardWidth, cardHeight);
                Card card = new PlayingCard(width, height, r, s, front, cardback, this);
                cards.add(card);


                subX++;
            }
            subX = 0;
            subY++;
        }
        stack.addCards(cards);
        numberOfCards = cards.size();


    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < this.cards.size(); i++) {
            ((PlayingCard) this.cards.get(i)).draw(g);
        }
//        for(Card c : this.cards) {
//            ((PlayingCard) c).draw(g);
//        }
    }



    @Override
    public void sort() {
        this.cards.sort((o1,o2) ->{
{
                PlayingCard c1 = (PlayingCard) o1;
                PlayingCard c2 = (PlayingCard) o2;
                if(c1.suit.getValue() < c2.suit.getValue())
                    return -1;
                else if(c1.suit == c2.suit && c1.rank.getValue() < c2.rank.getValue())
                    return -1;
                else
                    return 1;
            }
        });
    }
}
