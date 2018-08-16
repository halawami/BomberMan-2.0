import java.util.ArrayList;
/**
 * 
 * MonsterTwoControl moves a MonsterTwo by utilizing a thread.
 * @param allOfTheYellowMonsters = all the monsters that need to be moved by
 * an instance of this class.
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class MonsterTwoControl implements Runnable {
	ArrayList<MonsterTwo> allOfTheYellowMonsters;
	myPanel panel;

	public MonsterTwoControl(ArrayList<MonsterTwo> allOfTheYellowMonsters, myPanel panel){
		this.allOfTheYellowMonsters = new ArrayList<MonsterTwo>();
		this.panel = panel;
		for(int i = 0; i < allOfTheYellowMonsters.size(); i++){
			this.allOfTheYellowMonsters.add(allOfTheYellowMonsters.get(i));
		}
	}
	@Override
	public synchronized void run() {
		try{
			while(true){
				if(panel.isYellowMonsterIsOnBoard()){
					for(int i = 0; i < this.allOfTheYellowMonsters.size(); i++){
						this.allOfTheYellowMonsters.get(i).updateYellowPosition();
					}
				}
				Thread.sleep(300);
			}
		} catch(Exception e){
			// do nothing
		}

	}

}
