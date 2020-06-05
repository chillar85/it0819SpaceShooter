package application;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EndVC {
	
    @FXML	private AnchorPane		mainBox;
    
    //HighScoreList GFX
    @FXML 	private Pane 			scoreBox; 
    @FXML 	private ImageView		imgVictory; 
    @FXML 	private ImageView		imgLoose; 
    @FXML 	private Label			lblScore; 
    @FXML 	private Button 			btnPlayAgain; 
    
    //Background GFX
    @FXML	private Pane			backgroundLayer1_1;
    @FXML	private Pane			backgroundLayer1_2;
    @FXML	private Pane			backgroundLayer2_1;
    @FXML	private Pane			backgroundLayer2_2;
    @FXML	private Pane			backgroundLayer3_1;
    @FXML	private Pane			backgroundLayer3_2;
    @FXML	private Pane			backgroundLayer4_1;
    @FXML	private Pane			backgroundLayer4_2;
    @FXML	private ImageView 		backgroundLayerImg1_1;
    @FXML	private ImageView 		backgroundLayerImg1_2;
    @FXML	private ImageView 		backgroundLayerImg2_1;
    @FXML	private ImageView 		backgroundLayerImg2_2;
    @FXML	private ImageView 		backgroundLayerImg3_1;
    @FXML	private ImageView 		backgroundLayerImg3_2;
    @FXML	private ImageView 		backgroundLayerImg4_1;
    @FXML	private ImageView 		backgroundLayerImg4_2;
    
    public EndVC() {
		// TODO Auto-generated constructor stub
	}
    
    Timeline timer;
    
    int loop = 0;
    public void initialize(){
    	mainBox.requestFocus();
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
    		moveAllBackground(loop);
    		loop += 1; 

    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
    	timer.play();
    	setAllEvents();
    	lblScore.setText(SVars.userName+ " got " +SVars.score +" Points");
    	
    }
    
    private void setAllEvents() {
		// TODO Auto-generated method stub
    	btnPlayAgain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				goToGameView();
			}
		});
    	showVictory();
	}
    
    private void showVictory() {
    	if (SVars.victory) {
			imgVictory.setVisible(true);
		}
    	if (SVars.victory == false) {
			imgLoose.setVisible(true);
		}
    }
    private void goToGameView() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Stage stage = (Stage) mainBox.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

	}
    
    
    //======== Background Parallax ========
    private void moveAllBackground(int loop) {
		// TODO Auto-generated method stub
    	if (loop%5==0) {
			moveBackgroundLayer1();
		}
		if (loop%2==0) {
			moveBackgroundLayer2();
		}
		if (loop%4==0) {
			moveBackgroundLayer3();
		}
		if (loop%1==0) {
			moveBackgroundLayer4();
		}
	}
    private void moveBackgroundLayer1() {
    	if (backgroundLayer1_1.getLayoutY() == 1200) {
    		backgroundLayer1_1.setLayoutY(-1200);

		}
    	if (backgroundLayer1_2.getLayoutY() == 1200) {
    		backgroundLayer1_2.setLayoutY(-1200);
		}
    	backgroundLayer1_1.setLayoutY(backgroundLayer1_1.getLayoutY()+0.1);
    	backgroundLayer1_2.setLayoutY(backgroundLayer1_2.getLayoutY()+0.1);
    	
    }
    private void moveBackgroundLayer2() {
    	if (backgroundLayer2_1.getLayoutY() == 1200) {
    		backgroundLayer2_1.setLayoutY(-1200);
   
		}
    	if (backgroundLayer2_2.getLayoutY() == 1200) {
    		backgroundLayer2_2.setLayoutY(-1200);

		}
    	backgroundLayer2_1.setLayoutY(backgroundLayer2_1.getLayoutY()+0.2);
    	backgroundLayer2_2.setLayoutY(backgroundLayer2_2.getLayoutY()+0.2);
    	
    }
    private void moveBackgroundLayer3() {
    	if (backgroundLayer3_1.getLayoutY() == 1200) {
    		backgroundLayer3_1.setLayoutY(-1200);
		}
    	if (backgroundLayer3_2.getLayoutY() == 1200) {
    		backgroundLayer3_2.setLayoutY(-1200);

		}
    	backgroundLayer3_1.setLayoutY(backgroundLayer3_1.getLayoutY()+0.25);
    	backgroundLayer3_2.setLayoutY(backgroundLayer3_2.getLayoutY()+0.25);
    	
    }
    private void moveBackgroundLayer4() {
    	if (backgroundLayer4_1.getLayoutY() == 1200) {
    		backgroundLayer4_1.setLayoutY(-1200);

		}
    	if (backgroundLayer4_2.getLayoutY() == 1200) {
    		backgroundLayer4_2.setLayoutY(-1200);

		}
    	backgroundLayer4_1.setLayoutY(backgroundLayer4_1.getLayoutY()+0.5);
    	backgroundLayer4_2.setLayoutY(backgroundLayer4_2.getLayoutY()+0.5);
    	
    }
}
