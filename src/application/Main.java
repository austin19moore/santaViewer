package application;

/**
 * @author Austin Moore
 * 
 * Main.java, to make and display stage/scenecircle from Main.fxml
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Create stage
			stage = primaryStage;
			
			// Connect to the FXML, load it in
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("/application/view/Main.fxml") );
			AnchorPane layout = (AnchorPane) loader.load();
			
			// Put the layout onto the scene
			Scene scene = new Scene(layout, 800, 800);
			
			// Set the scene on the stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lab6");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
