package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

import org.json.simple.DeserializationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import org.json.simple.parser.JSONParser;


public class Sysdata {

		static final String QUESTIONS_FILENAME = "questions_scheme.json";
		static final String QUESTIONS_JSONOBJECT = "questions";
		static final String GAME_HISTORY_FILENAME = "game_history.json";
		static final String GAME_HISTORY_JSONOBJECT = "gamesHistory";
		static final int MAX_CAPACITY_SCORES = 50;

		private static Sysdata instance;
		private List<Questions> questionsList;
		private List <Game> gameHistoryList; 
		private static int questionID = 0;
		private JSONArray questionsListJson= new JSONArray();
		private JSONArray scoresListJson= new JSONArray();
		private List<String> historyList;
		private JSONArray historyJson;
		private JSONArray suggestionsJson;
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
		public void writeJsonFile(JSONArray objectList, String objectName, String fileName) {
			try (FileWriter file = new FileWriter(fileName)) {
				JSONObject obj = new JSONObject();
				obj.put(objectName, objectList);
				file.write(obj.toJSONString());
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
		private JSONArray readJsonFile(String objectName, String fileName) throws ParseException {
			try (FileReader reader = new FileReader(fileName)) {
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = null;
				try {
					jsonObject = (JSONObject) parser.parse(reader);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (JSONArray) jsonObject.get(objectName);
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
			return new JSONArray();
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
				this.questionsListJson = new JSONArray();
				return;
			}
			Questions q;
			for (int i = 0; i < this.questionsListJson.size(); i++) {
				JSONObject exploreObject = (JSONObject) this.questionsListJson.get(i);
				q = new Questions(exploreObject);
				q.setQuestionId(Sysdata.questionID++);
				questionsList.add(q);
			}
		System.out.println("Question List ");
		System.out.println(questionsList);
		System.out.println(questionsList.toString());
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
		        JSONObject jsonQuestion = (JSONObject) questionsListJson.get(index);

		        // Update the fields of the JSON object
		        jsonQuestion.put("question", updatedQuestion.getQuestion());
		        jsonQuestion.put("answers", updatedQuestion.getAnswers());
		        jsonQuestion.put("correct_ans", updatedQuestion.getCorrect_ans());
		        jsonQuestion.put("difficulty", updatedQuestion.getDifficulty());

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
			this.questionsListJson = new JSONArray();
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
				this.scoresListJson = new JSONArray();
				return;
			}
			Game g;
			for (int i = 0; i < scoresListJson.size(); i++) {
				JSONObject exploreObject = (JSONObject) this.scoresListJson.get(i);
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

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			Sysdata sysdata = new Sysdata();
			List<Player> players= new ArrayList<Player>();
		//	sysdata.readQuestions();
			Player winningPlayer= new Player (3, "Ahmad");
			Player player1= new Player(4, "Ayman");
			players.add(player1);
			Player player2= new Player(5, "Roai");
			players.add(player2);
			LocalDateTime timer = LocalDateTime.of(2024, 2, 7, 15, 30, 0);
			sysdata.readGameHistory();
			Game game= new Game(numberss,3, winningPlayer, players, timer);
			sysdata.readQuestions();
			
			System.out.println(numberss);
			
		}
	}


