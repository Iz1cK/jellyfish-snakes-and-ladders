package Model;

public class QuesSquare extends Square{
	private Questions quesSeq;
	
	public QuesSquare(int numberPosition) {
		super(numberPosition);
	}

	public QuesSquare(int row,int column) {
		super(row,column);
	}

	public Questions getQuesSeq() {
		return quesSeq;
	}

	public void setQuesSeq(Questions quesSeq) {
		this.quesSeq = quesSeq;
	}

	@Override
	public String toString() {
		return "QuesSquare [quesSeq=" + quesSeq + "]";
	}
	public void print() {
		System.out.print("check");
	}
	
}
