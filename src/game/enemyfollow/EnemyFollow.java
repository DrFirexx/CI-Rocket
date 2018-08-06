package game.enemyfollow;

import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class EnemyFollow extends GameObject {

    public Vector2D velocity;

    public EnemyFollow() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
    }

    public void update(Vector2D position) {
        this.velocity.set(position.subtract(this.position).normalize()).multiply(1.5f);
    }

}
