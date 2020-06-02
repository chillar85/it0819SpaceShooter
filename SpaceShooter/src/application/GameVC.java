package application;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameVC {
	
    @FXML	private AnchorPane		mainBox;
    @FXML	private Pane			gameBox;
    @FXML	private Pane			projectileBox;
    @FXML	private Pane			menuBox;
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
	
    Player player;
    Timeline timer;
    int loop;
    
    
	public GameVC() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		createPlayer();
		setEvents();
		setAllGameParams();
	}
	
	private void createPlayer() {
		// TODO Auto-generated method stub
		player = new Player(1);
      	player.setLayoutX(400);
      	player.setLayoutY(400);
      	player.setPrefHeight(75);
      	player.setPrefWidth(100);
      	
      	gameBox.getChildren().add(player);
      	player.setImgSize();
      	
	}
	private void setEvents() {
		fireprojectileObj();
		
	}
	private void setAllGameParams() {
    	mainBox.setCursor(Cursor.NONE);
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), ev -> {
    		player.movePlayer();

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
    		if (loop% 12 == 0) {
    			shooting();
			}
    		for (Projectile projectile  : projectileList) {
				projectile.moveProjectile();

			}
    			
    		
    		loop += 1; 
    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();
    }
	
	boolean shoot;
	int shootCnt = 0;
    public void fireprojectileObj() {
		// TODO Auto-generated method stub
		mainBox.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Released");
				shoot = false;
			}
		});
		mainBox.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Pressed");
				shoot = true;
			}
		});
	}
    ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    private void shooting() {
		// TODO Auto-generated method stub
    	if (shoot) {
    		shootCnt +=1;
			Projectile projectile = new Projectile(shootCnt);
			projectile.setPrefHeight(80);
			projectile.setPrefWidth(40);
			if (shootCnt %2 == 0) {
				projectile.setLayoutX(player.getLayoutX()+17);
				projectile.setLayoutY(player.getLayoutY()-40);
			}
			else {
				projectile.setLayoutX(player.getLayoutX()+43);
				projectile.setLayoutY(player.getLayoutY()-40);
			}

			projectileBox.getChildren().add(projectile);
			projectile.setImgSize();
			projectileList.add(projectile);
		}
    	
	}
    public void removeProjectile(Projectile t) {
    	projectileBox.getChildren().remove(t);
    	projectileList.remove(t);
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
