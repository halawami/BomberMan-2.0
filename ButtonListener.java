import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * 
 * This makes an action when the button is pressed.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class ButtonListener implements ActionListener {
	
	private JButton theButton;
	
	public ButtonListener(JButton thebutton){
		this.theButton = thebutton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.theButton.setText("Game Has Begun");
		myFrame gameFrame = new myFrame();
	}

}
