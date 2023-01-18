package Entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemySmall extends Enemy implements IEnemy {

    private BufferedImage image;
    private int animationStep = 0, imgH = 22;

    public EnemySmall(BufferedImage img) {
        maxHealth = 10;
        health = maxHealth;
        speed = 2;
        size = 44;
        x = 1250;
        y = (int) (Math.random() * 600) + 50;
        this.image = img;
    }

    @Override
    public void draw(Graphics2D g2, int aStep) {
        if(aStep == 2) animationStep++;
        if(animationStep >= 3) animationStep = 0;
        g2.drawImage(image, x - imgH, y - imgH, x + imgH, y + imgH, imgH * animationStep, 0, imgH * (animationStep + 1), imgH, null);
    }
}
