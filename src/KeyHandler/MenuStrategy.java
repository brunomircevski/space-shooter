package KeyHandler;

import Game.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuStrategy implements KeyHandlerStrategy, KeyListener {
    private boolean spacePressed;
    private GamePanel gp;

    public MenuStrategy(GamePanel _gp) {
        gp = _gp;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE) gp.changeState("game");
        if(code == KeyEvent.VK_D) gp.changeDifficulty();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
