package Entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyMedium extends Enemy  implements IEnemy{

    private BufferedImage img;

    public EnemyMedium(BufferedImage img) {
        maxHealth = 30;
        health = maxHealth;
        speed = 2;
        size = img.getWidth()*2 - 14;
        x = 1250;
        y = (int) (Math.random() * 600) + 50;
        this.img = img;
    }

    @Override
    public void draw(Graphics2D g2, int animationStep) {
        g2.drawImage(img, x - img.getWidth(), y - img.getHeight(), img.getWidth()*2, img.getHeight()*2,null);
    }
}
