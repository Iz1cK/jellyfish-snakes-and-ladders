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


public class HomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3514602183732985694L;
	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public void setColor(JPanel panel) 
	{
		Random rand = new Random();
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        
        panel.setBackground(new Color(red, green, blue));
		//panel.setBackground(new java.awt.Color(194, 226, 241));
	}
	public void resetColor(JPanel panel) 
	{
		panel.setBackground(new java.awt.Color(117, 123, 235));
	}
	
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
	
	
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*this for start a new game in menu*/
		JLabel playGame = new JLabel("PLAY");
        playGame.setForeground(Color.WHITE);
        playGame.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
        playGame.setBounds(624, 240, 141, 51);
        contentPane.add(playGame);
		
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
					ChooseGame1 chooseGame = new ChooseGame1();
				 	chooseGame.setVisible(true);
				 	dispose();
				 	}
				});
		
		
		
		/*that's for game history */
		JPanel gameHistoryPanel = new JPanel();
		gameHistoryPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(gameHistoryPanel);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(gameHistoryPanel);
			}
			public void mousePressed(MouseEvent e) {
				 	GameHistoryViews gameDetails = new GameHistoryViews();
				 	gameDetails.setVisible(true);
				 	dispose();
	                }
		});
		gameHistoryPanel.setBackground(new Color(117, 123, 235));
		gameHistoryPanel.setLayout(null);
		gameHistoryPanel.setBounds(332, 255, 150, 150);
		contentPane.add(gameHistoryPanel);
		
		JLabel historyLabel = new JLabel("History");
		historyLabel.setForeground(Color.white);
		historyLabel.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		historyLabel.setBounds(44, 98, 96, 51);
		gameHistoryPanel.add(historyLabel);
		
		JLabel historyIcon = new JLabel("");
		historyIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/HistoryIcon.png")));
		historyIcon.setBounds(48, 33, 59, 63);
		gameHistoryPanel.add(historyIcon);
		
		/*that for question page*/
		JPanel questionPanel = new JPanel();
		questionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(questionPanel);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(questionPanel);
			}
			@Override
			public void mousePressed(MouseEvent e) {
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
		questionPanel.setBackground(new Color(117, 123, 235));
		questionPanel.setLayout(null);
		questionPanel.setBounds(144, 429, 150, 150);
		contentPane.add(questionPanel);
		
		JLabel questionLabel = new JLabel("Question");
		questionLabel.setForeground(Color.white);
		questionLabel.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		questionLabel.setBounds(33, 96, 134, 43);
		questionPanel.add(questionLabel);
		
		JLabel questionIcon = new JLabel("");
		questionIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/QuestionIcon.png")));
		questionIcon.setBounds(51, 32, 59, 63);
		questionPanel.add(questionIcon);
		
		//exit
		JPanel exitPanel = new JPanel();
		
		exitPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColor(exitPanel);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetColor(exitPanel);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		exitPanel.setBackground(new Color(117, 123, 235));
		exitPanel.setLayout(null);
		exitPanel.setBounds(332, 429, 150, 150);
		contentPane.add(exitPanel);
		
		JLabel exitLabel = new JLabel("EXIT");
		exitLabel.setForeground(Color.white);
		exitLabel.setFont(new Font("Kristen ITC", Font.BOLD, 18));
		exitLabel.setBounds(49, 100, 59, 28);
		exitPanel.add(exitLabel);
		
		JLabel exitIcon = new JLabel("");
		exitIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/ExitIcon.png")));
		exitIcon.setBounds(59, 26, 59, 63);
		exitPanel.add(exitIcon);
		//Sysdata sysdata= Sysdata.getInstance();
		//sysdata.readQuestions();
		//System.out.println(sysdata.questionsList.toString());
		
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
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(HomeView.class.getResource("/img/homeBackground.png")));
		lblBackground.setBounds(0, 0,650,650);
		contentPane.add(lblBackground);
		
	}	

}
