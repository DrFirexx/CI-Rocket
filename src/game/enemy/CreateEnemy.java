package game.enemy;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateEnemy extends GameObject {

    private Random random = new Random();
    private FrameCounter frameCounter = new FrameCounter(300);

    @Override
    public void run() {
        if (this.frameCounter.checkCounter()) {
            Enemy enemy = new Enemy();
            enemy.position.set(random.nextInt(1024), random.nextInt(600));
            enemy.velocity.set(random.nextInt(2)+1, 0);
            GameObjectManager.instance.add(enemy);
            this.frameCounter.resetCount();
        }
    }
}
