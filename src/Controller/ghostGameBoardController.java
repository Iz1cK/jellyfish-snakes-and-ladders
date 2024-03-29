package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import Model.Board;
import Model.COLORS;
import Model.Game;
import Model.Ladder;
import Model.Player;
import Model.QuesSquare;
import Model.Questions;
import Model.Scores;
import Model.Snake;
import Model.Square;
import Model.SurpriseSquare;
import Model.Sysdata;
import Utils.QuestionCallback;
import View.FinalPage;
import View.FinalPageScores;
import View.QuestionPopUp;
import View.ghostGame;

public class ghostGameBoardController {
	
	private Board gameBoard;

	private static ghostGameBoardController GBCinstance = null;
	public static String diceRoll;
	static Sysdata sysdata = Sysdata.getInstance();
	static questionPopUpController QPUC= questionPopUpController.getInstance();
	private ghostGame gameBoardView;
	private HashMap<Player, Integer> lastCheckedSquarePosition = new HashMap<>();
	private int previousPosition;
	
	public void setGameBoardView(ghostGame gameBoardView) {
	    this.gameBoardView = gameBoardView;
	}
	
	private ghostGameBoardController() {}
	
	public static synchronized ghostGameBoardController getInstance()
    {
        if (GBCinstance == null)
        	GBCinstance = new ghostGameBoardController();
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
		return diceRoll;
	}
	
	public void playTurn() {
		ghostGame.rolling = true;
		ArrayList<Player> players = this.gameBoard.getPlayers();
		Player currentPlayer = this.gameBoard.getCurrentPlayerTurn();
		Scores game = new Scores(this.gameBoard.getDifficultyBoard(), this.gameBoard ,currentPlayer, ghostGame.score, ghostGame.timerLabel.getText());
		int boardSize = (int) Math.pow(this.gameBoard.getRows(), 2);
		lastCheckedSquarePosition = new HashMap<>();
		
		HashMap<Player, Integer> playersPositions = this.gameBoard.getPlayersPositions();
		
		previousPosition = playersPositions.get(currentPlayer);
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
		gameBoardView.animatePlayerMovement(currentPlayer, ghostGame.playerLabels.get(currentPlayer), gameBoard, previousPosition ,()->{
			
			if(playersPositions.get(currentPlayer) >= boardSize) {
				gameBoardView.setVisible(false);
				gameBoardView.dispose();
				sysdata.addScore(game);
				FinalPageScores FP = new FinalPageScores(game);
				FP.setVisible(true);
				System.out.println("PLAYER " + currentPlayer.getPlayername() + " WON!");
			} 
			
			if(this.checkSquares(currentPlayer, false)) {
				
				gameBoardView.animatePlayerMovement(currentPlayer, ghostGame.playerLabels.get(currentPlayer),gameBoard, previousPosition, ()->{
					int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
					this.gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
					ghostGame.rolling = false;
					gameBoardView.updatePlayersList(players, playersPositions, this.gameBoard);
				});
			} else {
				int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
				this.gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
				ghostGame.rolling = false;
				gameBoardView.updatePlayersList(players, playersPositions, this.gameBoard);
			}
		});
//		gameBoardView.updatePlayerPositionsOnBoard(this.gameBoard.getDifficultyBoard(), players, playersPositions,this.gameBoard.getRows(),this.gameBoard.getColumns());
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
			if(!noQuestions) {
				int questionDifficulty = ((QuesSquare) landingSquare).getDifficulty();
				System.out.println("Landed on a question square with difficulty: " + questionDifficulty);
				showQuestion(questionDifficulty, currentPlayer);
				lastCheckedSquarePosition.put(currentPlayer, currentPosition);
				return true;
			}
			break;
		case "SurpriseSquare":
			System.out.println("Landed on a surprise square!");
			SurpriseSquareController SSC = SurpriseSquareController.getInstance();
			previousPosition = playersPositions.get(currentPlayer);
			SSC.handleSurpriseSquare(playersPositions, currentPlayer, gameBoard, (SurpriseSquare) landingSquare);
			return true;
		default:
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
	        	ArrayList<Player> players = gameBoard.getPlayers();
	        	HashMap<Player,Integer> playersPositions = gameBoard.getPlayersPositions();
	        	Game game = new Game(gameBoard.getDifficultyBoard(), gameBoard ,currentPlayer, gameBoard.getPlayers(), ghostGame.timerLabel.getText());
	    		int boardSize = (int) Math.pow(gameBoard.getRows(), 2);
	            System.out.println("Current player is: " + currentPlayer);
	            int penaltyOrReward = calculatePenaltyOrReward(difficulty, isCorrect);
	            previousPosition = playersPositions.get(currentPlayer);
	            updatePlayerPosition(currentPlayer, penaltyOrReward);
	            if(playersPositions.get(currentPlayer) >= boardSize) {
	            	playersPositions.put(currentPlayer, boardSize);
	            }
	            gameBoardView.animatePlayerMovement(currentPlayer, ghostGame.playerLabels.get(currentPlayer), gameBoard, previousPosition, ()->{
//	            	System.out.println("Moving " + currentPlayer.getPlayername() + " from " + previousPosition + " to " + playersPositions.get(currentPlayer)); 
	            	if(playersPositions.get(currentPlayer) == boardSize) {
	            		 sysdata.addGameHistory(game);
	            		 FinalPage FP = new FinalPage(game);
	            		 FP.setVisible(true);
	            		 gameBoardView.setVisible(false);
	            		 gameBoardView.dispose();
	            		 System.out.println("PLAYER " + currentPlayer.getPlayername() + " WON!");
	            	 }
	            	 
	            	 if(checkSquares(currentPlayer, true)) {
	     				gameBoardView.animatePlayerMovement(currentPlayer, ghostGame.playerLabels.get(currentPlayer), gameBoard, previousPosition, ()->{
	     					int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
	     					gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
	     					ghostGame.rolling = false;
	     					gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
	     				});
	     			} else {
	     				int newCurrentPlayerIndex = (players.indexOf(currentPlayer) + 1) % players.size();
	     				gameBoard.setCurrentPlayerTurn(players.get(newCurrentPlayerIndex));
	     				ghostGame.rolling = false;
	     				gameBoardView.updatePlayersList(players, playersPositions, gameBoard);
	     			}
	            	 
	            });
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
}

