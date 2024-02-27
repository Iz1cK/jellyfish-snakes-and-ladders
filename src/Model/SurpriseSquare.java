package Model;

import java.util.Random;

public class SurpriseSquare extends Square {
	public SurpriseSquare(int numberPosition) {
		super(numberPosition);
	}
	
	public SurpriseSquare(int row,int column) {
		super(row,column);
	}
	
	 public SurpriseSquare() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "SurpriseSquare [moveCount=]";
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