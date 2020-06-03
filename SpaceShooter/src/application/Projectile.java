package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends Box{
	int index;
	ImageView imgView;
	String projectileImg = SVars.PATH+"/img/projectile/Shot_1_002.png";
	public Projectile(int index) {
		// TODO Auto-generated constructor stub
		this.index = index;
		createImageView();
	}
	//Loading Skin for the Projectiles
	private void createImageView() {
		imgView = new ImageView();
		File file = new File(projectileImg);
		Image img = new Image(file.toURI().toString());
		imgView.setImage(img);
		imgView.setX(0);
		imgView.setY(0);
		getChildren().add(imgView);

	}
	// size of the Projectile skin to the proectile Model
	public void setImgSize() {
		imgView.setFitHeight(getPrefHeight());
		imgView.setFitWidth(getPrefWidth());
		
	}
	
	//Movement of the Projectile
	int speed = 5;
	GameVC target = new GameVC();
	public void moveProjectile() {
		setLayoutY(getLayoutY() - speed);
		if (getLayoutY() <= -600) {
			speed = 0;
			die();
		}
	}
	private void die() {
	
	}
}
