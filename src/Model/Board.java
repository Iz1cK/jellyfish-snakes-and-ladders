package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
	
    private static int lastBoardId = 0; // Static variable to track the last used board ID

	private int boardId;
	private Square[][] squares;
	private ArrayList<Integer> surpriseSquarePositions;
	private ArrayList<Integer> questionSquarePositions;
	private ArrayList<Ladder> ladders;
	private ArrayList<Player> players;
	private ArrayList<Snake> snakes;
	private HashMap<Player,Integer> playersPositions;
	private DIFFICULTY difficultyBoard;
	private int rows;
	private int columns;
	private Player currentPlayerTurn;
	private ArrayList<String> diceOptions;
	
	// Main Board constructor, creates all basic board elements from the difficulty, like rows and columns , and then initializes all other data structures
	public Board(DIFFICULTY difficultyBoard) {
		super();
		this.boardId = ++lastBoardId;
		this.difficultyBoard = difficultyBoard;
		this.diceOptions = new ArrayList<>();
		switch(difficultyBoard) {
			case EASY:
				this.rows = 7;
				this.columns = 7;
				String[] easyOptions = {"0","1","2","3","4","E","M","H"};
				List<String> easyList = Arrays.asList(easyOptions);
				this.diceOptions.addAll(easyList);
			break;
			case MEDIUM:
				this.rows = 10;
				this.columns = 10;
				String[] mediumOptions = {"0","1","2","3","4","5","6","E","E","M","M","H","H"};
				List<String> mediumList = Arrays.asList(mediumOptions);
				this.diceOptions.addAll(mediumList);
			break;
			case HARD:
				this.rows = 13;
				this.columns = 13;
				String[] hardOptions = {"0","1","2","3","4","5","6","E","E","M","M","H","H","H","H"};
				List<String> hardList = Arrays.asList(hardOptions);
				this.diceOptions.addAll(hardList);
			break;
		}
		this.squares = new Square[this.rows][this.columns];
		this.surpriseSquarePositions = new ArrayList<>();
		this.questionSquarePositions = new ArrayList<>();
		this.ladders = new ArrayList<>();
		this.players = new ArrayList<>();
		this.snakes = new ArrayList<>();
		this.playersPositions = new HashMap<>();
		this.currentPlayerTurn = new Player();
	}
	
	
	public ArrayList<Integer> getQuestionSquarePositions() {
		return questionSquarePositions;
	}

	public void setQuestionSquarePositions(ArrayList<Integer> questionSquarePositions) {
		this.questionSquarePositions = questionSquarePositions;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = null; // Initialize the board as null
        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Create and Generate Board");
            System.out.println("2 - Roll Dice");
            System.out.println("3 - Display Board");
            System.out.println("4 - Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Handling the create and generate board option
                    System.out.println("Creating a new board.");
                    DIFFICULTY difficulty = DIFFICULTY.EASY;
                    boolean valid = false;
                    while (!valid) {
                        System.out.println("Select difficulty (1 - Easy, 2 - Medium, 3 - Hard):");
                        int difficultyChoice = scanner.nextInt();
                        switch (difficultyChoice) { // deciding the difficulty of the board
                            case 1:
                                difficulty = DIFFICULTY.EASY;
                                valid = true;
                                break;
                            case 2:
                                difficulty = DIFFICULTY.MEDIUM;
                                valid = true;
                                break;
                            case 3:
                                difficulty = DIFFICULTY.HARD;
                                valid = true;
                                break;
                            default:
                                System.out.println("Invalid difficulty selected. Please try again.");
                                break;
                        }
                    }
                    board = new Board(difficulty); // initializes the board
                    board.generateBoard(); // generates the squares for the board
                    System.out.println("Board created with difficulty: " + difficulty);
                    break;
                case 2: // Rolls the dice and prints the result
                    if (board == null) {
                        System.out.println("Please create a board first.");
                    } else {
                        String result = board.rollDice(); // rolls the dice
                        System.out.println("Rolled: " + result);
                    }
                    break;
                case 3: // Prints the squares of the board
                    if (board == null) {
                        System.out.println("Please create and generate a board first.");
                    } else {
                        board.printBoard();
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        scanner.close();
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

	public ArrayList<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(ArrayList<Ladder> ladders) {
		this.ladders = ladders;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(ArrayList<Snake> snakes) {
		this.snakes = snakes;
	}

	public HashMap<Player,Integer> getPlayersPositions() {
		return playersPositions;
	}

	public void setPlayersPositions(HashMap<Player,Integer> playersPositions) {
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

	public Player getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(Player currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}
	
	public ArrayList<Integer> getSurpriseSquarePositions() {
		return surpriseSquarePositions;
	}

	public void setSurpriseSquarePositions(ArrayList<Integer> surpriseSquarePositions) {
		this.surpriseSquarePositions = surpriseSquarePositions;
	}

	public ArrayList<String> getDiceOptions() {
		return diceOptions;
	}

	public void setDiceOptions(ArrayList<String> diceOptions) {
		this.diceOptions = diceOptions;
	}

	/*
	 * generateBoard()
	 * this function generates the squares of the board,
	 * since the board is numbered in an ascending way in 
	 * even rows and a descending way in odd rows, 
	 * we need to keep flipping the inner loops "direction"
	 * from left to right, and the outer loop is descending so 
	 * that we start from the end since visually the board starts
	 * at the bottom(rows,columns) not the top(0,0).
	 */
	public void generateBoard() {
		boolean leftToRight = false; 
		int count = 0;
		for (int i = this.rows - 1; i >= 0; i--) {
		    leftToRight = !leftToRight;
		    for (int j = 0; j < this.columns; j++) {
		        int columnIndex = leftToRight ? j : this.columns - 1 - j;
		        double squareChance = Math.random();
//		        String squareType = "";
		        if(squareChance < 0.1) {
		            this.squares[i][columnIndex] = new QuesSquare(i,columnIndex);
		            this.squares[i][columnIndex].calculatePosition(this.rows);
//		            squareType = "Question Square";
		            surpriseSquarePositions.add(count);
		        } else if(squareChance >= 0.1 && squareChance < 0.2) {
		            this.squares[i][columnIndex] = new SurpriseSquare(i,columnIndex);
		            this.squares[i][columnIndex].calculatePosition(this.rows);
//		            squareType = "Surprise Square";
		            questionSquarePositions.add(count);
		        } else {
		            this.squares[i][columnIndex] = new Square(i,columnIndex);
		            this.squares[i][columnIndex].calculatePosition(this.rows);
//		            squareType = "Normal Square";
		        }
		        count++;
//		        System.out.println("Square at " + count + " is: " + squareType);
		    }
		}
	}
	
	/*
	 * printBoard()
	 * a function that loops over the squares of the
	 * board and prints ? for question squares
	 * ! for surprise squares
	 * and + for normal squares
	 */
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
	/*
	 * rollDice()
	 * this function randomly picks an option from
	 * the dice that is generated in the constructor
	 * and decided by the difficulty of the game
	 */
	public String rollDice() {
		int roll = getRandom(0,this.diceOptions.size());
		String result = this.diceOptions.get(roll);
		return result;
	}
	
	/*
	 * movePlayer(Player player, int position)
	 * this function changes the player's position
	 * on the board to the position parameter
	 */
	public void movePlayer(Player player, int position) {
		this.playersPositions.put(player, position);
	}
	
	/*
	 * checkLandingSquare(Square target)
	 * this function returns the type of Square
	 * the target Square is
	 */
	public String checkLandingSquare(Square target) {
		if (target instanceof QuesSquare) {
			return "QuestionSquare";
        } else if (target instanceof SurpriseSquare) {
        	return "SurpriseSquare";
        }
        return "NormalSquare";
	}
	
	/*
	 * checkWinner()
	 * this function checks if a player has reached 
	 * the end of the board and returns the Player object
	 */
	
	public Player checkWinner() {
		Player winner = null;
		for (Player p : players) {
			if(this.playersPositions.get(p) == this.rows*this.columns) {
				winner = p;
			}
		}
		return winner;
	}
	
	/*
	 * getRandom(int from, int to)
	 * this function returns a random number
	 * in the range of from and to
	 */
	
	private static int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}
	
	
}