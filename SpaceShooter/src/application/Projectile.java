package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends Box{
	private int index;
	private ImageView imgView;
	private String projectileImg = SVars.PATH+"/img/projectile/Shot_1_002.png";
	private double startPosY;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getStartPosY() {
		return startPosY;
	}
	public void setStartPosY(double startPosY) {
		this.startPosY = startPosY;
	}
	public Projectile(int index, double startPos) {
		this.index = index;
		this.startPosY = startPos;
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
	//size of the Projectile skin to the projectile Model
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
		}
	}
}
