package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Sysdata {
		static final String QUESTIONS_FILENAME = "questions_scheme.json"; //json file name for questions
		static final String QUESTIONS_JSONOBJECT = "questions";//the name of the questions in the file
		static final String GAME_HISTORY_FILENAME = "game_history.json"; //json file name for game history
		static final String GAME_HISTORY_JSONOBJECT = "gamesHistory"; //the name of the game history in the file
		static final String ADMINS_FILENAME = "admins.json"; //json file name for admins
		static final String ADMINS_JSONOBJECT = "admins"; //the name of the game history in the file
		public static final int MAX_CAPACITY_GAME_HISTORY = 50; //the maximum game history we want

		private static Sysdata instance;
		public List<Questions> questionsList;
		public List <Game> gameHistoryList;
		private List <Admin> adminList; 
		private static int questionID = 0;
		private JsonArray questionsListJson;
		private JsonArray gameHistoryListJson;
		public static int numberss=0;
		private LinkedList<Game> historyLinkedList=new LinkedList<>();

		public static Sysdata getInstance() {
			if (instance == null)
				instance = new Sysdata();
			return instance;
		}

		/****************************************************************************
		 * function to write data to json file, objectList the objects in json array, 
		 * objectName, array of question in json, fileName name of json file 
		 ****************************************************************************/
		public void writeJsonFile(JsonArray objectList, String objectName, String fileName) {
			try (FileWriter file = new FileWriter(fileName)) {
				JsonObject obj = new JsonObject();
				obj.add(objectName, objectList);
				file.write(obj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/************************************************************************
		 * function to read a json file (name of file is passed as an argument)
		 *  objectName
		 *  fileName
		 ************************************************************************/
		public JsonArray readJsonFile(String objectName, String fileName) throws ParseException {
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
					writeJsonFile(gameHistoryListJson, objectName, fileName);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new JsonArray();
		}

		/*************************** Question Functions *******************************/
		public List<Questions> getQuestionsList() {
			return questionsList;
		}

		public void setQuestionsList(List<Questions> questionsList) {
			this.questionsList = questionsList;
		}

		/**************************************************************************
		 	A function is implemented to read the questions. It invokes the
			'readJsonFile' function and passes the appropriate parameters to it.
 			The function adds questions to a JSON array called
 			'questionsListJson'. Subsequently, this function converts the JSON
 			objects to Question sets, assigns an ID to each question, and then
 			adds them to a list named 'questionsList'
		 **************************************************************************/
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
		}
		
		/*********************************************************************************
		 * Gets a question and adds it to questionsList, and then writes it to questions
		 * json file
		 *********************************************************************************/
		public void addQuestion(Questions question) {
			question.setQuestionId(Sysdata.questionID++);
			JsonObject questionJsonObject = question.toJSON();
			this.questionsListJson.add(questionJsonObject);
			this.questionsList.add(question);
			writeJsonFile(this.questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);	
		}

		/******************************************************************************
		 * Gets a question ID and updates the question after getting its index in the
		 * List, then json file is re-written
		 ******************************************************************************/

		public void updateQuestion(int qID, Questions updatedQuestion) {
		    // Find the index of the question to be updated
		    int index = getQuestionIndexByID(qID);
		    if (index != -1) {
		        // Get the JSON object representing the question
		        JsonObject jsonQuestion = (JsonObject) questionsListJson.get(index);
		        JsonArray updatedAnswersArray = new JsonArray();
		        for (String answer : updatedQuestion.getAnswers()) {
		            updatedAnswersArray.add(answer);
		        }
		        // Update the fields of the JSON object
		        jsonQuestion.addProperty("question", updatedQuestion.getQuestion());
		        jsonQuestion.add("answers",  updatedAnswersArray);
		        jsonQuestion.addProperty("correct_ans", updatedQuestion.getCorrect_ans());
		        jsonQuestion.addProperty("difficulty", updatedQuestion.getDifficulty());

		        // Optionally, update the corresponding Questions object in questionsList
		        questionsList.set(index, updatedQuestion);
		        // Write the updated JSON object to the file or wherever it's stored
		        writeJsonFile(questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
		        
		    }
		}

		/*******************************************************************************
		 * Gets a questionID, retrieves the question, deletes it and then updates
		 * questionsJson file and questionsJsonList
		 ******************************************************************************/
		public boolean deleteQuestion(int questionID) {
			Questions toBeDeleted = this.getQuestionByID(questionID);
			if (toBeDeleted == null)
				return false;
			else {
				this.questionsList.remove(toBeDeleted);
				updateQuestionsJsonList();
				writeJsonFile(this.questionsListJson, QUESTIONS_JSONOBJECT, QUESTIONS_FILENAME);
				//System.out.println("deleteQuestion");
				//System.out.println(questionsList.toString());
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

		private void updateQuestionsJsonList() {
			this.questionsListJson = new JsonArray();
			int i =0;
			for (Questions q : this.questionsList) {
				System.out.println(q.getQuestionId());
				q.setQuestionId(i);
				this.questionsListJson.add(q.toJSON());
				i++;
			}
			System.out.println("updateQuestionsJsonList");
			System.out.println(questionsList.toString());
		}

		public boolean isDuplicateQuestion(String questionBody) {
			return this.questionsList.stream().filter(o -> o.getQuestion().equals(questionBody)).findFirst().isPresent();
		}
		
		/**********************************game history**************************************/
		
		public List<Game> getScoresList() {
			return gameHistoryList;
		}
		
		/************************************************************************************
		 	* A function for reading game history is implemented. It invokes the
 			'readJsonFile' function and passes the appropriate parameters to it.
 			The function adds game history to a JSON array named 'gameHistoryListJson'.
 			Additionally, this function converts the JSON objects to game history
 			objects and then adds them to a list called 'gameHistoryList'.
		 ************************************************************************************/
		public void readGameHistory() {
			gameHistoryList = new ArrayList<Game>();
			try {
				this.gameHistoryListJson = readJsonFile(GAME_HISTORY_JSONOBJECT, GAME_HISTORY_FILENAME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (gameHistoryListJson == null) {
				this.gameHistoryListJson = new JsonArray();
				return;
			}
			Game g;
			for (int i = 0; i < gameHistoryListJson.size(); i++) {
				JsonObject exploreObject = (JsonObject) this.gameHistoryListJson.get(i);
				g = new Game(exploreObject);
				gameHistoryList.add(g);
				historyLinkedList.addFirst(g);
			}
			numberss=gameHistoryList.size();
		}

		/*****************************************************************************
		 * Gets a score and adds it to scoresList, and then writes it to scores json
		 * file
		 *****************************************************************************/
		public void addGameHistory(Game gameHistory) {
		    // Check if the gameHistoryList already contains a game with the same ID
		    boolean isDuplicate = false;
		    for (Game existingGame : gameHistoryList) {
		        if (existingGame.getGameID() == gameHistory.getGameID()) {
		            isDuplicate = true;
		            break;
		        }
		    }

		    if (!isDuplicate) {
		        if (this.gameHistoryList.size() >= MAX_CAPACITY_GAME_HISTORY) {
		            this.gameHistoryList.remove(0); // Remove the oldest game history to maintain max capacity
		        }
		        
		        // Add the new game history to both the JSON array and the list
		        this.gameHistoryListJson.add(gameHistory.toJSON());
		        this.gameHistoryList.add(gameHistory);

		        // Write the updated game history list to the JSON file
		        writeJsonFile(this.gameHistoryListJson, GAME_HISTORY_JSONOBJECT, GAME_HISTORY_FILENAME);
		        System.out.println("Added Successfully");
		    } else {
		        System.out.println("Duplicate Game ID: " + gameHistory.getGameID() + ". Game not added.");
		    }
		}

		
		/*********************************Admins*******************************************/
		
		
		public List<Admin> getAdmins()
		{
			return adminList;
		}
		
		
		/*********************************************************************************/
		public void readAdmins() {
			adminList = new ArrayList<Admin>();
			try {
				this.gameHistoryListJson = readJsonFile(ADMINS_JSONOBJECT, ADMINS_FILENAME);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (gameHistoryListJson == null) {
				this.gameHistoryListJson = new JsonArray();
				return;
			}
			Admin a;
			for (int i = 0; i < gameHistoryListJson.size(); i++) {
				JsonObject exploreObject = (JsonObject) this.gameHistoryListJson.get(i);
				a = new Admin(exploreObject);
				adminList.add(a);
			}
		}
	}