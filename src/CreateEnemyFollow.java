import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemyFollow implements CreateObject {

    public List<EnemyFollow> enemiesFollow = new ArrayList<>();

    private FrameCounter frameCounter = new FrameCounter(300);
    private Random random = new Random();

    @Override
    public void create() {
        if (this.frameCounter.checkCounter()) {
            EnemyFollow enemyFollow = new EnemyFollow();
            enemyFollow.position.set(random.nextInt(1024), random.nextInt(600));
            enemyFollow.velocity.set(random.nextInt(2)+1, 0);
            this.enemiesFollow.add(enemyFollow);
            this.frameCounter.resetCount();
        } else {
            this.frameCounter.increment();
        }
    }
}
