package Model;

import java.util.ArrayList;
import java.util.Random;

public class Snake {
	private String snakeID;
	private COLORS snakeSnake;
	private Square headSquare;
	private Square tailSquare;
	private int rank;
	public Snake(String snakeID, Square headSquare, Square tailSquare) {
		super();
		this.snakeID = snakeID;
		this.headSquare = headSquare;
		this.tailSquare = tailSquare;
		
	}
	
	public Snake() {
		super();
	}
	

	
	public String getSnakeID() {
		return snakeID;
	}

	public void setSnakeID(String snakeID) {
		this.snakeID = snakeID;
	}

	public COLORS getSnakeSnake() {
		return snakeSnake;
	}

	public void setSnakeSnake(COLORS snakeSnake) {
		this.snakeSnake = snakeSnake;
	}

	public Square getHeadSquare() {
		return headSquare;
	}

	public void setHeadSquare(Square headSquare) {
		this.headSquare = headSquare;
	}

	public Square getTailSquare() {
		return tailSquare;
	}

	public void setTailSquare(Square tailSquare) {
		this.tailSquare = tailSquare;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Snake [snakeID=" + snakeID + ", snakeSnake=" + snakeSnake + ", headSquare=" + headSquare
				+ ", tailSquare=" + tailSquare + ", rank=" + rank + "]";
	}

	private static int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}

	

}