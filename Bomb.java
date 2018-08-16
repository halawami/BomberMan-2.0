import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 
 * Constructs a bomb, which is drawn on the panel. When it explodes, it creates
 * and explosion which reacts with the environment, and the bomb deletes itself.
 * 
 *
 * @author alzatee, haussmee, alawamhm.
 *         Created May 10, 2017.
 */
public class Bomb extends GamePiece{
	static Color BOMB_COLOR = Color.PINK;
	static int BOMB_RADIUS = 31;
	myMap map;
	myPanel panel;
	boolean isExploding;
	private BufferedImage image;
	

	public Bomb(int x, int y, myMap map){
		super(x, y, map);
		this.map = map;
		this.panel = map.panel;
		try{
			this.image = ImageIO.read(new File("./bomb.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				if(panel.getDetonate() == false){
					try {
						Thread.sleep(1000);
						Bomb.this.explode();
					} catch (InterruptedException exception) {
						// TODO Auto-generated catch-block stub.
						exception.printStackTrace();
					}
				}else{
					
				}

				
			}
		});
		th.start();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(BOMB_COLOR);
		g.fillOval(this.xCoor-5, this.yCoor-8, BOMB_RADIUS-4, BOMB_RADIUS-4);
		g.drawImage(this.image,this.xCoor-10,this.yCoor-11,null);

	}
		

	public synchronized void explode() {
		this.panel.removeBomb(this);
		Explosion explosion = new Explosion(this.xCoor, this.yCoor, this.map);
		this.map.getMyArray().add(explosion);
		this.isExploding = true;
		this.remove();
	}

	@Override
	public Area getArea() {
		Shape s = new Ellipse2D.Double(this.xCoor, this.yCoor, BOMB_RADIUS, BOMB_RADIUS);
		return new Area(s);
	}

	@Override
	void collide(GamePiece m) {
		m.collideWithBomb(this);
	}

	@Override
	void collideWithPlayer(Bomberman m) {
		
	}

	@Override
	void collideWithMonster(MonsterOne m) {
		if(m.vxCoord[vxCoor]<0){
			m.vxCoord[vxCoor]= 2;
		}else if(m.vxCoord[vxCoor]>0){
			m.vxCoord[vxCoor] = -2;
		}if(m.vyCoord[vxCoor]<0){
			m.vyCoord[vxCoor]= 2;
		}else if(m.vyCoord[vxCoor]>0){
			m.vyCoord[vxCoor] = -2;
		}
	}

	@Override
	void collideWithMonster2(MonsterTwo m) {
		// TODO Auto-generated method stub
		if(m.vxCoord[vxCoor]<0){
			m.vxCoord[vxCoor]= 2;
		}else if(m.vxCoord[vxCoor]>0){
			m.vxCoord[vxCoor] = -2;
		}if(m.vyCoord[vxCoor]<0){
			m.vyCoord[vxCoor]= 2;
		}else if(m.vyCoord[vxCoor]>0){
			m.vyCoord[vxCoor] = -2;
		}
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
		if(m.vxCoord[vxCoor]<0){
			m.vxCoord[vxCoor]= 2;
		}else if(m.vxCoord[vxCoor]>0){
			m.vxCoord[vxCoor] = -2;
		}if(m.vyCoord[vxCoor]<0){
			m.vyCoord[vxCoor]= 2;
		}else if(m.vyCoord[vxCoor]>0){
			m.vyCoord[vxCoor] = -2;
		}
		
	}	

}
