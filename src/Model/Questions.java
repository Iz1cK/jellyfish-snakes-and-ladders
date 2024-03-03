package Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Questions extends JSONSerializable {
	private int questionId; //question id which represents an unique identifier for the question
	private String question; //represents the questions content
	private List<String> answers = new ArrayList<String>(); //holds the question�s answers.
	private int correct_ans; //represents the index of the correct answer. in the answers data structure.
	private int difficulty; //the questions difficulty, values could be one of {EASY,MEDIUM,HARD}.

	public Questions(int questionId, String question, List<String> answers, int correct_ans, int difficulty) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.difficulty = difficulty;
	}

	public Questions() {
		super();
	}


	public Questions (JsonObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {
			
		}
	}

	public void fromJSON(JsonObject jsonObject) {
		 this.question = jsonObject.get("question").getAsString();

		try {
			JsonArray jsonArray = (JsonArray) jsonObject.get("answers");
			answers.clear();
			for (int i = 0; i < jsonArray.size(); i++) {
			    answers.add(jsonArray.get(i).getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		this.correct_ans = Integer.parseInt(jsonObject.get("correct_ans").toString());
		this.difficulty = Integer.parseInt(jsonObject.get("difficulty").toString());
	}
	
	

	public JsonObject toJSON() {
		JsonObject question = new JsonObject();
		JsonArray jsonArray = new JsonArray();

		question.addProperty("question", this.question);
		for (int i = 0; i < answers.size(); i++) {
			jsonArray.add(answers.get(i));
		}
		question.add("answers", jsonArray);
		question.addProperty("correct_ans", this.correct_ans);
		question.addProperty("difficulty", this.difficulty);
		return question;
	}

	
	 /**
	 * A function to check if a players answer to a questions is correct or not, 
	 * as an input it takes the answer number of the players choice
	 * compares it to the defined correct answer stored in memory
	 * and returns true or false accordingly 
	 */
	public boolean checkAnswer(int inputAnswer)
	{
		 if (inputAnswer==this.correct_ans)
			 return true;
		 else 
			 return false;
	}

	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	public int getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(int correct_ans) {
		this.correct_ans = correct_ans;
	}


	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", question=" + question + ", answers=" + answers
				+ ", correct_ans=" + correct_ans + ", difficulty=" + difficulty + "]" +'\n';
	}
	
}