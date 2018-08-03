import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemy implements CreateObject {

    public static List<Enemy> enemies = new ArrayList<>();

    private FrameCounter frameCounter = new FrameCounter(300);
    private Random random = new Random();

    @Override
    public void create() {
        if (this.frameCounter.checkCounter()) {
            Enemy enemy = new Enemy();
            enemy.position.set(random.nextInt(1024), random.nextInt(600));
            enemy.velocity.set(random.nextInt(2)+1, 0);
            this.enemies.add(enemy);
            this.frameCounter.resetCount();
        } else {
            this.frameCounter.increment();
        }
    }
}
