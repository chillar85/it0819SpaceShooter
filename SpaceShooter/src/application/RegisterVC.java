package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RegisterVC {
	//Main GFX
    @FXML	private AnchorPane			mainBox;
    //Register-Form GFX
    @FXML	private Pane				loginBox;
    @FXML	private TextField 			txtUsername;
    @FXML	private PasswordField		txtPassword;
    @FXML	private Button 				btnRegister;
    @FXML	private Button 				btnCancel;
    @FXML   private ComboBox<String>	comboLocation;
    @FXML   private ComboBox<String>	comboProfilePicture;
    
    //Background GFX
    @FXML	private Pane				backgroundLayer1_1;
    @FXML	private Pane				backgroundLayer1_2;
    @FXML	private Pane				backgroundLayer2_1;
    @FXML	private Pane				backgroundLayer2_2;
    @FXML	private Pane				backgroundLayer3_1;
    @FXML	private Pane				backgroundLayer3_2;
    @FXML	private Pane				backgroundLayer4_1;
    @FXML	private Pane				backgroundLayer4_2;

    
    
    ImageView background;
    ArrayList<ImageView> backgroundList = new ArrayList<ImageView>();
    boolean login = false;
    Timeline timer;
    public RegisterVC() {
		// TODO Auto-generated constructor stub
	}
    int loop = 0;
    public void initialize(){
    	mainBox.requestFocus();
    	//
    	comboLocation.getItems().addAll("Test1", "Test2","Test3","Test4");
    	comboProfilePicture.getItems().addAll("Test1", "Test2","Test3","Test4");
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
    		moveAllBackground(loop);
    		loop += 1;    	
    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
    	timer.play();
 
    	setAllEvents();
    }

    private void setAllEvents() {
		// TODO Auto-generated method stub
    	btnRegister.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (register() == false) {
					
				}
				else {
					goToStartView();
				}
			}
		});
    	btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				goToStartView();
			}
		});
	}
    private boolean register() {
    	//To-Do: Abfrage ob Username schon vorhanden, wenn ja: neuen Username wählen lassen| wenn nein: Datensatz in Datenbank speichern
		return true;
	}
    
    private void goToStartView() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Stage stage = (Stage) mainBox.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

	}
    
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
    	backgroundLayer1_1.setLayoutY(backgroundLayer1_1.getLayoutY()+1);
    	backgroundLayer1_2.setLayoutY(backgroundLayer1_2.getLayoutY()+1);
    	
    }
    private void moveBackgroundLayer2() {
    	if (backgroundLayer2_1.getLayoutY() == 1200) {
    		backgroundLayer2_1.setLayoutY(-1200);
    		
		}
    	if (backgroundLayer2_2.getLayoutY() == 1200) {
    		backgroundLayer2_2.setLayoutY(-1200);
		}
    	backgroundLayer2_1.setLayoutY(backgroundLayer2_1.getLayoutY()+1);
    	backgroundLayer2_2.setLayoutY(backgroundLayer2_2.getLayoutY()+1);
    	
    }
    private void moveBackgroundLayer3() {
    	if (backgroundLayer3_1.getLayoutY() == 1200) {
    		backgroundLayer3_1.setLayoutY(-1200);
		}
    	if (backgroundLayer3_2.getLayoutY() == 1200) {
    		backgroundLayer3_2.setLayoutY(-1200);
		}
    	backgroundLayer3_1.setLayoutY(backgroundLayer3_1.getLayoutY()+1);
    	backgroundLayer3_2.setLayoutY(backgroundLayer3_2.getLayoutY()+1);
    	
    }
    private void moveBackgroundLayer4() {
    	if (backgroundLayer4_1.getLayoutY() == 1200) {
    		backgroundLayer4_1.setLayoutY(-1200);
		}
    	if (backgroundLayer4_2.getLayoutY() == 1200) {
    		backgroundLayer4_2.setLayoutY(-1200);
		}
    	backgroundLayer4_1.setLayoutY(backgroundLayer4_1.getLayoutY()+1);
    	backgroundLayer4_2.setLayoutY(backgroundLayer4_2.getLayoutY()+1);
    	
    }

}
