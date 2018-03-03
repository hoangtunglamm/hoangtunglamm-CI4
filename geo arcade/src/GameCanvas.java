import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameCanvas extends JPanel {

    BufferedImage backgroud;
    BufferedImage player;
    int positionPlayerX;
    int positionPlayerY;
    BufferedImage square;
    int positionSquareY;
    int positionSquareX;
    int vectorX = 3;
    int vectorY = 3;
    BufferedImage backBuffer;
    Graphics graphics;


    public GameCanvas() {
        this.setSize(400, 600);
        this.setVisible(true);
        this.backBuffer = new BufferedImage(400, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffer.getGraphics();
        //Load Images
        try {
            this.backgroud = ImageIO.read(new File("resources/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {
            this.player = ImageIO.read(new File("resources/player/straight.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            this.square = ImageIO.read(new File("resources/square/enemy_square_small.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Draw images
        g.drawImage(this.backBuffer, 0, 0, null);

    }
    public void run(){

        if (this.positionSquareY < 600) {
            positionSquareY += vectorY;
        }
        else{

            vectorY = -3;
            positionSquareY += vectorY;
        }


    }

    Random rd = new Random();
    public void renderAll(){
        this.graphics.drawImage(this.backgroud, 0, 0, null);
        this.graphics.drawImage(this.player, this.positionPlayerX, this.positionPlayerY, null);
        this.graphics.drawImage(this.square, 200,positionSquareY, null);


        this.repaint();
    }
}
