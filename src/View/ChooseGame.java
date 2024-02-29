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
import Model.Player;

import javax.swing.SwingConstants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	int players=0;
	int playersCounter=0;
	int colorsCounter=0;
	int playersNumber=0;
	int currentNumberOfPlayers=0;
	private boolean isEasy=false, isMedium=false, isHard=false;
	DIFFICULTY selectedDifficulty;
	Boolean playerN1=false, playerN2=false,playerN3=false, playerN4=false, playerN5=false, playerN6=false, playerN7=false,playerN8=false;
	
	
	
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
		setBounds(400, 100, 680, 530);
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
		
		JLabel selectedPlayer = new JLabel();
		ImageIcon colorSelected=new ImageIcon(ChooseGame.class.getResource("/img/selected.png"));
		selectedPlayer.setIcon(new ImageIcon(colorSelected.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		//selectedPlayer.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/selected.png")));		
		selectedPlayer.setBounds(37, 119, 101, 87);
		contentPane.add(selectedPlayer);
		selectedPlayer.setLayout(null);
		selectedPlayer.setVisible(false);
		
		
		
		JLabel playerTaken = new JLabel();
		ImageIcon playerISTaken=new ImageIcon(ChooseGame.class.getResource("/img/taken.png"));
		playerTaken.setIcon(new ImageIcon(playerISTaken.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			
		playerTaken.setBounds(37, 119, 30, 30);
		contentPane.add(playerTaken);
		playerTaken.setLayout(null);
		playerTaken.setVisible(false);
		
			
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
	                currentNumberOfPlayers = Integer.parseInt(numbers.getText());
	                
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
	               playersNumber=currentNumberOfPlayers;
	              // System.out.print(currentNumberOfPlayers);
	            }
	        });
	        contentPane.add(plus);
    
	        
	        System.out.println(currentNumberOfPlayers);
	        
	        
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
	                  //  playersNumber=currentNumberOfPlayers;
	                }
	            }
	        });
	        contentPane.add(minus);
	        
	        playersNumber=Integer.valueOf(numbers.getText());
	     //   System.out.print(playersNumber);
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
		
		
		


		JLabel lblNewLabel_3 = new JLabel("choose number of players");
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.WHITE); // Set the foreground color to white
		lblNewLabel_3.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(376, 84, 190, 28);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(400, 281, 212, 41);
		contentPane.add(layeredPane);

		JTextField nicknameField = new JTextField("insert your nickname");
        nicknameField.setBounds(0, 0, 212, 41);
        layeredPane.add(nicknameField, JLayeredPane.PALETTE_LAYER);
//        String nickname = nicknameField.getText();
//        System.out.println("Nickname: " + nickname);

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
		difficltyLeve_1.setBounds(480, 373, 155, 41);
		contentPane.add(difficltyLeve_1);
		
		JLabel next = new JLabel();
		next.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));		
		next.setBounds(523, 373, 75, 41);
		contentPane.add(next);
		next.setLayout(null);
		

//		next.addMouseListener(new MouseAdapter() {
//		    @Override
//		    public void mouseClicked(MouseEvent e) {
//		        int playersNumber = Integer.parseInt(numbers.getText());
//		        if (isEasy && counter < playersNumber) {
//		            counter++; // Increment numCurrentPlayer
//		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
//		        } else if (isMedium && counter < playersNumber) {
//		            counter++; // Increment numCurrentPlayer
//		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
//		        } else if (isHard && counter < playersNumber) {
//		            counter++; // Increment numCurrentPlayer
//		            currentPlayer.setText("player " + counter); // Update currentPlayer label text
//		        }
//		        if (counter == playersNumber) {
//		            difficltyLeve_1.setText("play");
//		        }
//		        
////		        if(difficltyLeve_1.getText().equals("play")) {
////	        	GameBoardView startGame= new GameBoardView(new ArrayList<Player>(),selectedDifficulty,false);
////		        	startGame.setVisible(true);
////		        	dispose();
////		        }
//		      //   Display selectedLevel on each player
//		        if (selectedLevel.isVisible()) {
//		            selectedLevel.setVisible(false); // Hide selectedLevel if it's currently visible
//		        }
//		      
//		        selectedLevel.setVisible(true); // Show selectedLevel
//		    }
//		});

		JLabel backButton = new JLabel("back");
		backButton.setHorizontalAlignment(SwingConstants.CENTER);
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Dialog", Font.BOLD, 15));
		backButton.setBounds(427, 373, 94, 41);
		contentPane.add(backButton);
		
		JLabel back = new JLabel();
		back.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));		
		back.setBounds(438, 373, 75, 41);
		contentPane.add(back);
		back.setLayout(null);
		
		
		int players = Integer.parseInt(numbers.getText());
		
		player1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    		colorsCounter++;
//		    		System.out.println(colorsCounter);
//		    		System.out.println(players);
		    		//System.out.println(playersNumber);
		    		if(colorsCounter<=currentNumberOfPlayers) {
		    			selectedPlayer.setVisible(true);
			    	    selectedPlayer.setBounds(player1.getBounds());
			            playerN1=true;
		    		}
		    		else {
		    			selectedPlayer.setVisible(false);
		    		}
		    			
		    	
		    }
		});

		player2.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player2.getBounds());
			        playerN2=true;
		    	}
		    }
		});

		player3.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	System.out.println(colorsCounter);
		    	System.out.println(players);
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    	//	System.out.println("test");
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player3.getBounds());
			        playerN3=true;
		    	}
		    }
		});

		player4.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player4.getBounds());
			        playerN4=true;
		    	}
		    }
		});
		player5.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player5.getBounds());
			        playerN5=true;
		    	}
		    }
		});
		player6.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player6.getBounds());
			        playerN6=true;
		    	}
		    }
		});
		player7.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player7.getBounds());
		    	}   playerN7=true;
		    	
		    }
		});
		player8.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	colorsCounter++;
		    	if(colorsCounter<=currentNumberOfPlayers) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player8.getBounds());
			        playerN8=true;
		    	}
		    }
		});
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
       /*backgrounds*/
		JLabel player1_1 = new JLabel();
		ImageIcon img1_1=new ImageIcon(ChooseGame.class.getResource("/img/whitePlayer.png"));
		player1_1.setIcon(new ImageIcon(img1_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player1_1.setBounds(31, 364, 41, 78);	
		contentPane.add(player1_1);
		player1_1.setLayout(null);
		player1_1.setVisible(false);
		
		
		JLabel player2_1 = new JLabel();
		ImageIcon img2_1=new ImageIcon(ChooseGame.class.getResource("/img/orangePlayer.png"));
		player2_1.setIcon(new ImageIcon(img2_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player2_1.setBounds(77, 364, 41, 78);
		contentPane.add(player2_1);
		player2_1.setLayout(null);
		player2_1.setVisible(false);
		
		
		JLabel player3_1 = new JLabel();
		ImageIcon img3_1=new ImageIcon(ChooseGame.class.getResource("/img/purplePlayer.png"));
		player3_1.setIcon(new ImageIcon(img3_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player3_1.setBounds(123, 364, 41, 78);
		contentPane.add(player3_1);
		player3_1.setLayout(null);
		player3_1.setVisible(false);
		
		
		JLabel player4_1 = new JLabel();
		ImageIcon img4_1=new ImageIcon(ChooseGame.class.getResource("/img/pinkPlayer.png"));
		player4_1.setIcon(new ImageIcon(img4_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player4_1.setLayout(null);
		player4_1.setBounds(169, 364, 41, 78);
		contentPane.add(player4_1);
		player4_1.setVisible(false);
		
		
		JLabel player5_1 = new JLabel();
		ImageIcon img5_1=new ImageIcon(ChooseGame.class.getResource("/img/greenPlayer.png"));
		player5_1.setIcon(new ImageIcon(img5_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player5_1.setBounds(215, 364, 41, 78);
		contentPane.add(player5_1);
		player5_1.setLayout(null);
		player5_1.setVisible(false);
		
		
		JLabel player6_1 = new JLabel();
		ImageIcon img6_1=new ImageIcon(ChooseGame.class.getResource("/img/bluePlayer.png"));
		player6_1.setIcon(new ImageIcon(img6_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player6_1.setBounds(261, 364, 41, 78);
		contentPane.add(player6_1);
		player6_1.setLayout(null);
		player6_1.setVisible(false);
		
		
		JLabel player7_1 = new JLabel();
		ImageIcon img7_1=new ImageIcon(ChooseGame.class.getResource("/img/redPlayer.png"));
		player7_1.setIcon(new ImageIcon(img7_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player7_1.setBounds(307, 364, 41, 78);
		contentPane.add(player7_1);
		player7_1.setLayout(null);
		player7_1.setVisible(false);
		
		
		JLabel player8_1 = new JLabel();
		ImageIcon img8_1=new ImageIcon(ChooseGame.class.getResource("/img/yellowPlayer.png"));
		player8_1.setIcon(new ImageIcon(img8_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player8_1.setBounds(353, 364, 41, 78);
		contentPane.add(player8_1);
		player8.setLayout(null);
		player8_1.setVisible(false);
		
		
		
		JLabel lblNewLabe2 = new JLabel("");
		lblNewLabe2.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/rectangle.png")));
		lblNewLabe2.setBounds(0, 0,671,491);
		contentPane.add(lblNewLabe2);
        
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ChooseGame.class.getResource("/img/background.png")));
		lblNewLabel.setBounds(0, 0,671,491);
		contentPane.add(lblNewLabel);
		
		
		// ** LOGIC **//
		next.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	
		        int playersNumber = Integer.parseInt(numbers.getText());
		        playersCounter++;
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
		        

		        if(playersCounter<=playersNumber) {
		        	String nickname = nicknameField.getText();
		        //    System.out.println("Nickname: " + nickname);
		            nicknameField.setText("");
		        if(playerN1) {
		        	
		        	System.out.println(nickname);
		        	System.out.println(nickname);
		        	player1_1.setVisible(true);
		     
		        	selectedPlayer.setVisible(false);
		        	player1.setVisible(false);
		        	
		        	
		        }
		        if(playerN2) {
		        	player2_1.setVisible(true);
		        
		        	selectedPlayer.setVisible(false);
		        	player2.setVisible(false);
		        }
		        if(playerN3) {
		        	player3_1.setVisible(true);
		       
		        	selectedPlayer.setVisible(false);
		        	player3.setVisible(false);
		        }
		        if(playerN4) {
		        	player4_1.setVisible(true);
	
		        	selectedPlayer.setVisible(false);
		        	player4.setVisible(false);
		        }
		        if(playerN5) {
		        	player5_1.setVisible(true);

		        	selectedPlayer.setVisible(false);
		        	player5.setVisible(false);
		        }
		        if(playerN6) {
		        	player6_1.setVisible(true);

		        	selectedPlayer.setVisible(false);
		        	player6.setVisible(false);
		        }
		        if(playerN7) {
		        	player7_1.setVisible(true);

		        	selectedPlayer.setVisible(false);
		        	player7.setVisible(false);
		        }
		        if(playerN8) {
		        	player8_1.setVisible(true);
		        	selectedPlayer.setVisible(false);
		        	player8.setVisible(false);
		        }
		        }
		    }
		});
    }
}