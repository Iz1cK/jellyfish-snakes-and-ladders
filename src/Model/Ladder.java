package Model;

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

	public void setStartAndDest(DIFFICULTY diffLevel) {
		Random random = new Random();

		switch (diffLevel) {
        case EASY:
        	Square easyS1= new Square(random.nextInt(10 - 1 + 1));
        	Square easyS2= new Square(random.nextInt(40 - 11 + 1));
        	Ladder ladderE1= new Ladder ("easy1",easyS1,easyS2);
            System.out.println("HARD");
            break;
        case MEDIUM:
            System.out.println("MEDIUM");
            break;
        case HARD:
            System.out.println("Received HARD");
            break;
	}
	}
}

