package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.AddQuestionController;
import Controller.EditQuestionController;
import Controller.GameBoardController;
import Controller.ghostGameBoardController;
import Model.Board;
import Model.DIFFICULTY;
import Model.Game;
import Model.PLAYERCOLORS;
import Model.Player;
import Model.Questions;
import Model.Scores;
import View.QuestionPopUp.Glass;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;


public class FinalPageScores extends javax.swing.JFrame {
	

	private static final long serialVersionUID = 1L;
	private JTextField obtion1TeksField;
	private JTextField questionTextField;
	private JTextField obtion3textField;
	private JTextField obtion2TextField;
	private JTextField obtion4textField;
	@SuppressWarnings("rawtypes")
	private JComboBox<Comparable> comboBox;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private int questionid;
	/**
     * Creates new form Login
     */
    public FinalPageScores(Scores game) {
        initComponents(game);
        setBackground(new Color(0, 0, 0, 0));
    }
    
    public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(Scores game) {

        background = new Background();
        
        JLabel timerLabel = new JLabel(game.getDuration(), SwingConstants.CENTER);
        timerLabel.setBounds(590, 400, 163, 37);
        timerLabel.setForeground(Color.WHITE);
        background.add(timerLabel);
        
       
    		
    		JLabel home = new JLabel("");
			ImageIcon ImageIcon6 = new ImageIcon(QuestionsView.class.getResource("/img/home1.png"));
			ImageIcon test6= resized(ImageIcon6.getImage(), 100, 100);
			home.setIcon(test6);
			// Set size to match content pane
			home.setBounds(300, 500, 100, 100);
			background.add(home);
			home.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/home2.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
					home.setIcon(test);
					}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/home1.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
					home.setIcon(test);
					}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					HomeView homeview = new HomeView();
					homeview.setVisible(true);
					dispose();
					}
				});
        
        JLabel winnerName = new JLabel("New label");
        winnerName.setBounds(420, 200, 184, 62);
        winnerName.setForeground(Color.BLACK);
        winnerName.setFont(new Font("Dialog", Font.BOLD, 36));
        winnerName.setText(game.getWinningPlayer());
        background.add(winnerName);
        
        JLabel score = new JLabel("New label");
        score.setBounds(420, 320, 184, 62);
        score.setForeground(Color.BLACK);
        score.setFont(new Font("Dialog", Font.BOLD, 36));
        score.setText(String.valueOf(game.getScore()));
        background.add(score);
        
        JLabel difficultyImage = new JLabel("New label");
        difficultyImage.setBounds(320, 410, 60, 37);
        String path = game.getDifficulty() == 1 ? "easy" : game.getDifficulty() == 2 ? "medium" : game.getDifficulty() == 3 ? "hard" : "easy";
        ImageIcon difficultyIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + path + ".png"));
        difficultyImage.setIcon(difficultyIcon);
        background.add(difficultyImage);
        
        
        JLabel left = new JLabel();
        left.setIcon(new ImageIcon(FinalPageScores.class.getResource("/img/aseel.gif")));
        left.setBounds(530, 3, 500, 400);
        background.add(left);

        JLabel right = new JLabel();
        right.setIcon(new ImageIcon(FinalPageScores.class.getResource("/img/aseel.gif")));
        right.setBounds(-60, 3, 500, 400);
        
        background.add(right);
        
        JLabel gameDifficulty_1 = new JLabel("PLAYER");
        gameDifficulty_1.setHorizontalAlignment(SwingConstants.CENTER);
        gameDifficulty_1.setForeground(Color.BLACK);
        gameDifficulty_1.setFont(new Font("Dialog", Font.BOLD, 36));
        gameDifficulty_1.setBounds(370, 150, 250, 60);
        background.add(gameDifficulty_1);
        
        JLabel gameScore = new JLabel("SCORE");
        gameScore.setHorizontalAlignment(SwingConstants.CENTER);
        gameScore.setForeground(Color.BLACK);
        gameScore.setFont(new Font("Dialog", Font.BOLD, 36));
        gameScore.setBounds(340, 280, 250, 60);
        background.add(gameScore);

        
        JLabel again = new JLabel("");
		ImageIcon ImageAgain = new ImageIcon(QuestionsView.class.getResource("/img/again1.png"));
		ImageIcon testagain= resized(ImageAgain.getImage(), 100, 100);
		again.setIcon(testagain);
		// Set size to match content pane
		again.setBounds(450, 500, 100, 100);
		background.add(again);
		again.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/again2.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
				again.setIcon(test);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/again1.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
				again.setIcon(test);
				}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ghostGameBoardController gbc = ghostGameBoardController.getInstance();
        		Board board = game.getBoardgame();
        		Board newBoard = new Board(board.getDifficultyBoard(), board.getPlayers());
        		newBoard.generateBoard();
        		newBoard.initiateQuestionSquares();
        		newBoard.generateSnakesAndLadder();
        		gbc.setGameBoard(newBoard);
        		ghostGame gbv = new ghostGame();
        		gbv.setExtendedState(JFrame.MAXIMIZED_BOTH);
        		gbv.setVisible(true);
        		dispose();
				}
			});

        
        JLabel gameHistory = new JLabel("");
		ImageIcon ImageHistory = new ImageIcon(QuestionsView.class.getResource("/img/history1.png"));
		ImageIcon testHistory= resized(ImageHistory.getImage(), 100, 100);
		gameHistory.setIcon(testHistory);
		// Set size to match content pane
		gameHistory.setBounds(600, 500, 100, 100);
		background.add(gameHistory);
		gameHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/history2.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
				gameHistory.setIcon(test);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/history1.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
				gameHistory.setIcon(test);
				}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameScoreView ghv = new GameScoreView();
        		ghv.setVisible(true);
        		dispose();
				}
			});
     
		JLabel duration = new JLabel("");
		ImageIcon ImageDuration = new ImageIcon(QuestionsView.class.getResource("/img/duration.png"));
		ImageIcon testduration= resized(ImageDuration.getImage(), 250, 120);
		duration.setIcon(testduration);
		// Set size to match content pane
		duration.setBounds(530, 370, 250, 120);
		background.add(duration);
		
		
		JLabel diff = new JLabel("");
		ImageIcon Imagediff = new ImageIcon(QuestionsView.class.getResource("/img/diffFinal.png"));
		ImageIcon testdiff= resized(Imagediff.getImage(), 250, 120);
		diff.setIcon(testdiff);
		// Set size to match content pane
		diff.setBounds(200, 370, 250, 120);
		background.add(diff);
		
		questionLabel_1 = new JLabel("END GAME");
        questionLabel_1.setForeground(Color.WHITE);
        questionLabel_1.setBounds(280, 20, 500, 50);
        questionLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 45));
        background.add(questionLabel_1);
        
        
        
    	
    		
        		
        		ImageIcon ImageIconQue = new ImageIcon(ChooseGame1.class.getResource("/img/addQ1.png"));
        		ImageIcon testQue= resized(ImageIconQue.getImage(), 90, 90);
    		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        backgroundLayout.setHorizontalGroup(
        	backgroundLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 952, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
        	backgroundLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 679, Short.MAX_VALUE)
        );
        background.setLayout(backgroundLayout);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(238)
        			.addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(1012))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(background, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddQuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddQuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddQuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddQuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	   try {
                   	ArrayList<Player> aplayers = new ArrayList<>();
                       aplayers.add(new Player(0,"george",PLAYERCOLORS.BLUE));
                       aplayers.add(new Player(0,"aseel",PLAYERCOLORS.ORANGE));
                       
                       Board board = new Board(DIFFICULTY.EASY, aplayers);
                       board.generateBoard();
                       board.initiateQuestionSquares();
                       board.generateSnakesAndLadder();
                       int scoreee= 2213;
                       
                       HashMap<Player,Integer> playersposs = board.getPlayersPositions();
                       playersposs.put(aplayers.get(0), 23);
                       board.setPlayersPositions(playersposs);
                       
                       FinalPageScores frame = new FinalPageScores(new Scores(DIFFICULTY.EASY, board, aplayers.get(0),scoreee , "19:10.02"));
                       frame.setVisible(true);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               // new hjjh(null).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Background background;
    private JLabel questionLabel_1;
}