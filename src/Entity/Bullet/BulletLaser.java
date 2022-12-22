package Entity.Bullet;

import java.awt.image.BufferedImage;

public class BulletLaser extends Bullet implements IBullet {

    private int resistance;

    public BulletLaser(int y, BufferedImage image, int speed, int _resistance) {
        super(y, image, speed);
        resistance = _resistance;
    }

    @Override
    public void update() {
        x += speed;
    }
}
