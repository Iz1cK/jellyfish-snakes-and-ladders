package Model;

public class Square {
	private int numberPosition;
	private int row, column; //the square in the board
	private COLORS squareColor;
	
	
	public Square(int numberPosition) {
		super();
		this.numberPosition = numberPosition;
	}
	public Square(int numberPosition, int row, int column) {
		super();
		this.numberPosition = numberPosition;
		this.row = row;
		this.column = column;
	}
	public Square(int numberPosition, int row, int column, COLORS squareColor) {
		super();
		this.numberPosition = numberPosition;
		this.row = row;
		this.column = column;
		this.squareColor = squareColor;
	}
	public int getNumberPosition() {
		return numberPosition;
	}
	public void setNumberPosition(int numberPosition) {
		this.numberPosition = numberPosition;
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