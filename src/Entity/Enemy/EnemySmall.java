package Entity.Enemy;

import java.awt.*;

public class EnemySmall extends Enemy implements IEnemy {

    public EnemySmall() {
        maxHealth = 10;
        health = maxHealth;
        speed = 2;
        size = 30;
        x = 1250;
        y = (int) (Math.random() * 600) + 50;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.yellow);
        g2.fillRect(x-size/2, y-size/2, size, size);
    }
}
