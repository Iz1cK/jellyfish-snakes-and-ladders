package Model;

public class QuesSquare extends Square{
	
	public QuesSquare(int numberPosition) {
		super(numberPosition);
	}

	public QuesSquare(int row,int column) {
		super(row,column);
	}

	public QuesSquare() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "QuesSquare ";
	}
	public void print() {
		System.out.print("check");
	}
	
}
