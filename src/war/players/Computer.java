package war.players;

import cards.CardStack;

public class Computer {
    public CardStack stack;
    public CardStack playStack;
    public CardStack winStack;

    public Computer() {
        this.stack = new CardStack(150,100);
        this.playStack = new CardStack(200, 300);
        this.winStack = new CardStack(350, 100);
    }

    public void playUp() {
        playStack.addCards(stack.drawFaceUp());
    }

    public void playDown() {
        playStack.addCards(stack.drawFaceDown());
    }
}
