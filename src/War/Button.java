package War;

import graphics.Renderable;
import mouse.Clickable;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button implements Clickable, Renderable {
    private String text;
    private State state;
    private int x, y;
    private int width, height;

    public Button(int x, int y, int width, int height, String text, State state) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.text = text;
        this.state = state;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(this.x, this.y, this.width, this.height);
        g.setColor(Color.BLACK);
        g.drawString(text, x + 8, y + height/2 + 4);
    }

    @Override
    public void wasClicked(MouseEvent e, GameState gameState) {
        if(gameState.state != State.PLAYING &&
                e.getX() > this.x &&
                e.getX() < this.x + this.width &&
                e.getY() > this.y &&
                e.getY() < this.y + this.height) {
            gameState.state = this.state;
        }
    }
}
