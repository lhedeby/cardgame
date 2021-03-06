package cards;

import war.GameState;
import war.State;
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
                gameState.player.playStack.addCards(gameState.player.stack.drawFaceDown());
                gameState.waiting = false;
            } else {
                gameState.player.playStack.addCards(gameState.player.stack.drawFaceUp());
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
    public void putOnBottom(Card card) { cards.add(0, card); }

    public Card drawFaceUp() {
        Card drawn = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        if (!drawn.isFacingUp()) drawn.flip();
        return drawn;
    }

    public Card drawFaceDown() {
        Card drawn = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        if (drawn.isFacingUp()) drawn.flip();
        return drawn;
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
            cards[i].setX(this.x);
            cards[i].setY(this.y);
            this.cards.add(cards[i]);
        }
    }

    public void addCards(List<Card> list) {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setX(this.x);
            list.get(i).setY(this.y);
            cards.add(list.get(i));
        }
    }

    public void dealFaceUp(int numberOfCards, CardStack... stacks) {
        for(int i = 0; i < numberOfCards; i++) {
            stacks[i%stacks.length].addCards(drawFaceDown());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void dealFaceDown(int numberOfCards, CardStack... stacks) {
        for(int i = 0; i < numberOfCards; i++) {
            stacks[i%stacks.length].addCards(drawFaceUp());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
