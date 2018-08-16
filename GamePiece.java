import java.awt.Graphics2D;
import java.awt.geom.Area;
/**
 * 
 * This abstract class describes anything that will be drawn on the board. 
 * Pieces have coordinates, can be moved, can check for collision, and return
 * relevant parameters.
 * 
 * @param x - the x coordinate
 * @param y - the y coordinate
 * @param map - the map the GamePiece will be drawn on
 *
 * @author alzatee, haussmee, alawamhm.
 *         Created May 10, 2017.
 */
public abstract class GamePiece{
	protected static final int wallSize = 31;
	int yCoor;
	int xCoor;
	int vxCoor = (int)(Math.random()*3);
	int[] vxCoord = {0,-20,20};
	int[] vyCoord = {0,20,-20};
	myMap map;
	int speed;


	//function definitions
	abstract void collide(GamePiece m);
	abstract void collideWithPlayer(Bomberman m);
	abstract void collideWithMonster(MonsterOne m);
	abstract void collideWithMonster2(MonsterTwo m);
	abstract void collideWithPowerUp(PowerUp powerUp);
	abstract void collideWithExplosion(Explosion m);
	abstract void collideWithBomb(Bomb m);
	abstract void collideWithUnbreakableWall(UnbreakableWall m);
	abstract void collideWithBrickWall(BrickWall m);
	abstract void collideWithMonster3(MonsterThree m);
	public abstract Area getArea();
	
	
	public GamePiece(int xCoor, int yCoor, myMap map){
		this.yCoor = yCoor;
		this.xCoor = xCoor;
		this.map = map;
		this.speed = 2;
	}
	
	public int getyCoor() {
		return this.yCoor;
	}
	

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}


	public int getxCoor() {
		return this.xCoor;
	}


	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}
	
	public void moveUp(){
		this.yCoor = this.yCoor - speed;
	}
	
	public void moveDown(){
		this.yCoor = this.yCoor + speed;
	}
	
	public void moveLeft(){
		this.xCoor = this.xCoor - speed;
	}
	
	public void moveRight(){
		this.xCoor = this.xCoor + speed;
	}
	
	
	
	public synchronized void remove(){
		this.map.getMyArray().remove(this);
		this.map.getGreenMonsters().remove(this);
		this.map.getYellowMonsters().remove(this);
		this.map.getGhostMonster().remove(this);
	}
	
	public abstract void draw(Graphics2D g);
	
	public myMap getMap(){
		return this.map;
	}
	
	
	public boolean checkCollision(int x,int y){
		for(GamePiece piece:this.getMap().getWalls()){
			if(Math.abs(this.getyCoor()-piece.getyCoor()+y)<=29 &&
					Math.abs(this.getxCoor()-piece.getxCoor()+x)<=29){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkCollisionWithWall(int x,int y){
		Class<? extends GamePiece> className = this.getMap().walls.get(0).getClass();
		for(GamePiece piece:this.getMap().walls){
			if(piece.getClass().equals(className)){
				if(Math.abs(this.getyCoor()-piece.getyCoor()+y)<=29 &&
						Math.abs(this.getxCoor()-piece.getxCoor()+x)<=29){
					return false;
				}
			}
		}
		return true;
	}
	
	public void setSpeed(){
		speed = speed + 1;
	}
	

	

}	
