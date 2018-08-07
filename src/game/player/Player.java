package game.player;

import base.GameObject;
import base.KeyListener;
import base.Vector2D;
import renderer.PolygonRenderer;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    public Vector2D velocity;

    public double angle = 0.0;

    public PlayerShoot playerShoot;

    private Random random = new Random();

    public Player() {
        this.velocity = new Vector2D();

        this.renderer = new PolygonRenderer(
                Color.RED,
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20,8)
        );

        this.playerShoot = new PlayerAttack();
    }

    @Override
    public void run() {
        super.run();

        this.moveAround();
        this.position.addUp(this.velocity);
        this.backToScreen();

        ((PolygonRenderer) this.renderer).angle = this.angle;

        this.playerShoot.run(this);
    }

    private void backToScreen() {
        if (this.position.x > 1024) this.position.set(0, this.random.nextInt(600));
        if (this.position.x < 0) this.position.set(1024, this.random.nextInt(600));
        if (this.position.y > 600) this.position.set(this.random.nextInt(1024), 0);
        if (this.position.y < 0) this.position.set(this.random.nextInt(1024), 600);
    }

    public void moveAround() {
        if (KeyListener.instance.getCommand() == "turn right clockwise") this.angle += 5;
        if (KeyListener.instance.getCommand() == "turn left anticlockwise") this.angle -= 5;
        this.velocity.set(new Vector2D(3.5f, 0).rotate(this.angle));

        if (KeyListener.instance.getCommand() == "speed up") this.velocity.multiply(4);
        if (KeyListener.instance.getCommand() == "speed down") this.velocity.multiply(0.25f);

        KeyListener.instance.resetCommand();
    }
}

