package Model;

public class QuesSquare extends Square{
	
	private int difficulty;
	
	public QuesSquare(int numberPosition, int difficulty) {
		super(numberPosition);
		this.difficulty = difficulty;
	}

	public QuesSquare(int row,int column, int difficulty) {
		super(row,column);
		this.difficulty = difficulty;
	}

	public QuesSquare() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QuesSquare [difficulty=" + difficulty + "]";
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
