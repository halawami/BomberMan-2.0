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
 * This powerup increases the size of the explosion each time it is picked up.
 *
 * @author haussmee,alawamhm,alzatee
 *         Created May 18, 2017.
 */
public class Flame extends PowerUp {
	private BufferedImage image;
	
	public Flame(int x, int y, myMap map){
		super(x, y, map);
		try{
			this.image = ImageIO.read(new File("./bombsrad.png"));
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
	
	@Override
	public void upgrade(){
		Explosion.updateBlastRadius();
	} 
	
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.cyan);
		g.fillRect(xCoor, yCoor, 15, 15);
		g.drawImage(this.image,this.xCoor,this.yCoor,null);
	}
		
	@Override
	public Area getArea() {
		Shape s = new Ellipse2D.Double(xCoor, yCoor, 15, 15);
		return new Area(s);
	}
	@Override
	void collideWithMonster3(MonsterThree m) {
		
	}


}
