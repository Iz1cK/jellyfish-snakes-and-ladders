package Model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Questions {
	private int questionId; //question id which represents an unique identifier for the question
	private String question; //represents the questions content
	private List<String> answers = new ArrayList<String>(); //holds the question’s answers.
	private int correct_ans; //represents the index of the correct answer. in the answers data structure.
	private int difficulty; //the questions difficulty, values could be one of {EASY,MEDIUM,HARD}.
	//private int correctMove; //represents how many squares to move forward if answered correctly
	//private int wrongMove; //represents how many squares to move backwards if answered incorrectly.
	public Questions(int questionId, String question, List<String> answers, int correct_ans, int difficulty) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.difficulty = difficulty;
	}
	

	public Questions (JSONObject jsonObject) {
		try {
			fromJSON(jsonObject);
		} catch (Exception e) {

		}

	}


	public void fromJSON(JSONObject jsonObject) {
		this.question = (String) jsonObject.get("question");

		try {
			JSONArray jsonArray = (JSONArray) jsonObject.get("answers");
			answers.clear();
			for (int i = 0; i < jsonArray.size(); i++) {

				answers.add((String) jsonArray.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		this.correct_ans = Integer.parseInt(jsonObject.get("correct_ans").toString());
		this.difficulty = Integer.parseInt(jsonObject.get("difficulty").toString());
//		System.out.println(correctAnswer+" "+level");
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject question = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		question.put("question", this.question);
		for (int i = 0; i < answers.size(); i++) {
			jsonArray.add(answers.get(i));
		}
		question.put("answers", jsonArray);
		question.put("correct_ans", this.correct_ans);
		question.put("difficulty", this.difficulty);
		return question;
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
				+ ", correct_ans=" + correct_ans + ", difficulty=" + difficulty + "]";
	}


	
	
	
}