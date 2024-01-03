package gameView;

import game.GameWorld;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView extends Application{
	// This is the entry point of the application
	private GameWorld game;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root);
		
		this.game = new GameWorld();
		
		ControlView controls = new ControlView(game,root);
		
		root.setLeft(controls);
		root.setCenter(game.load());
		
		
        scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
        
        
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();
	}

}
