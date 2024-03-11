package Model;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Scores extends JSONSerializable{
	private static int idCounter = 1;
	private int gameID;
	private int difficulty;
	private Board boardgame;
	private String winningPlayer;
	private int score;
	private String duration; //??
	
	
	public Scores(int gameID, DIFFICULTY difficulty, String winningPlayer, int score, String duration) {
		super();
		this.gameID = ++idCounter;
		this.difficulty = (difficulty == DIFFICULTY.EASY ? 1 : difficulty == DIFFICULTY.MEDIUM ? 2 : difficulty == DIFFICULTY.HARD ? 3 : 1);
		this.winningPlayer = winningPlayer;
		this.score = score;
		this.duration = duration;
	}
	
	public Scores(DIFFICULTY difficulty, Board board, Player winningPlayer, int score, String timer) {
		super();
		this.gameID = ++idCounter;
		this.boardgame = board;
		this.difficulty = difficulty == DIFFICULTY.EASY ? 1 : difficulty == DIFFICULTY.MEDIUM ? 2 : difficulty == DIFFICULTY.HARD ? 3 : 1;
		this.winningPlayer = winningPlayer.getPlayername();
		this.score=score;
		this.duration = timer;
	}
	
	public Scores() {
		this.gameID=++idCounter;
	}
	
	public Scores(JsonObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {

		}
	}


	public Scores(DIFFICULTY difficulty,Board boardgame,  String winningPlayer, int score, String duration) {
		super();
		this.gameID = ++idCounter;
		this.difficulty = difficulty == DIFFICULTY.EASY ? 1 : difficulty == DIFFICULTY.MEDIUM ? 2 : difficulty == DIFFICULTY.HARD ? 3 : 1;
		this.boardgame = boardgame;
		this.winningPlayer = winningPlayer;
		this.score = score;
		this.duration = duration;
	}
	

	@Override
	public void fromJSON(JsonObject jsonObject) {
		this.gameID = Integer.parseInt(jsonObject.get("gameID").toString());
		this.difficulty = Integer.parseInt(jsonObject.get("difficulty").toString());
		this.winningPlayer = jsonObject.get("winningPlayer").getAsString();
		this.duration = (String) jsonObject.get("duration").getAsString();
		this.score =Integer.parseInt( jsonObject.get("score").toString());
	}
	
	@Override
	public JsonObject toJSON() {
		JsonObject gameScore = new JsonObject();
		JsonArray jsonArray = new JsonArray();

		gameScore.addProperty("gameID", this.gameID);
		gameScore.addProperty("difficulty", this.difficulty);
		gameScore.addProperty("winningPlayer", this.winningPlayer);
		gameScore.addProperty("duration", this.duration);
		gameScore.addProperty("score", this.score);
		return gameScore;
	}
	




	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Scores.idCounter = idCounter;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Board getBoardgame() {
		return boardgame;
	}

	public void setBoardgame(Board boardgame) {
		this.boardgame = boardgame;
	}

	public String getWinningPlayer() {
		return winningPlayer;
	}

	public void setWinningPlayer(String winningPlayer) {
		this.winningPlayer = winningPlayer;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Scores [gameID=" + gameID + ", difficulty=" + difficulty + ", boardgame=" + boardgame
				+ ", winningPlayer=" + winningPlayer + ", score=" + score + ", duration=" + duration + "]";
	}

	


}
