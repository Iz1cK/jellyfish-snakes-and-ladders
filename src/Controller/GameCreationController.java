package Controller;

import java.util.ArrayList;

import Model.Player;

public class GameCreationController {
	
	
	private static GameCreationController GCCinstance = null;
	
	private GameCreationController() {}
	
	public static synchronized GameCreationController getInstance()
    {
        if (GCCinstance == null)
        	GCCinstance = new GameCreationController();
        return GCCinstance;
    }
	
	
	ArrayList<Player> players=new ArrayList<Player>();
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		System.out.println(players);
	}

}
