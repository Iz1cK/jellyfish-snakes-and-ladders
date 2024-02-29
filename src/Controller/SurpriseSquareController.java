package Controller;


import java.util.HashMap;

import Model.Board;
import Model.Player;
import Model.SurpriseSquare;
import View.SurpriseSquarePopupView;



public class SurpriseSquareController {
	private SurpriseSquare square;
	private String direction;
	private Board board;
	public SurpriseSquareController(SurpriseSquare square, Board board)
	{
		this.board=board;
		this.square=square;
		this.direction=randomDirection();
		openSurpriseSquarePopupView();
	}


	/*
	 * function to decide a direction for moving
	 * when landing on a surprise square
	 */
	private String randomDirection() {
		if(square.pickDirection()>=0)
			return("forward");
		else
			return("backward");
	}

	private void openSurpriseSquarePopupView() {
			SurpriseSquarePopupView S = new SurpriseSquarePopupView(direction,this);
			S.setVisible(true);
	}



	public void innitiateMove()
	{
		board.moveFromSurpriseSquare(this);
	}
	
	

	
	/*
	 * method that updates position of a player that landed
	 * on a surprise square, 10 squares backward/ forward 
	 */
	public void movePlayerToDestination(HashMap<Player, Integer> playerPositions, Player currentPlayer) 
	{
		if(direction.equals("forward"))
			{
				if(playerPositions.get(currentPlayer)+10<=Math.pow(board.getRows(), 2))
					{
						playerPositions.put(currentPlayer, square.getPosition()+10);
					}
				else
					{
						playerPositions.put(currentPlayer, (int) (Math.pow(board.getRows(), 2)-(10-(Math.pow(board.getRows(), 2)-square.getPosition()))));
					}
			}
		else
			{
				if(playerPositions.get(currentPlayer)-10>=1)
					{
						playerPositions.put(currentPlayer, square.getPosition()-10);
					}
				else
					{
						playerPositions.put(currentPlayer, (int) (Math.pow(board.getRows(), 2)-(10-(Math.pow(board.getRows(), 2)-square.getPosition()))));
					}
			}
	}
	
	
	
	 
	
	
	
}
