package War;

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

    void playUp() {
        playStack.addCards(stack.draw());
    }

    void playDown() {
        playStack.addCards(stack.deal());
    }
}
