package Shooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Gra extends JPanel implements ActionListener {
    
    private Image background;
    public Gracz player;
    private Timer timer;
    int a;
    int best = 0;
    private int increment = 10;
    private int state;
    private List<Asteroida> asteroidy;
    Random rand = new Random();

    public Gra() {

        setFocusable(true); 
        setDoubleBuffered(true); 
        addKeyListener(new NowaGra());
        ImageIcon img = new ImageIcon(this.getClass().getResource("/images/cosmos.jpg"));
        background = img.getImage();
        player = new Gracz();
        state = 1;
        initializeEnemies();
        timer = new Timer(5,this);
        timer.start();

    }
    public void initializeEnemies() {
        asteroidy = new ArrayList<Asteroida>();
        for (int i=0;i<40;i++) 
        {
            asteroidy.add(new Asteroida(rand.nextInt(2000)+800, rand.nextInt(440)));
        }
    }
    
    public boolean checkinitialize(){
        int checker=0;
        for (int i = 0; i < asteroidy.size(); i++) {
            Asteroida en = asteroidy.get(i);
            if(en.getY()>440 || en.getY()<0)
            {
                checker++;
            }
        }
        if(checker>0)
        {
            return false;
        }else{
            return true;  
        }
    }
    
 public void paintComponent(Graphics g) {

        Graphics2D graph = (Graphics2D) g;
        graph.drawImage(background, 0, 0, null); 
        Font myFont = new Font ("Calibri", 1, 17);
        if (state==2) {
            graph.drawImage(player.getImage(), player.getX(), player.getY(), this);

            List<Pocisk> pociski = player.getPociski();

            for (int i = 0; i < pociski.size(); i++) {

                Pocisk m = (Pocisk) pociski.get(i);
                graph.drawImage(m.getImage(), m.getX(), m.getY(), this);

            }

            for (int i = 0; i < asteroidy.size(); i++) {

                Asteroida en = asteroidy.get(i);
                graph.drawImage(en.getImage(), en.getX(), en.getY(), this);

            }

            graph.setColor(Color.red);
            graph.setFont(myFont);
            graph.drawString("Score: " + a, 5, 15);
            

        } else if(state==0){

            ImageIcon gameOver = new ImageIcon(this.getClass().getResource("/images/go.png"));
            graph.drawImage(gameOver.getImage(), 0, 0, null);
            graph.setColor(Color.white);
            graph.setFont(myFont);
            graph.drawString("Score: " + a, 300, 380);
            graph.drawString("Best Score: " + best, 300, 400);
            graph.drawString("Wcisnij Enter aby zagrać ponownie ", 300, 420);
            
        }else if(state==1){

            ImageIcon gameOver = new ImageIcon(this.getClass().getResource("/images/title.jpg"));
            graph.drawImage(gameOver.getImage(), 0, 0, null);
            graph.setColor(Color.white);
            graph.setFont(myFont);
            graph.drawString("Wciśnij enter aby zagrać",300, 250);
            graph.drawString("Wciśnij 'I' aby wyświetlić instrukcję",300, 270);

        }else if(state==4){

            ImageIcon gameOver = new ImageIcon(this.getClass().getResource("/images/title.jpg"));
            graph.drawImage(gameOver.getImage(), 0, 0, null);
            graph.setColor(Color.white);
            graph.setFont(myFont);
            graph.drawString("Celem gry jest zdobycie jak największej liości punktów ",250, 250);
            graph.drawString("eliminując naptkane asteroidy",250, 270);
            graph.drawString("Gdy gracz zderzy się z asteroidą następuje koniec gry",250, 290);
            graph.drawString("Poruszanie sie klawiszami strzałek",250, 320);
            graph.drawString("'Spacja' - Wypuszczenie pocisku",250, 340);
            graph.drawString("'+' , '-' - przyspieszninie lub zwolnienie asteroid",250, 360);

        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

      if(state == 2){
        checkColision();
        player.move();
        
        //repaint();
        List<Pocisk> pociski = player.getPociski();

        for (int i = 0; i < pociski.size(); i++) 
        {

            Pocisk m = (Pocisk) pociski.get(i);

            if (m.isVisible()) 
            {
                m.move();
            }else{
                    pociski.remove(i);

            }

        }

        for (int i = 0; i < asteroidy.size(); i++) {

            Asteroida en = asteroidy.get(i);
            
            if (en.isVisible()) {
                 en.move();
            } else if(en.getX()<1){
                asteroidy.remove(i);
                asteroidy.add(new Asteroida(rand.nextInt(2000)+800, rand.nextInt(440)));
            }
            else {
                asteroidy.remove(i);
                asteroidy.add(new Asteroida(rand.nextInt(2000)+800, rand.nextInt(440)));
            }
        }
        
        player.move();
      }
        repaint();

    }

    public void addPoints(){
        a+=increment;
    }
    
    
    
    
    
    public void checkColision() {

        Rectangle playerBound = player.getBounds();
        Rectangle enemyBound;
        Rectangle missileBound;

        for (int i = 0; i < asteroidy.size(); i++) {

            Asteroida tempEnemy = asteroidy.get(i);
            enemyBound = tempEnemy.getBounds();

            if (playerBound.intersects(enemyBound)) {
                if(a>best)
                    best = a;
                player.setVisible(false);
                tempEnemy.setVisible(false);
                state =0;
            }

        }

        List<Pocisk> missiles = player.getPociski();

        for (int i = 0; i < missiles.size(); i++) {

            Pocisk tempMissile = missiles.get(i);
            missileBound = tempMissile.getBounds();

            for (int j = 0; j < asteroidy.size(); j++) {
                Asteroida tempEnemy = asteroidy.get(j);
                enemyBound = tempEnemy.getBounds();

                if (missileBound.intersects(enemyBound)) {
                    tempEnemy.setVisible(false);
                    tempMissile.setVisible(false);
                    addPoints();
                }
            }

        }

    }

    private class NowaGra extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
            if(state != 2){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    state = 2;
                    player = new Gracz();
                    initializeEnemies();
                    a=0;
                }
                        
                if (e.getKeyCode() == KeyEvent.VK_I) {
                
                    state = 4;
                }
            
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                
                    state = 1;
                 }
            }
        }
               
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            
            if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    for (int i = 0; i < asteroidy.size(); i++) {
                        Asteroida en = asteroidy.get(i);   
                        en.addSpeed();
                    }
                    increment+=1;
               }

            
            if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                
                 for (int i = 0; i < asteroidy.size(); i++) {
                    Asteroida en = asteroidy.get(i);   
                    en.substractSpeed();
                }
                if(increment<=10)
                    increment = 10;
                else
                    increment -=1;                
                }

       
        }


    }

}

