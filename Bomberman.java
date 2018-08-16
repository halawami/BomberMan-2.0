import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * Bomberman is another instance of the Character superclass, and functions much
 * like other instances of the class; the keyboard listener handles most special controls such
 * as bomb dropping and keyboard movement.
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 10, 2017.
 */
public class Bomberman extends Character {
	private static final Color BOMBERMAN_COLOR = Color.RED;
	int initialX;
	int initialY;
	int lives;
	private BufferedImage image;


	public Bomberman(int x, int y, myMap map) {
		super(x, y, map);
		this.initialX = x;
		this.initialY = y;
		this.lives = 3;
		try{
			this.image = ImageIO.read(new File("./theRealHero.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(31, 139, 0));
		g.draw(new Rectangle(this.xCoor, this.yCoor, wallSize, wallSize));
		g.drawImage(this.image,this.xCoor,this.yCoor,null);

	}

	@Override
	public Area getArea() {
		Shape s = new Rectangle2D.Double(this.xCoor,this.yCoor,wallSize,wallSize);
		return new Area(s);
	}

	@Override
	void collide(GamePiece m) {
		m.collideWithPlayer(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
	}

	@Override
	void collideWithMonster(MonsterOne m) {
		this.loseLife();

	}

	@Override
	void collideWithMonster2(MonsterTwo m) {
		this.loseLife();
	}

	@Override
	void collideWithPowerUp(PowerUp m) {
		m.upgrade();
		m.remove();

	}

	@Override
	void collideWithExplosion(Explosion m) {
		this.loseLife();
	}

	@Override
	void collideWithBomb(Bomb m) {
		
	}

	@Override
	void collideWithUnbreakableWall(UnbreakableWall m) {
	}

	@Override
	void collideWithBrickWall(BrickWall m) {
	}

	@Override
	void collideWithMonster3(MonsterThree m) {
		// TODO Auto-generated method stub
		
	}  
	void loseLife(){
		super.map.panel.setMapLevel(super.map.panel.getMapLevel());
		Bomberman bomberman = (Bomberman)this.map.getBomberman();
		this.map.panel.loseLife();
		System.out.println(this.map.panel.getLives());
	}
	
	
}
