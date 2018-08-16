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
 * This character moves randomly but can go through breakable walls
 *
 * @author alawamhm,alzatee, haussmee
 *         Created May 10, 2017.
 */
public class MonsterThree extends Character {
	private myMap map;
	private static final int wallSize = 31;
	private BufferedImage image;
	
	MonsterThree(int x, int y, myMap map) {
		super(x, y, map);
		this.map = map;
		try{
			this.image = ImageIO.read(new File("./ghost.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(new Color(31,139,0));
		g.draw(new Rectangle(this.xCoor, this.yCoor, wallSize, wallSize));
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}

	@Override
	void collide(GamePiece m) {
		m.collideWithMonster3(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
		super.map.panel.setMapLevel(super.map.panel.getMapLevel());
	}

	@Override
	void collideWithMonster(MonsterOne m) {
		
	}

	public void updateGhostPosition(){		
		if(map.panel.isPaused==false){
		this.vxCoor = (int)(Math.random()*3);
		if(this.checkCollisionWithWall(0, this.vyCoord[this.vxCoor])){
			this.yCoor = this.yCoor + this.vyCoord[this.vxCoor];
		}
		if(this.checkCollisionWithWall(this.vxCoord[this.vxCoor],0 )){
			this.xCoor = this.xCoor + this.vxCoord[this.vxCoor];
		}
		}
	}
	
	@Override
	void collideWithMonster2(MonsterTwo m) {
		
	}

	@Override
	void collideWithPowerUp(PowerUp powerUp) {
		
	}

	@Override
	void collideWithExplosion(Explosion m) {
		this.remove();
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
		
	}
	

}
