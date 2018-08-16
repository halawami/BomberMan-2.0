/**
 * 
 * BombermanControl moves bomberman(hero) by utilizing a thread.
 * @Param bomberman position and color
 *
 * @author alawamhm, alzatee, haussmee
 *         Created May 10, 2017.
 */
public class BombermanControl implements Runnable {
	GamePiece bomberman;
	myPanel panel;
	
	public BombermanControl(GamePiece bomberman){
		this.bomberman = bomberman;
		this.panel = this.bomberman.map.panel;
	}

	@Override
	public synchronized void run() {
		try{
			while(true){
				if(this.panel.isMovingDown()&&this.checkCollision(0, 2)){
					this.bomberman.moveDown();
				}
				
				if(this.panel.isMovingLeft()&&this.checkCollision(-2, 0)){
					this.bomberman.moveLeft();
				}
				if(this.panel.isMovingRight()&&this.checkCollision(2, 0)){
					this.bomberman.moveRight();
				}
				if(this.panel.isMovingUp()&&this.checkCollision(0, -2)){
					this.bomberman.moveUp();
				}
				
				Thread.sleep(10);
			}
		} catch (Exception e){
			// do nothing
		}
		
	}
	
		
	
	public boolean checkCollision(int x,int y){
		for(GamePiece piece:this.bomberman.getMap().getWalls()){
			if(Math.abs(this.bomberman.getyCoor()-piece.getyCoor()+y)<=29 &&
					Math.abs(this.bomberman.getxCoor()-piece.getxCoor()+x)<=29){
				return false;
			}
		}
		return true;
	}
}
