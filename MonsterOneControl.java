import java.util.ArrayList;
/**
 * 
 * MonsterOneControl moves a MonsterOne by utilizing a thread.
 * @Param All of the monsters this class will move
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class MonsterOneControl implements Runnable {
	public ArrayList<MonsterOne> allOfTheGreenMonsters;
	
	public MonsterOneControl(ArrayList<MonsterOne> allOfTheMonsters){
		this.allOfTheGreenMonsters = new ArrayList<MonsterOne>();
		for(int i = 0; i < allOfTheMonsters.size(); i++){
			this.allOfTheGreenMonsters.add(allOfTheMonsters.get(i));
		}
	}
	
	@Override
	public synchronized void run(){
		try{
			while(true){
				if(this.allOfTheGreenMonsters.size()>0){
					for(int i = 0; i < this.allOfTheGreenMonsters.size(); i++){
						this.allOfTheGreenMonsters.get(i).updateGreenPosition();
					}
				}
				Thread.sleep(500);
			}
		} catch(Exception e){
			// do nothing
		}
	}
}
