package application;

import java.util.ArrayList;

public class DataManager {

	public DataManager() {
		// TODO Auto-generated constructor stub
	}
	
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
	
}
