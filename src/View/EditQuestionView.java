package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Controller.EditQuestionController;
import Model.Questions;

public class EditQuestionView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 6317513228542388990L;
	private JPanel contentPane;
	private JTextField obtion1TeksField;
	private JTextField questionTextField;
	private JTextField obtion3textField;
	private JTextField obtion2TextField;
	private JTextField obtion4textField;
	private JButton backButton;
	@SuppressWarnings("rawtypes")
	private JComboBox<Comparable> comboBox;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private JButton editButton;
	private int questionid;
	
	@SuppressWarnings("rawtypes")
	public EditQuestionView(Questions question1) {
		questionid=question1.getQuestionId();
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
	     question.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/questionIcon1.png")));
	     question.setForeground(Color.WHITE);
	     question.setBounds(10, 15, 162, 25);
	     questionPanel.add(question);
	     //reportNum.setIcon(new ImageIcon(creatReport.class.getResource("/img/num22.png")));
	     question.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			
	     questionTextField = new JTextField(question1.getQuestion());
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
	     choices.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/choices.png")));
	     choices.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
	     choices.setBounds(10, 16, 162, 25);
	     choicesPanel.add(choices);
		
		JLabel obtion1 = new JLabel("Obtion 1");
	    obtion1.setForeground(Color.WHITE);
	    obtion1.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/ans1.png")));
	    obtion1.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
	    obtion1.setBounds(10, 53, 162, 25);
	    choicesPanel.add(obtion1);
	     
	    obtion1TeksField = new JTextField(question1.getAnswers().get(0));
	    obtion1TeksField.setBounds(123, 52, 230, 28);
	    choicesPanel.add(obtion1TeksField);
		
		JLabel obtion2 = new JLabel("Obtion 2");
		obtion2.setForeground(Color.WHITE);
	    obtion2.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/ans2.png")));
		obtion2.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion2.setBounds(10, 102, 116, 25);
		choicesPanel.add(obtion2);
		
		obtion2TextField = new JTextField(question1.getAnswers().get(1));
	    obtion2TextField.setBounds(123, 101, 230, 28);
	    choicesPanel.add(obtion2TextField);
		
		JLabel obtion3 = new JLabel("Obtion 3");
		obtion3.setForeground(Color.WHITE);
	    obtion3.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/ans3.png")));
		obtion3.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion3.setBounds(373, 53, 118, 25);
		choicesPanel.add(obtion3);
		
		obtion3textField = new JTextField(question1.getAnswers().get(2));
	    obtion3textField.setBounds(479, 52, 230, 28);
	    choicesPanel.add(obtion3textField);
		
		JLabel obtion4 = new JLabel("Obtion 4");
		obtion4.setForeground(Color.WHITE);
	    obtion4.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/ans4.png")));
		obtion4.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		obtion4.setBounds(373, 102, 118, 25);
		choicesPanel.add(obtion4);
		
	    obtion4textField = new JTextField(question1.getAnswers().get(3));
	    obtion4textField.setBounds(479, 101, 230, 28);
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
		correctAns.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/correctAns.png")));
		correctAns.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		correctAns.setBounds(10, 15, 162, 25);
		correctDiffPanel.add(correctAns);
		
		comboBox = new JComboBox<Comparable>();
		comboBox.setEditable(true);
		comboBox.addItem("...");
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		correctDiffPanel.add(comboBox);
		
		comboBox.setSelectedItem(question1.getCorrect_ans());
		comboBox.setBounds(158, 17, 84, 22);
		
			
		JLabel difficulty = new JLabel("Difficulty:");
		difficulty.setForeground(Color.WHITE);
		difficulty.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/difficulty.png")));
		difficulty.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		difficulty.setBounds(286, 15, 122, 25);
		correctDiffPanel.add(difficulty);
		
		ButtonGroup buttonGroup = new ButtonGroup();
			
		easy = new JRadioButton("Easy");
		easy.setForeground(Color.WHITE);
		easy.setBackground(new Color(0, 0, 0, 140));
		easy.setBounds(402, 17, 60, 23);
		correctDiffPanel.add(easy);
			
		medium = new JRadioButton("Medium");
		medium.setBackground(new Color(0, 0, 0, 140));
		medium.setForeground(Color.WHITE);
		medium.setBounds(504, 17, 77, 23);
		correctDiffPanel.add(medium);
			
		hard = new JRadioButton("Hard");
		hard.setBackground(new Color(0, 0, 0, 140));
		hard.setForeground(Color.WHITE);
		hard.setBounds(637, 17, 60, 23);
		correctDiffPanel.add(hard);
		buttonGroup.add(easy);
		buttonGroup.add(medium);
		buttonGroup.add(hard);
		
		JLabel easyIcon = new JLabel("");
		easyIcon.setForeground(Color.WHITE);
		easyIcon.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/easy2.png")));

		easyIcon.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		easyIcon.setBounds(462, 15, 36, 25);
		correctDiffPanel.add(easyIcon);
		
		JLabel mediumIcon = new JLabel("");
		mediumIcon.setForeground(Color.WHITE);
		mediumIcon.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/med.png")));

		mediumIcon.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		mediumIcon.setBounds(587, 15, 36, 25);
		correctDiffPanel.add(mediumIcon);
		
		JLabel hardIcon = new JLabel("");
		hardIcon.setForeground(Color.WHITE);
		hardIcon.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/hard1.png")));

		hardIcon.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		hardIcon.setBounds(703, 15, 30, 25);
		correctDiffPanel.add(hardIcon);
		
        editButton = new JButton("EDIT & SAVE");
        editButton.addActionListener(this);
        editButton.setIcon(new ImageIcon(AddQuestionView.class.getResource("/img/save.png")));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        editButton.setBackground(new Color(204, 153, 255));
        editButton.setBounds(316, 439, 166, 45);
		contentPane.add(editButton);
		
		switch (question1.getDifficulty()) {
	    case 1:
	        easy.setSelected(true);
	        break;
	    case 2:
	        medium.setSelected(true);
	        break;
	    case 3:
	        hard.setSelected(true);
	        break;
	    default:
	        // Handle the case where the difficulty level is not 1, 2, or 3 (optional)
	        break;
		}
		
		backButton = new JButton();
		backButton.setBounds(0,0,53,94);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setPreferredSize(new Dimension(0,0));
		backButton.addActionListener(this);
			
		JLabel backIcon = new JLabel("");
		backIcon.setBounds(0, -25, 53, 100);
		backIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/backQuestionView1.png")));
		 //contentPane.add(backIcon);	
		 
		JPanel backPanel = new JPanel();
		backPanel.setBounds(47, 44, 53, 53);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		backPanel.add(backIcon);
		backPanel.add(backButton);	
		contentPane.add(backPanel);
		
        JLabel backgrounde = new JLabel("");
		backgrounde.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/editQuestionBackground.jpeg")));
		backgrounde.setBounds(0, -38,848,605);
		contentPane.add(backgrounde);	    
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==editButton) {
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
			String que= questionTextField.getText();
			List<String> answers = new ArrayList<String>(); //holds the question’s answers.
			 String answer1 = obtion1TeksField.getText();
	         String answer2 = obtion2TextField.getText();
	         String answer3 = obtion3textField.getText();
	         String answer4 = obtion4textField.getText();
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
	         
	         int correctAns=(int) comboBox.getSelectedItem(); // Add 1 to match question indexes (1-based)
	         
	         try {
	        	 EditQuestionController.getInstance().updateQuestion(questionid, que, answers, correctAns, difficulty);	        	    
	        	    // Show a message dialog to inform the user about successful question edit
	        	    JOptionPane.showMessageDialog(this, "Question edited successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	        	    
	        	    // Navigate back to the Test page
	        	    QuestionsView testPage = new QuestionsView();
	        	    testPage.setVisible(true);
	        	    dispose(); // Close the current window
	        	} catch (Exception e2) {
	        	    // Display a message dialog to inform the user about the error
	        	    JOptionPane.showMessageDialog(this, "Failed to edit question: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        	    e2.printStackTrace(); // Print the stack trace for debugging purposes
	        	}
			}	
		}
		else if(e.getSource()==backButton) {
			 QuestionsView testPage = new QuestionsView();
     	    testPage.show();
     	    dispose(); 
		}
		
	}
}
