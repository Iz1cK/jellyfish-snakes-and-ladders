package Model;

public class Player {
	private int playerID; //player id which represents an unique identifier for the player 
	private String playername; //player name which represents the players chosen username
	private PLAYERCOLORS color;
	
	
	//constructors
	public Player(int playerID, String playername, PLAYERCOLORS color) {
		super();
		this.playerID = playerID;
		this.playername = playername;
		this.color = color;
	}
	



	public Player() {
		super();
	}

	public PLAYERCOLORS getColor() {
		return color;
	}

	public void setColor(PLAYERCOLORS color) {
		this.color = color;
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

	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", playername=" + playername + ", color=" + color + "]";
	}
	
}

