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
 * Class to take care of the properties of breakable walls.
 * @param position, and other properties
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 18, 2017.
 */
public class BrickWall extends Wall {
	private BufferedImage image;
	private static final int wallSize = 31;
	
	public BrickWall(int x, int y, myMap map) {
		super(x, y, map);
		try{
			this.image = ImageIO.read(new File("./breakableWall.jpg"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.BLUE);
		g.fill(new Rectangle(xCoor, yCoor, wallSize, wallSize));
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}
	
	@Override
	void collide(GamePiece m) {
		m.collideWithBrickWall(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
		
	}

	@Override
	void collideWithMonster(MonsterOne m) {
		
	}

	@Override
	void collideWithMonster2(MonsterTwo m) {
		
	}

	@Override
	void collideWithPowerUp(PowerUp m) {
		
	}

	@Override
	void collideWithExplosion(Explosion m) {
		m.remove();
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
	public Area getArea() {
		Shape s = new Rectangle2D.Double(this.xCoor,this.yCoor,wallSize,wallSize);
		return new Area(s);
	}
	@Override
	public synchronized void remove(){
		this.map.getMyArray().remove(this);
		this.map.getWalls().remove(this);
	}

	@Override
	void collideWithMonster3(MonsterThree m) {
		
	}
	
}
