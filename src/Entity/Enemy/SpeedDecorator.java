package Entity.Enemy;

public class SpeedDecorator extends EnemyDecorator {

    public SpeedDecorator(Enemy _enemy) {
        super(_enemy);
    }

    @Override
    public void update() {
        super.update();
        super.update();
    }
}
