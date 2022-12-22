import Entity.*;
import KeyHandler.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int screenWidth = 1200;
    final int screenHeight = 700;
    final int FPS = 60;
    private String state = "menu";

    Ship ship = new Ship(100, 100);

    KeyHandlerStrategy keyHandlerMenu = new MenuStrategy(this);
    KeyHandlerStrategy keyHandlerGame = new GameStrategy(this);
    KeyHandlerStrategy keyHandler = keyHandlerMenu;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        keyHandler.Update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);

        if(state == "menu") {
            g2.drawString("Press space to start", screenWidth/2, screenHeight/2);
            g2.dispose();
            return;
        }

        g2.fillRect(ship.x, ship.y, ship.width, ship.height);

        g2.dispose();
    }

    public void changeState(String st) {
        this.removeKeyListener(keyHandler);

        if(st == "menu" && state != "menu") {
            state = "menu";
            keyHandler = keyHandlerMenu;
        }
        else if(st == "game" && state != "game") {
            state = "game";
            keyHandler = keyHandlerGame;
        }

        this.addKeyListener(keyHandler);
    }
}
