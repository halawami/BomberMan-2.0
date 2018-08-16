import java.awt.Graphics2D;
/**
 * 
 * A superclass for powerups
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 18, 2017.
 */
public abstract class PowerUp extends GamePiece {
	
	public PowerUp(int x, int y, myMap map){
		super(x, y, map);
	}
	
	@Override
	public void draw(Graphics2D g) {

	}
	@Override
	void collideWithPowerUp(PowerUp m) {
		
	}
	@Override
	void collide(GamePiece m) {
		m.collideWithPowerUp(this);
	}
	@Override
	void collideWithPlayer(Bomberman m) {
		this.upgrade();
		this.remove();
	}
	@Override
	void collideWithMonster(MonsterOne m) {
		
	}
	@Override
	void collideWithMonster2(MonsterTwo m) {
		
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
	// Causes the effect of whatever the powerup is
	public abstract void upgrade();

}
