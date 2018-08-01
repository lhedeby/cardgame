package graphics;



import war.GameState;
import war.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer implements Runnable {



    private List<Renderable> gameObjects;
    private Graphics graphics;
    private Graphics backBufferGraphics;
    private BufferedImage backBuffer;
    private List<Renderable> menuObjects;
    private GameState gameState;



    public Renderer(Window window, GameState gameState) {
        this.gameState = gameState;

        backBuffer = new BufferedImage(window.WIDTH, window.HEIGHT, BufferedImage.TYPE_INT_RGB);
        gameObjects = new ArrayList<>();
        menuObjects = new ArrayList<>();

        graphics = window.frame.getGraphics();
        backBufferGraphics = backBuffer.getGraphics();


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


    private void drawBackground() {
        backBufferGraphics.setColor(new Color(34, 177,76));
        backBufferGraphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

    }

    private void renderTitle() {
        backBufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 50));
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.drawString("WAR", 150, 90);
    }



    private void renderMenu() {
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

    private void renderGame() {
        drawBackground();
        for(Renderable r : gameObjects) {
            r.draw(backBufferGraphics);
        }
        graphics.drawImage(backBuffer, 0, 0, null);
    }


}
