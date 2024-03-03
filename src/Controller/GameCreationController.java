package Controller;

import java.util.ArrayList;

import Model.DIFFICULTY;
import Model.Player;

public class GameCreationController {
	
	DIFFICULTY difficultyLevel;
	public void setDifficultyLevel(DIFFICULTY difficulty) {
        this.difficultyLevel = difficulty;
       // System.out.println(difficulty);
    }
	
	ArrayList<Player> players=new ArrayList<Player>();
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		//System.out.println(players);
	}

	@Override
	public String toString() {
		return "GameCreationController [difficultyLevel=" + difficultyLevel + ", players=" + players + "]";
	}
	
	
	
}
