package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game {
	private static int idCounter = 1;
	private int gameID;
	private int difficulty;
	private Board boardgame;
	private String winningPlayer;
	private List<String> Players = new ArrayList<String >();
	private String duration; //??
	public Game(int gameID, int difficulty, Player winningPlayer, List<Player> players, LocalDateTime timer) {
		super();
		this.gameID = ++gameID;
		this.difficulty = difficulty;
		this.winningPlayer = winningPlayer.getPlayername();
		 for (Player player : players) {
	            Players.add(player.getPlayername());
	        }
		this.duration = convertLocalDatetimeToString(timer);
	}
	
	private String convertLocalDatetimeToString(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = dateTime.format(formatter);
		return formattedDateTime;
	}
	
	public Game(JSONObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {

		}
	}

	public void fromJSON(JSONObject jsonObject) {
		this.gameID = Integer.parseInt(jsonObject.get("gameID").toString());
		this.difficulty = Integer.parseInt(jsonObject.get("difficulty").toString());
		try {
			JSONArray jsonArray = (JSONArray) jsonObject.get("Players");
			Players.clear();
			for (int i = 0; i < jsonArray.size(); i++) {

				Players.add((String) jsonArray.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		this.winningPlayer = (String) jsonObject.get("winningPlayer");
		this.duration = (String) jsonObject.get("duration");
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject gameHistory = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		gameHistory.put("gameID", this.gameID);
		gameHistory.put("difficulty", this.difficulty);
		for (int i = 0; i < Players.size(); i++) {
			jsonArray.add(Players.get(i));
		}
		gameHistory.put("Players", jsonArray);
		gameHistory.put("winningPlayer", this.winningPlayer);
		gameHistory.put("duration", this.duration);
		return gameHistory;
	}

	public Game(int gameID, int difficulty, Board boardgame, String winningPlayer, List<String> players,
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

	public List<String> getPlayers() {
		return Players;
	}

	public void setPlayers(List<String> players) {
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
