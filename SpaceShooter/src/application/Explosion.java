package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Explosion extends Box{

	public Explosion(int index) {
		// TODO Auto-generated constructor stub
		this.index = index;
		createImageView();
	}
	String imgPath;
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
}
