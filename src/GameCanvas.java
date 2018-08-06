import base.GameObject;
import base.GameObjectManager;
import game.background.Background;
import game.enemy.CreateEnemy;
import game.enemyfollow.CreateEnemyFollow;
import game.player.Player;
import game.star.CreateStar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameCanvas extends JPanel {

    public static Player player;

    public CreateEnemyFollow createEnemyFollow;

    // Tao 1 backbuffer
    private BufferedImage backBuffered;
    public Random random = new Random();

    // Khoi tao co ve cho backbuffer
    private Graphics graphics;

    public GameCanvas() {
        // Cho de ve tat ca moi thu len
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupCharacter();
        this.setupPlayer();
        // draw
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        // Khoi tao backbuffer
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        // Lay co ve tu backbuffer => Dung graphics la ve len backbuffer
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        GameObjectManager.instance.add(new CreateStar());
        GameObjectManager.instance.add(new CreateEnemy());
        this.createEnemyFollow = new CreateEnemyFollow();
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
        GameObjectManager.instance.add(this.player);
    }

    // Noi de ve len trong gameCanvas
    @Override
    protected void paintComponent(Graphics g) {
        // Ve backuffer len day roi chi can lat len
        // g la but ve dung de lat
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    // Ve het len backbuffer
    public void renderAll() {
        GameObjectManager.instance.renderAll(graphics);

        this.createEnemyFollow.render(graphics);

        this.repaint();
    }

    public void runAll() {
        GameObjectManager.instance.runAll();

        this.createEnemyFollow.run();
    }
}
