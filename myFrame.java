import javax.swing.JFrame;
/**
 * 
 * myFrame is a version of JFrame which contains particular parameters necessary
 * for the Bomberman game, such as the title.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class myFrame extends JFrame {
	myPanel myPanel = new myPanel();

	private static final long serialVersionUID = 1L;
	
	public myFrame(){
		super();
		super.add(myPanel);
		super.pack();
		super.setTitle("Bomberman");
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}
	

}
