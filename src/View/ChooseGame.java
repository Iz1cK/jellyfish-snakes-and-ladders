package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DIFFICULTY;

import javax.swing.SwingConstants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ChooseGame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7958152447515121679L;
	private JPanel contentPane;
	int numCurrentPlayer=1;
	int counter=1;
	private int maxNumberOfPlayers=2;
	private boolean isEasy=false, isMedium=false, isHard=false;
	DIFFICULTY selectedDifficulty;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChooseGame frame = new ChooseGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChooseGame() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 680, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel difficltyLeve = new JLabel("level difficulty");
		contentPane.add(difficltyLeve);
		difficltyLeve.setForeground(Color.WHITE); // Set the foreground color to white
		difficltyLeve.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
        difficltyLeve.setHorizontalAlignment(SwingConstants.CENTER);
		difficltyLeve.setBounds(97, 78, 155, 41);
		
		JLabel selectedLevel = new JLabel();
		selectedLevel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/selected.png")));		
		selectedLevel.setBounds(37, 119, 101, 87);
		contentPane.add(selectedLevel);
		selectedLevel.setLayout(null);
		selectedLevel.setVisible(false);
		
//		JLabel colorIsTaken = new JLabel();
//		colorIsTaken.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/taken.png")));		
//		colorIsTaken.setBounds(0, 0, 50, 67);
//		contentPane.add(colorIsTaken);
//		colorIsTaken.setLayout(null);
//		colorIsTaken.setVisible(true);
		
			
		JLabel easy = new JLabel("easy");
		easy.setForeground(Color.WHITE); // Set the foreground color to white
		easy.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		easy.setBounds(27, 130, 116, 48); // Adjusted bounds to fit the text
		easy.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(easy);
        
		JLabel easyLevel = new JLabel();
		easyLevel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/easy.png")));		
		easyLevel.setBounds(58, 130, 55, 55);
		contentPane.add(easyLevel);
		easyLevel.setLayout(null);
		
		
		JLabel medium = new JLabel("medium");
		medium.setForeground(Color.WHITE); // Set the foreground color to white
		medium.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		medium.setBounds(123, 130, 90, 48); // Adjusted bounds to fit the text
		medium.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(medium);
		
		JLabel mediumLevel = new JLabel();
		mediumLevel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/medium.png")));		
		mediumLevel.setBounds(141, 130, 55, 55);
		contentPane.add(mediumLevel);
		mediumLevel.setLayout(null);
		
		JLabel hard = new JLabel("hard");
		hard.setForeground(Color.WHITE); // Set the foreground color to white
		hard.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		hard.setBounds(195, 130, 90, 48); // Adjusted bounds to fit the text
		hard.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(hard);
		
		JLabel hardLevel = new JLabel();
		hardLevel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/hard.png")));		
		hardLevel.setBounds(217, 130, 55, 55);
		contentPane.add(hardLevel);
		hardLevel.setLayout(null);
		
        

		JLabel colorTaken = new JLabel();
		colorTaken.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/taken.png")));		
		//colorTaken.setBounds(37, 130, 101, 87);
		contentPane.add(colorTaken);
		colorTaken.setLayout(null);
		colorTaken.setVisible(false);
		
		
		JLabel numbers = new JLabel("2");
		numbers.setForeground(Color.WHITE); // Set the foreground color to white
		numbers.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		numbers.setHorizontalAlignment(SwingConstants.CENTER);
		numbers.setBounds(455, 107, 61, 87);
		contentPane.add(numbers); 
		
		easy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedDifficulty=DIFFICULTY.EASY;
        		selectedLevel.setVisible(false);
        		isEasy=true;
        		isMedium=false;
        		isHard=false;
                selectedLevel.setBounds(55, 120, 101, 87);
        		selectedLevel.setVisible(true);
        		maxNumberOfPlayers=3;
        		numbers.setText(Integer.toString(2)); // Update the text field with the new value

            }
        });

        medium.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedDifficulty=DIFFICULTY.MEDIUM;
        		selectedLevel.setVisible(false);
        		isEasy=false;
        		isMedium=true;
        		isHard=false;
                selectedLevel.setBounds(135, 120, 101, 87);
        		selectedLevel.setVisible(true);
        		maxNumberOfPlayers=6;
        		numbers.setText(Integer.toString(2)); // Update the text field with the new value

            }
        });

        hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedDifficulty=DIFFICULTY.HARD;
        		selectedLevel.setVisible(false);
        		isEasy=false;
        		isMedium=false;
        		isHard=true;
                selectedLevel.setBounds(215, 120, 101, 87);
        		selectedLevel.setVisible(true);
        		maxNumberOfPlayers=8;
        		numbers.setText(Integer.toString(2)); // Update the text field with the new value
            }
        });

        
        JLabel players_1 = new JLabel();
		players_1.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));

		players_1.setBounds(37, 47, 280, 151);
		contentPane.add(players_1);	
			
		/* number of players *///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel_2 = new JLabel("choose number of players");
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.WHITE); // Set the foreground color to white
		lblNewLabel_2.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(376, 84, 190, 28);
		
 
		
		 JLabel plus = new JLabel();
	        plus.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/plus.png")));
	        plus.setBounds(530, 107, 61, 87);
	        plus.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int currentNumberOfPlayers = Integer.parseInt(numbers.getText());
	                if (isEasy && currentNumberOfPlayers < 3) {
	                    currentNumberOfPlayers++;
	                    numbers.setText(Integer.toString(currentNumberOfPlayers));
	                } else if (isMedium && currentNumberOfPlayers < 6) {
	                    currentNumberOfPlayers++;
	                    numbers.setText(Integer.toString(currentNumberOfPlayers));
	                } else if (isHard && currentNumberOfPlayers < 8) {
	                    currentNumberOfPlayers++;
	                    numbers.setText(Integer.toString(currentNumberOfPlayers));
	                }
	            }
	        });
	        contentPane.add(plus);
    
	        JLabel minus = new JLabel();
	        minus.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/menus.png")));
	        minus.setBounds(386, 107, 61, 87);
	        minus.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int currentNumberOfPlayers = Integer.parseInt(numbers.getText());
	                if (currentNumberOfPlayers > 2) {
	                    currentNumberOfPlayers--;
	                    numbers.setText(Integer.toString(currentNumberOfPlayers));
	                }
	            }
	        });
	        contentPane.add(minus);

	  
		JLabel difficulyLabel = new JLabel();
		difficulyLabel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));		
		difficulyLabel.setBounds(344, 47, 280, 151);
		contentPane.add(difficulyLabel);
		difficulyLabel.setLayout(null);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* nickname and color */
		

		JLabel selectedColor = new JLabel();
		selectedColor.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/selected.png")));		
		selectedColor.setBounds(27, 269, 116, 71);
		contentPane.add(selectedColor);
		selectedColor.setLayout(null);
		selectedColor.setVisible(false); 
		
		JLabel player1 = new JLabel();
		ImageIcon img1=new ImageIcon(ChooseGame.class.getResource("/img/whitePlayer.png"));
		player1.setIcon(new ImageIcon(img1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player1.setBounds(32, 257, 41, 78);	
		contentPane.add(player1);
		player1.setLayout(null);
		
		JLabel player2 = new JLabel();
		ImageIcon img2=new ImageIcon(ChooseGame.class.getResource("/img/orangePlayer.png"));
		player2.setIcon(new ImageIcon(img2.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player2.setBounds(78, 257, 41, 78);
		contentPane.add(player2);
		player2.setLayout(null);

		JLabel player3 = new JLabel();
		ImageIcon img3=new ImageIcon(ChooseGame.class.getResource("/img/purplePlayer.png"));
		player3.setIcon(new ImageIcon(img3.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player3.setBounds(124, 257, 41, 78);
		contentPane.add(player3);
		player3.setLayout(null);

		JLabel player4 = new JLabel();
		ImageIcon img4=new ImageIcon(ChooseGame.class.getResource("/img/pinkPlayer.png"));
		player4.setIcon(new ImageIcon(img4.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player4.setBounds(170, 258, 41, 78);
		contentPane.add(player4);
		player4.setLayout(null);
		
		JLabel player5 = new JLabel();
		ImageIcon img5=new ImageIcon(ChooseGame.class.getResource("/img/greenPlayer.png"));
		player5.setIcon(new ImageIcon(img5.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player5.setBounds(216, 258, 41, 78);
		contentPane.add(player5);
		player5.setLayout(null);
		
		JLabel player6 = new JLabel();
		ImageIcon img6=new ImageIcon(ChooseGame.class.getResource("/img/bluePlayer.png"));
		player6.setIcon(new ImageIcon(img6.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player6.setBounds(262, 258, 41, 78);
		contentPane.add(player6);
		player6.setLayout(null);
		
		
		JLabel player7 = new JLabel();
		ImageIcon img7=new ImageIcon(ChooseGame.class.getResource("/img/redPlayer.png"));
		player7.setIcon(new ImageIcon(img7.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player7.setBounds(308, 258, 41, 78);
		contentPane.add(player7);
		player7.setLayout(null);
		
		JLabel player8 = new JLabel();
		ImageIcon img8=new ImageIcon(ChooseGame.class.getResource("/img/yellowPlayer.png"));
		player8.setIcon(new ImageIcon(img8.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player8.setBounds(354, 258, 41, 78);
		contentPane.add(player8);
		player8.setLayout(null);
		
		
		


        
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(400, 281, 212, 41);
		contentPane.add(layeredPane);

		JTextField nicknameField = new JTextField("insert your nickname");
        nicknameField.setBounds(0, 0, 212, 41);
        layeredPane.add(nicknameField, JLayeredPane.PALETTE_LAYER);

        JLabel iconLabel = new JLabel(new ImageIcon(ChooseGame.class.getResource("/img/id.png")));
        iconLabel.setBounds(0, 0, 212, 41);
        layeredPane.add(iconLabel, JLayeredPane.DEFAULT_LAYER);

        // Add FocusListener to clear default text when field gains focus
        nicknameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nicknameField.getText().equals("insert your nickname")) {
                    nicknameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // No action needed when focus is lost
            }
        });




        JLabel currentPlayer = new JLabel("player "+numCurrentPlayer);
		currentPlayer.setForeground(Color.WHITE); // Set the foreground color to white
		currentPlayer.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayer.setBounds(285, 230, 69, 28);
		contentPane.add(currentPlayer); 	

		JLabel next_1 = new JLabel();
		next_1.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));
		next_1.setBounds(27, 211, 597, 151);
		contentPane.add(next_1);
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		/* next */


		JLabel difficltyLeve_1 = new JLabel("next");
		difficltyLeve_1.setHorizontalAlignment(SwingConstants.CENTER);
		difficltyLeve_1.setForeground(Color.WHITE);
		difficltyLeve_1.setFont(new Font("Dialog", Font.BOLD, 15));
		difficltyLeve_1.setBounds(252, 380, 155, 41);
		contentPane.add(difficltyLeve_1);
		
		JLabel next = new JLabel();
		next.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));		
		next.setBounds(261, 380, 129, 41);
		contentPane.add(next);
		next.setLayout(null);
		
		 

		next.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int playersNumber = Integer.parseInt(numbers.getText());
		        if (isEasy && counter < playersNumber) {
		            counter++; // Increment numCurrentPlayer
		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
		        } else if (isMedium && counter < playersNumber) {
		            counter++; // Increment numCurrentPlayer
		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
		        } else if (isHard && counter < playersNumber) {
		            counter++; // Increment numCurrentPlayer
		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
		        }
		        if (counter == playersNumber) {
		            difficltyLeve_1.setText("play");
		        }
		        
		        if(difficltyLeve_1.getText().equals("play")) {
//		        	GameBoardView startGame= new GameBoardView(selectedDifficulty);
//		        	startGame.setVisible(true);
//		        	dispose();
		        }
		        // Display selectedLevel on each player
		        if (selectedLevel.isVisible()) {
		            selectedLevel.setVisible(false); // Hide selectedLevel if it's currently visible
		        }
		      
		        selectedLevel.setVisible(true); // Show selectedLevel
		    }
		});

		player1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player1.getBounds());
		    }
		});

		player2.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player2.getBounds());
		    }
		});

		player3.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player3.getBounds());
		    }
		});

		player4.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player4.getBounds());
		    }
		});
		player5.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player5.getBounds());
		    }
		});
		player6.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player6.getBounds());
		    }
		});
		player7.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player7.getBounds());
		    }
		});
		player8.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        selectedLevel.setVisible(true);
		        selectedLevel.setBounds(player8.getBounds());
		    }
		});
		

		
		JLabel lblNewLabel_1=new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setBounds(299, 453, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
       /*backgrounds*/
		JLabel lblNewLabe2 = new JLabel("");
		lblNewLabe2.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));
		lblNewLabe2.setBounds(0, 0,671,442);
		contentPane.add(lblNewLabe2);
        
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/background.png")));
		lblNewLabel.setBounds(0, 0,671,442);
		contentPane.add(lblNewLabel);
    }
}