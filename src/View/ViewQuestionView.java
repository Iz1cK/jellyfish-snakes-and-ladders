package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import Model.Questions;

public class ViewQuestionView extends JFrame {


	private JPanel contentPane;


	    private JTextField obtion1TeksField;

		//private ArrayList<casualties> casualtiesArr=new ArrayList<>();
//		private ArrayList<includeReport> includesReport=new ArrayList<>();

		private JTextField questionTextField;
		private JTextField obtion3textField;
		private JTextField obtion2TextField;
		private JTextField obtion4textField;
		private JButton back;
		private JComboBox comboBox;
		JRadioButton easy;
		JRadioButton medium;
		JRadioButton hard;

	/**
	 * Launch the application.


	/**
	 * Create the frame.
	 */
	    public ViewQuestionView(Questions question1) {
			
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
				
		     questionTextField = new JTextField(question1.getQuestion());
		     questionTextField.setEditable(false);
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
		     
		    obtion1TeksField = new JTextField(question1.getAnswers().get(0));
		    obtion1TeksField.setEditable(false);
		    obtion1TeksField.setBounds(123, 52, 219, 28);
		    choicesPanel.add(obtion1TeksField);
			
			JLabel obtion2 = new JLabel("Obtion 2");
			obtion2.setForeground(Color.WHITE);
			//date.setIcon(new ImageIcon(creatReport.class.getResource("/img/daate.png")));
			obtion2.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			obtion2.setBounds(10, 102, 116, 25);
			choicesPanel.add(obtion2);
			
			obtion2TextField = new JTextField(question1.getAnswers().get(1));
			obtion2TextField.setEditable(false);
		    obtion2TextField.setBounds(123, 101, 219, 28);
		    choicesPanel.add(obtion2TextField);
			
			JLabel obtion3 = new JLabel("Obtion 3");
			obtion3.setForeground(Color.WHITE);
			//latitude.setIcon(new ImageIcon(creatReport.class.getResource("/img/latitude.png")));
			obtion3.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			obtion3.setBounds(373, 53, 118, 25);
			choicesPanel.add(obtion3);
			
			obtion3textField = new JTextField(question1.getAnswers().get(2));
			obtion3textField.setEditable(false);
		    obtion3textField.setBounds(444, 50, 219, 28);
		    choicesPanel.add(obtion3textField);
			
			JLabel obtion4 = new JLabel("Obtion 4");
			obtion4.setForeground(Color.WHITE);
		//	longitude.setIcon(new ImageIcon(creatReport.class.getResource("/img/longitude.png")));
			obtion4.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			obtion4.setBounds(373, 102, 118, 25);
			choicesPanel.add(obtion4);
			
		    obtion4textField = new JTextField(question1.getAnswers().get(3));
		    obtion4textField.setEditable(false);
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
			comboBox.setEnabled(false);
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
			difficulty.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
			difficulty.setBounds(278, 15, 84, 25);
			correctDiffPanel.add(difficulty);
			
			ButtonGroup buttonGroup = new ButtonGroup();
				
			easy = new JRadioButton("Easy");
			easy.setEnabled(false);
			easy.setForeground(Color.WHITE);
			easy.setBackground(new Color(0, 0, 0, 140));
			easy.setBounds(381, 17, 109, 23);
			correctDiffPanel.add(easy);
				
			medium = new JRadioButton("Medium");
			medium.setEnabled(false);
			medium.setBackground(new Color(0, 0, 0, 140));
			medium.setForeground(Color.WHITE);
			medium.setBounds(492, 17, 109, 23);
			correctDiffPanel.add(medium);
				
			hard = new JRadioButton("Hard");
			hard.setEnabled(false);
			hard.setBackground(new Color(0, 0, 0, 140));
			hard.setForeground(Color.WHITE);
			hard.setBounds(607, 17, 109, 23);
			correctDiffPanel.add(hard);
			
			buttonGroup.add(easy);
			buttonGroup.add(medium);
			buttonGroup.add(hard);
			
			if (question1.getDifficulty() == 1) {
			    easy.setSelected(true);
			}
			else if (question1.getDifficulty() == 2) {
			    medium.setSelected(true);
			}
			if (question1.getDifficulty() == 3) {
			    hard.setSelected(true);
			}
			back = new JButton("BACK");
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 QuestionsView questionsView = new QuestionsView();
				        questionsView.setVisible(true);
				        dispose();
				}
			});
			back.setForeground(Color.BLACK);
			back.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
			back.setBackground(new Color(255, 255, 255, 200));
			back.setBounds(283, 441, 140, 45);
			
			contentPane.add(back);
			
			JLabel backgrounde = new JLabel("");
			backgrounde.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/addQuestionBackground.jpeg")));
			backgrounde.setBounds(0, -38,848,605);
			contentPane.add(backgrounde);	
		}
		
	

}
