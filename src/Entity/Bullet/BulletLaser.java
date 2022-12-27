package Entity.Bullet;

import Entity.Enemy.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletLaser extends Bullet implements IBullet {

    public int delay;

    public BulletLaser(int y, BufferedImage image, int speed) {
        super(y, image, speed);
        resistance = true;
        delay = 0;
    }

    @Override
    public void update() {
        x += speed;
        if(delay > 0) delay--;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(delay > 0) g2.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
        else super.draw(g2);
    }

    @Override
    public boolean canDamage() {
        if(delay == 0) {
            delay = 10;
            return true;
        }
        return false;
    }
}
