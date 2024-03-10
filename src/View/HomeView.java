package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Sysdata;


public class HomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3514602183732985694L;
	private JPanel contentPane;
	private static Sysdata sysdata = Sysdata.getInstance();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//method to resized the image
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
	
	/**
	 * Create the frame.
	 */
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*this for start a new game in menu*/
		JLabel historyLabel_1 = new JLabel("PLAY");
        historyLabel_1.setForeground(Color.WHITE);
        historyLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
        historyLabel_1.setBounds(624, 240, 141, 51);
        contentPane.add(historyLabel_1);
		
        JLabel playgame1 = new JLabel("");
		contentPane.add(playgame1);
		 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/blueWood.png"));
		 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
	     playgame1.setIcon(test);
	     // Set size to match content pane
	     playgame1.setBounds(430, 233, 457, 72);
	     contentPane.add(playgame1);	
	     playgame1.addMouseListener(new MouseAdapter() {
	    	 @Override
	    	 public void mouseEntered(MouseEvent e) {
	    		 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/blueWood2.png"));
			     ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
			     playgame1.setIcon(test);
					}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/blueWood.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
					playgame1.setIcon(test);
					}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(!sysdata.minimumQuestionsRequirment()) {
			    		JOptionPane.showMessageDialog(contentPane, "please make sure there is at least 1 question for each difficulty");
			    	} else {
			    		ChooseGame1 chooseGame = new ChooseGame1();
			    		chooseGame.setVisible(true);
			    		dispose();
			    	}
				 }
			});
	     
	     /*that's for game history */
	     JLabel gameHistory = new JLabel("GAME HISTORY");
	     gameHistory.setForeground(Color.WHITE);
	     gameHistory.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 28));
	     gameHistory.setBounds(532, 335, 303, 51);
	     contentPane.add(gameHistory);
	     
	     JLabel gameHistoryIcon = new JLabel("");
	     contentPane.add(gameHistoryIcon);
	     ImageIcon ImageHistory = new ImageIcon(HomeView.class.getResource("/img/greenWood.png"));
	     ImageIcon testHistory= resized(ImageHistory.getImage(), 500, 212);
	     gameHistoryIcon.setIcon(testHistory);
	     // Set size to match content pane
	     gameHistoryIcon.setBounds(430, 318, 553, 100);
	     contentPane.add(gameHistoryIcon);	
	     gameHistoryIcon.addMouseListener(new MouseAdapter() {
	    	 @Override
	    	 public void mouseEntered(MouseEvent e) {
	    		 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/greenWood2.png"));
	    		 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
	    		 gameHistoryIcon.setIcon(test);
	    		 }
	    	 @Override
	    	 public void mouseExited(MouseEvent e) {
	    		 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/greenWood.png"));
	    		 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
	    		 gameHistoryIcon.setIcon(test);
	    		 }
	    	 @Override
	    	 public void mouseClicked(MouseEvent arg0) {
	    		 GameHistoryViews gameDetails = new GameHistoryViews();
	    		 gameDetails.setVisible(true);
	    		 dispose();
	    		 }
	    	 });
		
		/*that for question page*/
		 JLabel questionsLabel = new JLabel("QUESTIONS");
		 questionsLabel.setForeground(Color.WHITE);
		 questionsLabel.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
		 questionsLabel.setBounds(578, 429, 257, 51);
		 contentPane.add(questionsLabel);
		 
		 JLabel questionsIcon = new JLabel("");
		 contentPane.add(questionsIcon);
		 ImageIcon ImageQuestion = new ImageIcon(HomeView.class.getResource("/img/yellowWood.png"));
		 ImageIcon testQuestion= resized(ImageQuestion.getImage(), 500, 212);
		 questionsIcon.setIcon(testQuestion);
		 // Set size to match content pane
		 questionsIcon.setBounds(430, 413, 484, 100);
		 contentPane.add(questionsIcon);
		 questionsIcon.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/yellowWood2.png"));
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
				 questionsIcon.setIcon(test);
				 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/yellowWood.png"));
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
				 questionsIcon.setIcon(test);
				 }
			 @Override
			 public void mouseClicked(MouseEvent arg0) {
				 String[] messages = {
						 "That page is only for admins.",
						 };
				 int option = JOptionPane.showOptionDialog(null,
						 messages[0], "Admin Confirmation",
						 JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE,
						 null,
						 new Object[]{"I am an admin", "Cancel"},
						 null);
				 if (option == JOptionPane.YES_OPTION) {
					 // User is an admin
					 // Proceed to admin page
					 AdminView adminPage = new AdminView();
					 adminPage.setVisible(true);
					 dispose(); // You need to implement this method to close the current frame/dialog
					 }
				 }
			 });
		
		//exit
		 JLabel Exit = new JLabel("EXIT");
		 Exit.setForeground(Color.WHITE);
		 Exit.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
		 Exit.setBounds(641, 524, 257, 51);
		 contentPane.add(Exit);
				
		 JLabel exitIcon = new JLabel("");	
		 contentPane.add(exitIcon);				
		 ImageIcon ImageExit = new ImageIcon(HomeView.class.getResource("/img/orangeWood.png"));				
		 ImageIcon testExit= resized(ImageExit.getImage(), 500, 212);	
		 exitIcon.setIcon(testExit);								
		 // Set size to match content pane				
		 exitIcon.setBounds(440, 503, 484, 100);	
		 contentPane.add(exitIcon);						
		 exitIcon.addMouseListener(new MouseAdapter() {					
			 @Override					
			 public void mouseEntered(MouseEvent e) {						
				 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/orangeWood2.png"));					    
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);					    
				 exitIcon.setIcon(test);						
			 }					
			 @Override					
			 public void mouseExited(MouseEvent e) {						
				 ImageIcon ImageIcon = new ImageIcon(HomeView.class.getResource("/img/orangeWood.png"));						
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);						
				 exitIcon.setIcon(test);						
			 }					
			 @Override					
			 public void mouseClicked(MouseEvent arg0) {						
				 System.exit(0);						
			 }					
		 });
		
		JLabel lblInstructionsIcon = new JLabel("");
		lblInstructionsIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/circle_crop.png")));
		lblInstructionsIcon.setBounds(10, 100,100,100);
		contentPane.add(lblInstructionsIcon);
		
		lblInstructionsIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InstructionsView instructions = new InstructionsView();
				instructions.setVisible(true);
				dispose();
			}
		});
		
		/*
		 * background icon	
		 */
		 JLabel backgrounde = new JLabel("");
	        ImageIcon backgroundImageIcon = new ImageIcon(HomeView.class.getResource("/img/snakeAndLadder.jpg"));

	        // Resize the background image to fit the frame
	        Image backgroundImage = backgroundImageIcon.getImage();
	        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

	        backgrounde.setIcon(resizedBackgroundIcon);

	        // Set size to match content pane
	        backgrounde.setBounds(0, 0, getWidth(), getHeight());

	        contentPane.add(backgrounde);	
	        
		
	}	

}
