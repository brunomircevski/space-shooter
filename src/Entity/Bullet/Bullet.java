package Entity.Bullet;

import Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Bullet extends Entity implements IBullet {
    protected float speed;
    protected BufferedImage image;
    public boolean resistance = false;
    public int strength = 10;

    protected Bullet(int _y, BufferedImage _image, int _speed) {
        image = _image;
        speed = _speed;
        y = _y;
        x = 170;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, image.getWidth()*2, image.getHeight()*2, null);
    }

    @Override
    public void update() {
        x += speed;
    }

    public boolean canDamage() {
        return true;
    }
}
