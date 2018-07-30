package mouse;


import War.GameState;

import java.awt.event.MouseEvent;

public interface Clickable {
    void wasClicked(MouseEvent e, GameState gameState);
}
