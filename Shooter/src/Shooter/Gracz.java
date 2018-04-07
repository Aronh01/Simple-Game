package Shooter;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Gracz extends KeyAdapter{

    private int x, y;
    private int dx, dy;
    private int height, width;
    private boolean isVisible = true;
    private Image image;
    private List<Pocisk> pociski;

    public Gracz() {

        ImageIcon img = new ImageIcon(this.getClass().getResource("/images/UFO.png"));
        image = img.getImage();
        height = image.getHeight(null);
        width = image.getWidth(null);
        pociski = new ArrayList<Pocisk>();
        this.x = 100;
        this.y = 100;
    }

    public void move() {

        x = x + dx; 
        y = y + dy; 

        if (this.x < 1) {
            x = 1;
        }

        if (this.x > 755) {
            x = 755;
        }

        if (this.y < 1) {
            y = 1;
        }
        if (this.y > 405) {
            y = 405;
        }

    }

    public List<Pocisk> getPociski() {
        return pociski;
    }

    public void setdX(int x) {
        this.dx = x;
    }

    public void setdY(int y) {
        this.dy = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void shoot() {
        if(isVisible == true)
        {
            Pocisk missle;
            missle = new Pocisk(x + width, y + height / 2);
            this.pociski.add(missle);
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, height, width);

    }

    public void keyPressed(KeyEvent k) {

        int button_press = k.getKeyCode();

        if (button_press == KeyEvent.VK_SPACE) {
            shoot();
        }

        if (button_press == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (button_press == KeyEvent.VK_DOWN) {
            dy = 1;
        }

        if (button_press == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (button_press == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

    }

    public void keyReleased(KeyEvent k) {

        int button_release= k.getKeyCode();

        if (button_release == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (button_release == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (button_release == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (button_release == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
}
