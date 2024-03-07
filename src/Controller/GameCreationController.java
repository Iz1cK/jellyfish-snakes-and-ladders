package Controller;

import java.util.ArrayList;

import Model.Player;
import Model.Questions;
import Model.Sysdata;

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
		//System.out.println(players);
	}

	public boolean minimumQuestionsRequirment() {
		boolean easy=false,medium=false,hard=false;
		for(Questions q : Sysdata.getInstance().questionsList)
		{
			if(q.getDifficulty()==1)
				easy=true;
			if(q.getDifficulty()==2)
				easy=true;
			if(q.getDifficulty()==3)
				easy=true;
		}
		if(easy&&medium&&hard)
			return true;
		return false;
	}
}
