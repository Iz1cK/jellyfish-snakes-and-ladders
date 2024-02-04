package Model;

import java.util.ArrayList;
import java.util.List;
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
	
	public void setStartAndDest( ArrayList<Integer> questions, ArrayList<Integer> surprise, int numberOfSnakes, int boardSize) {
		int snakeStart;
		for(int i=1;i <= numberOfSnakes; i++) {
		snakeStart = getRandom(boardSize + 1 + (i-1), boardSize*boardSize + 1);
		while(questions.contains(snakeStart) || surprise.contains(snakeStart))
			snakeStart = getRandom(boardSize + 1 + (i-1)*boardSize, boardSize*boardSize + 1);
			// SNAKE HEAD
			System.out.println("Snake Start: " + snakeStart);
			headSquare = new Square(snakeStart);
			// SNAKE TAIL
			int row = (int) Math.floor(snakeStart/boardSize);
			int snakeEnd = getRandom(boardSize*(row-i) + 1, boardSize*row + (i-1));
			System.out.println("Snake End: "  + snakeEnd);
			tailSquare = new Square(snakeEnd);
		}
            
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
	// for testing the methods
	public static void main(String[] args) {
		Snake snake = new Snake();
		ArrayList<Integer> questions = new ArrayList<>();
		questions.add(10);
		ArrayList<Integer> surprise = new ArrayList<>();
		surprise.add(16);
		snake.setStartAndDest(questions,surprise,4,10);
	}
	

}