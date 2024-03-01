package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Controller.AddQuestionController;
import Model.Sysdata;

import javax.swing.JRadioButton;


public class AddQuestionView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField obtion1TeksField;

	//private ArrayList<casualties> casualtiesArr=new ArrayList<>();
//	private ArrayList<includeReport> includesReport=new ArrayList<>();

	//private JDateChooser dateChooser;
	private String k;
	private JTextField questionTextField;
	private JTextField obtion3textField;
	private JTextField obtion2TextField;
	private JTextField obtion4textField;
	private JButton addQuestion;
	private JComboBox comboBox;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private JButton back;
	private AddQuestionController controller = AddQuestionController.getInstance();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddQuestionView frame = new AddQuestionView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddQuestionView() {
		initComponents();
		//fetchAndRefresh();
}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 822, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/***************Question Panel******************/

	     JPanel questionPanel = new JPanel();
	     questionPanel.setBounds(28, 124, 733, 51);
	     questionPanel.setLayout(null);
	     questionPanel.setBackground(new Color(0, 0, 0, 140));
	     Border border1 = BorderFactory.createLineBorder(Color.white, 1);
	     questionPanel.setBorder(border1);
	     contentPane.add(questionPanel);
			
	     JLabel question = new JLabel("Question:");
	     question.setForeground(Color.WHITE);
	     question.setBounds(10, 15, 162, 25);
	     questionPanel.add(question);
	     //reportNum.setIcon(new ImageIcon(creatReport.class.getResource("/img/num22.png")));
	     question.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			
	     questionTextField = new JTextField();
	     questionTextField.setText((String) null);
	     questionTextField.setColumns(10);
	     questionTextField.setBounds(126, 14, 597, 28);
	     questionPanel.add(questionTextField);
	 
	     /********************Choices Panel*************************/
		JPanel choicesPanel = new JPanel();
		choicesPanel.setBounds(28, 193, 733, 173);
		choicesPanel.setLayout(null);
		choicesPanel.setBackground(new Color(0, 0, 0, 140));
		Border border = BorderFactory.createLineBorder(Color.white, 1);
		choicesPanel.setBorder(border);		
		contentPane.add(choicesPanel);
		
		 JLabel choices = new JLabel("Choices:");
	     choices.setForeground(Color.WHITE);
	     choices.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
	     choices.setBounds(10, 16, 162, 25);
	     choicesPanel.add(choices);
		
		JLabel obtion1 = new JLabel("Obtion 1");
	    obtion1.setForeground(Color.WHITE);
	    obtion1.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
	    obtion1.setBounds(10, 53, 162, 25);
	    choicesPanel.add(obtion1);
	     
	    obtion1TeksField = new JTextField();
	    obtion1TeksField.setColumns(10);
	    obtion1TeksField.setText(k);
	    obtion1TeksField.setBounds(123, 52, 219, 28);
	    choicesPanel.add(obtion1TeksField);
		
		JLabel obtion2 = new JLabel("Obtion 2");
		obtion2.setForeground(Color.WHITE);
		//date.setIcon(new ImageIcon(creatReport.class.getResource("/img/daate.png")));
		obtion2.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion2.setBounds(10, 102, 116, 25);
		choicesPanel.add(obtion2);
		
		obtion2TextField = new JTextField();
	    obtion2TextField.setText((String) null);
	    obtion2TextField.setColumns(10);
	    obtion2TextField.setBounds(123, 101, 219, 28);
	    choicesPanel.add(obtion2TextField);
		
		JLabel obtion3 = new JLabel("Obtion 3");
		obtion3.setForeground(Color.WHITE);
		//latitude.setIcon(new ImageIcon(creatReport.class.getResource("/img/latitude.png")));
		obtion3.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion3.setBounds(373, 53, 118, 25);
		choicesPanel.add(obtion3);
		
		obtion3textField = new JTextField();
	    obtion3textField.setText((String) null);
	    obtion3textField.setColumns(10);
	    obtion3textField.setBounds(444, 50, 219, 28);
	    choicesPanel.add(obtion3textField);
		
		JLabel obtion4 = new JLabel("Obtion 4");
		obtion4.setForeground(Color.WHITE);
	//	longitude.setIcon(new ImageIcon(creatReport.class.getResource("/img/longitude.png")));
		obtion4.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion4.setBounds(373, 102, 118, 25);
		choicesPanel.add(obtion4);
		
	    obtion4textField = new JTextField();
	    obtion4textField.setText((String) null);
	    obtion4textField.setColumns(10);
	    obtion4textField.setBounds(444, 105, 219, 28);
	    choicesPanel.add(obtion4textField);
	     
	    /*********************Correct Answer & Difficulty*********************************/
		JPanel correctDiffPanel = new JPanel();
		correctDiffPanel.setLayout(null);
		correctDiffPanel.setBounds(28, 377, 733, 51);
		correctDiffPanel.setBackground(new Color(0, 0, 0, 140));
		Border border2 = BorderFactory.createLineBorder(Color.white, 1);
		correctDiffPanel.setBorder(border2);
		contentPane.add(correctDiffPanel);
			
		JLabel correctAns = new JLabel("Correct Answer:");
		correctAns.setForeground(Color.WHITE);
		correctAns.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		correctAns.setBounds(28, 15, 162, 25);
		correctDiffPanel.add(correctAns);
		
		comboBox = new JComboBox();
		comboBox.setBounds(158, 17, 84, 22);
		comboBox.addItem("...");
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		correctDiffPanel.add(comboBox);
			
		JLabel difficulty = new JLabel("Difficulty:");
		difficulty.setForeground(Color.WHITE);
		difficulty.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		difficulty.setBounds(278, 15, 84, 25);
		correctDiffPanel.add(difficulty);
		
		ButtonGroup buttonGroup = new ButtonGroup();
			
		easy = new JRadioButton("Easy");
		easy.setForeground(Color.WHITE);
		easy.setBackground(new Color(0, 0, 0, 140));
		easy.setBounds(381, 17, 109, 23);
		correctDiffPanel.add(easy);
			
		medium = new JRadioButton("Medium");
		medium.setBackground(new Color(0, 0, 0, 140));
		medium.setForeground(Color.WHITE);
		medium.setBounds(492, 17, 109, 23);
		correctDiffPanel.add(medium);
			
		hard = new JRadioButton("Hard");
		hard.setBackground(new Color(0, 0, 0, 140));
		hard.setForeground(Color.WHITE);
		hard.setBounds(607, 17, 109, 23);
		correctDiffPanel.add(hard);
		
		buttonGroup.add(easy);
		buttonGroup.add(medium);
		buttonGroup.add(hard);
			
		addQuestion = new JButton("save");
		addQuestion.addActionListener(this);
		addQuestion.setForeground(Color.BLACK);
		addQuestion.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		addQuestion.setBackground(new Color(255, 255, 255, 200));
		addQuestion.setBounds(386, 441, 140, 45);
		
		contentPane.add(addQuestion);
		back = new JButton("back");
		back.setForeground(Color.BLACK);
		back.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		back.addActionListener(this);
		back.setBackground(new Color(255, 255, 255, 200));
		back.setBounds(209, 441, 140, 45);
		contentPane.add(back);
		
		JLabel backgrounde = new JLabel("");
		backgrounde.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/addQuestionBackground.jpeg")));
		backgrounde.setBounds(0, -38,848,605);
		contentPane.add(backgrounde);	
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addQuestion) {
			int q=0;
			try {
				/*check if the admin full all the text field and if not nullPointerException will throw*/
				if(comboBox.getSelectedItem().equals("...")||questionTextField.getText().isEmpty()|| obtion1TeksField.getText().isEmpty() || obtion2TextField.getText().isEmpty() || obtion3textField.getText().isEmpty() || obtion3textField.getText().isEmpty()
						|| (!easy.isSelected() && !medium.isSelected() && !hard.isSelected())) {
					throw new NullPointerException("return value is null");
				}		
				} catch( NullPointerException e2) {
					q=1;
					JOptionPane.showMessageDialog(null, "full all field!",
							"warning", JOptionPane.WARNING_MESSAGE);
				}
			if(q==0) {
				List<String> answers = new ArrayList<String>(); //holds the question’s answers.
				String questionBody;
				questionBody= (String)questionTextField.getText();
				
				Boolean n=controller.isDulicated(questionBody);
				if (n==true) {
	                JOptionPane.showMessageDialog(null, "sameQuestion.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
				}
				
				 String answer1 = obtion1TeksField.getText().trim();
		         String answer2 = obtion2TextField.getText().trim();
		         String answer3 = obtion3textField.getText().trim();
		         String answer4 = obtion4textField.getText().trim();
		         if (answer1.equals(answer2) || answer1.equals(answer3) || answer1.equals(answer4) ||
		                    answer2.equals(answer3) || answer2.equals(answer4) || answer3.equals(answer4)) {
		                JOptionPane.showMessageDialog(null, "All answers should be different.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		         else {
		        	 answers.add(answer1);
		        	 answers.add(answer2);
		        	 answers.add(answer3);
		        	 answers.add(answer4);
		         }
		         
		         int difficulty = 0;
		         if (easy.isSelected()) {
		        	    difficulty = 1;
		        	} else if (medium.isSelected()) {
		        	    difficulty = 2;
		        	} else if (hard.isSelected()) {
		        	    difficulty = 3;
		        	}
		         
		         int correctAns=(int) comboBox.getSelectedItem();
		         try {
			         controller.addQuestion(questionBody, answers, correctAns, difficulty);
			         // Show a message dialog to inform the user about successful addition
			         JOptionPane.showMessageDialog(this, "Question added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			         
			         // Navigate back to the Test page
			         QuestionsView testPage = new QuestionsView();
			         testPage.setVisible(true);
			         dispose(); // Close the current window

		        	} catch (Exception e2) {
		        	    // Display a message dialog to inform the user about the error
		        	    JOptionPane.showMessageDialog(this, "Failed to add question: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        	    e2.printStackTrace(); // Print the stack trace for debugging purposes
		        	}

		        	 
			}
           
		}
		else if(e.getSource()==back) {
			QuestionsView testPage = new QuestionsView();
     	    testPage.show();
     	    dispose(); 
		}
		
	}
}

