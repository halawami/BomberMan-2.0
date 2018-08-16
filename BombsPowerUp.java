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
 * This powerup increases the number of unexploded bombs there can be on the field at once.
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 18, 2017.
 */
public class BombsPowerUp extends PowerUp {
	private static final int wallSize = 31;
	private BufferedImage image;

	public BombsPowerUp(int x, int y, myMap map) {
		super(x, y, map);
		myMap mappers = map;
		mappers.panel.setBombsAllowed();
		try{
			this.image = ImageIO.read(new File("./bombp.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}

	@Override
	public void upgrade() {
		this.map.panel.setBombsAllowed();
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.BLUE);
		g.fillOval(xCoor, yCoor, wallSize, wallSize);
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}


	@Override
	public Area getArea() {
		Shape s = new Ellipse2D.Double(xCoor, yCoor, wallSize, wallSize);
		return new Area(s);
	}

	@Override
	void collideWithMonster3(MonsterThree m) {
		
	}




}
