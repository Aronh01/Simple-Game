package Shooter;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pocisk{

    private Image image;
    private int x, y;
    private int width, height;
    private boolean isVisible;
    private static final int SCREEN_WIDTH = 800;
    private static final int SPEED = 5;

    public Pocisk(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon img = new ImageIcon(this.getClass().getResource("/images/had.png"));
        image = img.getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        isVisible = true;

    }

    public void move() {

        this.x += SPEED;

        if (this.x > SCREEN_WIDTH) {
            isVisible = false;
        }

    }
    
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, width, height);

    }
}
