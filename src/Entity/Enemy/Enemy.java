package Entity.Enemy;

import Entity.*;

import java.awt.*;

public abstract class Enemy extends Entity implements IEnemy {
    public int health;
    protected int maxHealth;
    protected int speed;
    public int size;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }
}
