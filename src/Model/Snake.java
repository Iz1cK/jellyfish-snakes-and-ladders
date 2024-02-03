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
	
	public List<Snake> setStartAndDest( int gridSize, int numberOfSnakes) {
		Random random = new Random();
		List<Snake> snakes = new ArrayList<>();
		
		// Create List for all board's squares
        List<Square> allSquares = new ArrayList<>();
        for (int i = 1; i <= gridSize * gridSize; i++) {
            allSquares.add(new Square(i));
        }

        for (int i = 1; i <= numberOfSnakes; i++) {
            //initial start square for the ladder
            Square snakeEnd = allSquares.remove(random.nextInt(allSquares.size()+(i+1)));

            Square snakeStart = allSquares.remove(random.nextInt((snakeEnd.getPosition()%i-i*gridSize)+(gridSize)));
            //adding the ladder to the ladders array
            snakes.add(new Snake(String.valueOf(i),snakeStart, snakeEnd));
        }
        return snakes;

    }
	

}