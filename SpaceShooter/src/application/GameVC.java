package application;

import java.util.ArrayList;
import java.util.Random;

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
    Random rnd = new Random();
    CollisionManager cm = new CollisionManager();
    
    
	public GameVC() {
		// TODO Auto-generated constructor stub
	}

	public void initialize() {
		createPlayer();
		setEvents();
		setAllGameParams();
	}
	

	private void setEvents() {
		fireprojectileObj();
		
	}
	private void setAllGameParams() {
    	mainBox.setCursor(Cursor.NONE);
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), ev -> {
    		player.movePlayer();
    		if (loop == 0 || loop%50 == 0) {
				createEnemy(enemyCnt);
			}
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
    		for (Projectile projectile  : projectileList) {
				projectile.moveProjectile();

			}
    		for (Enemy enemy : enemyList) {
				enemy.moveEnemy();
			}
    		for (Enemy enemy : enemyList) {
				for (Projectile projectile : projectileList) {
					if (cm.checkBoundsProjectile(projectile, enemy)) {
						System.out.println("Enemy Hit");
						projectileList.remove(projectile);
						projectileBox.getChildren().remove(projectile);
						enemyList.remove(enemy);
						gameBox.getChildren().remove(enemy);
						break;				
					}
				}
			}
    		for (Enemy enemy : enemyList) {
				if (cm.checkBoundsPlayer(player, enemy)) {
					System.out.println("Player Hit");
				}
			}
    			
    		
    		loop += 1; 
    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();
    }
	//======= Creating Player ========
	private void createPlayer() {
		// TODO Auto-generated method stub
		player = new Player(0);
      	player.setLayoutX(400);
      	player.setLayoutY(400);
      	player.setPrefHeight(75);
      	player.setPrefWidth(100);
     	//Shot Hitbox
      	//player.setStyle("-fx-border-color: white");
       	gameBox.getChildren().add(player);
      	player.setImgSize();
      	
	}
	//======= Creating Projectiles for Player
	boolean shoot;
	int shootCnt = 0;
	Timeline projectileTimer;
	int loopShooting;
	//Events for shooting when mouse Pressed
    public void fireprojectileObj() {
		// TODO Auto-generated method stub
		mainBox.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Released");
				shoot = false;
				projectileTimer.stop();
			}
		});

		mainBox.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Pressed");
				shoot = true;
				loopShooting = 0;
				projectileTimer = new Timeline(new KeyFrame(Duration.seconds(0.02), ev -> {
					if (loopShooting == 0 || loopShooting % 12 == 0) {;
						shooting();
					}
					loopShooting += 1;
				}));
				projectileTimer.setCycleCount(Animation.INDEFINITE);
				projectileTimer.play();
			}
		});
	}
    //Check if mouse is Pressed to create Projectiles and shoot them
    ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    private void shooting() {
		// TODO Auto-generated method stub
    	if (shoot) {
    		
			Projectile projectile = new Projectile(shootCnt);
			projectile.setPrefHeight(30);
			projectile.setPrefWidth(15);
			if (shootCnt %2 == 0) {
				projectile.setLayoutX(player.getLayoutX()+25);
				projectile.setLayoutY(player.getLayoutY());
			}
			else {
				projectile.setLayoutX(player.getLayoutX()+55);
				projectile.setLayoutY(player.getLayoutY());
			}

			projectileBox.getChildren().add(projectile);
			projectile.setImgSize();
			//Shot Hitbox
	    	//projectile.setStyle("-fx-border-color: white");
			projectileList.add(projectile);
			shootCnt +=1;
		}
    
    	
	}
    public void removeProjectile(Projectile t) {
    	projectileBox.getChildren().remove(t);
    	projectileList.remove(t);
    }
    
    //====== Creating Enemies ========
    int enemyCnt = 0;
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private void createEnemy(int index) {
		// TODO Auto-generated method stub
    	Enemy enemy = new Enemy(index);
    	enemy.setLayoutX(rnd.nextInt(600)+100);
    	enemy.setLayoutY(-200);
    	enemy.setPrefHeight(rnd.nextInt(75+-50)+50+1);
    	enemy.setPrefWidth(rnd.nextInt(75+-50)+50+1);
    	gameBox.getChildren().add(enemy);
    	enemy.setImgSize();
    	//Shot Hitbox
    	//enemy.setStyle("-fx-border-color: white");
    	enemyList.add(enemy);
    	enemyCnt += 1;
    	
	}
	
	
	
    
    
    //======== Background Parallax ========
	
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
