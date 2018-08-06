package game.enemyfollow;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;
import game.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemyFollow extends GameObject {

    private Random random = new Random();
    private FrameCounter frameCounter = new FrameCounter(300);
    public List<EnemyFollow> enemiesFollow = new ArrayList<>();

    @Override
    public void run() {
        if (this.frameCounter.checkCounter()) {
            EnemyFollow enemyFollow = new EnemyFollow();
            enemyFollow.position.set(random.nextInt(1024), random.nextInt(600));
            enemyFollow.velocity.set(random.nextInt(2)+1, 0);
            this.enemiesFollow.add(enemyFollow);
            this.frameCounter.resetCount();
        }

        this.enemiesFollow.forEach(enemyFollow -> enemyFollow.run());
        this.enemiesFollow.forEach(enemyFollow -> enemyFollow.update(GameObjectManager.instance.getPlayer().position));
    }

    @Override
    public void render(Graphics graphics) {
        this.enemiesFollow.forEach(enemyFollow -> enemyFollow.render(graphics));
    }
}
