package war;

import playingcards.PlayingCard;
import playingcards.PlayingCardDeck;
import war.data.Rules;
import war.data.Statistics;
import war.players.Computer;
import war.players.Player;

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
        state = State.PLAYING;
        player.stack.dealFaceUp(player.stack.size(), deck.stack);
        computer.stack.dealFaceUp(computer.stack.size(), deck.stack);
        computer.winStack.dealFaceUp(computer.winStack.size(), deck.stack);
        player.winStack.dealFaceUp(player.winStack.size(), deck.stack);
        player.playStack.dealFaceUp(player.playStack.size(), deck.stack);
        computer.playStack.dealFaceUp(computer.playStack.size(), deck.stack);
        deck.stack.shuffle();
        deck.stack.dealFaceUp(deck.stack.size(), player.stack, computer.stack);
        waiting = true;
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
            computer.playStack.dealFaceDown(computer.playStack.size(), computer.winStack);
            player.playStack.dealFaceDown(player.playStack.size(), computer.winStack);
        }
        else if(computerCard.getRank().getValue() < playerCard.getRank().getValue()) {
            computer.playStack.dealFaceDown(computer.playStack.size(), player.winStack);
            player.playStack.dealFaceDown(player.playStack.size(), player.winStack);
        } else {
            warCounter = 3;
        }
    }

    void checkStack() {
        if(player.stack.size() == 0 && player.winStack.size() == 0) {
            won = false;
            end();
        } else if( player.stack.size() == 0 && player.winStack.size() > 0) {
            player.winStack.dealFaceUp(player.winStack.size() ,player.stack);
            player.stack.shuffle();
        }

        if(computer.stack.size() == 0 && computer.winStack.size() == 0) {
            won = true;
            end();
        } else if( computer.stack.size() == 0 && computer.winStack.size() > 0) {
            computer.winStack.dealFaceUp(computer.winStack.size() ,computer.stack);
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
