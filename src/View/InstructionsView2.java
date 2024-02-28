package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InstructionsView2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7108532504413472904L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionsView2 frame = new InstructionsView2();
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
	public InstructionsView2() {
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
		chooseGameIcon.setBounds(0, 0, 598,633);
		chooseGameIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/gameBoard"	+ ".png")));
		
		JPanel chooseGame = new JPanel();
		chooseGame.setOpaque(false);
		chooseGame.setBounds(0,134,598,633);
		chooseGame.add(chooseGameIcon);
		contentPane.add(chooseGame);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setText(convertToMultiline
				("1 aside from number of players, game difficulty will determine the following aspects of the game: "
						+ " \n Easy: Navigate a board with 4 snakes (see the provided image) and 4 ladders of varying lengths"
						+ " (1-4). Roll the dice to advance up to 4 spaces"
						+ " \nMedium: Up the ante with 6 snakes (2 red, 2 green, 1 blue, 1 yellow) and 6 ladders"
						+ " (1-6). rolling the dice lets you move up to 6 spaces"
						+ "\n Hard: Feeling adventurous? Conquer 8 snakes (2 of each color - placement is yours again) and 8 ladders (1-8). The dice still allows 6-step moves(brace yourself for a brain teaser!)."
		));
		lblNewLabel_1.setBounds(642, 228, 227, 277);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		/*
		 * Second part of instructions
		 */
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setText(convertToMultiline
						("2. landind on snake's head, moves the player to the bottom of the snake"
								+ "\n there are 4 types of snakes in each game"
								+ " \n yellow- move one row down"
								+ " \n green- move two rows down"
								+ " \n blue- move three rows down"
								+ " \n red- go back to square one"
				));
		lblNewLabel_2.setBounds(642, 515, 210, 161);
		contentPane.add(lblNewLabel_2);
		
		
		/*
		 * Third part of instructions
		 */
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setText(convertToMultiline
				("3. landind on the bottom of a ladder moves the player up to the top of the ladder"
						+ "\n there can be ladders of up to 8 lengths in each game 1-8"
						+ "\n in an easy game there are 4 ladders of lengths 1-4"
						+ "\n in an medium game there are 6 ladders of lengths 1-6"
						+ "\n in a difficult game there are 8 ladders of lengths 1-8"
					
		));
		lblNewLabel_3.setBounds(916, 228, 210, 210);
		contentPane.add(lblNewLabel_3);

		
		JLabel lblNextPage = new JLabel("Previous Page 2/2");
		lblNextPage.setBounds(931, 685, 127, 28);
		lblNextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InstructionsView instructions = new InstructionsView();
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
