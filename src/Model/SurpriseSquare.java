package Model;

import java.util.Random;

public class SurpriseSquare extends Square{
	private int moveCount;
	public SurpriseSquare(int numberPosition) {
		super(numberPosition);
	}
	
	public SurpriseSquare(int row,int column) {
		super(row,column);
	}
	
	 public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	@Override
	public String toString() {
		return "SurpriseSquare [moveCount=" + moveCount + "]";
	}

	/**
	 * A static function that generates a random rational number
	 * between -0.5 and 0.5 (0.5 excluded) to determine character
	 * direction of a player who landed on a surprise square,
	 * (forwards/backwards)10 steps.
	 */
	public double pickDirection()
	{
		Random random = new Random();
		double coin=(random.nextDouble()-0.5);
		return coin;
	}
}