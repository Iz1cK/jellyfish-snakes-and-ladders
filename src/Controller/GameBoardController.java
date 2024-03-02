package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Model.Board;
import Model.Ladder;
import Model.Player;
import Model.Questions;
import Model.Snake;
import Model.Square;
import Model.SurpriseSquare;
import Model.Sysdata;

public class GameBoardController {
	
	private Board gameBoard;

	private static GameBoardController GBCinstance = null;
	public static String diceRoll;
	static Sysdata sysdata = Sysdata.getInstance();
	
	private GameBoardController() {}
	
	public static synchronized GameBoardController getInstance()
    {
        if (GBCinstance == null)
        	GBCinstance = new GameBoardController();
        sysdata.readQuestions();
        return GBCinstance;
    }
	
	public void setGameBoard(Board board) {
		this.gameBoard = board;
	}
	
	public Board getGameBoard() {
		return this.gameBoard;
	}
	
	public void playTurn() {
		ArrayList<Player> players = this.gameBoard.getPlayers();
		Player currentPlayer = this.gameBoard.getCurrentPlayerTurn();
		int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
		this.gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
		
		HashMap<Player, Integer> playersPositions = this.gameBoard.getPlayersPositions();
		diceRoll = this.gameBoard.rollDice();
		switch(diceRoll) {
		case "0":
			System.out.println("you got 0, you dont move");
			break;
		case "1":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 1);
			break;
		case "2":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 2);
			break;
		case "3":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 3);
			break;
		case "4":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 4);
			break;
		case "5":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 5);
			break;
		case "6":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 6);
			break;
		case "7":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 7);
			break;
		case "8":
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 8);
			break;
		case "E":
			System.out.println("you rolled an easy question!");
			break;
		case "M":
			System.out.println("you rolled a medium question!");
			break;
		case "H":
			System.out.println("you rolled a hard question!");
			break;
		}
		
		if(playersPositions.get(currentPlayer) > 49) {
			playersPositions.put(currentPlayer, 49 - (playersPositions.get(currentPlayer) - 49));
		} else if(playersPositions.get(currentPlayer) == 49) {
			System.out.println("PLAYER " + currentPlayer.getPlayername() + " WON!");
		}
		
		Square landingSquare = this.gameBoard.getSquareByPosition(playersPositions.get(currentPlayer));
		String landingSquareType = this.gameBoard.checkLandingSquare(landingSquare);
		switch(landingSquareType) {
		case "QuestionSquare":
			List<Questions> questionsList = sysdata.getQuestionsList();
			Random rand = new Random();
			Questions question = questionsList.get(rand.nextInt(questionsList.size() - 1));
			
//			System.out.println("Generated question: " + question);
			break;
		case "SurpriseSquare":
			if(((SurpriseSquare) landingSquare).pickDirection() >= 0) {
				playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + 10);
			} else playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) - 10);
			break;
		default:
			// check for snakes and ladders
			ArrayList<Snake> snakes = this.gameBoard.getSnakes();
			for (Snake snake : snakes) {
				Square headSquare = snake.getHeadSquare();
				Square tailSquare = snake.getTailSquare();
				headSquare.calculatePosition(this.gameBoard.getRows());
				tailSquare.calculatePosition(this.gameBoard.getRows());
				if(playersPositions.get(currentPlayer) == headSquare.getPosition()) {
					System.out.println("Found snake head, falling down to tail!");
					playersPositions.put(currentPlayer, tailSquare.getPosition());
				}
			}
			ArrayList<Ladder> ladders = this.gameBoard.getLadders();
			for (Ladder ladder : ladders) {
				Square startSquare = ladder.getStartSquare();
				Square destSquare = ladder.getDestSquare();
				startSquare.calculatePosition(this.gameBoard.getRows());
				destSquare.calculatePosition(this.gameBoard.getRows());
				if(playersPositions.get(currentPlayer) == startSquare.getPosition()) {
					System.out.println("Found start ladder, climbing it up!");
					playersPositions.put(currentPlayer, destSquare.getPosition());
				}
			}
			break;
		}
	}
	
}
