package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import Model.Player;


public class Sysdata {
		static final String QUESTIONS_FILENAME = "questions_scheme.json";
		static final String QUESTIONS_JSONOBJECT = "questions";
		static final String GAME_HISTORY_FILENAME = "game_history.json";
		static final String GAME_HISTORY_JSONOBJECT = "gamesHistory";
		static final String ADMINS_FILENAME = "admins.json";
		static final String ADMINS_JSONOBJECT = "admins";
		static final int MAX_CAPACITY_SCORES = 50;

		private static Sysdata instance;
		private List<Questions> questionsList;
		private List <Game> gameHistoryList;
		private List <Admin> adminList; 
		private static int questionID = 0;
		private JsonArray questionsListJson;
		private JsonArray scoresListJson;
		private List<String> historyList;
		private JsonArray historyJson;
		private JsonArray suggestionsJson;
		public static int numberss=0;
		private LinkedList<Game> historyLinkedList=new LinkedList<>();

		public static Sysdata getInstance() {
			if (instance == null)
				instance = new Sysdata();
			return instance;
		}

		/**
		 * function to write data to json file
		 * 
		 * @param objectList the objects in json array
		 * @param objectName before array of questions in json
		 * @param fileName   name of json file
		 */
		@SuppressWarnings("unchecked")
		public void writeJsonFile(JsonArray objectList, String objectName, String fileName) {
			try (FileWriter file = new FileWriter(fileName)) {
				JsonObject obj = new JsonObject();
				obj.addProperty(objectName, objectList.toString());
				file.write(obj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * function to read a json file (name of file is passed as an argument)
		 * 
		 * @param objectName
		 * @param fileName
		 */
		private JsonArray readJsonFile(String objectName, String fileName) throws ParseException {
			try (FileReader reader = new FileReader(fileName)) {
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = null;
				try {
					jsonObject = (JsonObject) parser.parse(reader);
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (JsonArray) jsonObject.get(objectName);
			} catch (FileNotFoundException e) {
				if (objectName.equals(QUESTIONS_JSONOBJECT)) {
					writeJsonFile(questionsListJson, objectName, fileName);
				} else if (objectName.equals(GAME_HISTORY_JSONOBJECT)) {
					writeJsonFile(scoresListJson, objectName, fileName);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new JsonArray();
		}

		// --------------------------------------------------- Question Functions
		// ---------------------------------------------------

		public List<Questions> getQuestionsList() {
			return questionsList;
		}

		public void setQuestionsList(List<Questions> questionsList) {
			this.questionsList = questionsList;
		}

		/**
		 * A function to read the questions, it calls readJsonfile function, and passes
		 * the suitable parameters to it, the function adds questions to jsonArray
		 * called questionsListJson, and this function converts the objects from JSON to
		 * Question sets an id to each question, and adds them to a list called
		 * questionsList
		 * 
		 */
		public void readQuestions() {
			questionsList = new ArrayList<Questions>();
			try {
				questionsListJson = readJsonFile(QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (questionsListJson == null) {
				this.questionsListJson = new JsonArray();
				return;
			}
			Questions q;
			for (int i = 0; i < this.questionsListJson.size(); i++) {
				JsonObject exploreObject = (JsonObject) this.questionsListJson.get(i);
				q = new Questions(exploreObject);
				q.setQuestionId(Sysdata.questionID++);
				questionsList.add(q);
			}
		System.out.println("Question List ");
		System.out.println(questionsList);
		System.out.println(questionsList.toString());
		}
		public void checkAnswer(int questionId, int answerNumber) {
		    // Find the question with the given questionId
		    Questions question = null;
		    for (Questions q : questionsList) {
		        if (q.getQuestionId() == questionId) {
		            question = q;
		            break;
		        }
		    }

		    if (question == null) {
		        System.out.println("Question not found.");
		        return;
		    }

		    // Get the correct answer number
		    int correctAnswer = question.getCorrect_ans();

		    // Check if the answer is correct
		    if (answerNumber == correctAnswer) {
		    	
		        
		    } else {
		        System.out.println("Incorrect answer.");
		    }

		    // Check the difficulty of the question and print a message accordingly
		    int difficulty = question.getDifficulty();
		    switch (difficulty) {
		        case 1:
		            System.out.println("This is an easy question.");
		            break;
		        case 2:
		            System.out.println("This is a medium question.");
		            break;
		        case 3:
		            System.out.println("This is a difficult question.");
		            break;
		        default:
		            System.out.println("Invalid difficulty.");
		    }
		}


		/**
		 * Gets a question and adds it to questionsList, and then writes it to questions
		 * json file
		 * 
		 * @param question
		 */
		@SuppressWarnings("unchecked")
		public void addQuestion(Questions question) {
		/*	if (this.questionsList.size() >= MAX_CAPACITY_QUESTIONS) {
				this.questionsList.remove(0);
				this.questionsListJson.remove(0);
			}*/
				
			question.setQuestionId(Sysdata.questionID++);
			this.questionsListJson.add(question.toJSON());
			this.questionsList.add(question);
			writeJsonFile(this.questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
		}

		/**
		 * Gets a question ID and updates the question after getting its index in the
		 * List, then json file is re-written
		 * 
		 * @param qID
		 * @param question
		 */
		/*public void updateQuestion(int qID, Questions question) {
			int index = getQuestionIndexByID(qID);
			if (index != -1) {
				deleteQuestion(qID);
				addQuestion(question);
				writeJsonFile(this.questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
			}

		}*/

		public void updateQuestion(int qID, Questions updatedQuestion) {
		    // Find the index of the question to be updated
		    int index = getQuestionIndexByID(qID);
		    if (index != -1) {
		        // Get the JSON object representing the question
		        JsonObject jsonQuestion = (JsonObject) questionsListJson.get(index);

		        // Update the fields of the JSON object
		        jsonQuestion.addProperty("question", updatedQuestion.getQuestion());
		        jsonQuestion.add("answers", (JsonElement) updatedQuestion.getAnswers());
		        jsonQuestion.addProperty("correct_ans", updatedQuestion.getCorrect_ans());
		        jsonQuestion.addProperty("difficulty", updatedQuestion.getDifficulty());

		        // Optionally, update the corresponding Questions object in questionsList
		        questionsList.set(index, updatedQuestion);

		        // Write the updated JSON object to the file or wherever it's stored
		        writeJsonFile(questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
		    }
		}

		/**
		 * Gets a questionID, retrieves the question, deletes it and then updates
		 * questionsJson file and questionsJsonList
		 * 
		 * @param questionID
		 * @return
		 */
		public boolean deleteQuestion(int questionID) {
			Questions toBeDeleted = this.getQuestionByID(questionID);
			if (toBeDeleted == null)
				return false;
			else {
				this.questionsList.remove(toBeDeleted);
				updateQuestionsJsonList();
				writeJsonFile(this.questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
				return true;
			}
		}

		public Questions getQuestionByID(int questionID) {
			for (Questions q : this.questionsList) {
				if (q.getQuestionId() == questionID)
					return q;
			}
			return null;
		}

		public int getQuestionIndexByID(int questionID) {
			for (int i = 0; i < this.questionsList.size(); i++) {
				if (questionsList.get(i).getQuestionId() == questionID) {
					return i;
				}
			}
			return -1;
		}

		@SuppressWarnings("unchecked")
		private void updateQuestionsJsonList() {
			this.questionsListJson = new JsonArray();
			for (Questions q : this.questionsList) {
				this.questionsListJson.add(q.toJSON());
			}
		}

		public boolean isDuplicateQuestion(String questionBody) {
			return this.questionsList.stream().filter(o -> o.getQuestion().equals(questionBody)).findFirst().isPresent();
		}
		
		//game history
		
		public List<Game> getScoresList() {
			return gameHistoryList;
		}
		
		/**
		 * A function to read the scores, it calls readJsonfile function, and passes the
		 * suitable parameters to it, the function adds scores to jsonArray called
		 * scoresListJson, and this function converts the objects from JSON to Score,
		 * and adds them to a list called scoresListJson
		 * 
		 */
		public void readGameHistory() {
			gameHistoryList = new ArrayList<Game>();
			try {
				this.scoresListJson = readJsonFile(GAME_HISTORY_JSONOBJECT, GAME_HISTORY_FILENAME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (scoresListJson == null) {
				this.scoresListJson = new JsonArray();
				return;
			}
			Game g;
			for (int i = 0; i < scoresListJson.size(); i++) {
				JsonObject exploreObject = (JsonObject) this.scoresListJson.get(i);
				g = new Game(exploreObject);
				gameHistoryList.add(g);
				historyLinkedList.addFirst(g);
			}
			numberss=gameHistoryList.size();
			System.out.println("game history List ");
			System.out.println(gameHistoryList.toString());
			System.out.println("game history List LinkedList");
			System.out.println(historyLinkedList.toString());
		}

		/**
		 * Gets a score and adds it to scoresList, and then writes it to scores json
		 * file
		 * 
		 * @param score
		 */
		@SuppressWarnings("unchecked")
		public void addGameHistory(Game gameHistory) {
			if (this.gameHistoryList.size() >= MAX_CAPACITY_SCORES)
				this.gameHistoryList.remove(0);
			this.scoresListJson.add(gameHistory.toJSON());
			this.gameHistoryList.add(gameHistory);
			writeJsonFile(this.scoresListJson, GAME_HISTORY_JSONOBJECT, GAME_HISTORY_FILENAME);
		}
		
		public void readAdmins() {
			adminList = new ArrayList<Admin>();
			try {
				this.scoresListJson = readJsonFile(ADMINS_JSONOBJECT, ADMINS_FILENAME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (scoresListJson == null) {
				this.scoresListJson = new JsonArray();
				return;
			}
			Admin a;
			for (int i = 0; i < scoresListJson.size(); i++) {
				JsonObject exploreObject = (JsonObject) this.scoresListJson.get(i);
				a = new Admin(exploreObject);
				adminList.add(a);
			}
			numberss=gameHistoryList.size();
			System.out.println("Admins List ");
			System.out.println(adminList.toString());
		}
		
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			Sysdata sysdata = new Sysdata();
			List<Player> players= new ArrayList<Player>();
		sysdata.readQuestions();
			Player winningPlayer= new Player (3, "Ahmad");
			Player player1= new Player(4, "Ayman");
			players.add(player1);
			Player player2= new Player(5, "Roai");
			players.add(player2);
			LocalDateTime timer = LocalDateTime.of(2024, 2, 7, 15, 30, 0);
			sysdata.readGameHistory();
			Game game= new Game(numberss,3, winningPlayer, players, timer);
			sysdata.readQuestions();
			
			sysdata.readAdmins();
			System.out.println(numberss);
		}
	}