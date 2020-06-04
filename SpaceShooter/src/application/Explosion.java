package application;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

public class Explosion extends Box{
	DataManager dm = new DataManager();	
	GameVC parentClass;
	public Explosion(int index, GameVC parentClass) {
		this.index = index;
		this.parentClass = parentClass;
		dm.createExploFrames();
		createImageView();
	}
	String imgPath = SVars.PATH+"/img/effects/explosion/Explosion_1_001.png";
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
	
	Timeline exploTime;
	Timeline exploFrameTime;
	int frameCount;
	
	public void startExplo() {
		frameCount = 0;
		exploFrameTime = new Timeline(new KeyFrame(Duration.seconds(0.05), ev ->{
			setExploImg();
			setScaleX(getScaleX()+0.1);
			setScaleY(getScaleY()+0.1);
			
		} ));
		exploFrameTime.setCycleCount(Animation.INDEFINITE);
		exploFrameTime.play();
		
		exploTime = new Timeline(new KeyFrame(Duration.seconds(0.35), ev ->{
			parentClass.removeExplo(this);
			exploTime.stop();
		} ));
		exploTime.setCycleCount(Animation.INDEFINITE);
		exploTime.play();
	}
	

	private void setExploImg() {
		if (frameCount<dm.cntExploFrames()) {
			String str = dm.getExploFrame(frameCount);
			File file = new File(str);
			Image img = new Image(file.toURI().toString());
			imgView.setImage(img);
		}
		else {
			exploFrameTime.stop();
		}
		frameCount += 1;
	}
}
