package Model;

import java.util.ArrayList;
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
	
	public void setStartAndDest( ArrayList<Integer> questions, ArrayList<Integer> surprise, int numberOfSnakes, int boardSize) {
		int ladderStart;
		for(int i=1;i <= numberOfSnakes; i++) {
		ladderStart = getRandom(2, boardSize*boardSize-boardSize*i);
		while(questions.contains(ladderStart) || surprise.contains(ladderStart))
			ladderStart = getRandom(2, boardSize*boardSize-boardSize*i);
			// LADDER HEAD
			System.out.println("Ladder Start: " + ladderStart);
			startSquare = new Square(ladderStart);
			// LADDER TAIL
			int row = (int) Math.floor(ladderStart/boardSize);
			int ladderEnd = getRandom(boardSize*(row+i) + 1, boardSize*(row+i) + boardSize);
			System.out.println("Ladder End: "  + ladderEnd);
			destSquare = new Square(ladderEnd);
		}
            
    }
	
	private static int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}
	// for testing the methods
	public static void main(String[] args) {
		Ladder ladder = new Ladder();
		ArrayList<Integer> questions = new ArrayList<>();
		questions.add(10);
		ArrayList<Integer> surprise = new ArrayList<>();
		surprise.add(16);
		ladder.setStartAndDest(questions,surprise,4,10);
	}
	
	@Override
	public String toString() {
		return "Ladder [LadderUD=" + LadderUD + ", length=" + length + ", startSquare=" + startSquare + ", destSquare="
				+ destSquare + "]";
	}
	
}

