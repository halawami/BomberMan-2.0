import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
/**
 * 
 * Explosion takes in the same parameters as other GamePieces and is created by
 * a bomb when it explodes. The explosion then reacts with other GamePieces to 
 * remove them if necessary.
 *
 * @author alzatee, haussmee, alawamhm.
 *         Created May 10, 2017.
 */
public class Explosion extends GamePiece{
	static int BLAST_RADIUS = 31;
	static Color COLOR = Color.orange;
	static int bombX;
	static int bombY;
	static boolean isExploding;
	private boolean isDead;
	private myMap map;
	
	
	public Explosion(int x, int y, myMap map){
		super(x, y, map);
		this.map=map;
		isExploding = false;
		bombX = 0;
		bombY = 0;
		this.isDead=false;
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					remove();
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
				
			}
		});
		th.start();
	}
		
	@Override
	public void draw(Graphics2D g2){
		g2.setColor(Color.BLACK);
		g2.fillRect(this.xCoor - wallSize/2, this.yCoor - wallSize/2, BLAST_RADIUS, BLAST_RADIUS);
		g2.fillRect(this.xCoor + wallSize/2, this.yCoor - wallSize/2, BLAST_RADIUS, BLAST_RADIUS);
		g2.fillRect(this.xCoor - wallSize/2, this.yCoor + wallSize/2, BLAST_RADIUS, BLAST_RADIUS);
		g2.fillRect(this.xCoor - (wallSize/2) * 3, this.yCoor - wallSize/2, BLAST_RADIUS, BLAST_RADIUS);
		g2.fillRect(this.xCoor - wallSize/2, this.yCoor - (wallSize/2) * 3, BLAST_RADIUS, BLAST_RADIUS);
		bombX = this.xCoor;
		bombY = this.yCoor;
		isExploding = true;
	}
	
	public static void updateBlastRadius(){
		BLAST_RADIUS = BLAST_RADIUS + wallSize;
	}
	
	public static void resetBlastRadius(){
			BLAST_RADIUS = 	wallSize * 3;
	}

	@Override
	public Area getArea() {
		Shape rect = new Rectangle2D.Double(this.xCoor-(int)(wallSize/2)*3, this.yCoor-(int)31/2,wallSize*3, wallSize);
		Shape rect2 = new Rectangle2D.Double(this.xCoor-(int)31/2, this.yCoor-(int)(wallSize/2)*3,wallSize,wallSize*3);
	    Area area = new Area(rect);
	    area.add(new Area(rect2));
	    return area;
	}

	@Override
	void collide(GamePiece m) {
		m.collideWithExplosion(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
		m.loseLife();
	}

	@Override
	void collideWithMonster(MonsterOne m) {
		m.remove();
	}

	@Override
	void collideWithMonster2(MonsterTwo m) {
		m.remove();
	}

	@Override
	void collideWithPowerUp(PowerUp m) {
	}

	@Override
	void collideWithExplosion(Explosion m) {
		
	}

	@Override
	void collideWithBomb(Bomb m) {
	}

	@Override
	void collideWithUnbreakableWall(UnbreakableWall m) {
	}

	@Override
	void collideWithBrickWall(BrickWall m) {
		m.remove();
	}

	@Override
	void collideWithMonster3(MonsterThree m) {
		m.remove();
	}
	
}
