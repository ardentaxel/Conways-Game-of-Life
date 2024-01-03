package game;

import java.util.ArrayList;

public class Position {
	private int xPos;
	private int yPos;
	
	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public ArrayList<Position> neighbours() {
		ArrayList<Position> neighbours = new ArrayList<Position>(0);
		
		neighbours.add(getTopLeft());
		neighbours.add(getTop());
		neighbours.add(getTopRight());
		
		neighbours.add(getLeft());
		neighbours.add(getRight());
		
		neighbours.add(getBottomLeft());
		neighbours.add(getBottom());
		neighbours.add(getBottomRight());
		
		return neighbours;
	}
	
	public Position getTopLeft() {
		int topLeftX = xPos - 1;
		int topLeftY = yPos - 1;
		return new Position(topLeftX, topLeftY);
	}
	
	public Position getTop() {
		int topX  = xPos - 1;
		int topY = yPos;
		return new Position(topX, topY);
	}
	
	public Position getTopRight() {
		int topRightX  = xPos - 1;
		int topRightY = yPos + 1;
		return new Position(topRightX, topRightY);
	}
	
	public Position getRight() {
		int rightX = xPos;
		int rightY = yPos + 1;
		return new Position(rightX, rightY);
	}
	
	public Position getBottomRight() {
		int bottomRightX = xPos + 1;
		int bottomRightY = yPos + 1;
		return new Position(bottomRightX, bottomRightY);
	}
	
	public Position getBottom() {
		int bottomX = xPos + 1;
		int bottomY = yPos;
		return new Position(bottomX, bottomY);
	}
	
	public Position getBottomLeft() {
		int bottomLeftX = xPos + 1;
		int bottomLeftY = yPos - 1;
		return new Position(bottomLeftX, bottomLeftY);
	}
	
	public Position getLeft() {
		int leftX = xPos;
		int leftY = yPos - 1;
		return new Position(leftX, leftY);
	}
}
