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
	
	private static int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}
	@Override
	public String toString() {
		return "Ladder [LadderUD=" + LadderUD + ", length=" + length + ", startSquare=" + startSquare + ", destSquare="
				+ destSquare + "]";
	}
	
}

