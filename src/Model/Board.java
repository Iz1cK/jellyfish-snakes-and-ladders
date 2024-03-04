package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Controller.SurpriseSquareController;

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
	public Board(DIFFICULTY difficultyBoard, ArrayList<Player> players) {
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
		this.playersPositions = new HashMap<>();
		this.ladders = new ArrayList<>();
		if(players == null) {
			this.players = new ArrayList<>();
		} else this.players = players; 
		for(Player p : players) {
			this.playersPositions.put(p, 1);
		}
		this.snakes = new ArrayList<>();
		this.currentPlayerTurn = players.get(0);
	}

	public ArrayList<Integer> getQuestionSquarePositions() {
		return questionSquarePositions;
	}

	public void setQuestionSquarePositions(ArrayList<Integer> questionSquarePositions) {
		this.questionSquarePositions = questionSquarePositions;
	}

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(0,"george",PLAYERCOLORS.BLUE));
		Board board = new Board(DIFFICULTY.MEDIUM, players);
        board.generateBoard();
        board.initiateQuestionSquares();

        board.generateSnakesAndLadder();
        for(int i=0;i<board.getSnakes().size(); i++) {
        	System.out.println(board.getSnakes().get(i));
        }
        for(int i=0;i<board.getLadders().size(); i++) {
        	System.out.println(board.getLadders().get(i));
        }
    }
	
	public void initiateQuestionSquares() {
        this.assignQuestionToSquare(0);   // Assuming index 1 is intentional; adjust if needed

	}
	
	public void assignQuestionToSquare(int num) {
	    int[] eindexes = this.getIndexesByPosition(this.questionSquarePositions.get(num));
	    squares[eindexes[0]][eindexes[1]] = new QuesSquare(eindexes[0], eindexes[1], num+1);
	    int[] mindexes = this.getIndexesByPosition(this.questionSquarePositions.get(num+1));
	    squares[mindexes[0]][mindexes[1]] = new QuesSquare(mindexes[0], mindexes[1], num+2);
	    int[] hindexes = this.getIndexesByPosition(this.questionSquarePositions.get(num+2));
	    squares[hindexes[0]][hindexes[1]] = new QuesSquare(hindexes[0], hindexes[1], num+3);
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
	 * the function uses factory method design pattern,
	 * the function itself does not know which kind of squares it is
	 * instantiating, that process is instead done in the SquareFactory class
	 */
	
	public void generateBoard() {
	    boolean leftToRight = this.rows % 2 == 0; 
	    Random random = new Random();
	    SquareFactory factory = new SquareFactory();
	    SquareInterface square;

	    while (questionSquarePositions.size() != 3) {
	        int coin = (random.nextInt(this.rows * this.rows - 4) + 4);
	        if (!questionSquarePositions.contains(coin))
	            questionSquarePositions.add(coin);
	    }
	    
	    int exclusionZoneEnd = this.rows * this.rows;
	    switch (this.getDifficultyBoard()) {
	    case MEDIUM:
	        while (surpriseSquarePositions.size() != 1) {
	            int coin = (random.nextInt(exclusionZoneEnd - 10) + 1);
	            if (!questionSquarePositions.contains(coin) && !surpriseSquarePositions.contains(coin))
	                surpriseSquarePositions.add(coin);
	        }
	        break;
	    case HARD:
	        while (surpriseSquarePositions.size() != 2) {
	            int coin = (random.nextInt(exclusionZoneEnd - 10) + 1);
	            if (!questionSquarePositions.contains(coin) && !surpriseSquarePositions.contains(coin))
	                surpriseSquarePositions.add(coin);
	        }
	        break;
	    default:
	        break;
	    }

	    for (int i = this.rows - 1; i >= 0; i--) {
	        for (int j = 0; j < this.columns; j++) {
	            int columnIndex = leftToRight ? j : this.columns - 1 - j;
	            int position = getPositionByIdenxes(i, columnIndex);

	            if (surpriseSquarePositions.contains(position)) {
	                square = factory.getSquare("SurpriseSquare", i, columnIndex);
	                this.squares[i][columnIndex] = (SurpriseSquare) square;
	            } else if (questionSquarePositions.contains(position)) {
	                square = factory.getSquare("QuestionSquare", i, columnIndex);
	                this.squares[i][columnIndex] = (QuesSquare) square;
	            } else {
	                square = factory.getSquare("Square", i, columnIndex);
	                this.squares[i][columnIndex] = (Square) square;
	            }

	            this.squares[i][columnIndex].calculatePosition(this.rows);
	        }
	        leftToRight = !leftToRight;
	    }
	}
	
	public void generateSnakesAndLadder() {
	    int numberOfRedSnakes = 0;
	    int numberOfGreenSnakes = 0;
	    int numberOfYellowSnakes = 0;
	    int numberOfBlueSnakes = 0;
	    int numberOfLadders = 0;
	    switch(this.difficultyBoard) {
	        case EASY:
	            numberOfGreenSnakes = 1;
	            numberOfBlueSnakes = 1;
	            numberOfYellowSnakes = 1;
	            numberOfRedSnakes = 1;
	            numberOfLadders = 4;
	            break;
	        case MEDIUM:
	            numberOfGreenSnakes = 2;
	            numberOfBlueSnakes = 1;
	            numberOfYellowSnakes = 1;
	            numberOfRedSnakes = 2;
	            numberOfLadders = 6;
	            break;
	        case HARD:
	            numberOfGreenSnakes = 2;
	            numberOfBlueSnakes = 2;
	            numberOfYellowSnakes = 2;
	            numberOfRedSnakes = 2;
	            numberOfLadders = 8;
	            break;
	    }

	    ArrayList<Integer> occupiedPositions = new ArrayList<>();
	    occupiedPositions.addAll(questionSquarePositions);
	    occupiedPositions.addAll(surpriseSquarePositions);
	    occupiedPositions.add(1);
	    int boardSize = this.rows;
	    
	    for (int rank = 1; rank <= numberOfLadders; rank++) {
	        boolean validLadderFound = false;
	        while (!validLadderFound) {
	            int ladderStart = getRandom(2, boardSize * boardSize - 1);
	            int startRow = (ladderStart - 1) / boardSize;
	            int endRow = startRow + rank;
	            
	            if (endRow >= boardSize) continue;

	            int ladderEnd = getRandom(boardSize * endRow + 1, boardSize * (endRow + 1));

	            if (!occupiedPositions.contains(ladderStart) && !occupiedPositions.contains(ladderEnd) && ladderStart != ladderEnd) {
	                Square startSquare = new Square(ladderStart);
	                startSquare.calculateRowAndColumn(this.getRows());
	                Square endSquare = new Square(ladderEnd);
	                endSquare.calculateRowAndColumn(this.getRows());

	                occupiedPositions.add(ladderStart);
	                occupiedPositions.add(ladderEnd);
	                
	                ladders.add(new Ladder("" + rank, startSquare, endSquare));
	                validLadderFound = true;
	            }
	        }
	    }
	    
	    generateSpecificSnakes(COLORS.RED, numberOfRedSnakes, occupiedPositions, 0);
	    generateSpecificSnakes(COLORS.YELLOW, numberOfYellowSnakes, occupiedPositions, 1);
	    generateSpecificSnakes(COLORS.GREEN, numberOfGreenSnakes, occupiedPositions, 2);
	    generateSpecificSnakes(COLORS.BLUE, numberOfBlueSnakes, occupiedPositions, 3);
	}
	
	private void generateSpecificSnakes(COLORS color, int count, ArrayList<Integer> occupiedPositions, int rowsBack) {
	    for (int i = 0; i < count; i++) {
	        int snakeStart;
	        // Adjust the range for snakeStart based on rowsBack to ensure enough space
	        do {
	            int minRowForStart = rowsBack; // Minimum row index where a snake can start
	            int minPositionForStart = rows * minRowForStart + 1; // Convert row index to board position
	            snakeStart = getRandom(minPositionForStart, rows * rows);
	        } while (occupiedPositions.contains(snakeStart));

	        occupiedPositions.add(snakeStart);
	        
	        Square headSquare = new Square(snakeStart);
	        headSquare.calculateRowAndColumn(this.rows);

	        if (color == COLORS.RED) {
	            // Red snakes don't need an end position as they take the player back to the start
	            snakes.add(new Snake("Red" + i, headSquare, null, color));
	        } else {
	            // For non-red snakes, calculate the end position based on the specified rowsBack
	            int startRow = (snakeStart - 1) / rows;
	            int endRow = Math.max(startRow - rowsBack, 0); // Ensure the endRow is at least rowsBack behind

	            int snakeEnd;
	            // Make sure snakeEnd is also within the correct range to drop the player by rowsBack
	            do {
	                snakeEnd = getRandom(rows * endRow + 1, rows * (endRow + 1) - 1); // Adjusted to ensure it's within the end row
	            } while (occupiedPositions.contains(snakeEnd) || snakeStart == snakeEnd);

	            occupiedPositions.add(snakeEnd);
	            Square tailSquare = new Square(snakeEnd);
	            tailSquare.calculateRowAndColumn(this.rows);
	            snakes.add(new Snake(color.toString() + i, headSquare, tailSquare, color));
	        }
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
	
	public void moveFromSurpriseSquare(SurpriseSquareController c) {
		c.movePlayerToDestination(playersPositions, currentPlayerTurn);
		checkLandingSquare(getSquareByPosition(playersPositions.get(currentPlayerTurn)));
	}
	
	public int getPositionByIdenxes(int row, int column) {
        if (row % 2 == 0) { // Even row, numbers increase from left to right
            return row * this.rows + column + 1;
        } else { // Odd row, numbers increase from right to left
            return row * this.rows + (this.rows - column - 1) + 1;
        }
	}
	
	public int[] getIndexesByPosition(int position) {
	    // Adjust position to be 0-indexed
	    position -= 1;
	    
	    int rows = this.rows; // Assuming 'this.rows' is the number of rows in your grid
	    int row = position / rows;
	    int column;

	    if (row % 2 == 0) {
	        // Even row, numbers increase from left to right
	        column = position % rows;
	    } else {
	        // Odd row, numbers increase from right to left
	        column = rows - (position % rows) - 1;
	    }

	    // Adjust row and column to be 1-indexed if necessary
	    return new int[]{row, column};
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
	
	
	public Square getSquareByPosition(int position) {
		Square square = null;
		for(int i = 0; i < this.squares.length; i++) {
			for(int j = 0; j< this.squares[0].length; j++) {
				this.squares[i][j].calculatePosition(this.rows);
				if(this.squares[i][j].getPosition() == position) {
					
					square = this.squares[i][j];
				}
			}
		}
		return square;
	}
	
	
}