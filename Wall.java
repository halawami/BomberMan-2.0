import java.awt.Graphics2D;
/**
 * 
 * A superclass for wall objects
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 18, 2017.
 */
public abstract class Wall extends GamePiece{


	
	public Wall(int x, int y, myMap map){	
		super(x, y, map);
	}


	@Override
	public void draw(Graphics2D g) {
	}
	
}
