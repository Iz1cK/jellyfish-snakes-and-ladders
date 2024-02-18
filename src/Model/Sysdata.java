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
		static final int MAX_CAPACITY_GAME_HISTORY = 50; //the maximum game history we want

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
			for (Questions q : this.questionsList) {
				this.questionsListJson.add(q.toJSON());
			}
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
			if (this.gameHistoryList.size() >= MAX_CAPACITY_GAME_HISTORY)
				this.gameHistoryList.remove(0);
			this.gameHistoryListJson.add(gameHistory.toJSON());
			this.gameHistoryList.add(gameHistory);
			writeJsonFile(this.gameHistoryListJson, GAME_HISTORY_JSONOBJECT, GAME_HISTORY_FILENAME);
			System.out.println("Added Successfully");
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
		
		
		
		public static void main(String[] args) throws IOException, ParseException {
			Scanner scanner = new Scanner(System.in);
			Sysdata sysdata = new Sysdata();
			sysdata.readAdmins();
			sysdata.readGameHistory();
			sysdata.readQuestions();
			int choice;
			do {
			    System.out.println("Menu:");
			    System.out.println("1. Question");
			    System.out.println("2. Game History");
			    System.out.println("3. Exit");
			    System.out.print("Enter your choice: ");
			    choice = scanner.nextInt();
			    scanner.nextLine(); // Consume newline character
			    
			    switch (choice) {
                    case 1:
                    	System.out.println("Are you admin?");
			            System.out.println("1. Yes");
			            System.out.println("2. No");
			            int yesOrNo;
				        do {
				            yesOrNo = scanner.nextInt();
				            scanner.nextLine();
				            if (yesOrNo == 2) {
				            	System.out.println("Thats Page is only for admins!");
				            	} else if (yesOrNo == 1) {
				            		System.out.println("Welcome Admin!");
				            		} else {
				            			System.out.println("Please choose 1 or 2.");
				            			}
				            } while (yesOrNo != 1 && yesOrNo != 2);
				        if(yesOrNo == 1) {
				        	System.out.println("Admins List:");
				        	System.out.println(sysdata.adminList.toString());
				        	boolean isAuthenticated = false;
				        	String username;
				        	boolean userExists = false;
				        	do {
				        		System.out.print("Enter your username: ");
				        		username = scanner.nextLine();
				        		userExists = checkIfUserExists(username);
				        		if (!userExists) {
				        			System.out.println("Username does not exist. Please try again.");
				        			}
				        		} while (!userExists);
				        		while (!isAuthenticated) {
				        		System.out.print("Enter your password: ");
				        		String password = scanner.nextLine();
				        		isAuthenticated = authenticateUser(username, password);
				        		if (isAuthenticated) {
				        			// Show menu options if authentication is successful
				        			
				        	        int choose;
				        	        do {
				        	        	System.out.println("Menu:");
					        			System.out.println("1. Show all questions");
					        	        System.out.println("2. Add question");
					        	        System.out.println("3. Update question");
					        	        System.out.println("4. Delete question");
					        	        System.out.println("5. Exit");
				        	        	System.out.print("Enter your choice: ");
				        	        	choose = scanner.nextInt();
				        	        	scanner.nextLine(); // Consume newline character

				        	        	switch (choose) {
				        	        	case 1:
				        	        		System.out.println("All Questions in the system:");
				        	        		System.out.println(sysdata.questionsList.toString());
				        	        		break;
				        	        	case 2:
				        	        		System.out.print("Enter your question: \n");
				        	        		String question = scanner.nextLine();
				        	        		List<String> answers=new ArrayList<String>();
				        	        		for(int i=0; i<4; i++) {
				        	        			System.out.print("Enter your"+ i +" answer: \n");
					        	        		String answer1 = scanner.nextLine();
					        	        		answers.add(answer1);
				        	        			
				        	        		}
			        	                	System.out.print("Enter the correct number answer: \n");
			        	                	int correctAns = scanner.nextInt();
			        	                	System.out.print("choose diffculty: 1-easy, 2-medium, 3-hard \n");
			        	                	int diffculty = scanner.nextInt();
			        	                	Questions newQuestion= new Questions(questionID, question, answers, correctAns, diffculty);
			        	                	sysdata.addQuestion(newQuestion);
			        	                    break;
			        	                case 3:
			        	                    System.out.println("Choose Question id to update:");
			        	                    System.out.println(sysdata.questionsList.toString());
			        	                    int questionId = scanner.nextInt();
			        	                    scanner.nextLine();
			        	                    
			        	                    System.out.println("if you dont want to update all question and answers where you dont want to update enter 1");
			        	                    System.out.print("the question is: "+ sysdata.questionsList.get(questionId).getQuestion());
			        	                    System.out.print("\n update :");
			        	                	String questionUpdate = scanner.nextLine();
			        	                	if("1".equals(questionUpdate)) {
			        	                		questionUpdate=sysdata.questionsList.get(questionId).getQuestion();
			        	                	}
			        	                	List<String> answersU=new ArrayList<String>();
			        	                	for(int i=0; i<4; i++) {
			        	                		System.out.print("Answer"+i+": "+ sysdata.questionsList.get(questionId).getAnswers().get(i));
				        	                    System.out.print("\n update: \n");
				        	                	String answer1U = scanner.nextLine();
				        	                	if("1".equals(answer1U)) {
				        	                		answer1U=sysdata.questionsList.get(questionId).getAnswers().get(i);
				        	                	}
				        	                	answersU.add(answer1U);
			        	                		
			        	                	}
			        	                	System.out.print("the correct Answer is: "+ sysdata.questionsList.get(questionId).getCorrect_ans());
			        	                	System.out.print("\n update: \n");
			        	                	int correctAnsU = scanner.nextInt();
			        	                	if(correctAnsU==1) {
			        	                		correctAnsU=sysdata.questionsList.get(questionId).getCorrect_ans();
			        	                	}
			        	                	System.out.print("the diffculty is: "+ sysdata.questionsList.get(questionId).getDifficulty());
			        	                	System.out.print("\n update: \n");
			        	                	int diffcultyU = scanner.nextInt();
			        	                	if(diffcultyU==1) {
			        	                		diffcultyU=sysdata.questionsList.get(questionId).getDifficulty();
			        	                	}
			        	                	Questions questionU= new Questions(questionId, questionUpdate, answersU, correctAnsU, diffcultyU);
			        	                	sysdata.updateQuestion(questionId, questionU);
			        	                    
			        	                    break;
			        	                case 4:
			        	                	System.out.println("All Question: ");
				        	                System.out.println(sysdata.questionsList.toString());
				        	                System.out.println("Choose Question id to delete: ");
				        	                    int questionId2 = scanner.nextInt();
				        	                    scanner.nextLine();
				        	                    sysdata.deleteQuestion(questionId2);
			        	                    break;
			        	                case 5:
			        	                    System.out.println("Exiting...");
			        	                    break;
			        	                default:
			        	                	System.out.println("Invalid choice. Please choose a number between 1 and 5.");
				        	        	}
				        	        } while (choose != 5);
				        		} else {
				        			System.out.println("The password is INCORRECT! Please try again.");
				        		}
				        		}
				        }
				        break;
                    case 2:
                    	System.out.println("Menu:");
	        			System.out.println("1. Show all game history");
	        	        System.out.println("2. Add game history");
	        	        System.out.println("3. Exit");
	        	        int choose;
	        	        do {
	        	        	System.out.print("Enter your choice: ");
	        	        	choose = scanner.nextInt();
	        	        	scanner.nextLine();
	        	        	
	        	        	switch (choose) {
	        	        	case 1:
	        	        		System.out.println("All Game History:");
	        	        		System.out.println(sysdata.gameHistoryList.toString());
	        	        		break;
	        	        	case 2:
	        	        		System.out.println("Add Game History:");
	        	        		List<Player> playerss = new ArrayList<Player>();
	        	        		
	        	        		LocalDateTime time =LocalDateTime.of(2023, 2, 9, 23, 58);
	        	        		Player newpl= new Player(1, "aseel",PLAYERCOLORS.PINK);
	        	        		Player newp2= new Player(1, "lna", PLAYERCOLORS.PURPLE);
	        	        		playerss.add(newpl);
	        	        		playerss.add(newp2);
	        	        		Game newGame= new Game(numberss, 2, newpl, playerss, time);
	        	        		System.out.println("Added this Game:" + newGame.toString());
	        	        		sysdata.addGameHistory(newGame);
				        	break;
				        	
				        	case 3:
				        		System.out.println("Exiting...");
        	                    break; 
				        	default:
	        	                	System.out.println("Invalid choice. Please choose a number between 1 and 3.");
		        }
                }while (choose != 3);
	        	        
                    case 3:
		        		System.out.println("Exiting...");
	                    break; 
		        	default:
    	                	System.out.println("Invalid choice. Please choose a number between 1 and 3.");
		    scanner.close();
		}
		}while (choice != 3);
		}
		 private static boolean checkIfUserExists(String username) {
			 Sysdata sysdata = new Sysdata();
		        sysdata.readAdmins();
				for (Admin obj: sysdata.adminList ) {
				    String storedUsername = obj.getAdminName();
				    if (storedUsername.equals(username)) {
				        return true; // User authentication successful
				    }
				}
				return false;
				}
				
	    private static boolean authenticateUser(String username, String password) throws IOException, ParseException {
	        Sysdata sysdata = new Sysdata();
	        sysdata.readAdmins();
			for (Admin obj: sysdata.adminList ) {
			    String storedUsername = obj.getAdminName();
			    String storedPassword = obj.getPassword();

			    if (storedUsername.equals(username) && storedPassword.equals(password)) {
			        return true; // User authentication successful
			    }
			}
	        return false; // User authentication failed
	    }
	}