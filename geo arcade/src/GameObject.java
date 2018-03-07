import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    public BufferedImage image;
    public int x;
    public int y;

    public GameObject() {
    }

    public void run() {
    }

    public void render(Graphics graphics) {
        if (this.image != null)
            graphics.drawImage(this.image, this.x, this.y, null);
    }
}
