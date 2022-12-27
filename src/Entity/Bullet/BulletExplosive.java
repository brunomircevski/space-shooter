package Entity.Bullet;

import java.awt.image.BufferedImage;

public class BulletExplosive extends Bullet implements IBullet {

    private int power;

    public BulletExplosive(int y, BufferedImage image, int speed, int _power) {
        super(y, image, speed);
        power = _power;
        strength = 100;
    }

    @Override
    public void update() {
        speed += (float) power/10;
        super.update();
    }
}
