package Entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBig extends Enemy implements IEnemy {

    private BufferedImage image;
    private int animationStep = 0, aStepCounter = 0;

    public EnemyBig(BufferedImage img) {
        maxHealth = 50;
        health = maxHealth;
        speed = 1;
        size = img.getHeight()*2-30;
        x = 1250;
        y = (int) (Math.random() * 500) + 100;
        this.image = img;
    }

    @Override
    public void draw(Graphics2D g2, int aStep) {
        if(aStep == 2) aStepCounter++;
        if(aStepCounter >= 10) {
            aStepCounter = 0;
            animationStep++;
        }
        if(animationStep >= 3) animationStep = 0;

        g2.drawImage(image, x - image.getHeight(), y - image.getHeight(), x + image.getHeight(), y + image.getHeight(), image.getHeight() * animationStep, 0, image.getHeight() * (animationStep + 1), image.getHeight(), null);
    }
}
