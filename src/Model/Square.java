package Model;

public class Square implements SquareInterface {
	private int position;
	private int row, column; //the square in the board
	private COLORS squareColor;
	public Square(int position) {
		super();
		this.position = position;
	}
	
	public Square(int position, int row, int column) {
		super();
		this.position = position;
		this.row = row;
		this.column = column;
	}
	public Square(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	public Square(int position, int row, int column, COLORS squareColor) {
		super();
		this.position = position;
		this.row = row;
		this.column = column;
		this.squareColor = squareColor;
	}
	public Square() {
		// TODO Auto-generated constructor stub
	}

	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public COLORS getSquareColor() {
		return squareColor;
	}
	public void setSquareColor(COLORS squareColor) {
		this.squareColor = squareColor;
	}
	
	public void calculateRowAndColumn(int boardSize) {
		this.row = (this.position - 1) / boardSize;
        // Determine if row is even or odd and calculate column accordingly
        if (this.row % 2 == 0) {
            this.column = (this.position - 1) % boardSize;
        } else {
            this.column = boardSize - 1 - (this.position - 1) % boardSize;
        }
	}
	
	public void calculatePosition(int boardSize) {
        if (this.row % 2 == 0) { // Even row, numbers increase from left to right
            this.position = this.row * boardSize + this.column + 1;
        } else { // Odd row, numbers increase from right to left
            this.position = this.row * boardSize + (boardSize - this.column - 1) + 1;
        }
    }

	@Override
	public String toString() {
		return "Square [position=" + position + ", row=" + row + ", column=" + column + ", squareColor=" + squareColor
				+ "]";
	}
	
	public void getSquare() {
		// TODO Auto-generated method stub
		
	}
	
}