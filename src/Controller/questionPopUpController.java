package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Questions;
import Model.Sysdata;
import Utils.QuestionCallback;
import View.QuestionPopUp;
import View.Test;

public class questionPopUpController {
	Sysdata sysdata= Sysdata.getInstance();
	Test test;
	String question;
	String answer1;
	String answer2;
	String answer3;
	String answer4;
	public List<Questions> Questions= new ArrayList<>();
	Random random = new Random();
    public boolean isCorrectAns;
    public int level;
	
	public boolean isCorrectAns() {
		return isCorrectAns;
	}

	public void setCorrectAns(boolean isCorrectAns) {
		this.isCorrectAns = isCorrectAns;
	}

	private static questionPopUpController instance;
	public static questionPopUpController getInstance() {
		if (instance == null)
			instance = new questionPopUpController();
		return instance;
	}
	
	public void questionRank(int diffculty,QuestionCallback callback) {
		level=diffculty;
		for (Questions qd : sysdata.questionsList) {
			if(qd.getDifficulty()==diffculty) {
				Questions.add(qd);
			}
		}
		if (!Questions.isEmpty()) {
		    // Generate a random index within the bounds of the list's size
		    int randomIndex = random.nextInt(Questions.size());

		    // Retrieve the question at the random index
		    Questions randomQuestion = Questions.get(randomIndex);
		    question= randomQuestion.getQuestion();
		    answer1= randomQuestion.getAnswers().get(0);
			answer2= randomQuestion.getAnswers().get(1);
			answer3= randomQuestion.getAnswers().get(2);
			answer4= randomQuestion.getAnswers().get(3);
		    // Now you have a random question from the easyQuestion list
		    // Do something with the random question
			
			QuestionPopUp obj = new QuestionPopUp(callback, diffculty);
	        obj.cmdobt1.setText(answer1);
	        obj.cmdobt2.setText(answer2);
	        obj.cmdobt3.setText(answer3);
	        obj.cmdobt4.setText(answer4);
	        String questionBody=question;
	        obj.correctAns=randomQuestion.getCorrect_ans();
	        obj.difficulty=diffculty;
	        if(diffculty==1) {
	        obj.showMessage("easy", questionBody);
	        }
	        else if(diffculty==2) {
	        	obj.showMessage("medium", questionBody);	
	        }
	        else if(diffculty==3) {
	        	obj.showMessage("hard", questionBody);	
	        }
	        
		} else {
		    // Handle the case where the easyQuestion list is empty
		    System.out.println("No questions available in the Question list.");
		}
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}


	

}
