package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameVC {
	
    @FXML	private AnchorPane		mainBox;
    @FXML	private Pane			gameBox;
    @FXML	private Pane			projectileBox;
    @FXML	private Pane			menuBox;
    
    //GUI GFX
    @FXML   private ProgressBar 	proBarHealth;
    @FXML   private TextField 		txtTime; 
    @FXML   private TextField 		txtScore;  
    
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
	
    Player player;
    Timeline timer;
    int loop;
    Random rnd = new Random();
    CollisionManager cm = new CollisionManager();
    
    
	public GameVC() {
	}

	public void initialize() {
		createPlayer();
		setEvents();
		setAllGameParams();
		playBGM(1);
	}
	

	private void setEvents() {
		fireprojectileObj();
		
	}
	private void setAllGameParams() {
		int time = 100;
		int score = 0;
		txtTime.setText(Integer.toString(time));
		txtScore.setText(Integer.toString(score));
    	mainBox.setCursor(Cursor.NONE);
    	timer = new Timeline(new KeyFrame(Duration.seconds(0.02), ev -> {
    		player.movePlayer();
    		spawnEnemy(loop);
    		gameTime(loop);
    		moveAllBackground(loop);
    		moveAllProjectile();
    		moveAllEnemy();
    		checkCollisionAll();		
    		goToEndScene();
    		changeBGM(2,loop);
    		loop += 1; 
    		
    		
    	}));
    	timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();
    }
	//======= Creating Player ========
	private void createPlayer() {
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
		mainBox.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (shoot) {
					shoot = false;
					projectileTimer.stop();
				}
			}
		});

		mainBox.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown()) {
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
				if (event.isSecondaryButtonDown()) {
					
				}
				if (event.isMiddleButtonDown()) {
					
				}


			}
		});
	}
    //Check if mouse is Pressed to create Projectiles and shoot them
    ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    private void shooting() {
    	if (shoot) {
    		
			Projectile projectile = new Projectile(shootCnt, player.getLayoutY());
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
			File file = new File(SVars.PATH+"/sound/laser_01.mp3");
			AudioClip laser = new AudioClip(file.toURI().toString());
			laser.setVolume(0.05);
			laser.play();
			shootCnt +=1;
		}    	
	}
    
    //Move All current Projectiles
    private void moveAllProjectile(){
		for (Projectile projectile  : projectileList) {
			projectile.moveProjectile();

		}
    }
    
    //====== Creating Enemies ========
    int enemyCnt = 0;
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private void createEnemy(int index) {
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
    //Spawning Intervall for Enemies
    private void spawnEnemy(int loop) {
		if (loop == 0 || loop%50 == 0) {
			createEnemy(enemyCnt);
		}
		if (loop >= 1200 && loop % 100 == 0) {
			createEnemy(enemyCnt);
		}
		if (loop >= 3000 && loop % 100 == 0) {
			createEnemy(enemyCnt);
		}
	}
    
    //Move all current Enemy
    private void moveAllEnemy() {
		for (Enemy enemy : enemyList) {
			enemy.moveEnemy();
		}
	}
     
    //========Checking for Collisions========
    private void checkCollisionAll() {
		boolean collision = false;
		for (Enemy enemy : enemyList) {
			for (Projectile projectile : projectileList) {
				if (projectile.getLayoutY() <= 0 || projectile.getLayoutY() <= projectile.getStartPosY()-mainBox.getPrefHeight()/2) {
					projectileList.remove(projectile);
					projectileBox.getChildren().remove(projectile);
					break;
				}
				if (cm.checkBoundsProjectile(projectile, enemy)) {
					projectileList.remove(projectile);
					projectileBox.getChildren().remove(projectile);
					enemyList.remove(enemy);
					gameBox.getChildren().remove(enemy);
					collision = true;
					score(enemy);
					explosion(enemy);
					break;	
				}
			}
			if (collision) {
				break;
			}
		}
		for (Enemy enemy : enemyList) {
			if (cm.checkBoundsPlayer(player, enemy)) {
				explosion(enemy);
				enemyList.remove(enemy);
				gameBox.getChildren().remove(enemy);
				proBarHealth.setProgress(proBarHealth.getProgress() - 0.1);
				break;
			}
		}
	}   
    ArrayList<Explosion> exploList = new ArrayList<Explosion>();
    int cntexplo = 0;
    private void createExplosion(Enemy enemy) {
    	Explosion explo = new Explosion(cntexplo, this);
    	explo.setPrefHeight(enemy.getPrefHeight());
    	explo.setPrefWidth(enemy.getPrefWidth());
    	explo.setLayoutX(enemy.getLayoutX());
    	explo.setLayoutY(enemy.getLayoutY());
    	gameBox.getChildren().add(explo);
    	explo.setImgSize();
    	exploList.add(explo);
    	cntexplo += 1;
    	explo.startExplo();
    }
    private void explosion(Enemy enemy){
    	createExplosion(enemy);
    	File file = new File(SVars.PATH+"/sound/explosion_0"+(rnd.nextInt(2)+1)+".mp3");
		AudioClip explo = new AudioClip(file.toURI().toString());
		explo.setVolume(0.1);
		explo.play();
    	
    }
    public void removeExplo(Explosion explo) {
    	gameBox.getChildren().remove(explo);
    	exploList.remove(explo);
	}
    
    
    //======== Setting up Menu and methods for Menu
    private void gameTime(int loop) {
    	if (loop % 50 == 0) {
        	int time = Integer.valueOf(txtTime.getText());
        	time -=1;
        	txtTime.setText(Integer.toString(time));
		}

	}
    private void score(Enemy enemy) {
		int score = Integer.valueOf(txtScore.getText());
		score += (enemy.getPrefWidth() + enemy.getPrefHeight()) * (enemy.getSpeed() - enemy.getRotateSpeed() + 1) ;
		txtScore.setText(Integer.toString(score));
	}
    
    private void goToEndScene() {
    	if (Integer.valueOf(txtTime.getText()) <= 0 || proBarHealth.getProgress() <= 0.0001) {
    		if (Integer.valueOf(txtTime.getText()) <= 0 ) {
				SVars.victory = true;
			}
    		else {
				SVars.victory = false;
			}
    		timer.stop();
    		bgm.stop();
			
			SVars.score = txtScore.getText();
	    	try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("End.fxml"));
	            Stage stage = (Stage) mainBox.getScene().getWindow();
	            Scene scene = new Scene(loader.load());
	            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            stage.setScene(scene);
	        }catch (IOException io){
	            io.printStackTrace();
	        }
		}
	}
    
    //======== BGM

    //Play the first BGM
    MediaPlayer bgm;
    ArrayList<MediaPlayer> bgmList = new ArrayList<MediaPlayer>();
    private void playBGM(int bgmNumber) {
		// TODO Auto-generated method stub
    	   File bgmFile = new File(SVars.PATH+"/sound/bgm_0"+bgmNumber+".mp3");
    	    Media media = new Media(bgmFile.toURI().toString());
    	    bgm = new MediaPlayer(media);
    	    bgm.setVolume(0.1);
    	    bgm.setCycleCount(MediaPlayer.INDEFINITE);
     	    bgm.play();
     	    
	}
    //play the second BGM after 60 Seconds
    private void changeBGM(int bgmNumber, int loop) {
		// TODO Auto-generated method stub
    	if (loop == 3000) {
    		bgm.stop();
        	playBGM(bgmNumber);
		}
    	if (loop == 2900 || loop == 2950 || loop == 2975) {
			bgm.setVolume(bgm.getVolume()-0.025);
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
