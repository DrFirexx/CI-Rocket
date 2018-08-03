import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateStar implements CreateObject {

    public static List<Star> stars = new ArrayList<>();

    private FrameCounter frameCounter = new FrameCounter(30);
    private Random random = new Random();

    @Override
    public void create() {
        if (frameCounter.checkCounter()) {
            Star star = new Star();
            star.position.set(1024, random.nextInt(600));
            star.velocity.set(random.nextInt(10)+1, 0);
            this.stars.add(star);
            frameCounter.resetCount();
        } else {
            frameCounter.increment();
        }
    }
}
