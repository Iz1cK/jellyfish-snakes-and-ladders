package Controller;

import Model.Game;
import Model.Scores;
import Model.Sysdata;

public class GameScoreController {
private static Sysdata sysdata= Sysdata.getInstance();
	

	public GameScoreController() {
		sysdata.readGameHistory();;	
	}
	
	public static Object[][] getTableData() {
		System.out.println(sysdata.scoreList.toString());
        // Retrieve data from sysdata or perform any necessary operations to get the data
        Object[][] data = new Object[sysdata.scoreList.size()][8]; // Assuming each question has 8 fields
        int index = 0;
        int i=1;
        for (Scores qd : sysdata.scoreList) {
        	
            // Add row data
        	if(qd.getDifficulty()==1) {
        		data[index][1] = "easy";
        	}
        	else if(qd.getDifficulty()==2) {
        		data[index][1] = "medium";
        	}
        	else if(qd.getDifficulty()==3) {
        		data[index][1] = "hard";

        	}
            data[index][0] = i;
            data[index][2] = qd.getWinningPlayer();
            data[index][3] = qd.getScore();
            data[index][4] = qd.getDuration();
            index++; 
            i++;
    }

        return data;
	}
	

}
