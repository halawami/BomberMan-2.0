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
 * Class to take care of the properties of unbreakable walls.
 * @param position, and other properties
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 18, 2017.
 */
public class UnbreakableWall extends Wall {
	private BufferedImage image;
	private static final int wallSize = 31;
	
	public UnbreakableWall(int x, int y, myMap map){
		super(x, y, map);
		try{
		this.image = ImageIO.read(new File("./unbreakableWall.jpg"));
	}catch(IOException e){
		throw new RuntimeException();
		}
	}

	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fill(new Rectangle(xCoor, yCoor, wallSize, wallSize));
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
		
	}

	
	@Override
	void collide(GamePiece m) {
		m.collideWithUnbreakableWall(this);
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
	void collideWithMonster3(MonsterThree m) {
		// TODO Auto-generated method stub
		
	}

}
