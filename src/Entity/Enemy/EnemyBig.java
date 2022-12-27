package Entity.Enemy;

import java.awt.*;

public class EnemyBig extends Enemy implements IEnemy {

    public EnemyBig() {
        maxHealth = 50;
        health = maxHealth;
        speed = 1;
        size = 80;
        x = 1250;
        y = (int) (Math.random() * 500) + 100;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect(x-size/2, y-size/2, size, size);
    }
}
