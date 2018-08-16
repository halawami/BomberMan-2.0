import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.swing.JComponent;
/**
 * 
 * myMap creates the game board and moves pieces on it. It is constructed with arraylists of particular pieces needed.
 * @param allOfTheGreenMonsters - the MonsterOne pieces the game will need
 * @param allOfTheYellowMonsters - the MonsterTwo pieces the game will need
 * @param allOfTheGhostMonsters - the MonsterThree pieces the game will need
 *
 * @author alawamhm, alzatee, haussmee.
 *         Created May 10, 2017.
 */
public class myMap extends JComponent{
	private GamePiece bomberman;
	private ArrayList<GamePiece> myArray;
	public ArrayList<Wall> walls;
	private ArrayList<MonsterOne> allOfTheGreenMonsters;
	private ArrayList<MonsterTwo> allOfTheYelloMonsters;
	private ArrayList<MonsterThree> allOfTheGhostMonsters;
	private ArrayList<Point> initialPos;
	private static final int wallSize = 31;
	private BufferedImage image;

	
	myPanel panel;
	
	public myMap(myPanel panel, ArrayList<MonsterOne> allOfTheGreenMonsters, ArrayList<MonsterTwo> allOfTheYellowMonsters,
			ArrayList<MonsterThree> allOfTheGhostMonsters){
		this.panel = panel;
		this.initialPos = new ArrayList<>();
		this.walls = new ArrayList<Wall>();
		this.allOfTheGreenMonsters = allOfTheGreenMonsters;
		this.allOfTheYelloMonsters = allOfTheYellowMonsters;
		this.allOfTheGhostMonsters = allOfTheGhostMonsters;

		this.loadMap();
		Thread monsterOneThread = new Thread(new MonsterOneControl(this.allOfTheGreenMonsters));
		Thread monsterTwoThread = new Thread(new MonsterTwoControl(this.allOfTheYelloMonsters, this.panel));
		Thread monsterThreeThread = new Thread(new MonsterThreeControl(this.allOfTheGhostMonsters));
		monsterOneThread.start();
		monsterTwoThread.start();
		monsterThreeThread.start();
	}
	


	public synchronized void loadMap(){
		this.myArray = new ArrayList<GamePiece>();
		
		File myFile = new File("src/map" + this.panel.getMapLevel());
		try {
			Scanner reader = new Scanner(myFile);
			for(int i = 0; i < 15; i++){
				String line = reader.nextLine();
				for(int j = 0; j < 20; j++){
					char value = line.charAt(j);
					if(value == 'X'){
						this.myArray.add(new UnbreakableWall(j * wallSize, i * wallSize, this));
						this.walls.add(new UnbreakableWall(j * wallSize, i * wallSize, this));
					}
					if(value == 'P'){
						int randomNum = 1 + (int)(Math.random() * 4);
						if(randomNum == 1){
							this.myArray.add(new Detonator(j * wallSize, i * wallSize, this));
						}
						if(randomNum == 2){
							this.myArray.add(new Flame(j * wallSize, i * wallSize, this));
						}
						if(randomNum == 3){
							this.myArray.add(new BombsPowerUp(j * wallSize, i * wallSize, this));
						}
						if(randomNum == 4){
							this.myArray.add(new Speed(j * wallSize, i * wallSize, this));
						}
						BrickWall wall = new BrickWall(j * wallSize, i * wallSize, this);
						this.myArray.add(wall);
						this.walls.add(wall);
					}
					if(value == 'H'){
						this.bomberman = new Bomberman(j * wallSize, i * wallSize, this);
						Thread bombermanThread = new Thread(new BombermanControl(this.bomberman));
						bombermanThread.start();
						if(!initialPos.contains(new Point(j * wallSize, i * wallSize))){
							initialPos.add(new Point(j * wallSize, i * wallSize));
						}
						this.myArray.add(this.bomberman);
					}
					if(value == 'T'){
						this.panel.setGhostMonsterIsOnBoard(true);
						this.allOfTheGhostMonsters.add(new MonsterThree(j * wallSize, i * wallSize, this));
					}
					if(value == 'G'){
						this.panel.setGreenMonsterIsOnBoard(true);
						this.allOfTheGreenMonsters.add(new MonsterOne(j * wallSize, i * wallSize, this));
					}
					if(value == 'Y'){
						this.panel.setYellowMonsterIsOnBoard(true);
						this.allOfTheYelloMonsters.add(new MonsterTwo(j * wallSize, i * wallSize, this));
					}
					if(value == 'B'){
						BrickWall wall = new BrickWall(j * wallSize, i * wallSize, this);
						this.myArray.add(wall);
						this.walls.add(wall);
					}
				}
			}

			for(int i = 0; i < this.allOfTheGreenMonsters.size(); i++){
				this.myArray.add(this.allOfTheGreenMonsters.get(i));
			}
			for(int j = 0; j < this.allOfTheGhostMonsters.size(); j++){
				this.myArray.add(this.allOfTheGhostMonsters.get(j));
			}
			for(int j = 0; j < this.allOfTheYelloMonsters.size(); j++){
				this.myArray.add(this.allOfTheYelloMonsters.get(j));
			}
			for(int i =0;i<initialPos.size();i++){
				System.out.println("initial pos " + i + " : " + initialPos.get(i));
			}


		} catch (Exception runtimException) {
			System.out.println("RunTime Error!");
		}
		

	}
	public GamePiece getBomberman(){
		return this.bomberman;
	}
	public synchronized ArrayList<GamePiece> getMyArray(){
		return this.myArray;
	}
	public ArrayList<Wall> getWalls(){
		return this.walls;
	}
	public ArrayList<MonsterOne> getGreenMonsters(){
		return this.allOfTheGreenMonsters;
	}
	public ArrayList<MonsterTwo> getYellowMonsters(){
		return this.allOfTheYelloMonsters;
	}
	public ArrayList<MonsterThree> getGhostMonster(){
		return this.allOfTheGhostMonsters;
	}
	public ArrayList<Point> getInitialPositions(){
		return this.initialPos;
	}
	

	public synchronized void draw(Graphics2D g2){
		if(panel.isPaused==false){
		for(int i = 0; i < this.myArray.size(); i++){
			GamePiece gp = this.myArray.get(i);
			gp.draw(g2);
		}
		this.collide();
		if(this.allOfTheGreenMonsters.isEmpty() && this.allOfTheYelloMonsters.isEmpty() &&
				this.allOfTheGhostMonsters.isEmpty()){
			if(this.panel.getMapLevel() != 4){
				int mapLevel = this.panel.getMapLevel() + 1;
				this.panel.setMapLevel(mapLevel);
			} else{
				System.out.println("WINNER!");
			}
		}
		}else{
			System.out.println("Game is paused. Press P to continue.");
		}
	}
	
	
	public synchronized void collide(){
		for(int i = 0; i<myArray.size()-1;i++){
			for(int j = i+ 1; j<myArray.size();j++){
				if(myArray.get(i).getArea()!=null && myArray.get(j).getArea()!=null){
				Area ar1 = myArray.get(i).getArea();
				ar1.intersect(myArray.get(j).getArea());
				if(!ar1.isEmpty()){   //not colliding
					myArray.get(i).collide(myArray.get(j));
					}
				}
			}
		}
	}
	

}
