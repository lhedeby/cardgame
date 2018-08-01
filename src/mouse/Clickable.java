package mouse;


import war.GameState;

import java.awt.event.MouseEvent;

public interface Clickable {
    void wasClicked(MouseEvent e, GameState gameState);
}
