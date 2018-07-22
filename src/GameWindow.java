import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {
    // Khung de ve
    private GameCanvas gameCanvas;
    private long lastTime = 0;
    public Random random = new Random();
    private String enemyDirection = "Left Down";

    public GameWindow() {
        this.setSize(1024, 600);

        // Dua gameCanvas vao gameWindow
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) gameCanvas.positionXPlayer += 10;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) gameCanvas.positionXPlayer -= 10;

                if (gameCanvas.positionXPlayer > 1024){
                    gameCanvas.positionXPlayer = 0;
                    gameCanvas.positionYPlayer = random.nextInt(600);
                } else if (gameCanvas.positionXPlayer < 0) {
                    gameCanvas.positionXPlayer = 1024;
                    gameCanvas.positionYPlayer = random.nextInt(600);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        // Spawn random star every 8 second, 8 second initial delay
        Timer timer = new Timer();
        TimerTask spawnStar = new TimerTask() {
            @Override
            public void run() {
                gameCanvas.positionXStar = 1024;
                gameCanvas.positionYStar = random.nextInt(600);
            }
        };
        timer.schedule(spawnStar,8000,8000);

        this.setVisible(true);
    }

    public void gameLoop() {
        while (true) {
            // 60fps
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                gameCanvas.positionXStar -= 5;

                // Enemy bat tuong
                switch(enemyDirection) {
                    case ("Left Down"):
                        this.gameCanvas.positionXEnemy -= 5;
                        this.gameCanvas.positionYEnemy += 5;

                        if (this.gameCanvas.positionXEnemy<=0) {
                            enemyDirection = "Right Down";
                            this.gameCanvas.positionXEnemy = 0;
                        } else if (this.gameCanvas.positionYEnemy >= 540) {
                            enemyDirection = "Left Up";
                            this.gameCanvas.positionYEnemy = 540;
                        }
                        break;
                    case ("Right Down"):
                        this.gameCanvas.positionXEnemy += 5;
                        this.gameCanvas.positionYEnemy += 5;

                        if (this.gameCanvas.positionXEnemy>=990) {
                            enemyDirection = "Left Down";
                            this.gameCanvas.positionXEnemy = 990;
                        } else if (this.gameCanvas.positionYEnemy>=540) {
                            enemyDirection = "Right Up";
                            this.gameCanvas.positionYEnemy = 540;
                        }
                        break;
                    case ("Right Up"):
                        this.gameCanvas.positionXEnemy += 5;
                        this.gameCanvas.positionYEnemy -= 5;

                        if (this.gameCanvas.positionXEnemy>=990) {
                            enemyDirection = "Left Up";
                            this.gameCanvas.positionXEnemy = 990;
                        } else if (this.gameCanvas.positionYEnemy<=0) {
                            enemyDirection = "Right Down";
                            this.gameCanvas.positionYEnemy = 0;
                        }
                        break;
                    case ("Left Up"):
                        this.gameCanvas.positionXEnemy -= 5;
                        this.gameCanvas.positionYEnemy -= 5;

                         if (this.gameCanvas.positionXEnemy<=0) {
                             enemyDirection = "Right Up";
                            this.gameCanvas.positionXEnemy = 0;
                        } else if (this.gameCanvas.positionYEnemy<=0) {
                             enemyDirection = "Left Down";
                             this.gameCanvas.positionYEnemy = 0;
                         }
                        break;
                }

                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }
        }
    }
}
