package game;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameWorld{
	
	private final int ROWS = 40;
	private int COLUMNS = 40;
	private double WIDTH = 800;
	private double HEIGHT = 600;
	
	
	private GameGrid grid;
	
	private static StackPane game;
	
	
	
	public StackPane load() {
		this.game = new StackPane();

        // create grid
        this.grid = new GameGrid( ROWS, COLUMNS, WIDTH, HEIGHT);
        
        GameCellPlotter plotter = new GameCellPlotter(true);
        
        // fill grid
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                GameCell cell = new GameCell(c, r);

                grid.addCell(cell, c, r);
                
                plotter.makePaintable(cell);
            }
        }
        
        game.getChildren().addAll(grid);
        game.getStyleClass().add("world");
        return game;
	}
	
	
	
	
	public void clearGrid() {
		this.grid.clear();
	}
	
	public void start() {
		this.grid.start();
	}
	
	public void stop() {
		this.grid.stop();
	}
	
	
	


}
