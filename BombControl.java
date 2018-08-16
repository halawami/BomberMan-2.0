import java.awt.Graphics2D;
/**
 * 
 * BombControl describes when a bomb can be dropped; this is necessary since two
 * cannot be on the field at the same time. It manages a thread to do so.
 * 
 * @param bomb- the bomb the class checks for drop conditions
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class BombControl implements Runnable {
	GamePiece bomb;
	Graphics2D g;
	

	public BombControl(GamePiece bomb){
		this.bomb = bomb;
	}

	@Override
	public void run() {	
		try{
			while(true){
				System.out.println("What about here?");
				for(int i = 0; i < this.bomb.map.panel.getBombsAllowed(); i++){
					System.out.println("Did you even get here?");
				}
				Thread.sleep(10);
			}
		} catch (Exception e){
			// do nothing
		}

	}

}
