package Game;

import Entity.*;
import Entity.Enemy.*;
import KeyHandler.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable{
    public final int screenWidth = 1200, screenHeight = 700, FPS = 60;
    private String state = "menu";
    public int score = 0, maxEnemies = 50;

    public Ship ship = new Ship(100, 100);

    KeyHandlerStrategy keyHandlerMenu = new MenuStrategy(this);
    KeyHandlerStrategy keyHandlerGame = new GameStrategy(this);
    KeyHandlerStrategy keyHandler = keyHandlerMenu;

    Thread gameThread;

    private Settings settings = Settings.getInstance();

    private EnemyFactory enemyFactory = new EnemyFactory();

    private LinkedList<Enemy> enemies = new LinkedList<Enemy>();

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

        if(state == "menu") {
            return;
        }

        if(enemies.size() < maxEnemies && Math.random() < 0.05) enemies.add(enemyFactory.createEnemy());

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if(e.x < 0) enemies.remove(e);
        };
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));

        if(state == "menu") {
            String menuString = "Press space to start";
            g2.drawString(menuString, screenWidth/2 - g2.getFontMetrics().stringWidth(menuString)/2, screenHeight/2);
            g2.drawString("High score: " + settings.getHighScore(), screenWidth - 220, screenHeight - 20);
            g2.dispose();
            return;
        }

        enemies.forEach((e) -> e.draw(g2));

        ship.draw(g2);

        g2.drawString("Score: " + score, screenWidth - 150, screenHeight - 20);

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
