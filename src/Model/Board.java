package Model;

public class Board {
	private int boardId;
	private Square[] squares;
	private Ladder[] ladders;
	private Player[] Players;
	private Snake[] snakes;
	private int[] playersPositions;
	private DIFFICULTY difficultyBoard;
	private int rows;
	private int columns;
	private int currentPlayerTurn;
}