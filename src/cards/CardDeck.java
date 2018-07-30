package cards;



import java.util.List;

public abstract class CardDeck {
    protected int numberOfCards;
    public List<Card> cards;
    public CardStack stack;


    public CardDeck() {

    }

    public int getSize() {
        return cards.size();
    }



    public void putOnTop(Card c) {
        cards.remove(c);
        cards.add(c);
    }


    public abstract void sort();



}
