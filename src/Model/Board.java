package Model;

import java.util.ArrayList;

public class Board {
	private int boardId;
	private Square[][] squares;
	private ArrayList<Integer> surpriseSquarePositions;
	private ArrayList<Integer> questionSquarePositions;
	private Ladder[] ladders;
	private Player[] players;
	private Snake[] snakes;
	private int[] playersPositions;
	private DIFFICULTY difficultyBoard;
	private int rows;
	private int columns;
	private int currentPlayerTurn;
	
	public Board(int boardId, Square[][] squares, ArrayList<Integer> surpriseSquarePositions, ArrayList<Integer> questionSquarePositions,Ladder[] ladders, Player[] players, Snake[] snakes,
			int[] playersPositions, DIFFICULTY difficultyBoard, int rows, int columns, int currentPlayerTurn) {
		super();
		this.boardId = boardId;
		this.squares = squares;
		this.surpriseSquarePositions = surpriseSquarePositions;
		this.questionSquarePositions = questionSquarePositions;
		this.ladders = ladders;
		this.players = players;
		this.snakes = snakes;
		this.playersPositions = playersPositions;
		this.difficultyBoard = difficultyBoard;
		this.rows = rows;
		this.columns = columns;
		this.currentPlayerTurn = currentPlayerTurn;
	}
	
	public ArrayList<Integer> getQuestionSquarePositions() {
		return questionSquarePositions;
	}

	public void setQuestionSquarePositions(ArrayList<Integer> questionSquarePositions) {
		this.questionSquarePositions = questionSquarePositions;
	}

	public static void main(String[] args) {
		System.out.println("hello");
		Board currBoard = new Board();
		currBoard.setDifficultyBoard(DIFFICULTY.EASY);
		currBoard.generateBoard();
	}

	public Board() {
		super();
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public Ladder[] getLadders() {
		return ladders;
	}

	public void setLadders(Ladder[] ladders) {
		this.ladders = ladders;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Snake[] getSnakes() {
		return snakes;
	}

	public void setSnakes(Snake[] snakes) {
		this.snakes = snakes;
	}

	public int[] getPlayersPositions() {
		return playersPositions;
	}

	public void setPlayersPositions(int[] playersPositions) {
		this.playersPositions = playersPositions;
	}

	public DIFFICULTY getDifficultyBoard() {
		return difficultyBoard;
	}

	public void setDifficultyBoard(DIFFICULTY difficultyBoard) {
		this.difficultyBoard = difficultyBoard;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(int currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}
	
	public ArrayList<Integer> getSurpriseSquarePositions() {
		return surpriseSquarePositions;
	}

	public void setSurpriseSquarePositions(ArrayList<Integer> surpriseSquarePositions) {
		this.surpriseSquarePositions = surpriseSquarePositions;
	}

	public void generateBoard() {
		
		int boardSize = 0;
		switch(this.difficultyBoard) {
			case EASY:
				boardSize = 7;
			break;
			case MEDIUM:
				boardSize = 10;
			break;
			case HARD:
				boardSize = 13;
			break;
		}
		this.squares = new Square[boardSize][boardSize];
		this.surpriseSquarePositions = new ArrayList<>();
		this.questionSquarePositions = new ArrayList<>();
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				double squareChance = Math.random();
				String squareType = "";
				if(squareChance < 0.1) {
					this.squares[i][j] = new QuesSquare(i,j);
					squareType = "Question Square";
					surpriseSquarePositions.add(j + boardSize*i);
				} else if(squareChance >= 0.1 && squareChance < 0.2) {
					this.squares[i][j] = new SurpriseSquare(i,j);	
					squareType = "Surprise Square";
					questionSquarePositions.add(j + boardSize*i);
				} else {
					this.squares[i][j] = new Square(i,j);
					squareType = "Normal Square";
				}
				System.out.println("Square at " + (j + boardSize*i) + " is: " + squareType);
			}
		}
		printBoard();
//		for(int i=0;i<surpriseSquarePositions.size();i++) {
//			System.out.println(surpriseSquarePositions.get(i));
//		}
	}
	
	private void printBoard() {
	    if (this.squares == null) {
	        System.out.println("The board has not been generated yet.");
	        return;
	    }

	    for (int i = 0; i < this.squares.length; i++) {
	        for (int j = 0; j < this.squares[i].length; j++) {
	            if (this.squares[i][j] instanceof QuesSquare) {
	                System.out.print("? ");
	            } else if (this.squares[i][j] instanceof SurpriseSquare) {
	                System.out.print("! ");
	            } else {
	                System.out.print("+ ");
	            }
	        }
	        System.out.print("\n");
	    }
	}

//	public void rollDice() {
//		String[] options;
//		switch(this.difficultyBoard) {
//			case EASY:
//				options = ["0","1","2","3","4"];
//			break;
//			case MEDIUM:
//				boardSize = 10;
//			break;
//			case HARD:
//				boardSize = 13;
//			break;
//		}
//	}
}