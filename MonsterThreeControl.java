import java.util.ArrayList;
/**
 * 
 * MonsterThreeControl moves a MonsterThree by utilizing a thread.
 * @param allOfTheGhostMonsters = all the monsters that need to be moved by
 * an instance of this class.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class MonsterThreeControl implements Runnable {
	public ArrayList<MonsterThree> allOfTheGhostMonsters;
	
	public MonsterThreeControl(ArrayList<MonsterThree> allOfTheMonsters){
		this.allOfTheGhostMonsters = new ArrayList<MonsterThree>();
		for(int i = 0; i < allOfTheMonsters.size(); i++){
			this.allOfTheGhostMonsters.add(allOfTheMonsters.get(i));
		}
	}
	
	@Override
	public synchronized void run(){
		try{
			while(true){
				if(this.allOfTheGhostMonsters.size()>0){
					for(int i = 0; i < this.allOfTheGhostMonsters.size(); i++){
						this.allOfTheGhostMonsters.get(i).updateGhostPosition();
					}
				}
				Thread.sleep(300);
			}
		} catch(Exception e){
			// do nothing
		}
	}
}
