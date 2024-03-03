package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Game extends JSONSerializable{
	private static int idCounter = 1;
	private int gameID;
	private int difficulty;
	private Board boardgame;
	private String winningPlayer;
	private ArrayList<String> Players = new ArrayList<String >();
	private String duration; //??
	public Game(DIFFICULTY difficulty, Player winningPlayer, ArrayList<Player> players, String timer) {
		super();
		this.gameID = ++idCounter;
		this.difficulty = difficulty == DIFFICULTY.EASY ? 1 : difficulty == DIFFICULTY.MEDIUM ? 2 : difficulty == DIFFICULTY.HARD ? 3 : 1;
		this.winningPlayer = winningPlayer.getPlayername();
		 for (Player player : players) {
	            Players.add(player.getPlayername());
	        }
		this.duration = timer;
	}
	
	public Game(DIFFICULTY difficulty, Board board, Player winningPlayer, ArrayList<Player> players, String timer) {
		super();
		this.gameID = ++idCounter;
		this.boardgame = board;
		this.difficulty = difficulty == DIFFICULTY.EASY ? 1 : difficulty == DIFFICULTY.MEDIUM ? 2 : difficulty == DIFFICULTY.HARD ? 3 : 1;
		this.winningPlayer = winningPlayer.getPlayername();
		 for (Player player : players) {
	            Players.add(player.getPlayername());
	        }
		this.duration = timer;
	}
	
	public Game() {
		this.gameID=++idCounter;
	}
	
	public Game(JsonObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {

		}
	}

	@Override
	public void fromJSON(JsonObject jsonObject) {
		this.gameID = Integer.parseInt(jsonObject.get("gameID").toString());
		this.difficulty = Integer.parseInt(jsonObject.get("difficulty").toString());
		try {
			JsonArray jsonArray = (JsonArray) jsonObject.get("Players");
			Players.clear();
			for (JsonElement element : jsonArray) {
			    Players.add(element.getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		this.winningPlayer = jsonObject.get("winningPlayer").getAsString();
		this.duration = (String) jsonObject.get("duration").getAsString();
	}
	@Override
	public JsonObject toJSON() {
		JsonObject gameHistory = new JsonObject();
		JsonArray jsonArray = new JsonArray();

		gameHistory.addProperty("gameID", this.gameID);
		gameHistory.addProperty("difficulty", this.difficulty);
		for (int i = 0; i < Players.size(); i++) {
			jsonArray.add(Players.get(i));
		}
		gameHistory.add("Players", jsonArray);
		gameHistory.addProperty("winningPlayer", this.winningPlayer);
		gameHistory.addProperty("duration", this.duration);
		return gameHistory;
	}

	public Game(int gameID, int difficulty, Board boardgame, String winningPlayer, ArrayList<String> players,
			String duration) {
		super();
		this.gameID = gameID;
		this.difficulty = difficulty;
		this.boardgame = boardgame;
		this.winningPlayer = winningPlayer;
		Players = players;
		this.duration = duration;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Game.idCounter = idCounter;
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

	public ArrayList<String> getPlayers() {
		return Players;
	}

	public void setPlayers(ArrayList<String> players) {
		Players = players;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Game [gameID=" + gameID + ", difficulty=" + difficulty + ", boardgame=" + boardgame + ", winningPlayer="
				+ winningPlayer + ", Players=" + Players + ", duration=" + duration + "]";
	}

}
