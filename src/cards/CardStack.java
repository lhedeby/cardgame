package cards;

import War.GameState;
import War.State;
import mouse.Clickable;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CardStack implements Clickable {
    List<Card> cards;
    int x, y;
    int width, height;

    public CardStack(int x, int y) {
        cards = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.width = 73;
        this.height = 98;
    }

    public void shuffle() {
        List<Card> temp = new ArrayList<>();
        while (cards.size() > 0) {
            int rand = (int) (Math.random() * cards.size());
            temp.add(cards.get(rand));
            cards.remove(rand);
        }
        cards = temp;
    }

    @Override
    public void wasClicked(MouseEvent e, GameState gameState) {
        if(gameState.state == State.PLAYING &&
                gameState.waiting &&
                gameState.player.stack.size() > 0 &&
                e.getX() < x + width &&
                e.getX() > x &&
                e.getY() < y + height &&
                e.getY() > y) {
            if(gameState.warCounter > 0) {
                gameState.player.playStack.addCards(gameState.player.stack.deal());
                gameState.waiting = false;
            } else {
                gameState.player.playStack.addCards(gameState.player.stack.draw());
                gameState.waiting = false;

            }
        }
    }
    public int size() {
        return this.cards.size();
    }

    public void putOnTop(Card card) {
        cards.add(card);
    }

    public Card draw() {
        Card drawn = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        if (!drawn.isFacingUp()) drawn.flip();
        return drawn;
    }


    public Card deal() {
        Card drawn = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        if (drawn.isFacingUp()) drawn.flip();
        return drawn;
    }


    public void flipStack() {
        for(Card c : cards) {
            c.flip();
        }
    }

    public Card getTopCard() {
        if (!this.isEmtpy()) {
            return cards.get(cards.size() - 1);
        } else {
            return null;
        }
    }



    public boolean isEmtpy() {
        return cards.isEmpty();
    }

    public void addCards(Card... cards) {

        for(int i = 0; i < cards.length; i++) {
            cards[i].setxPos(this.x);
            cards[i].setyPos(this.y);
            this.cards.add(cards[i]);
        }

//        for(Card c : cards) {
//            c.setxPos(this.x);
//            c.setyPos(this.y);
//            this.cards.add(c);
//        }
    }

    public void addCards(List<Card> list) {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setxPos(this.x);
            list.get(i).setyPos(this.y);
            cards.add(list.get(i));
        }
//        for (Card c : list) {
//            c.setxPos(this.x);
//            c.setyPos(this.y);
//            cards.add(c);
//        }
    }



    public void dealTo(int numberOfCards, CardStack... stacks) {
        for(int i = 0; i < numberOfCards; i++) {
            stacks[i%stacks.length].addCards(deal());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void drawTo(int numberOfCards, CardStack... stacks) {
        for(int i = 0; i < numberOfCards; i++) {
            stacks[i%stacks.length].addCards(draw());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
