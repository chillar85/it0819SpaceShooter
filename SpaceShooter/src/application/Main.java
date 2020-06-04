package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override

	public void start(Stage primaryStage) {
		try {
			File file = new File(SVars.PATH + "/img/icon/icon.png");
			Image icon = new  Image(file.toURI().toString());
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("End.fxml"));
			Scene scene = new Scene(root,790,590);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setIconified(false);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Space Shooter");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
