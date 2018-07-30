package War;

import playingcards.PlayingCard;
import playingcards.PlayingCardDeck;

public class GameState {
    Computer computer;
    public Player player;
    public int warCounter;
    public PlayingCardDeck deck;
    public volatile State state;

    private boolean won;
    public Statistics stats;
    public Rules rules;


    public volatile boolean waiting;

    public GameState(Computer computer, Player player, PlayingCardDeck deck) {
        this.computer = computer;
        this.player = player;
        this.deck = deck;
        this.warCounter = 0;
        this.state = State.MENU;
        this.stats = new Statistics();
        this.rules = new Rules();

    }

    void start() {
        this.state = State.PLAYING;
        player.stack.dealTo(player.stack.size(), deck.stack);
        computer.stack.dealTo(computer.stack.size(), deck.stack);
        computer.winStack.dealTo(computer.winStack.size(), deck.stack);
        player.winStack.dealTo(player.winStack.size(), deck.stack);
        player.playStack.dealTo(player.playStack.size(), deck.stack);
        computer.playStack.dealTo(computer.playStack.size(), deck.stack);
        deck.stack.shuffle();
        deck.stack.dealTo(deck.stack.size(), player.stack, computer.stack);

        this.waiting = true;
        this.state = State.PLAYING;

    }

    void end() {
        if(won) {
            stats.addWin();
            this.state = State.WON;
        } else {
            stats.addLoss();
            this.state = State.LOST;
        }
        stats.save();

    }

    void update() {

        PlayingCard computerCard = (PlayingCard) computer.playStack.getTopCard();
        PlayingCard playerCard = (PlayingCard) player.playStack.getTopCard();
        if(warCounter > 0) {
            warCounter--;
        } else if(computerCard.getRank().getValue() > playerCard.getRank().getValue()) {
            computer.playStack.drawTo(computer.playStack.size(), computer.winStack);
            player.playStack.drawTo(player.playStack.size(), computer.winStack);
        }
        else if(computerCard.getRank().getValue() < playerCard.getRank().getValue()) {
            computer.playStack.drawTo(computer.playStack.size(), player.winStack);
            player.playStack.drawTo(player.playStack.size(), player.winStack);
        } else {
            warCounter = 3;
            System.out.println("WARRR!!");
        }
    }

    void checkStack() {
        if(player.stack.size() == 0 && player.winStack.size() == 0) {
            System.out.println("Player loose");
            won = false;
            this.end();
        } else if( player.stack.size() == 0 && player.winStack.size() > 0) {
            player.winStack.dealTo(player.winStack.size() ,player.stack);
            player.stack.shuffle();
        }

        if(computer.stack.size() == 0 && computer.winStack.size() == 0) {
            System.out.println("computer loose");
            won = true;
            this.end();
        } else if( computer.stack.size() == 0 && computer.winStack.size() > 0) {
            computer.winStack.dealTo(computer.winStack.size() ,computer.stack);
            computer.stack.shuffle();
        }
    }

    void computerAction() {
        if(warCounter > 0) {
            computer.playDown();
        } else {
            computer.playUp();
        }
    }
}
