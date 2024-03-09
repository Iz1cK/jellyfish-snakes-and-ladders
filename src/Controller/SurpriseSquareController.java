package Controller;


import java.util.HashMap;

import Model.Board;
import Model.Player;
import Model.SurpriseSquare;
import View.SurpriseSquarePopupView;



public class SurpriseSquareController {

	private static SurpriseSquareController SSCinstance = null;
	
	private SurpriseSquareController() {}
	
	public static synchronized SurpriseSquareController getInstance()
    {
        if (SSCinstance == null)
        	SSCinstance = new SurpriseSquareController();
        return SSCinstance;
    }
	
	/*
	 * function to decide a direction for moving
	 * when landing on a surprise square
	 */
	private String randomDirection(SurpriseSquare square) {
		return square.pickDirection()>= 0 ? "forward" : "backward";
	}

	public void handleSurpriseSquare(HashMap<Player, Integer> playerPositions, Player currentPlayer, Board board, SurpriseSquare square) {
		String direction = randomDirection(square);
		SurpriseSquarePopupView S = new SurpriseSquarePopupView(direction,this);
		S.setVisible(true);
		movePlayerToDestination(playerPositions, currentPlayer, direction, board);
	}
	/*
	 * method that updates position of a player that landed
	 * on a surprise square, 10 squares backward/ forward 
	 */
	public void movePlayerToDestination(HashMap<Player, Integer> playerPositions, Player currentPlayer, String direction, Board board) {
	    int boardSize = (int) Math.pow(board.getRows(), 2);
	    int currentPosition = playerPositions.get(currentPlayer);
	    int newPosition;

	    if ("forward".equals(direction)) {
	        newPosition = Math.min(currentPosition + 10, boardSize);
	    } else {
	        newPosition = Math.max(currentPosition - 10, 1);
	    }

	    playerPositions.put(currentPlayer, newPosition);
	}
}
