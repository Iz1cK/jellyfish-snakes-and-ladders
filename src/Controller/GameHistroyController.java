package Controller;

import Model.Game;
import Model.Sysdata;

public class GameHistroyController {
private static Sysdata sysdata= Sysdata.getInstance();
	

	public GameHistroyController() {
		sysdata.readGameHistory();;	
	}
	
	public static Object[][] getTableData() {
        // Retrieve data from sysdata or perform any necessary operations to get the data
        Object[][] data = new Object[sysdata.gameHistoryList.size()][8]; // Assuming each question has 8 fields
        int index = 0;
        int i=1;
        for (Game qd : sysdata.gameHistoryList) {
        	
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
            data[index][2] = qd.getPlayers();
            data[index][3] = qd.getWinningPlayer();
            data[index][4] = qd.getDuration();
            index++; 
            i++;
    }

        return data;
	}
	

}
