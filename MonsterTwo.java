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
 * This character currently moves randomly; more functionality will be added later.
 * Dies after all the green monsters have died.
 *
 * @author alawamhm,alzatee, haussmee
 *         Created May 10, 2017.
 */
public class MonsterTwo extends Character {
	private static final Color MONSTER_TWO_COLOR = Color.YELLOW;
	private static final int wallSize = 31;
	private BufferedImage image;

	MonsterTwo(int x, int y, myMap map) {
		super(x, y, map);
		try{
			this.image = ImageIO.read(new File("./oneal.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(new Color(31, 139, 0));
		g.draw(new Rectangle(this.xCoor, this.yCoor, wallSize, wallSize));
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}

	@Override
	public Area getArea() {
		Shape s = new Rectangle2D.Double(this.xCoor,this.yCoor,wallSize,wallSize);
		return new Area(s);
	}

	public void updateYellowPosition(){
		if(map.panel.isPaused==false){
			int xDistance = this.map.getBomberman().getxCoor() - this.getxCoor();
			int yDistance = this.map.getBomberman().getyCoor() - this.getyCoor();
	
			double angle = Math.atan(Math.abs(yDistance / xDistance));
			int xVelocity = (int)(Math.cos(angle) * 10 * Math.signum(xDistance));
			int yVelocity = (int)(Math.sin(angle) * 10 * Math.signum(yDistance));	
			
			if(this.checkCollision(0, yVelocity)){
				this.yCoor = this.yCoor + yVelocity;
			}
			if(this.checkCollision(xVelocity,0 )){
				this.xCoor = this.xCoor + xVelocity;
			}
		}

	}
	@Override
	void collide(GamePiece m) {
		m.collideWithMonster2(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
		m.loseLife();
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
	void collideWithMonster3(MonsterThree m) {
		
	}
	
}
