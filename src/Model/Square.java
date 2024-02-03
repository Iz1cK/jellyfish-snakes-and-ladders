package Model;

public class Square {
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
	
	
	
}