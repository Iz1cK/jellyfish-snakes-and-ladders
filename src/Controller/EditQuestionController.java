package Controller;

import java.util.List;

import Model.Questions;
import Model.Sysdata;


public class EditQuestionController {
	private static EditQuestionController _instance;
	
	Sysdata sysdata= Sysdata.getInstance();
	
	public static EditQuestionController getInstance() {
		if (_instance == null)
			_instance = new EditQuestionController();
		return _instance;
	}
	
	
	

	public void updateQuestion(int questionid, String que, List<String> answers, int correctAns, int difficulty) {
		// TODO Auto-generated method stub
		Questions updatedQuestion= new Questions(questionid, que, answers, correctAns, difficulty);
        sysdata.updateQuestion(questionid, updatedQuestion);
		
	}
	
	public Boolean isDulicated(String questionBody, int questionid) {
		boolean result=sysdata.isDuplicateQuestion2(questionBody, questionid);
		return result;
		
	}



}
