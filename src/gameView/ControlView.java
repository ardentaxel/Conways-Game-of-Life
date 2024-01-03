package gameView;

import game.GameWorld;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ControlView extends VBox{
	private ControlBtn startBtn;
	private ControlBtn stopBtn;
	private ControlBtn clearGridBtn;
	private Label title;
	private GameWorld game;
	private BorderPane root;
	
	
	public ControlView(GameWorld game, BorderPane root) {
		
		this.game = game;
		this.root = root;
		
		setAlignment(Pos.CENTER);
		
		this.title = new Label("CONWAY'S GAME OF LIFE");
		this.title.setAlignment(Pos.TOP_CENTER);
		this.title.setMaxWidth(Double.MAX_VALUE);
		this.title.getStyleClass().add("title");
		
		this.stopBtn = new ControlBtn("Stop");
		this.stopBtn.setOnAction(e -> {
			this.game.stop();
		});
		this.startBtn = new ControlBtn("Start");
		this.startBtn.setOnAction(e -> {
			this.game.start();
		});
		this.clearGridBtn = new ControlBtn("Clear Grid");
		this.clearGridBtn.setOnAction(e -> {
			this.game.clearGrid();
			this.root.setRight(this.game.load());
		});
		
		VBox description = new VBox();
		Text intro = new Text("The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. ");
		intro.setTextAlignment(TextAlignment.JUSTIFY);
		intro.setWrappingWidth(280);
		intro.getStyleClass().add("description");
		intro.setFill(Color.web("#00FF00"));
		intro.setFont(new Font(14));
		
		Text rules = new Text("\t\t\t\t\t   Rules: \n\n"
				+ "1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.\n\n"
				+ "2. Any live cell with two or three live neighbours lives on to the next generation.\n\n"
				+ "3. Any live cell with more than three live neighbours dies, as if by overpopulation.\n\n"
				+ "4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\n\n"
				+ "~ From Wikipedia, the free encyclopedia");
		rules.setTextAlignment(TextAlignment.JUSTIFY);
		rules.setWrappingWidth(280);
		rules.getStyleClass().add("description");
		rules.setFill(Color.web("#00FF00"));
		rules.setFont(new Font(14));
		
		description.getChildren().addAll(intro, rules);
		description.getStyleClass().add("description-container");
		description.setAlignment(Pos.CENTER);
		
		
		HBox startStopContainer = new HBox(4);
		startStopContainer.getChildren().addAll(this.startBtn, this.stopBtn);
		startStopContainer.setAlignment(Pos.CENTER);
		
		
		getChildren().addAll(this.title,description,startStopContainer, this.clearGridBtn);
		setPrefWidth(300);
		getStyleClass().add("control");
		
		setSpacing(15);
	}
}
