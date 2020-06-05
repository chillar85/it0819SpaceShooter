package application;

import java.util.ArrayList;
import java.util.Random;

public class DataManager {

	Random rnd = new Random();
	int exploType = rnd.nextInt(3)+1;
	public DataManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	//Setting up different Enemy Textures
	public void createAllEnemies(){
		createAllAstroids();		

	

	}

	ArrayList<String> astroidList = new ArrayList<String>();
	public ArrayList<String> createAllAstroids() {
		for (int i = 0; i < 9; i++) {
			astroidList.add(SVars.PATH+"/img/enemy/meteor/Meteor_0"+i+".png");
		}
		return astroidList;
	}
	public int cntAstroidList() {return astroidList.size();}
	public String get(int index){ return exploFrames.get(index);}
	
	//======= Setting up different Explosion textures
	ArrayList<String> exploFrames = new ArrayList<String>();
	public ArrayList<String> createExploFrames(){
		for (int i = 1; i <= 7; i++) {
			exploFrames.add(SVars.PATH+"/img/effects/explosion/Explosion_"+exploType+"_00"+i+".png");
		}
		return exploFrames;
	}
	public int cntExploFrames() {
		return exploFrames.size();
	}
	public String getExploFrame(int index){ return exploFrames.get(index);}
}
