package war;

import graphics.Renderer;
import graphics.Window;
import mouse.Input;
import playingcards.PlayingCardDeck;
import war.players.Computer;
import war.players.Player;

public class Game {
    Window window;
    PlayingCardDeck deck;
    Player player;
    Computer computer;
    GameState state;
    Renderer renderer;
    Input input;
    Button startButton, statsButton, rulesButton;
    private void init() {
        window = new Window();
        deck = new PlayingCardDeck();
        player = new Player();
        computer = new Computer();
        state = new GameState(computer, player, deck);
        renderer = new Renderer(window, state);
        input = new Input(state);
        startButton = new Button(100, 150, 200, 60, "New Game", State.START);
        statsButton = new Button(100, 250, 200, 60, "Statistics", State.STATISTICS);
        rulesButton = new Button(100, 350, 200, 60, "Rules", State.RULES);

        window.getFrame().addMouseListener(input);
        input.addListener(player.stack);
        input.addListener(startButton);
        input.addListener(statsButton);
        input.addListener(rulesButton);

        renderer.addGameItem(deck);
        renderer.addMenuItem(startButton, statsButton, rulesButton);
        new Thread(renderer).start();
    }

    public Game() {
        init();
        loop();
    }

    private void loop() {
        while (true) {
            if (state.state == State.START) {
                state.start();
            }
            if (state.state == State.PLAYING) {
                state.computerAction();
                state.waiting = true;
                while (state.waiting) ;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state.update();
                state.checkStack();
            }
        }
    }
}
