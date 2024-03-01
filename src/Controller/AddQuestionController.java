package Controller;

import java.util.List;

import Model.Questions;
import Model.Sysdata;

public class AddQuestionController {
	private static AddQuestionController instance;
	public static AddQuestionController getInstance() {
		if (instance == null)
			instance = new AddQuestionController();
		return instance;
	}
	Sysdata sysdata= Sysdata.getInstance();
	
	public Boolean isDulicated(String questionBody) {
		boolean result=sysdata.isDuplicateQuestion(questionBody);
		return result;
		
	}
	public void addQuestion(String question, List<String> answers, int correctAns, int difficulty) {
		 Questions que=new Questions(0, question, answers, correctAns, difficulty);
		 sysdata.addQuestion(que);
		
		
	}

}
