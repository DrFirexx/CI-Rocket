package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;

    public BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public Enemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20 ,20);
        this.attributes.add(new EnemyShoot());
        this.boxCollider = new BoxCollider(20, 20);
        this.runHitObject = new RunHitObject(
                Player.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.runHitObject.run(this);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
