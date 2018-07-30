package War;

import cards.CardStack;

public class Player {
    public CardStack stack;
    public CardStack playStack;
    public CardStack winStack;

    public Player() {
        stack = new CardStack(350,500);
        playStack = new CardStack(300, 300);
        winStack = new CardStack(150,500);
    }

}
