import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 
 * This is responsible for doing an action when the buttons on the keyboard are being pressed.
 * @param panel - panel created
 * @param pauseCounter - a boolean to see if the game is paused.
 * 
 *
 * @author alzatee, haussmee, alawamhm.
 *         Created May 10, 2017.
 */
public class KeyboardListener implements KeyListener{
	private myPanel panel;
	private int pauseCounter;
	
	public KeyboardListener(myPanel myPanel) {
		this.panel = myPanel;
		this.pauseCounter= 0;
		
	}

	@Override
	public synchronized void keyTyped(KeyEvent e){

	}
//Sets booleans to move bomberman on screen, constructs bombs, and changes levels
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if (KeyCode == KeyEvent.VK_LEFT) {
			panel.setMovingLeft(true);
		}

		if (KeyCode == KeyEvent.VK_RIGHT) {
			panel.setMovingRight(true);
		}

		if (KeyCode == KeyEvent.VK_UP) {
			panel.setMovingUp(true);
		}

		if (KeyCode == KeyEvent.VK_DOWN) {
			panel.setMovingDown(true);
		}
		
		if (KeyCode == KeyEvent.VK_P) {
//			panel.setMovingDown(true);
			this.pauseCounter++;
			if(pauseCounter%2!=0){
				panel.isPaused =true; 
			}else{
			panel.isPaused=false;
			}
		}
		
		if (KeyCode == KeyEvent.VK_U){
			if(panel.getMapLevel() != 4){
				panel.setMapLevel(panel.getMapLevel() + 1);
			}
			else{
			System.out.println("Level Out Of Bounds");
			}
		}
		
		if (KeyCode == KeyEvent.VK_D){
			if(panel.getMapLevel() == 0){
				System.out.println("Level Out Of Bounds");
			}
			else{
			panel.setMapLevel(panel.getMapLevel() - 1);
			}
		}
		
		if (KeyCode == KeyEvent.VK_SPACE){
			if(panel.canDropBomb()){
				Bomb bomb = new Bomb(panel.map.getBomberman().getxCoor() + 12, panel.map.getBomberman().getyCoor() + 12,panel.map);
				panel.addBomb(bomb);
				panel.map.getMyArray().add(bomb);
			}
			
		}
		if(KeyCode == KeyEvent.VK_B){
			if(panel.getBombsOnField().size() != 0 && panel.getDetonate() == true){
				this.panel.getBombsOnField().get(0).explode();
			}

		}

	}
//Reverses movement and bomb booleans
	@Override
	public void keyReleased(KeyEvent e) {
		int KeyCode = e.getKeyCode();

		if (KeyCode == KeyEvent.VK_LEFT) {
			panel.setMovingLeft(false);
		}

		if (KeyCode == KeyEvent.VK_RIGHT) {
			panel.setMovingRight(false);
		}

		if (KeyCode == KeyEvent.VK_UP) {
			panel.setMovingUp(false);
		}

		if (KeyCode == KeyEvent.VK_DOWN) {
			panel.setMovingDown(false);
		}
		
		if (KeyCode == KeyEvent.VK_SPACE){
			panel.setIsDroppingBomb(false);
		}
	}

}
