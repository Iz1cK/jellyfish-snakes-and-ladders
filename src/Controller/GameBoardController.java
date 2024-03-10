package Controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.Board;
import Model.COLORS;
import Model.Game;
import Model.Ladder;
import Model.Player;
import Model.QuesSquare;
import Model.Questions;
import Model.Snake;
import Model.Square;
import Model.SurpriseSquare;
import Model.Sysdata;
import Utils.QuestionCallback;
import View.FinalPage;
import View.GameBoardView;
import View.QuestionPopUp;

public class GameBoardController {
	
	private Board gameBoard;

	private static GameBoardController GBCinstance = null;
	public static String diceRoll;
	static Sysdata sysdata = Sysdata.getInstance();
	static questionPopUpController QPUC= questionPopUpController.getInstance();
	private GameBoardView gameBoardView;
	private HashMap<Player, Integer> lastCheckedSquarePosition = new HashMap<>();
	private HashMap<Player, String> playersPowerUps = new HashMap<>();
	private boolean freezeActive = false; // worked
	private boolean anotherTurnActive = false; // worked
	private HashMap <Player, Boolean> playerSnakeShields = new HashMap<>();
	private boolean ladderPowerupActive = false;
	private boolean snakePowerupActive = false;
	private boolean doubleDiceResult = false; // worked
	private boolean onlySixResult = false;// worked
	private int previousPosition;
	public static boolean powerupsEnabled = true;
	
	public void setGameBoardView(GameBoardView gameBoardView) {
	    this.gameBoardView = gameBoardView;
	}
	
	public void setupPowerups() {
		this.playersPowerUps = new HashMap<>();
	}
	
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
	
	public String rollDice() {
		diceRoll = this.gameBoard.rollDice();
		if(onlySixResult) {
			diceRoll = "6";
			onlySixResult = false;
		}
		return diceRoll;
	}
	
	public void playTurn() {
		GameBoardView.rolling = true;
		Player currentPlayer = this.gameBoard.getCurrentPlayerTurn();
		Game game = new Game(this.gameBoard.getDifficultyBoard(), this.gameBoard ,currentPlayer, this.gameBoard.getPlayers(), GameBoardView.timerLabel.getText());
		int boardSize = (int) Math.pow(this.gameBoard.getRows(), 2);
		lastCheckedSquarePosition = new HashMap<>();
		HashMap<Player, Integer> playersPositions = this.gameBoard.getPlayersPositions();
		previousPosition = playersPositions.get(currentPlayer);
		
		switch(diceRoll) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
			int value = Integer.parseInt(diceRoll);
			if(doubleDiceResult) {
				System.out.println("Doubling result");
				doubleDiceResult = false;
				value *= 2;
			}
			if(onlySixResult) value = 6;
			playersPositions.put(currentPlayer, playersPositions.get(currentPlayer) + value);
			break;
		case "E":
			System.out.println("you rolled an easy question!");
			showQuestion(1,currentPlayer);
			return;
		case "M":
			System.out.println("you rolled a medium question!");
			showQuestion(2,currentPlayer);
			return;
		case "H":
			System.out.println("you rolled a hard question!");
			showQuestion(3,currentPlayer);
			return;
		}
		if(playersPositions.get(currentPlayer) <= 0) {
			playersPositions.put(currentPlayer, 1);
		}
		gameBoardView.animatePlayerMovement(currentPlayer, GameBoardView.playerLabels.get(currentPlayer), gameBoard, previousPosition ,()->{
			
			if(playersPositions.get(currentPlayer) >= boardSize) {
				gameBoardView.setVisible(false);
				gameBoardView.dispose();
				sysdata.addGameHistory(game);
				FinalPage FP = new FinalPage(game);
				FP.setVisible(true);
				System.out.println("PLAYER " + currentPlayer.getPlayername() + " WON!");
			} 
			
			if(this.checkSquares(currentPlayer, false)) {
				gameBoardView.animatePlayerMovement(currentPlayer, GameBoardView.playerLabels.get(currentPlayer),gameBoard, previousPosition, ()->{
					goNext();
				});
			} else {
				goNext();
			}
		});
	}
	
	private boolean checkSquares(Player currentPlayer, boolean noQuestions) {
		HashMap<Player, Integer> playersPositions = this.gameBoard.getPlayersPositions();
		int currentPosition = playersPositions.get(currentPlayer);
		if (lastCheckedSquarePosition.containsKey(currentPlayer) && lastCheckedSquarePosition.get(currentPlayer) == currentPosition) {
	        return false;
	    }
		 
		Square landingSquare = this.gameBoard.getSquareByPosition(playersPositions.get(currentPlayer));
		String landingSquareType = this.gameBoard.checkLandingSquare(landingSquare);
		switch(landingSquareType) {
		case "QuestionSquare":
//			if(!noQuestions) {
				int questionDifficulty = ((QuesSquare) landingSquare).getDifficulty();
				System.out.println("Landed on a question square with difficulty: " + questionDifficulty);
				showQuestion(questionDifficulty, currentPlayer);
				lastCheckedSquarePosition.put(currentPlayer, currentPosition);
				return true;
//			}
//			break;
		case "SurpriseSquare":
			System.out.println("Landed on a surprise square!");
			SurpriseSquareController SSC = SurpriseSquareController.getInstance();
			previousPosition = playersPositions.get(currentPlayer);
			SSC.handleSurpriseSquare(playersPositions, currentPlayer, gameBoard, (SurpriseSquare) landingSquare);
			return true;
		default:
			if(playerSnakeShields.size() != 0) {
				if(playerSnakeShields.get(currentPlayer)) {
					playerSnakeShields.put(currentPlayer, false);
					return false;
				}
			}
			ArrayList<Snake> snakes = this.gameBoard.getSnakes();
			for (Snake snake : snakes) {
				if(snake.getColor() == COLORS.RED) {
					Square headSquare = snake.getHeadSquare();
					headSquare.calculatePosition(this.gameBoard.getRows());
					if(playersPositions.get(currentPlayer) == headSquare.getPosition()) {
						System.out.println("Found RED snake head, falling down to START!");
						previousPosition = playersPositions.get(currentPlayer);
						playersPositions.put(currentPlayer, 1);
						return true;
					}
				} else {
					Square headSquare = snake.getHeadSquare();
					Square tailSquare = snake.getTailSquare();
					headSquare.calculatePosition(this.gameBoard.getRows());
					tailSquare.calculatePosition(this.gameBoard.getRows());
					if(playersPositions.get(currentPlayer) == headSquare.getPosition()) {
						System.out.println("Found snake head, falling down to tail!");
						previousPosition = playersPositions.get(currentPlayer);
						playersPositions.put(currentPlayer, tailSquare.getPosition());
						return true;
					}
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
					previousPosition = playersPositions.get(currentPlayer);
					playersPositions.put(currentPlayer, destSquare.getPosition());
					return true;
				}
			}
			break;
		}
		return false;
	}
	
	private QuestionCallback generateQuestionCallback(final int difficulty, Player currentPlayer) {
	    return new QuestionCallback() {
	        @Override
	        public void onQuestionAnswered(boolean isCorrect) {
	        	if(powerupsEnabled) {
	        		if(playersPowerUps.containsKey(currentPlayer)) {
	        			goNext();
	        			return;
	        		}
	        		if(!isCorrect) {
	        			System.out.println("Answered incorrectly, go Next!");
	        			goNext();
	        			return;
	        		}
	        		
	        		System.out.println("Answered Correctly, get powerup!");
	        		Random random = new Random();
	        		int powerupNumber = 0;
	        		if(difficulty == 3) {
	        			powerupNumber = 3;
	        		} else if(difficulty == 2) {
	        			powerupNumber = 3;
	        		} else {
	        			powerupNumber = random.nextInt(2) + 1;
	        		}
	        		String powerupImage = "Tier" + difficulty + "-" + powerupNumber;
//	        		String powerupImage = "Tier3-1";
	        		System.out.println("Player " + currentPlayer.getPlayername() + " got powerup " + powerupImage);
	        		ImageIcon powerUpIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + powerupImage + ".png"));
	        		Image powerUpIconScaled = powerUpIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        		GameBoardView.powerUpLabel.setIcon(new ImageIcon(powerUpIconScaled));
	        		playersPowerUps.put(currentPlayer, powerupImage);
	        		goNext();
	        	} else {
	        		int penaltyOrReward = calculatePenaltyOrReward(difficulty, isCorrect);
	        		ArrayList<Player> players = gameBoard.getPlayers();
		        	HashMap<Player,Integer> playersPositions = gameBoard.getPlayersPositions();
		        	Game game = new Game(gameBoard.getDifficultyBoard(), gameBoard ,currentPlayer, gameBoard.getPlayers(), GameBoardView.timerLabel.getText());
		    		int boardSize = (int) Math.pow(gameBoard.getRows(), 2);
		            previousPosition = playersPositions.get(currentPlayer);
		            updatePlayerPosition(currentPlayer, penaltyOrReward);
		            if(playersPositions.get(currentPlayer) >= boardSize) {
		            	playersPositions.put(currentPlayer, boardSize);
		            }
		            gameBoardView.animatePlayerMovement(currentPlayer, GameBoardView.playerLabels.get(currentPlayer), gameBoard, previousPosition, ()->{
//		            	System.out.println("Moving " + currentPlayer.getPlayername() + " from " + previousPosition + " to " + playersPositions.get(currentPlayer)); 
		            	if(playersPositions.get(currentPlayer) == boardSize) {
		            		 sysdata.addGameHistory(game);
		            		 FinalPage FP = new FinalPage(game);
		            		 FP.setVisible(true);
		            		 gameBoardView.setVisible(false);
		            		 gameBoardView.dispose();
		            		 System.out.println("PLAYER " + currentPlayer.getPlayername() + " WON!");
		            	 }
		            	 
		            	 if(checkSquares(currentPlayer, true)) {
		     				gameBoardView.animatePlayerMovement(currentPlayer, GameBoardView.playerLabels.get(currentPlayer), gameBoard, previousPosition, ()->{
		     					int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
		     					gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
		     					GameBoardView.rolling = false;
		     					gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
		     				});
		     			} else {
		     				int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
		     				gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
		     				GameBoardView.rolling = false;
		     				gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
		     			}
		            	 
		            });
	        	}
	        }
	    };
	}
	
	private int calculatePenaltyOrReward(int difficulty, boolean isCorrect) {
	    if (isCorrect) {
	        return difficulty == 3 ? 1 : 0;
	    } else {
	        return -difficulty;
	    }
	}
	
	private void updatePlayerPosition(Player currentPlayer, int move) {
	    HashMap<Player, Integer> playersPositions = gameBoard.getPlayersPositions();
	    int newPosition = Math.max(playersPositions.get(currentPlayer) + move, 1); // Ensure position doesn't go below 1
	    System.out.println("changed player " + currentPlayer.getPlayername() + " position from : " + playersPositions.get(currentPlayer) + " to: " + newPosition);
	    playersPositions.put(currentPlayer, newPosition);
	}
	
	public void goNext() {
		ArrayList<Player> players = gameBoard.getPlayers();
		HashMap<Player,Integer> playersPositions = gameBoard.getPlayersPositions();
		if(anotherTurnActive) {
			anotherTurnActive = false;
			GameBoardView.rolling = false;
			gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
			return;
		}
		int newCurrentPlayerIndex = (players.indexOf(gameBoard.getCurrentPlayerTurn()) + 1) % players.size();
		gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
		Player newCurrentPlayer = gameBoard.getCurrentPlayerTurn();
		GameBoardView.rolling = false;
		gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
		if(powerupsEnabled) {
			ImageIcon powerUpIcon;
			if(playersPowerUps.containsKey(newCurrentPlayer)) {
				powerUpIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + playersPowerUps.get(newCurrentPlayer) + ".png"));
			} else {
				powerUpIcon = new ImageIcon(GameBoardView.class.getResource("/img/noPowerup.png"));
			}
			Image powerUpIconScaled = powerUpIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			GameBoardView.powerUpLabel.setIcon(new ImageIcon(powerUpIconScaled));
			if(freezeActive) {
				freezeActive = false;
				goNext();
			}
		}
	}

	public void showQuestion(int difficulty, Player currentPlayer) {
		QuestionCallback callback = generateQuestionCallback(difficulty, currentPlayer);
		Random random = new Random();
		List<Questions> Questions = new ArrayList<>();
		for (Questions qd : sysdata.questionsList) {
			if(qd.getDifficulty()==difficulty) {
				Questions.add(qd);
			}
		}

		if (!Questions.isEmpty()) {
		    int randomIndex = random.nextInt(Questions.size());

		    Questions randomQuestion = Questions.get(randomIndex);
		    String question = randomQuestion.getQuestion();
		    String answer1 = randomQuestion.getAnswers().get(0);
			String answer2 = randomQuestion.getAnswers().get(1);
			String answer3 = randomQuestion.getAnswers().get(2);
			String answer4 = randomQuestion.getAnswers().get(3);
			
			QuestionPopUp obj = new QuestionPopUp(callback,difficulty,gameBoardView);
	        obj.cmdobt1.setText(answer1);
	        obj.cmdobt2.setText(answer2);
	        obj.cmdobt3.setText(answer3);
	        obj.cmdobt4.setText(answer4);
	        String questionBody = question;
	        obj.correctAns = randomQuestion.getCorrect_ans();
	        obj.difficulty = difficulty;
	        String message = difficulty == 1 ? "easy" : difficulty == 2 ? "medium" : "hard";
	        obj.showMessage(message, questionBody);
	        
		}
	}
	
	public boolean activatePowerup() {
		Player currentPlayer = gameBoard.getCurrentPlayerTurn();
		if(playersPowerUps.containsKey(currentPlayer)) {
			String powerup = playersPowerUps.get(currentPlayer);
//			String tierAndLevel = powerup.split("Tier")[1];
//			int tier = Integer.parseInt(tierAndLevel.split("-")[0]);
//			int number = Integer.parseInt(tierAndLevel.split("-")[1]);
			
			switch(powerup) {
			case "Tier1-1":
				onlySixResult = true;
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier1-2":
				freezeActive = true;
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier1-3":
				
				break;
			case "Tier2-1":
				ladderPowerupActive = true;
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier2-2":
				snakePowerupActive = true;
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier2-3":
				anotherTurnActive = true;
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier3-1":
				playerSnakeShields.put(currentPlayer, true);
				playersPowerUps.remove(currentPlayer);
				break;
			case "Tier3-2":
				break;
			case "Tier3-3":
				doubleDiceResult = true;
				playersPowerUps.remove(currentPlayer);
				break;
			}
			return true;
		} else {
			return false;
		}
	}
//	
//	private void clearPowerup() {
//		gameBoard.setDefaultDiceOptions();
//		freezeActive = false;
//		anotherTurnActive = false;
//		snakeShieldActive = false;
//		ladderPowerupActive = false;
//		snakePowerupActive = false;
//	}
}

