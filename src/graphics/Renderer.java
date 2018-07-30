package graphics;



import War.GameState;
import War.Rules;
import War.State;
import War.Statistics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer implements Runnable {



    List<Renderable> gameObjects;
    Graphics graphics;
    Graphics backBufferGraphics;
    BufferedImage backBuffer;
    List<Renderable> menuObjects;
    GameState gameState;
    Statistics stats;
    Rules rules;


    public Renderer(Window window, GameState gameState) {
        graphics = window.frame.getGraphics();
        backBuffer = new BufferedImage(window.WIDTH, window.HEIGHT, BufferedImage.TYPE_INT_RGB);
        backBufferGraphics = backBuffer.getGraphics();
        this.gameState = gameState;
        gameObjects = new ArrayList<>();
        menuObjects = new ArrayList<>();


    }

    @Override
    public void run() {
        while (true) {
            if(gameState.state == State.PLAYING)
                this.renderGame();
            else if(gameState.state != State.PLAYING)
                this.renderMenu();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGameItem(Renderable... items) {
        for(Renderable r : items) {
            this.gameObjects.add(r);
        }
    }

    public void addMenuItem(Renderable... items) {

        for(Renderable r : items) {
            this.menuObjects.add(r);
        }
    }





    public void drawBackground() {
        backBufferGraphics.setColor(new Color(34, 177,76));
        backBufferGraphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

    }

    public void renderTitle() {
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 50));
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.drawString("WAR", 150, 90);
    }



    public void renderMenu() {
        drawBackground();
        renderTitle();
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 30));
        for(Renderable r : menuObjects) {
            r.draw(backBufferGraphics);
        }
        if(gameState.state == State.RULES) {
            gameState.rules.draw(backBufferGraphics);
        } else if(gameState.state == State.STATISTICS) {
            gameState.stats.draw(backBufferGraphics);
        } else if(gameState.state == State.LOST) {
            youLost();
        } else if(gameState.state == State.WON) {
            this.youWon();
        }


        graphics.drawImage(backBuffer, 0, 0, null);
    }

    private void youWon() {
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 50));
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.drawString("YOU WON", 400, 200);
    }

    private void youLost() {
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 50));
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.drawString("YOU LOST", 400, 200);
    }





    public void renderGame() {
        drawBackground();
        for(Renderable r : gameObjects) {
            r.draw(backBufferGraphics);
        }
        graphics.drawImage(backBuffer, 0, 0, null);
    }


}
