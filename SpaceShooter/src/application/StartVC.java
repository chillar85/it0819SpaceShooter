package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.print.DocFlavor.STRING;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartVC {
	
    @FXML	private AnchorPane		mainBox;
    //Login-Form GFX
    @FXML	private Pane			loginBox;
    @FXML	private TextField 		txtUsername;
    @FXML	private PasswordField	txtPassword;
    @FXML	private Button 			btnLogin;
    @FXML	private Button 			btnRegister;
    
    
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

    
    String imgPath;
    File file;
    ImageView background;
    ArrayList<ImageView> backgroundList = new ArrayList<ImageView>();
    boolean login = false;
    Timeline timer;
    Random rnd = new Random();
    
    public StartVC() {
		// TODO Auto-generated constructor stub
	}
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
    }

    private void setAllEvents() {
    	btnLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				sendDataToHttp(txtUsername.getText(),txtPassword.getText());
				if (serverString.equalsIgnoreCase("no") == false) {
					System.out.println("Login ok");
					SVars.userName = txtUsername.getText();
					goToGameView();
				}
				else {
					System.out.println("Login falsch");
					txtUsername.requestFocus();
				}		
			}
		});
    	btnRegister.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				goToRegisterView();
			}
		});
		
	}
    String serverString;
    
	public void sendDataToHttp(String user, String pass)  {

		System.out.println("3. HTTP:load from server http");
		// TODO Auto-generated method stub
	    //1.hole Daten von Http mit Inputstream
		try {
			//System.out.println(readUrl("http://127.0.0.1:8080/java/getAllPlaces.php"));
//			String serverString = readUrl("http://127.0.0.1/java/Login.php?u=Alex&p=123456");
			serverString = readUrl("http://127.0.0.1/java/Login.php?u=" + user + "&p="+ pass);
		
			System.out.println(serverString);	
			serverString.isEmpty();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2. wandel die Daten in das gegebene Format
		//(XML,Json,Eigenes)
	    //3. sage DataManager bescheid 
		//wenn alles fertig ist
		System.out.println("4. HTTP:load finished");
	}

	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
	
	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	    
	}    
    
    private void goToRegisterView() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Stage stage = (Stage) mainBox.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
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
