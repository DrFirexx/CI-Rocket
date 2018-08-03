import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameCanvas extends JPanel {

    public Background background;
    public Player player;

    public CreateStar createStar = new CreateStar();
    public CreateEnemy createEnemy = new CreateEnemy();
    public CreateEnemyFollow createEnemyFollow = new CreateEnemyFollow();

    // Tao 1 backbuffer
    private BufferedImage backBuffered;
    public Random random = new Random();

    // Khoi tao co ve cho backbuffer
    private Graphics graphics;

    public GameCanvas() {
        // Cho de ve tat ca moi thu len
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupBackground();
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

    private void setupBackground() {
        this.background = new Background();
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
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
        this.background.render(graphics);

        this.createStar.stars.forEach(star -> star.render(graphics));

        this.player.render(this.graphics);

        this.createEnemy.enemies.forEach(shootingEnemy -> shootingEnemy.render(graphics));

        this.createEnemyFollow.enemiesFollow.forEach(enemyFollow -> enemyFollow.render(graphics));

        this.repaint();
    }

    public void runAll() {
        this.player.run();

        this.createStar.create();
        this.createStar.stars.forEach(star -> star.run());

        this.createEnemyFollow.create();
        this.createEnemyFollow.enemiesFollow.forEach(enemyFollow -> enemyFollow.run());
        this.createEnemyFollow.enemiesFollow.forEach(enemyFollow -> enemyFollow.update(this.player.position));

        this.createEnemy.create();
        this.createEnemy.enemies.forEach(shootingEnemy -> shootingEnemy.run());
    }
}
