package game.enemy;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import base.Vector2D;

public class EnemyShoot implements Attribute<Enemy> {

    private FrameCounter frameCounter = new FrameCounter(100);

    @Override
    public void run(Enemy gameObject) {
        if (this.frameCounter.checkCounter()) {
            for (double angle = 0.0; angle < 360.0; angle += 360/15) {
                BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
                bulletEnemy.position.set(gameObject.position);
                bulletEnemy.velocity.set(new Vector2D(2, 0).rotate(angle));
            }
            this.frameCounter.resetCount();
        }
    }
}

