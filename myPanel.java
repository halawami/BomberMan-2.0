import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * myPanel is a modification of JPanel which contains relevant booleans to show
 * when actions are happening, starts threads, and repaints itself.
 *
 * @author alzatee, haussmee, alawamhm. Created May 10, 2017.
 */
public class myPanel extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final long REPAINT_INTERVAL = 1;

	// Movement
	private boolean isMovingUp = false;
	private boolean isMovingDown = false;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean isDroppingBomb = false;
	private ArrayList<Bomb> bombsOnField;
	private int bombsAllowed;
	private boolean canDetonate = false;
	private boolean greenMonsterIsOnBoard = false;
	private boolean yellowMonsterIsOnBoard = false;
	private boolean ghostMonsterIsOnBoard = false;
	public int livesRemaining = 3; // this does not do anything yet
	private int mapLevel = 0;
	public boolean isPaused;


	// Monster One
	public ArrayList<MonsterOne> allOfTheGreenMonsters = new ArrayList<MonsterOne>();
	public ArrayList<MonsterTwo> allOfTheYellowMonsters = new ArrayList<MonsterTwo>();
	public ArrayList<MonsterThree> allOfTheGhostMonsters = new ArrayList<MonsterThree>();
	// Threads
	private Thread paintThread = new Thread(new Repainter());
	private Thread monsterOneThread = new Thread(new MonsterOneControl(this.allOfTheGreenMonsters));
	private Thread monsterTwoThread = new Thread(new MonsterTwoControl(this.allOfTheYellowMonsters, this));
	private Thread monsterThreeThread = new Thread(new MonsterThreeControl(this.allOfTheGhostMonsters));

	// Map
	public myMap map = new myMap(this, this.allOfTheGreenMonsters, this.allOfTheYellowMonsters,
			this.allOfTheGhostMonsters);

	public myPanel() {
		super();
		super.setPreferredSize(new Dimension(20 * 31, 15 * 31));
		JLabel status = new JLabel("Lives: ");
		JLabel status1 = new JLabel("Enemies: ");
		JButton button = new JButton("Is this owrking");
		super.add(button, BorderLayout.PAGE_END);
		super.add(status, BorderLayout.SOUTH);

		super.setFocusable(true);
		super.requestFocusInWindow();

		super.addKeyListener(new KeyboardListener(this));
		this.bombsOnField = new ArrayList<Bomb>();
		this.bombsAllowed = 1;

		this.paintThread.start();
		this.monsterOneThread.start();
		this.monsterTwoThread.start();
		this.monsterThreeThread.start();

		this.isPaused = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(31, 139, 0));
		g2.fillRect(0, 0, 20 * 31, 15 * 31);
		this.map.draw(g2);

	}

	public void withinExplosion(ArrayList<GamePiece> array) {
		for (int i = 0; i < array.size(); i++) {
			int xPos = array.get(i).xCoor;
			int yPos = array.get(i).yCoor;
			if ((((Math.abs(xPos - Explosion.bombX - 50) <= 49 || Math.abs(xPos - Explosion.bombX + 50) <= 49)
					&& Math.abs(yPos - Explosion.bombY) <= 49))
					|| (Math.abs(xPos - Explosion.bombX) <= 49 && ((Math.abs(yPos - Explosion.bombY + 50) <= 49)
							|| Math.abs(yPos - Explosion.bombY - 50) <= 49))) {
				this.remove(this);
			}
		}
	}

	public int getLivesRemaining() {
		System.out.println(livesRemaining);
		if (livesRemaining == 0) {
			System.out.println("You Lose");
		}
		return livesRemaining;
	}

	public void setLivesRemaining(int livesRemaining) {
		this.livesRemaining = livesRemaining;
	}

	private class Repainter implements Runnable {
		@Override
		public synchronized void run() {
			try {
				while (true) {
					Thread.sleep(REPAINT_INTERVAL);
					myPanel.this.repaint();
				}
			} catch (InterruptedException exception) {
				// stop when interrupted
			}
		}
	};

	public boolean isMovingUp() {
		return this.isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingDown() {
		return this.isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	public boolean isMovingLeft() {
		return this.isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return this.isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean canDropBomb() {
		if (this.bombsOnField.size() == this.bombsAllowed) {
			return false;
		}
		return true;
	}

	public void addBomb(Bomb bomb) {
		this.bombsOnField.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		this.bombsOnField.remove(bomb);
	}

	public ArrayList<Bomb> getBombsOnField() {
		return this.bombsOnField;
	}

	public void setBombsAllowed() {
		this.bombsAllowed += 1;
	}

	public int getBombsAllowed() {
		return this.bombsAllowed;
	}

	public boolean isDroppingBomb() {
		return this.isDroppingBomb;
	}

	public void setIsDroppingBomb(boolean hasBomb) {
		this.isDroppingBomb = hasBomb;
	}

	public boolean getDetonate() {
		return this.canDetonate;
	}

	public void setDetonate() {
		this.canDetonate = true;
	}

	public boolean isGreenMonsterIsOnBoard() {
		if (allOfTheGreenMonsters.size() > 0) {
			return true;
		}
		return false;
	}

	public void setGreenMonsterIsOnBoard(boolean greenMonsterIsOnBoard) {
		this.greenMonsterIsOnBoard = greenMonsterIsOnBoard;
	}

	public boolean isGhostMonsterIsOnBoard() {
		if (allOfTheGhostMonsters.size() > 0) {
			return true;
		}
		return false;
	}

	public void setGhostMonsterIsOnBoard(boolean ghostMonsterIsOnBoard) {
		this.ghostMonsterIsOnBoard = ghostMonsterIsOnBoard;
	}

	public boolean isYellowMonsterIsOnBoard() {
		if (allOfTheYellowMonsters.size() > 0) {
			return true;
		}
		return false;
	}

	public void setYellowMonsterIsOnBoard(boolean yellowMonsterIsOnBoard) {
		this.yellowMonsterIsOnBoard = yellowMonsterIsOnBoard;
	}

	public int getMapLevel() {
		return mapLevel;
	}

	public void loseLife() {
		this.livesRemaining -= 1;
		resetPowerups();
		if (livesRemaining == 0) {
			resetGame();
			this.livesRemaining = 3;
		}
	}

	public int getLives() {
		return this.livesRemaining;

	}

	private void resetGame() {
		System.out.println("Insert 25 cents to continue");
		this.setMapLevel(0);


	}

	private void resetPowerups() {
		this.bombsAllowed = 1;
		this.canDetonate = false;
		Explosion.resetBlastRadius();
	}


	public void setMapLevel(int nLevel) {
		mapLevel = nLevel;
		allOfTheGreenMonsters = new ArrayList<MonsterOne>();
		allOfTheYellowMonsters = new ArrayList<MonsterTwo>();
		allOfTheGhostMonsters = new ArrayList<MonsterThree>();
		this.bombsOnField = new ArrayList<Bomb>();
		this.map = new myMap(this, this.allOfTheGreenMonsters, this.allOfTheYellowMonsters, this.allOfTheGhostMonsters);
	}

}
