package War;

import graphics.Renderer;
import graphics.Window;
import mouse.Input;
import playingcards.PlayingCardDeck;

public class Game {

    public Game() {
        Window window = new Window();

        PlayingCardDeck deck = new PlayingCardDeck();
        Player player = new Player();
        Computer computer = new Computer();
        GameState state = new GameState(computer, player, deck);
        Renderer renderer = new Renderer(window, state);
        Input input = new Input(state);
        window.frame.addMouseListener(input);
        input.addListener(player.stack);

        renderer.addGameItem(deck);
        Button startButton = new Button(100,150, 200,60, "New Game", State.START);
        Button statsButton = new Button(100,250, 200,60, "Statistics", State.STATISTICS);
        Button rulesButton = new Button(100,350, 200,60, "Rules", State.RULES);
        renderer.addMenuItem(startButton, statsButton, rulesButton);

        input.addListener(startButton);
        input.addListener(statsButton);
        input.addListener(rulesButton);

        new Thread(renderer).start();

//        deck.stack.dealTo(52, player.stack, computer.stack);
//        player.stack.shuffle();
//        computer.stack.shuffle();





        while(true) {
            if(state.state == State.START) {
                state.start();
            }

            if(state.state == State.PLAYING) {
                state.computerAction();
                state.waiting = true;
                while(state.waiting);
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
