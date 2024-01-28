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
        	Square easySR1= new Square(random.nextInt(10 - 1 + 1));
        	Square easySC1= new Square(random.nextInt(20 - 11 + 1));
        	Ladder ladderE1= new Ladder ("easy1",easySR1,easySC1);
        	Square easySR2= new Square(random.nextInt(20 - 1 + 1));
        	Square easySC2= new Square(random.nextInt(40 - 11 + 1));
        	Ladder ladderE2= new Ladder ("easy2",easySR2,easySC2);
        	Square easySR3= new Square(random.nextInt(40 - 1 + 1));
        	Square easySC3= new Square(random.nextInt(70 - 11 + 1));
        	Ladder ladderE3= new Ladder ("easy3",easySR3,easySC3);
        	Square easySR4= new Square(random.nextInt(40 - 1 + 1));
        	Square easySC4= new Square(random.nextInt(80 - 11 + 1));
        	Ladder ladderE4= new Ladder ("easy4",easySR4,easySC4);
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

