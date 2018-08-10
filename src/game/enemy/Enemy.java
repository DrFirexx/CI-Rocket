package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import renderer.ImageRenderer;

public class Enemy extends GameObject {

    public Vector2D velocity;

    public BoxCollider boxCollider;

    public Enemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20 ,20);
        this.attributes.add(new EnemyShoot());
        this.boxCollider = new BoxCollider(20, 20);
    }

    @Override
    public void run() {
        super.run();

        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);

        if (GameObjectManager.instance.checkCollision4(this)) {
            GameObjectManager.instance.findPlayer().isAlive = false;
        }
    }
}
