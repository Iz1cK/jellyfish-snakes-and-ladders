package Model;

import java.time.LocalDateTime;

public class Game {
	private int gameID;
	private DIFFICULTY difficultyGame;
	private Board boardgame;
	private Player winningPlayer;
	private LocalDateTime timer;
	public Game(int gameID, DIFFICULTY difficultyGame, Board boardgame, Player winningPlayer, LocalDateTime timer) {
		super();
		this.gameID = gameID;
		this.difficultyGame = difficultyGame;
		this.boardgame = boardgame;
		this.winningPlayer = winningPlayer;
		this.timer = timer;
	}

}
