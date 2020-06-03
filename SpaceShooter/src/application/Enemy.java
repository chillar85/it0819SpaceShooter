package application;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Box {

	Random rnd = new Random();
	DataManager dm = new DataManager();
	String imgPath = dm.createAllAstroids().get(rnd.nextInt(8)+1);;
	public Enemy(int index) {
		this.index = index;
		// TODO Auto-generated constructor stub
		createImageView();
	}
	//Loading Skin for Enemies
	ImageView imgView;
	private void createImageView() {
		imgView = new ImageView();
		File file = new File(imgPath);
		Image img = new Image(file.toURI().toString());
		imgView.setImage(img);
		imgView.setX(0);
		imgView.setY(0);
		getChildren().add(imgView);

	}
	//Set Size of Enemies Skin to their Model
	public void setImgSize() {
		imgView.setFitHeight(getPrefHeight());
		imgView.setFitWidth(getPrefWidth());
		
	}

	//Enemies Movement
	int rotateSpeed = rnd.nextInt(3)+1;
	int speed = rnd.nextInt(5)+1;
	public void moveEnemy() {
		setLayoutY(getLayoutY() + speed);
		setRotate(getRotate() - rotateSpeed);
	}
	
	

}
