package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladder {
	private String LadderUD;
	private int length;
	private Square startSquare;
	private Square destSquare;
	
	public Ladder(String ladderUD, Square startSquare, Square destSquare) {
		super();
		LadderUD = ladderUD;
		this.startSquare = startSquare;
		this.destSquare = destSquare;
	}
	public Ladder(String ladderUD, int length, Square startSquare, Square destSquare) {
		super();
		LadderUD = ladderUD;
		this.length = length;
		this.startSquare = startSquare;
		this.destSquare = destSquare;
	}
	public Ladder() {
	}
	public String getLadderUD() {
		return LadderUD;
	}
	public void setLadderUD(String ladderUD) {
		LadderUD = ladderUD;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Square getStartSquare() {
		return startSquare;
	}
	public void setStartSquare(Square startSquare) {
		this.startSquare = startSquare;
	}
	public Square getDestSquare() {
		return destSquare;
	}
	public void setDestSquare(Square destSquare) {
		this.destSquare = destSquare;
	}

	
	public List<Ladder> setStartAndDest( int gridSize, int numberOfLadders) {
		Random random = new Random();
		List<Ladder> ladders = new ArrayList<>();
		
		// Create List for all board's squares
        List<Square> allSquares = new ArrayList<>();
        for (int i = 1; i <= gridSize * gridSize; i++) {
            allSquares.add(new Square(i));
        }

        for (int i = 1; i <= numberOfLadders; i++) {
            //initial start square for the ladder
            Square ladderEnd = allSquares.remove(random.nextInt(allSquares.size()+(i+1)));

            Square ladderStart = allSquares.remove(random.nextInt((ladderEnd.getPosition()%i-i*gridSize)+(gridSize)));
            //adding the ladder to the ladders array
            ladders.add(new Ladder(String.valueOf(i),ladderStart, ladderEnd));
        }
        return ladders;

    }
}

