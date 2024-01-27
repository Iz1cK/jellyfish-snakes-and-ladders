package Model;

public class Questions {
	private int questionId; //question id which represents an unique identifier for the question
	private String question; //represents the questions content
	private String[] answers; //holds the question’s answers.
	private int correctAnser; //represents the index of the correct answer. in the answers data structure.
	private DIFFICULTY questionDifficulty; //the questions difficulty, values could be one of {EASY,MEDIUM,HARD}.
	private int correctMove; //represents how many squares to move forward if answered correctly
	private int wrongMove; //represents how many squares to move backwards if answered incorrectly.

}