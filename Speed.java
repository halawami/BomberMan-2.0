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
 * This powerup increases the speed of the bomberman.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 18, 2017.
 */
public class Speed extends PowerUp{
	private BufferedImage image;
	
	public Speed(int x, int y, myMap map){
		super(x, y, map);
		try{
			this.image = ImageIO.read(new File("./speedp.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.magenta);
		g.fillOval(xCoor, yCoor, wallSize, wallSize);
		g.drawImage(this.image,this.xCoor,this.yCoor,null);

	}
	
	@Override
	public void upgrade(){
		this.map.getBomberman().setSpeed();
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
