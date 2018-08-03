import java.util.ArrayList;
import java.util.List;

public class EnemyAttack implements EnemyShoot {

    public List<BulletEnemy> bulletEnemies = new ArrayList<>();
    private FrameCounter frameCounter = new FrameCounter(30);

    @Override
    public void run(Enemy enemy) {
        if (this.frameCounter.checkCounter()) {
            for (double angle = 0.0; angle < 360.0; angle += 360/15) {
                BulletEnemy bulletEnemy = new BulletEnemy();
                bulletEnemy.position.set(enemy.position);
                bulletEnemy.velocity.set(new Vector2D(2, 0).rotate(angle));
                this.bulletEnemies.add(bulletEnemy);
                this.frameCounter.resetCount();
            }
        } else {
            this.frameCounter.increment();
        }
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }
}

