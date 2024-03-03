package Controller;

import javax.swing.JOptionPane;

import Model.Questions;
import Model.Sysdata;
import View.EditQuestionView;
import View.QuestionsView;
import View.ViewQuestionView;

public class QuestionViewController {
	private static Sysdata sysdata= Sysdata.getInstance();
	

	public QuestionViewController() {
			
	}
	
	public static Object[][] getTableData() {
		
        // Retrieve data from sysdata or perform any necessary operations to get the data
        Object[][] data = new Object[sysdata.questionsList.size()][8]; // Assuming each question has 8 fields
        int index = 0;
        for (Questions qd : sysdata.questionsList) {
        	
            // Add row data
        	if(qd.getDifficulty()==1) {
        		data[index][0] = "easy";
        	}
        	else if(qd.getDifficulty()==2) {
        		data[index][0] = "medium";
        	}
        	else if(qd.getDifficulty()==3) {
        		data[index][0] = "hard";

        	}
            data[index][1] = qd.getQuestion();
            data[index][2] = qd.getAnswers().get(0);
            data[index][3] = qd.getAnswers().get(1);
            data[index][4] = qd.getAnswers().get(2);
            data[index][5] = qd.getAnswers().get(3);
            data[index][6] = qd.getCorrect_ans();
            index++; 
    }

        return data;
	}
	
	public static void onEdit (int id) {
		System.out.println("we are in onEdit\n");
		System.out.println(sysdata.questionsList.toString());
		Questions selectedQuestion = sysdata.questionsList.get(id);
        // Open the edit page passing the selected question
        EditQuestionView editPage = new EditQuestionView(selectedQuestion);
        editPage.setVisible(true);
	}
	public static void onView (int id) {
		System.out.println("we are in onViewt\n");

		System.out.println(sysdata.questionsList.toString());
		Questions selectedQuestion = sysdata.questionsList.get(id);
        // Open the edit page passing the selected question
        ViewQuestionView editPage = new ViewQuestionView(selectedQuestion);
        editPage.setVisible(true);
	}
	
	public static Boolean onDelete (int id) {
		System.out.println("we are in onDelete\n");

		System.out.println(sysdata.questionsList.toString());
		Boolean n= sysdata.deleteQuestion(id);
		if (n) {
			JOptionPane.showMessageDialog(null, "Item deleted successfully.");
			QuestionsView editPage = new QuestionsView();
	        editPage.setVisible(true);
			return n;
            
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete the item.");
            return n;
        }
	}
	
}

