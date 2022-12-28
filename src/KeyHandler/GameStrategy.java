package KeyHandler;

import Game.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameStrategy implements KeyHandlerStrategy, KeyListener {
    private boolean upPressed, downPressed, spacePressed;
    private int a, spaceDelay = 0;
    private GamePanel gp;

    public GameStrategy(GamePanel _gp) {
        gp = _gp;
    }

    @Override
    public void update() {
        if(upPressed && a > -16) a-=2;
        if(downPressed && a < 16) a+=2;

        gp.ship.y += a;

        if(a > 0) a--;
        if(a < 0) a++;

        if(gp.ship.y > gp.screenHeight - gp.ship.size) gp.ship.y = gp.screenHeight - gp.ship.size;
        if(gp.ship.y < 0) gp.ship.y = 0;

        if(spaceDelay > 0) spaceDelay--;
        else if(spacePressed)  {
            gp.shoot();
            spaceDelay = gp.bulletDelay;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) upPressed = true;
        if(code == KeyEvent.VK_DOWN) downPressed = true;
        if(code == KeyEvent.VK_SPACE) spacePressed = true;
        if(code == KeyEvent.VK_ESCAPE) gp.changeState("menu");
        if(code == KeyEvent.VK_D) {
            if(gp.score > 500) {
                gp.ship.changeShip();
                gp.pressedD = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) upPressed = false;
        if(code == KeyEvent.VK_DOWN) downPressed = false;
        if(code == KeyEvent.VK_SPACE) spacePressed = false;
    }
}
