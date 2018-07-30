package mouse;

import War.GameState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Input extends MouseAdapter {
    private List<Clickable> items;
    private GameState gameState;
    public Input(GameState gameState) {
        super();
        items = new ArrayList<>();
        this.gameState = gameState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        for(Clickable c : items) {
            c.wasClicked(e, gameState);
        }

    }

    public void addListener(Clickable c) {
        items.add(c);
    }
}
