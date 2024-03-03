package Model;

public class Snake {
	
	private String snakeID;
	private Square headSquare;
	private Square tailSquare;
	private int rank;
	private COLORS color;
	
	public Snake(String snakeID, Square headSquare, Square tailSquare) {
		super();
		this.snakeID = snakeID;
		this.headSquare = headSquare;
		this.tailSquare = tailSquare;
		
	}
	
	public Snake(String snakeID, Square headSquare, Square tailSquare, COLORS color) {
		super();
		this.snakeID = snakeID;
		this.headSquare = headSquare;
		this.tailSquare = tailSquare;
		this.color = color;
	}
	
	public Snake() {
		super();
	}
	
	public COLORS getColor() {
		return color;
	}

	public void setColor(COLORS color) {
		this.color = color;
	}

	public String getSnakeID() {
		return snakeID;
	}

	public void setSnakeID(String snakeID) {
		this.snakeID = snakeID;
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
		return "Snake [snakeID=" + snakeID + ", headSquare=" + headSquare
				+ ", tailSquare=" + tailSquare + ", rank=" + rank + ", color=" + color + "]";
	}

}