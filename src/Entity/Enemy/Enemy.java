package Entity.Enemy;

import Entity.*;

import java.awt.*;

public abstract class Enemy extends Entity implements IEnemy {
    protected int health, maxHealth, speed, size;

    @Override
    public void update() {
        x -= speed;
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void damage(int amount) {
        health -= amount;
    }
}
