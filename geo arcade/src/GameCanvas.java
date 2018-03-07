import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameCanvas extends JPanel {

    BufferedImage background;
    Player player;
    Vector<Square> squareVector;
    Vector<Bullet> bulletVector;
    BufferedImage backBuffered;
    Graphics graphics;
    EnemySqawner enemySqawner;

    int countSquare = 0;
    int countBullet = 0;

    public GameCanvas() {
        this.setup();
        this.setupBackBuffered();
        this.setupBackground();
        this.setupPlayer();
        this.squareVector = new Vector<>();
        this.bulletVector = new Vector<>();
        this.enemySqawner = new EnemySqawner();
    }

    private void setup() {
        this.setSize(400, 600);
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.x = 200;
        this.player.y = 300;
    }

    private void setupBackground() {
        try {
            this.background = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void runAll() {
        // Shoot Bullet
        this.runBullets();
        this.runSquares();
        this.enemySqawner.run();
    }

    private void runSquares() {
        this.createSquare();
        this.squareVector.forEach(square -> square.run());
    }

    private void createSquare() {
        if (this.countSquare >= 30) {
            Square square = new Square();
            square.x = 20;
            square.dy = 3;
            this.squareVector.add(square);
            this.countSquare = 0;
        } else {
            this.countSquare += 1;
        }
    }

    private void runBullets() {
        this.createBullet();
        this.bulletVector.forEach(bullet -> bullet.run());
    }

    private void createBullet() {
        if (this.countBullet >= 30) {
            Bullet bullet = new Bullet();
            bullet.x = this.player.x;
            bullet.y = this.player.y;
            bullet.dy = -4;
            this.bulletVector.add(bullet);
            this.countBullet = 0;
        } else {
            this.countBullet += 1;
        }
    }

    public void renderAll() {
        this.graphics.drawImage(this.background, 0, 0, null);
        this.player.render(this.graphics);
        this.squareVector.forEach(square -> square.render(graphics));
        this.bulletVector.forEach(bullet -> bullet.render(graphics));
        this.enemySqawner.render(this.graphics);
        this.repaint();
    }
}