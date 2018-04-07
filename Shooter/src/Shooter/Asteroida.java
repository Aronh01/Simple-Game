package Shooter;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Asteroida{

    private Image image;
    private int x, y;
    private int width, height;
    private boolean isVisible;
    private static final int SCREEN_WIDTH = 800;
    public static double SPEED = 1;
    private static int counter = 0;

    public Asteroida(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon img;

        if (counter++ % 2 == 0) {

            img = new ImageIcon(this.getClass().getResource("/images/A1.png"));
        } else if(counter++ % 3 == 0) {
            img = new ImageIcon(this.getClass().getResource("/images/A2.png"));
        }else{
            img = new ImageIcon(this.getClass().getResource("/images/A3.png"));
        }

        image = img.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        isVisible = true;

    }
    
    public void move() {

        if (this.x < 0) {
            isVisible=false;
        } else {
            this.x -= SPEED;
        }

    }    
    public void addSpeed() {
       this.SPEED+=0.0125;
    }             
    
    public void substractSpeed() {
       this.SPEED-=0.0125;
       if(this.SPEED<1)
           this.SPEED=1;
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
