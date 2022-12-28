package Bonus;

public class Bonus {
    public int speed, bulletDelay, life;

    public Bonus(int _speed, int _bulletDelay, int _life) {
        speed = _speed;
        bulletDelay = _bulletDelay;
        life = _life;
    }

    public void update() {
        life--;
    }
}
