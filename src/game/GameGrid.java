package game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.control.Cell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

public class GameGrid extends Pane {
	private int rows;
	private int cols;
	
	private double gridWidth;
	private double gridHeight;
	
	private boolean running = false;
	
	
	private GameCell[][] cells;
	
	public GameGrid(int rows, int cols, double width, double height) {
		this.rows = rows;
		this.cols = cols;
		this.gridWidth = width;
		this.gridHeight = height;
		
		cells = new GameCell[rows][cols];
		
	}
	
	public void addCell(GameCell cell, int column, int row) {
		cells[row][column] = cell;
		double w = gridWidth / cols;
        double h = gridHeight / rows;
        double x = w * column;
        double y = h * row;
        
        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);
	}
	
	
	
	public void unhighlight() {
        for( int row=0; row < rows; row++) {
            for( int col=0; col < cols; col++) {
                cells[row][col].unhighlight();
            }
        }
    }
	

	public void start() {
	    this.running = true;
	    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	    executorService.scheduleAtFixedRate(() -> {
	        if (!running) {
	            executorService.shutdown();
	            return;
	        }
	        // Compute the next state for all cells
	        for (int row = 0; row < rows; row++) {
	            for (int col = 0; col < cols; col++) {
	                cells[row][col].analyzeNeighbourState(cells);
	            }
	        }
	        
	        // Update the state for all cells
	        for (int row = 0; row < rows; row++) {
	            for (int col = 0; col < cols; col++) {
	                cells[row][col].setAlive(cells[row][col].willBeAlive());
	                //System.out.println("Cell at [" + row + "," + col + "] isAlive: " + cells[row][col].isAlive());
	            }
	        }

	        // Update UI in Platform.runLater
	        Platform.runLater(() -> {
	            for (int row = 0; row < rows; row++) {
	                for (int col = 0; col < cols; col++) {
	                    if (cells[row][col].isAlive()) {
	                        cells[row][col].highlight();
	                    } else {
	                        cells[row][col].unhighlight();
	                    }
	                }
	            }
	        });
	        
	        
	        

	    }, 0,100, TimeUnit.MILLISECONDS); 
	}
	
	
	
	
	public void stop() {
		this.running = false;
	}
	
	public void clear() {
		this.running = false;
		this.cells = new GameCell[rows][cols];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public double getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(double width) {
		this.gridWidth = width;
	}

	public double getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(double height) {
		this.gridHeight = height;
	}

	public GameCell getCell(int column, int row) {
		return cells[row][column];
	}

	
	
}
