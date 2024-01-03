package game;

import java.util.Iterator;

import javafx.scene.layout.StackPane;

public class GameCell extends StackPane{
	
	private int col;
	private int row;
	private boolean isAlive;  // alive - true or dead - false
	private boolean willBeAlive;
	private Position cellPos;
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean state) {
		this.isAlive = state;
	}
	
	public boolean willBeAlive() {
		return this.willBeAlive;
	}

	public GameCell(int col, int row) {
		this.col = col;
		this.row = row;
		this.cellPos = new Position(this.row, this.col);
		
		getStyleClass().add("cell");
	}
	
	
	public void analyzeNeighbourState(GameCell[][] cells) {


		int liveNeighbours = countLiveNeighbours(cells);

		if((this.isAlive == true) && (countLiveNeighbours(cells) < 2 || countLiveNeighbours(cells) > 3)) {
			this.willBeAlive = false;					
		}
		
		if(isAlive) {
			// Rule 1: Any live cell with fewer than two live neighbours dies, as if by underpopulation.
			// Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
			// Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
			this.willBeAlive = liveNeighbours == 2 || liveNeighbours == 3;
		} else {
			// Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
			this.willBeAlive = liveNeighbours == 3;
		}
		
		
	}
	
	public int countLiveNeighbours(GameCell[][] cells) {
		
		
		int liveNeighboursCount = 0;
		
		for(int x = cellPos.getXPos()-1; x <= cellPos.getXPos()+1; x++) {
			for(int y = cellPos.getYPos()-1; y <= cellPos.getYPos()+1; y++) {
				if (x == cellPos.getXPos() && y == cellPos.getYPos()) {
					continue;
				}
				if (cellInBounds(cells, x, y) && cells[x][y].isAlive) {
	                liveNeighboursCount++;
	            }
			}
		}
		
		return liveNeighboursCount;
	}
	
	public boolean cellInBounds(GameCell[][] cells, int x, int y) {
		if((x >= 0 && x < cells.length) && (y >= 0 && y < cells[x].length)) {
			return true;
		}
		return false;
	}
	
	
	
	
	public void highlight() {
        // ensure the style is only once in the style list
        getStyleClass().remove("cell-highlight");

        // add style
        getStyleClass().add("cell-highlight");
        
        this.isAlive = true;
    }

    public void unhighlight() {
        getStyleClass().remove("cell-highlight");
        
        this.isAlive = false;
    }
    
    public void hoverHighlight() {
        // ensure the style is only once in the style list
        getStyleClass().remove("cell-hover-highlight");

        // add style
        getStyleClass().add("cell-hover-highlight");
    }
    
    public void hoverUnhighlight() {
        getStyleClass().remove("cell-hover-highlight");
    }

	
	public String getCellInfo() {
		return "Cell Info [col=" + col + ", row=" + row + ", alive=" + isAlive + "]";
	}

	
    
    

   
}
