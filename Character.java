import java.awt.Graphics2D;
/** 
 * 
 * Character is a subclass of GamePiece and a superclass for any monster or the
 * bomberman. Its constructor takes the same parameters as the superclass.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public abstract class Character extends GamePiece {
	
	Character(int x, int y, myMap map) {
		super(x, y, map);
	}
	
	@Override
	public void  draw(Graphics2D g){
		// Overrided by subclasses
	}
	

}
