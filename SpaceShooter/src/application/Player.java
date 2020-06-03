package application;

import java.io.File;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Player extends Box{

	private String playerImg = SVars.PATH+"/img/player/ship_level_1_01.png";
	private int playerHealth = 3;
	
	
	public Player(int index) {
		this.index = index;
		createImageView();
		// TODO Auto-generated constructor stub
	}
	
	//Loading Player Skin
	ImageView imgView;
	private void createImageView() {
		imgView = new ImageView();
		File file = new File(playerImg);
		Image img = new Image(file.toURI().toString());
		imgView.setImage(img);
		imgView.setX(0);
		imgView.setY(0);
		getChildren().add(imgView);

	}
	//set Size of PlayerSkin to Player Model
	public void setImgSize() {
		imgView.setFitHeight(getPrefHeight());
		imgView.setFitWidth(getPrefWidth());
		
	}	

	//Movement for the Player
    public void movePlayer() {
		// TODO Auto-generated method stub
    		getParent().getParent().setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setLayoutX(event.getSceneX());
				setLayoutY(event.getSceneY());
			}});
    		getParent().getParent().setOnMouseDragged(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setLayoutX(event.getSceneX());
				setLayoutY(event.getSceneY());

			}});
	}
   
}
