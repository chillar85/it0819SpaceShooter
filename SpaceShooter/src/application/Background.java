package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Background extends Pane{

	ImageView img1;
	ImageView img2;
	Pane pane1;
	Pane pane2;

	String imgPath = SVars.PATH +"/img/background/";
	String bg = "BG.png";
	String meteors = "Meteors.png";
	String planets = "Planets.png";
	String stars = "stars.png";
	public Background() {
		// TODO Auto-generated constructor stub
		createBackgroundLayer(bg);
		createBackgroundLayer(stars);
		createBackgroundLayer(planets);
		createBackgroundLayer(meteors);
	}

	private void createBackgroundLayer(String layer) {
		Pane pane = new Pane();
		pane1 = new Pane();
		pane1.setPrefHeight(1200);
		pane1.setPrefWidth(800);
		pane2 = new Pane();
		pane2.setPrefHeight(1200);
		pane2.setPrefWidth(800);
		img1 = new ImageView();
		img2 = new ImageView();
		File file = new File(imgPath+ "Space_BG_01/" +layer);
		img1.setImage(new Image(file.toURI().toString()));
		img1.setFitHeight(pane1.getPrefHeight());
		img1.setFitWidth(pane1.getPrefWidth());
		img1.setPreserveRatio(false);
		img2.setImage(new Image(file.toURI().toString()));
		img2.setPreserveRatio(false);
		pane1.getChildren().add(img1);
		pane2.getChildren().add(img2);
		pane.getChildren().add(pane1);
		pane.getChildren().add(pane2);
		
		
	}
	
    @SuppressWarnings("unused")
	private void moveBackgroundLayer() {
    	if (pane1.getLayoutY() == 1200) {
    		pane2.setLayoutY(-1200);
		}
    	if (pane1.getLayoutY() == 1200) {
    		pane2.setLayoutY(-1200);
		}
    	pane1.setLayoutY(this.getLayoutY()+1);
    	pane2.setLayoutY(this.getLayoutY()+1);
    }
}
