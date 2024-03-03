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
	// we fill the table with the questions we got from json file
	public static Object[][] getTableData() {
		Object[][] data = new Object[sysdata.getQuestionsList().size()][8];
        int index = 0;
        for (Questions qd : sysdata.getQuestionsList()) {
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
            data[index][2] = qd.getAnswers().get((qd.getCorrect_ans())-1);
         //   data[index][3] = qd.getAnswers().get(1);
          //  data[index][4] = qd.getAnswers().get(2);
           // data[index][5] = qd.getAnswers().get(3);
            //data[index][6] = qd.getCorrect_ans();
            index++; 
            }
        return data;
        }
	//we open the EditQuestionView to edit the question
	public static void onEdit (int id) {
		Questions selectedQuestion = sysdata.getQuestionsList().get(id);
        // Open the edit page passing the selected question
        EditQuestionView editPage = new EditQuestionView(selectedQuestion);
        editPage.setVisible(true);
        }
	//we open the ViewQuestionView to see the question
	public static void onView (int id) {
		Questions selectedQuestion = sysdata.questionsList.get(id);
        ViewQuestionView editPage = new ViewQuestionView(selectedQuestion);
        editPage.setVisible(true);
        }
	
	public static Boolean onDelete (int id) {
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

