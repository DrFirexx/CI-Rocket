package game.enemy;

import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class Enemy extends GameObject {

    public Vector2D velocity;

    public EnemyShoot enemyShoot;

    public Enemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20 ,20);

        this.enemyShoot = new EnemyAttack();
    }

    @Override
    public void run() {
        super.run();
        this.enemyShoot.run(this);
    }
}
