package Shooter;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;


public class Main extends JFrame {

    
    private void setSizeAndLocalization()
    {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth()/2;
	int height = (int) screenSize.getHeight()/2;
	setBounds(width/2, height/2, width, height);
    }
    
    public Main() 
    {

        add(new Gra());
        setTitle("Strzelanka");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSizeAndLocalization();
        setSize(800, 470);
        setResizable(false);
        setVisible(true);

    }


    public static void main(String[] args) {
        

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



}

