package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class InstructionsView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionsView frame = new InstructionsView();
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
	public InstructionsView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 1150, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		/*
		 * page title
		 */
		JLabel lblTitle = new JLabel("");
		lblTitle.setIcon(new ImageIcon(HomeView.class.getResource("/img/pageTitle.png")));
		lblTitle.setBounds(250, 0,585,124);
		contentPane.add(lblTitle);
		
		
		
		/*
		 * back to main menu option
		 */
		JButton backButton = new JButton();
		backButton.setBounds(0,0,53,45);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setPreferredSize(new Dimension(0,0));
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeView homeview = new HomeView();
				homeview.setVisible(true);
				dispose();
				
			}
		});
		
		JLabel backIcon = new JLabel("");
		backIcon.setBounds(0, 0, 53, 45);
		backIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/backQuestionView1"	+ ".png")));
		
		JPanel backPanel = new JPanel();
		backPanel.setBounds(20, 44, 53, 45);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		backPanel.add(backIcon);
		backPanel.add(backButton);	
		contentPane.add(backPanel);
		
		
		/*
		 *  instructions Title
		 */
		JLabel lblTitle_1 = new JLabel();
		lblTitle_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTitle_1.setText("Instructions:");
		lblTitle_1.setBounds(30, 99, 169, 28);
		contentPane.add(lblTitle_1);
		
		
		
		/*
		 * First part of instructions
		 */
		JLabel chooseGameIcon = new JLabel("");
		chooseGameIcon.setBounds(0, 169, 350,227);
		chooseGameIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/choosingGame"	+ ".png")));
		
		JPanel chooseGame = new JPanel();
		chooseGame.setBounds(0,134,350,227);
		chooseGame.add(chooseGameIcon);
		contentPane.add(chooseGame);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setText(convertToMultiline
				("1 when you press start game, the screen on left will appear, then you choose "
				+ "the follwing items in order: game difficulty: easy/medium/hard which will \n"
				+ "determine the board size 7*7/10*10/13*13, and upper limit on number of players \n"
				+ "3/6/8 both respectively, then each player has to choose a color for their character \n"
				+ "and a username, once the last player does this and only then a start game option will appear"
		));
		lblNewLabel_1.setBounds(356, 134, 210, 210);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		/*
		 * Second part of instructions
		 */
		JLabel gameplayIcon = new JLabel("");
		gameplayIcon.setBounds(0, 169, 350,227);
		gameplayIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/Gameplay"	+ ".png")));
		
		JPanel gameplay = new JPanel();
		gameplay.setBounds(0,370,350,227);
		gameplay.add(gameplayIcon);
		contentPane.add(gameplay);
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setText(convertToMultiline
						("2. when the game starts, player's charachters will be positioned on sqaure one \n"
						+ " and your goal is to be the first one to get to the last square on the board \n"
						+ "the current player's turn will be marked with an icon next to their name \n"
						+ "on the right hand side of the screen, pressing the dice icon rolls the players turn\n"
				));
		lblNewLabel_2.setBounds(356, 370, 210, 161);
		contentPane.add(lblNewLabel_2);
		
		
		/*
		 * Third part of instructions
		 */
		
		JLabel gameplay3 = new JLabel("");
		gameplay3.setBounds(570, 134, 350,227);
		gameplay3.setIcon(new ImageIcon(HomeView.class.getResource("/img/gameplayQuestion"	+ ".png")));
		
		JPanel gameInstructions3 = new JPanel();
		gameInstructions3.setBounds(570,134,350,227);
		gameInstructions3.add(gameplay3);
		contentPane.add(gameInstructions3);
		
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setText("<html>3.Squares marked with a question mark symbolize a question square!  landing on a question square can both benifit and harm the players progress when a player's character lands on a question square a multiple answer question belonging to one of three difficulty levels is presetned to the playeranswering an easy/medium level question correctly keeps the player in placeanswering an easy/medium/difficult question uncorrectly moves moves the player backwards 1/2/3 steps respectivelyanswering a difficult question correctly moves the player 1 square fowards");
		lblNewLabel_3.setBounds(930, 116, 210, 264);
		contentPane.add(lblNewLabel_3); 
		
		
		
		
		
		
		
		
		
		
		/*
		 * Fourth part of instructions
		 */
		JLabel gameplay11Icon = new JLabel("");
		gameplay11Icon.setBounds(570, 169, 350,227);
		gameplay11Icon.setIcon(new ImageIcon(HomeView.class.getResource("/img/gameplaySurpriseSquare"	+ ".png")));
		
		JPanel gameplay1 = new JPanel();
		gameplay1.setBounds(570,370,350,227);
		gameplay1.add(gameplay11Icon);
		contentPane.add(gameplay1);
		
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setText("<html>4.Squares marked with a gift symbolize a surprise square!  landing on a surprise square can both benifit and harm the players progress when a player's character lands on a sureprise square he has a 50/50 chance of going 10 steps either forwards or backwards");
		lblNewLabel_4.setBounds(930, 409, 210, 161);
		contentPane.add(lblNewLabel_4);

		
		JLabel lblNextPage = new JLabel("Next Page 1/2");
		lblNextPage.setBounds(975, 685, 83, 28);
		lblNextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InstructionsView2 instructions = new InstructionsView2();
				instructions.setVisible(true);
				dispose();
			}
		});
		contentPane.add(lblNextPage);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource("/img/instructionsBackground.png")));
		lblNewLabel.setBounds(0, 0,1150, 770);
		contentPane.add(lblNewLabel);
		
		
		
	}
	
	
	
	
	
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	
}
