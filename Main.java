import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */


public class Main{
	public static void main(String[] args){
		JFrame theFrame = new JFrame();
		JButton thebutton = new JButton("Start Bomberman");
		thebutton.addActionListener(new ButtonListener(thebutton));
		theFrame.add(thebutton, BorderLayout.CENTER);
		theFrame.pack();
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);
	}

}
