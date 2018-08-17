package game.star;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateStar extends GameObject {

    private Random random;
    private FrameCounter frameCounter;

    public CreateStar() {
        this.random = new Random();
        this.frameCounter = new FrameCounter(30);
    }

    @Override
    public void run() {
        super.run();
        if (frameCounter.checkCounter()) {
            Star star = GameObjectManager.instance.recycle(Star.class);
            star.position.set(1024, random.nextInt(600));
            star.velocity.set(random.nextInt(10)+1, 0);
            this.frameCounter.resetCount();
        }
    }
}
