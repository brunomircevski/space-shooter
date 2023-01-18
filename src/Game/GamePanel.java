package Game;

import Bonus.BonusStack;
import Difficulty.*;
import Entity.*;
import Entity.Bullet.Bullet;
import Entity.Bullet.BulletBasic;
import Entity.Bullet.BulletExplosive;
import Entity.Bullet.BulletLaser;
import Entity.Enemy.*;
import KeyHandler.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable {
    public final int screenWidth = 1200, screenHeight = 700, FPS = 60, maxHealth = 200;
    private String state = "menu";
    public int score = 0;
    private int animationStep = 0;
    private int health = maxHealth;
    public int bulletDelay = 15;
    public boolean pressedD = false;
    public Ship ship = new Ship(100, 100);

    KeyHandlerStrategy keyHandlerMenu = new MenuStrategy(this);
    KeyHandlerStrategy keyHandlerGame = new GameStrategy(this);
    KeyHandlerStrategy keyHandler = keyHandlerMenu;

    Thread gameThread;

    private Settings settings = Settings.getInstance();

    private EnemyFactory enemyFactory = new EnemyFactory(this);

    private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();

    private BufferedImage basicBulletImage, laserBulletImage, explosiveBulletImage;

    public Difficulty difficulty = new DifficultyEasy();

    public BonusStack bonuses = new BonusStack();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        try {
            basicBulletImage = ImageIO.read(new File("res/images/basic_bullet.png"));
            laserBulletImage = ImageIO.read(new File("res/images/laser_bullet.png"));
            explosiveBulletImage = ImageIO.read(new File("res/images/explosion_bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        keyHandler.update();
        difficulty.update();

        if(health <= 0) changeState("menu");

        if(state == "menu") {
            return;
        }

        if(enemies.size() < difficulty.getMaxEnemies() && difficulty.getChance()) enemies.add(enemyFactory.createEnemy());

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if(e.getX() < 0) {
                health -= e.getHealth();
                enemies.remove(e);
            }
        };

        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update();
            if(b.x > screenWidth) bullets.remove(b);

            //Collision detection
            for(int j = 0; j < enemies.size(); j++) {
                Enemy e = enemies.get(j);
                if((Math.abs(e.getX() - b.x) < 5 + e.getSize()/2) && (Math.abs(e.getY() - b.y) < 5 + e.getSize()/2)) {
                    if(b.canDamage()) e.damage(b.strength);
                    if(!b.resistance) bullets.remove(b);

                    if(e.getHealth() <= 0) enemies.remove(e);
                }
            };
        };

        if(!bonuses.empty()) {
            bonuses.getBonus().update();
            bulletDelay = bonuses.getBonus().bulletDelay;
            if(bonuses.getBonus().life <= 0) bonuses.pop();
        } else {
            bulletDelay = 15;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));

        if(state == "menu") {
            String menuString = "Press space to start";
            g2.drawString(menuString, screenWidth/2 - g2.getFontMetrics().stringWidth(menuString)/2, screenHeight/2);
            g2.drawString("Score: " + score, screenWidth - 162, screenHeight - 50);
            g2.drawString("High score: " + settings.getHighScore(), screenWidth - 220, screenHeight - 20);
            g2.drawString(difficulty.name, screenWidth/2 - 20, screenHeight - 20);
            g2.dispose();
            return;
        }

        enemies.forEach((e) -> e.draw(g2, animationStep));
        bullets.forEach((b) -> b.draw(g2));

        ship.draw(g2, animationStep);

        g2.setColor(Color.white);
        g2.drawString("Score: " + score, screenWidth - 162, screenHeight - 50);
        g2.drawString("High score: " + settings.getHighScore(), screenWidth - 220, screenHeight - 20);

        if(score > 500 && !pressedD) g2.drawString("Press D to change ship type", 5, screenHeight - 20);

        if(!bonuses.empty()) g2.drawString("Bonus " + bonuses.getBonus().bulletDelay + " ("+ bonuses.getBonus().life +")", screenWidth/2 - 80, 20);

        g2.setColor(Color.red);
        g2.fillRect(0, screenHeight - 5, (int)(screenWidth - (maxHealth - health) * screenWidth/maxHealth), 5);

        g2.dispose();

        animationStep++;
        if(animationStep > 2) animationStep = 0;
    }

    public void changeState(String st) {
        this.removeKeyListener(keyHandler);

        if(st == "menu" && state != "menu") {
            state = "menu";
            keyHandler = keyHandlerMenu;
            settings.writeHighScore(score);
            bullets.clear();
            enemies.clear();
        }
        else if(st == "game" && state != "game") {
            score = 0;
            health = maxHealth;
            state = "game";
            keyHandler = keyHandlerGame;
        }

        this.addKeyListener(keyHandler);
    }

    public void shoot() {
        int speed = 5;
        if(!bonuses.empty()) speed = bonuses.getBonus().speed;

        switch(ship.shipType) {
            case "basic":
                bullets.add(new BulletBasic(ship.y + ship.size/2, basicBulletImage, speed));
                break;
            case "laser":
                bullets.add(new BulletLaser(ship.y + ship.size/2, laserBulletImage, speed));
                break;
            case "explosive":
                bullets.add(new BulletExplosive(ship.y + ship.size/2, explosiveBulletImage, speed, 3));
                break;
        }
    }

    public void changeDifficulty() {
        if(difficulty instanceof DifficultyEasy) difficulty = new DifficultyHard();
        else difficulty = new DifficultyEasy();
    }

    public void addPoints(int amount) {
        score += amount;
    }
}
