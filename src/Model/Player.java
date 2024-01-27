package Model;

public class Player {
	private int playerID; //player id which represents an unique identifier for the player 
	private String playername; //player name which represents the players chosen username
	
	//constructor
	public Player(int playerID, String playername) {
		super();
		this.playerID = playerID;
		this.playername = playername;
	}
	
	//getter & setter
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}

	//to String
	@Override
	public String toString() {
		return "player [playerID=" + playerID + ", playername=" + playername + "]";
	}
	
	
	
	
}

