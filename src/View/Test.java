package View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GameBoardController;
import Controller.questionPopUpController;
import Model.PLAYERCOLORS;
import Model.Player;
import Model.Questions;
import Model.Sysdata;

public class Test extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	questionPopUpController controller= questionPopUpController.getInstance();
	
	Sysdata sysdata= Sysdata.getInstance();
	public List<Questions> questions= new ArrayList<>();
	Random random = new Random();

    public Test() {
    	sysdata.readQuestions();
        initComponents();
        setBackground();
    }

    private void setBackground() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/questionsViewBackground.png"));
        JLabel backgroundLabel = new JLabel(icon);
        backgroundLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JButton button = new JButton("Show Message");
        button.setBounds(315, 235, 150, 30);
        button.addActionListener(evt -> jButton1ActionPerformed(evt));

        panel.add(button);
        panel.add(backgroundLabel);
        getContentPane().add(panel);
    }

    private void initComponents() {
        new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    public Questions questionRank(int diffculty) {
		for (Questions qd : sysdata.questionsList) {
			if(qd.getDifficulty()==diffculty) {
				questions.add(qd);
			}
		}
		if (!questions.isEmpty()) {
		    // Generate a random index within the bounds of the list's size
		    int randomIndex = random.nextInt(questions.size());

		    // Retrieve the question at the random index
		    Questions randomQuestion = questions.get(randomIndex);
		    return randomQuestion;
		    // Now you have a random question from the easyQuestion list
		    // Do something with the random question
		} else {
		    // Handle the case where the easyQuestion list is empty
		    System.out.println("No questions available in the easyQuestion list.");
		    return null;
		}
	
		
	}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
       // MessageDialog obj = new MessageDialog(this);
    	GameBoardController g=GameBoardController.getInstance();
    	Player p=new Player(1, "ahmad", PLAYERCOLORS.RED);
    	g.showQuestion(2, p);
        
        //Questions question= questionRank(2);
        //obj.cmdobt1.setText(question.getAnswers().get(0));
        //obj.cmdobt2.setText(question.getAnswers().get(1));
        //obj.cmdobt3.setText(question.getAnswers().get(2));
        //obj.cmdobt4.setText(question.getAnswers().get(3));
        //obj.correctAns=question.getCorrect_ans();
        //String questionBody=question.getQuestion();
        
        //obj.showMessage("medium", questionBody);
        
       // if (obj.getMessageType() == MessageDialog.MessageType.ANS1) {
           
        //} else {

        //}
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }
}
