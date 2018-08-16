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
 * After picking up this power up, bombs will not explode until the user pressed the b key,
 * which detonates the oldest bomb on the field.
 *
 * @author alzatee, haussmee, alawamhm.
 *         Created May 18, 2017.
 */
public class Detonator extends PowerUp {
	private static final int wallSize = 31;
	private BufferedImage image;
	
	public Detonator(int x, int y, myMap map){
		super(x, y, map);
		try{
			this.image = ImageIO.read(new File("./remote.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	@Override
	public void upgrade() {
		this.map.panel.setDetonate();

	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.gray);
		g.fillOval(this.xCoor, this.yCoor, wallSize, wallSize);
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}

	@Override
	public Area getArea() {
		Shape s = new Ellipse2D.Double(this.xCoor, this.yCoor, wallSize, wallSize);
		return new Area(s);
	}
	@Override
	void collideWithMonster3(MonsterThree m) {
		
	}
	

}
