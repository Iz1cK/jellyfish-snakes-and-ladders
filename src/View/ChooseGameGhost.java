package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.GameBoardController;
import Controller.GameCreationController;
import Controller.ghostGameBoardController;
import Model.Board;
import Model.DIFFICULTY;
import Model.PLAYERCOLORS;
import Model.Player;


import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class ChooseGameGhost extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7958152447515121679L;
	private JPanel contentPane;
	int numCurrentPlayer=1;
	int counter=1;
	int y=0;
	int players=0;
	int playersCounter=0;
	int colorsCounter=0;
	int playersNumber=0;
	int currentNumberOfPlayers=2;
	int level=0;
	String nickname;
	int icon=0;
	int isReady=0;
	boolean iconChosed=false;
	GameCreationController gameCreationController = GameCreationController.getInstance();
	private boolean isEasy=false, isMedium=false, isHard=false;
	public static DIFFICULTY selectedDifficulty;
	Boolean playerN1=false, playerN2=false,playerN3=false, playerN4=false, playerN5=false, playerN6=false, playerN7=false,playerN8=false;
	ArrayList<Player> allPlayers=new ArrayList<Player>();
	private int playerID=0; 
	//private String playername;
	private PLAYERCOLORS color;
	String playerNumber="none";
	String validName="no";
	int count=0;
	int readyToStart=0;
	int noErrors=0;
	
	private AudioTest AT = AudioTest.getInstance();
	
	ArrayList<String> allNames=new ArrayList<String>();
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChooseGameGhost frame = new ChooseGameGhost();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}

   public ChooseGameGhost() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(0, 0, 1350, 760);
    	contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
	
		
		
		JLabel difficltyLeve = new JLabel("DIFFICULTY");
		contentPane.add(difficltyLeve);
		difficltyLeve.setForeground(Color.WHITE); // Set the foreground color to white
		difficltyLeve.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 25));  // Set the font family, size, and weight
        difficltyLeve.setHorizontalAlignment(SwingConstants.CENTER);
		difficltyLeve.setBounds(336, 103, 227, 41);
		
		JLabel selectedLevel = new JLabel();
		selectedLevel.setIcon(new ImageIcon(ChooseGameGhost.class.getResource("/img/selected.png")));		
		selectedLevel.setBounds(285, 193, 101, 87);
		contentPane.add(selectedLevel);
		selectedLevel.setLayout(null);
		selectedLevel.setVisible(false);
		
		JLabel selectedPlayer = new JLabel();
		ImageIcon colorSelected=new ImageIcon(ChooseGameGhost.class.getResource("/img/selected.png"));
		selectedPlayer.setIcon(new ImageIcon(colorSelected.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));		
		selectedPlayer.setBounds(285, 193, 101, 87);
		contentPane.add(selectedPlayer);
		selectedPlayer.setLayout(null);
		selectedPlayer.setVisible(false);
		
		
		
		JLabel playerTaken = new JLabel();
		ImageIcon playerISTaken=new ImageIcon(ChooseGameGhost.class.getResource("/img/taken.png"));
		playerTaken.setIcon(new ImageIcon(playerISTaken.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			
		playerTaken.setBounds(295, 226, 30, 30);
		contentPane.add(playerTaken);
		playerTaken.setLayout(null);
		playerTaken.setVisible(false);
		
			
		JLabel easy = new JLabel("EASY");
		easy.setForeground(Color.WHITE); // Set the foreground color to white
		easy.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20)); // Set the font family, size, and weight
		easy.setBounds(270, 208, 116, 48); // Adjusted bounds to fit the text
		easy.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(easy);
        
		JLabel easyLevel = new JLabel();
	//	easyLevel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/easy.png")));		
		easyLevel.setBounds(280, 193, 90, 90);
		ImageIcon ImageIconEasy = new ImageIcon(ChooseGameGhost.class.getResource("/img/greenEasy.png"));
		ImageIcon testEasy= resized(ImageIconEasy.getImage(), 90, 90);
		easyLevel.setIcon(testEasy);
		contentPane.add(easyLevel);
		easyLevel.setLayout(null);
		
		
		JLabel medium = new JLabel("MEDIUM");
		medium.setForeground(Color.WHITE); // Set the foreground color to white
		medium.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 15)); // Set the font family, size, and weight
		medium.setBounds(396, 210, 90, 48); // Adjusted bounds to fit the text
		medium.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(medium);
		
		JLabel mediumLevel = new JLabel();
		//mediumLevel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/medium.png")));		
		mediumLevel.setBounds(396, 193, 90, 90);
		ImageIcon ImageIconMedium = new ImageIcon(ChooseGameGhost.class.getResource("/img/yellowMedium.png"));
		ImageIcon testMedium= resized(ImageIconMedium.getImage(), 90, 90);
		mediumLevel.setIcon(testMedium);
		contentPane.add(mediumLevel);
		mediumLevel.setLayout(null);
		
		JLabel hard = new JLabel("HARD");
		hard.setForeground(Color.WHITE); // Set the foreground color to white
		hard.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20)); // Set the font family, size, and weight
 // Set the font family, size, and weight
		hard.setBounds(511, 208, 90, 48); // Adjusted bounds to fit the text
		hard.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(hard);
		
		JLabel hardLevel = new JLabel();
		//hardLevel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/hard.png")));		
		hardLevel.setBounds(511, 193, 90, 90);
		ImageIcon ImageIconHard = new ImageIcon(ChooseGameGhost.class.getResource("/img/redHard.png"));
		ImageIcon testHard= resized(ImageIconHard.getImage(), 90, 90);
		hardLevel.setIcon(testHard);
		contentPane.add(hardLevel);
		hardLevel.setLayout(null);
		
        

		JLabel colorTaken = new JLabel();
		colorTaken.setIcon(new ImageIcon(ChooseGameGhost.class.getResource("/img/taken.png")));		
		contentPane.add(colorTaken);
		colorTaken.setLayout(null);
		colorTaken.setVisible(false);
		
		
		JLabel numbers = new JLabel("1");
		numbers.setForeground(Color.BLACK); // Set the foreground color to white
		numbers.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 40)); // Set the font family, size, and weight
		numbers.setHorizontalAlignment(SwingConstants.CENTER);
		numbers.setBounds(918, 182, 61, 87);
		contentPane.add(numbers); 
		   
        
		JLabel diffLevel = new JLabel();

		//	players_1.setBounds(172, 83, 524, 267);
		//	contentPane.add(players_1);
			
			//difficulyLabel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		 diffLevel .setBounds(295, 58, 300, 150);
		 diffLevel .setLayout(null);
					
			ImageIcon ImageDiff = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodRed.png"));
			ImageIcon testDiff= resized(ImageDiff.getImage(), 300, 150);
			diffLevel .setIcon(testDiff);
			contentPane.add(diffLevel );
			
			
        
        JLabel players_1 = new JLabel();
		//players_1.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));

	//	players_1.setBounds(172, 83, 524, 267);
	//	contentPane.add(players_1);
		
		//difficulyLabel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		players_1.setBounds(172, 73, 524, 267);
		players_1.setLayout(null);
				
		ImageIcon ImageIcon1 = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodenPanel.png"));
		ImageIcon test1= resized(ImageIcon1.getImage(), 530, 250);
		players_1.setIcon(test1);
		contentPane.add(players_1);
		
		 
			
		/* number of players *///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel_2 = new JLabel("NUMBER of PLAYERS");
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.WHITE); // Set the foreground color to white
		lblNewLabel_2.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 18)); // Set the font family, size, and weight
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(829, 105, 300, 28);
	        ImageIcon ImageIconPlus = new ImageIcon(ChooseGameGhost.class.getResource("/img/plusIcon.png"));
			ImageIcon testPlus= resized(ImageIconPlus.getImage(), 90, 90);
	       // minus.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/menus.png")));
	        ImageIcon ImageIconMinus = new ImageIcon(ChooseGameGhost.class.getResource("/img/minusIcon.png"));
			ImageIcon testMinus= resized(ImageIconMinus.getImage(), 90, 90);
	        
	        easy.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	level++;
	            	if(isReady==0) {
	            	selectedDifficulty=DIFFICULTY.EASY;
	        		selectedLevel.setVisible(false);
	        		isEasy=true;
	        		isMedium=false;
	        		isHard=false;
	                selectedLevel.setBounds(295, 183, 136, 128);
	        		selectedLevel.setVisible(true);
	        		numbers.setText(Integer.toString(1)); // Update the text field with the new value

	           }
	            }
	        });

	        medium.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	level++;
	            	if(isReady==0) {
	            	selectedDifficulty=DIFFICULTY.MEDIUM;
	        		selectedLevel.setVisible(false);
	        		isEasy=false;
	        		isMedium=true;
	        		isHard=false;
	                selectedLevel.setBounds(410, 183, 136, 128);
	        		selectedLevel.setVisible(true);
	        		numbers.setText(Integer.toString(1)); // Update the text field with the new value
	            	}
	            }
	        });

	        hard.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	level++;
	            	if(isReady==0) {
	            	selectedDifficulty=DIFFICULTY.HARD;
	        		selectedLevel.setVisible(false);
	        		isEasy=false;
	        		isMedium=false;
	        		isHard=true;
	                selectedLevel.setBounds(525, 183, 136, 128);
	        		selectedLevel.setVisible(true);
	        		numbers.setText(Integer.toString(1)); // Update the text field with the new value
	            }
	            }
	        });
	       
	        JLabel numPlayers = new JLabel();

			//	players_1.setBounds(172, 83, 524, 267);
			//	contentPane.add(players_1);
				
				//difficulyLabel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
			numPlayers .setBounds(819, 58, 300, 150);
			numPlayers .setLayout(null);
						
				ImageIcon ImageNum = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodGreed.png"));
				ImageIcon testNum= resized(ImageNum.getImage(), 300, 150);
				numPlayers .setIcon(testNum);
				contentPane.add(numPlayers);
		JLabel difficulyLabel = new JLabel();
		//difficulyLabel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		difficulyLabel.setBounds(671, 73, 524, 267);
		difficulyLabel.setLayout(null);
		
		ImageIcon ImageIcon2 = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodenPanel.png"));
		ImageIcon test2= resized(ImageIcon2.getImage(), 530, 250);
		difficulyLabel.setIcon(test2);
		// Set size to match content pane
		//difficulyLabel.setBounds(1028, 265, 232, 72);
		contentPane.add(difficulyLabel);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/* nickname and color */
		JLabel namesColor = new JLabel("NAMES & COLORES");
		contentPane.add(namesColor);
		namesColor.setForeground(Color.WHITE); // Set the foreground color to white
		namesColor.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 18)); // Set the font family, size, and weight
		namesColor.setHorizontalAlignment(SwingConstants.CENTER);
		namesColor.setBounds(491, 373, 400, 28);
		

		JLabel selectedColor = new JLabel();
		selectedColor.setIcon(new ImageIcon(ChooseGameGhost.class.getResource("/img/selected.png")));		
		selectedColor.setBounds(227, 430, 116, 71);
		contentPane.add(selectedColor);
		selectedColor.setLayout(null);
		selectedColor.setVisible(false); 
		
		JLabel player1 = new JLabel();
		ImageIcon img1=new ImageIcon(ChooseGameGhost.class.getResource("/img/whitePlayer.png"));
		player1.setIcon(new ImageIcon(img1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player1.setBounds(244, 430, 41, 78);	
		contentPane.add(player1);
		player1.setLayout(null);
		
		JLabel player2 = new JLabel();
		ImageIcon img2=new ImageIcon(ChooseGameGhost.class.getResource("/img/orangePlayer.png"));
		player2.setIcon(new ImageIcon(img2.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		player2.setBounds(306, 430, 41, 78);
		contentPane.add(player2);
		player2.setLayout(null);

		JLabel player3 = new JLabel();
		ImageIcon img3=new ImageIcon(ChooseGameGhost.class.getResource("/img/purplePlayer.png"));
		player3.setIcon(new ImageIcon(img3.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player3.setBounds(374, 430, 41, 78);
		contentPane.add(player3);
		player3.setLayout(null);

		JLabel player4 = new JLabel();
		ImageIcon img4=new ImageIcon(ChooseGameGhost.class.getResource("/img/pinkPlayer.png"));
		player4.setIcon(new ImageIcon(img4.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player4.setBounds(435, 430, 41, 78);
		contentPane.add(player4);
		player4.setLayout(null);
		
		JLabel player5 = new JLabel();
		ImageIcon img5=new ImageIcon(ChooseGameGhost.class.getResource("/img/greenPlayer.png"));
		player5.setIcon(new ImageIcon(img5.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player5.setBounds(486, 430, 41, 78);
		contentPane.add(player5);
		player5.setLayout(null);
		
		JLabel player6 = new JLabel();
		ImageIcon img6=new ImageIcon(ChooseGameGhost.class.getResource("/img/bluePlayer.png"));
		player6.setIcon(new ImageIcon(img6.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player6.setBounds(543, 430, 41, 78);
		contentPane.add(player6);
		player6.setLayout(null);
		
		
		JLabel player7 = new JLabel();
		ImageIcon img7=new ImageIcon(ChooseGameGhost.class.getResource("/img/redPlayer.png"));
		player7.setIcon(new ImageIcon(img7.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player7.setBounds(608, 430, 41, 78);
		contentPane.add(player7);
		player7.setLayout(null);
		
		JLabel player8 = new JLabel();
		ImageIcon img8=new ImageIcon(ChooseGameGhost.class.getResource("/img/yellowPlayer.png"));
		player8.setIcon(new ImageIcon(img8.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player8.setBounds(671, 430, 41, 78);
		contentPane.add(player8);
		player8.setLayout(null);
		
		/*JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(770, 450, 300, 41);
		contentPane.add(layeredPane);*/

		TextField nicknameField = new TextField() {
	            @Override
	            protected Document createDefaultModel() {
	                return new PlainDocument() {
	                    @Override
	                    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	                        if (str == null) {
	                            return;
	                        }
	                        String currentText = getText(0, getLength());
	                        String newText = currentText.substring(0, offs) + str + currentText.substring(offs);
	                        if (newText.matches("(?!\\s+$)[a-zA-Z0-9\\s]+")) {
	                            super.insertString(offs, str, a);
	                        }
	                    }
	                };
	            }
	        };
		nicknameField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		nicknameField.setHint("insert your nickname");
		//nicknameField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
        nicknameField.setBounds(770, 450, 500, 41);
        contentPane.add(nicknameField);
   //     layeredPane.add(nicknameField, JLayeredPane.PALETTE_LAYER);
        nicknameField.setColumns(10);

        JLabel iconLabel = new JLabel(new ImageIcon(ChooseGameGhost.class.getResource("/img/id.png")));
        iconLabel.setBounds(0, 0, 212, 41);
    //    layeredPane.add(iconLabel, JLayeredPane.DEFAULT_LAYER);

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




        JLabel currentPlayer = new JLabel("PLAYER "+numCurrentPlayer);
		currentPlayer.setForeground(Color.BLACK); // Set the foreground color to white
		//currentPlayer.setFont(new Font("Poppins", Font.BOLD, 15)); // Set the font family, size, and weight
		currentPlayer.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20)); // Set the font family, size, and weight

		currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayer.setBounds(836, 411, 161, 28);
		contentPane.add(currentPlayer); 
		
		 JLabel PlayersNamesColors = new JLabel();

			//	players_1.setBounds(172, 83, 524, 267);
			//	contentPane.add(players_1);
				
				//difficulyLabel.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		 PlayersNamesColors .setBounds(526, 341, 300, 98);
		 PlayersNamesColors .setLayout(null);
						
				ImageIcon ImageName = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodBlue.png"));
				ImageIcon testName= resized(ImageName.getImage(), 300, 150);
				PlayersNamesColors .setIcon(testName);
				contentPane.add(PlayersNamesColors);

		JLabel next_1 = new JLabel();
		//next_1.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));
		next_1.setBounds(40, 373, 1155, 193);
		ImageIcon ImageNext = new ImageIcon(ChooseGameGhost.class.getResource("/img/woodenPanel.png"));
		ImageIcon testNext= resized(ImageNext.getImage(), 1200, 193);
		next_1.setIcon(testNext);
		contentPane.add(next_1);
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		/* next */


		JLabel difficltyLeve_1 = new JLabel("PLAY");
		difficltyLeve_1.setHorizontalAlignment(SwingConstants.CENTER);
		difficltyLeve_1.setForeground(Color.WHITE);
		difficltyLeve_1.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 25));
		difficltyLeve_1.setBounds(901, 577, 155, 41);
		contentPane.add(difficltyLeve_1);
		
		JLabel next = new JLabel();
		//next.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		next.setBounds(829, 502, 300, 200);
		contentPane.add(next);
		next.setLayout(null);
		
		ImageIcon ImageIcon4 = new ImageIcon(ChooseGameGhost.class.getResource("/img/yellowWood.png"));
	    ImageIcon test4= resized(ImageIcon4.getImage(), 300, 200);
	    next.setIcon(test4);
	    
	    
		
		JLabel homePage = new JLabel();
	//	homePage.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
		homePage.setBounds(40, 47, 90, 90);
		contentPane.add(homePage);
		homePage.setLayout(null);
		
		ImageIcon ImageIconHome = new ImageIcon(ChooseGameGhost.class.getResource("/img/backIcon.png"));
		ImageIcon testHome= resized(ImageIconHome.getImage(), 90, 90);
		homePage.setIcon(testHome);
		
		homePage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open HomeView and close the current view
                HomeView homeView = new HomeView();
                homeView.setVisible(true);
                dispose();
            }
        });
				
		
		player1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    	//y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.WHITE;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player1.getBounds());
			      //  playerN2=true;
			    	playerNumber="playerN1";
		    	}
		    }
		    }
		});

		player2.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
			    	//y=y+46;
			    	iconChosed=true;
			    	colorsCounter++;
			    	color=PLAYERCOLORS.ORANGE;
			    	if(readyToStart==0) {
			    		selectedPlayer.setVisible(true);
				    	selectedPlayer.setBounds(player2.getBounds());
				      //  playerN2=true;
			    	playerNumber="playerN2";
		    	}
		    }
		    }
		});

		player3.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    //	y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.PURPLE;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player3.getBounds());
			      //  playerN3=true;
			    	playerNumber="playerN3";
		    	}
		    	}
		    }
		});

		player4.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    //	y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.PINK;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player4.getBounds());
			      //  playerN4=true;
			    	playerNumber="playerN4";
		    	}
		    	}
		    }
		});
		player5.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    	//y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.GREEN;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player5.getBounds());
			       // playerN5=true;
			    	playerNumber="playerN5";
		    	}
		    	}
		    }
		});
		player6.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    	//y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.BLUE;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player6.getBounds());
			       // playerN6=true;
			    	playerNumber="playerN6";
		    	}
		    	}
		    }

		});
		player7.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    //	y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.RED;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player7.getBounds());
		    	  // playerN7=true;
			    	playerNumber="playerN7";
		    	}
		    	}
		    }

		});
		player8.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if(isHard==false && isMedium==false && isEasy==false) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose level difficulty");
		    	}
		    	else {
		    //	y=y+46;
		    	iconChosed=true;
		    	colorsCounter++;
		    	color=PLAYERCOLORS.YELLOW;
		    	if(readyToStart==0) {
		    		selectedPlayer.setVisible(true);
			    	selectedPlayer.setBounds(player8.getBounds());
			       // playerN8=true;
			    	playerNumber="playerN8";
		    	}
		    	}
		    }
		});
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
       /*backgrounds*/
		JLabel player1_1 = new JLabel();
		ImageIcon img1_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/whitePlayer.png"));
		player1_1.setIcon(new ImageIcon(img1_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
	//	player1_1.setBounds(y, 364, 41, 78);	
		contentPane.add(player1_1);
		player1_1.setLayout(null);
		player1_1.setVisible(false);
		
		
		JLabel player2_1 = new JLabel();
		ImageIcon img2_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/orangePlayer.png"));
		player2_1.setIcon(new ImageIcon(img2_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));
		//player2_1.setBounds(77, 364, 41, 78);
		contentPane.add(player2_1);
		player2_1.setLayout(null);
		player2_1.setVisible(false);
		
		
		JLabel player3_1 = new JLabel();
		ImageIcon img3_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/purplePlayer.png"));
		player3_1.setIcon(new ImageIcon(img3_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		//player3_1.setBounds(123, 364, 41, 78);
		contentPane.add(player3_1);
		player3_1.setLayout(null);
		player3_1.setVisible(false);
		
		
		JLabel player4_1 = new JLabel();
		ImageIcon img4_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/pinkPlayer.png"));
		player4_1.setIcon(new ImageIcon(img4_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		player4_1.setLayout(null);
		//player4_1.setBounds(169, 364, 41, 78);
		contentPane.add(player4_1);
		player4_1.setVisible(false);
		
		
		JLabel player5_1 = new JLabel();
		ImageIcon img5_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/greenPlayer.png"));
		player5_1.setIcon(new ImageIcon(img5_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		//player5_1.setBounds(215, 364, 41, 78);
		contentPane.add(player5_1);
		player5_1.setLayout(null);
		player5_1.setVisible(false);
		
		
		JLabel player6_1 = new JLabel();
		ImageIcon img6_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/bluePlayer.png"));
		player6_1.setIcon(new ImageIcon(img6_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
	//	player6_1.setBounds(261, 364, 41, 78);
		contentPane.add(player6_1);
		player6_1.setLayout(null);
		player6_1.setVisible(false);
		
		
		JLabel player7_1 = new JLabel();
		ImageIcon img7_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/redPlayer.png"));
		player7_1.setIcon(new ImageIcon(img7_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
	//	player7_1.setBounds(307, 364, 41, 78);
		contentPane.add(player7_1);
		player7_1.setLayout(null);
		player7_1.setVisible(false);
		
		
		JLabel player8_1 = new JLabel();
		ImageIcon img8_1=new ImageIcon(ChooseGameGhost.class.getResource("/img/yellowPlayer.png"));
		player8_1.setIcon(new ImageIcon(img8_1.getImage().getScaledInstance(41, 78, Image.SCALE_SMOOTH)));	
		//player8_1.setBounds(353, 364, 41, 78);
		contentPane.add(player8_1);
		player8.setLayout(null);
		player8_1.setVisible(false);
		
		

		 /* setting background */
        JLabel backgrounde = new JLabel("");
        ImageIcon backgroundImageIcon = new ImageIcon(ChooseGameGhost.class.getResource("/img/ChooseGameBackground.png"));

        // Resize the background image to fit the frame
        Image backgroundImage = backgroundImageIcon.getImage();
        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight()-40, Image.SCALE_SMOOTH);
        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

        backgrounde.setIcon(resizedBackgroundIcon);

        // Set size to match content pane
        backgrounde.setBounds(0, 0, getWidth(), getHeight()-40);

        contentPane.add(backgrounde);	
        
        JLabel lblNewLabel_2_1 = new JLabel("NUMBER of PLAYERS");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 18));
        lblNewLabel_2_1.setBounds(572, 372, 300, 28);
        contentPane.add(lblNewLabel_2_1);
		
		JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);
        
    	// ** LOGIC **//
		next.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	isReady=1;
		    	validName="no";
		    	String nickname = nicknameField.getText().trim();
		    	int playersNumber = Integer.parseInt(numbers.getText());
		    	
		    		
		    	if(isEasy==false && isMedium==false && isHard==false && nickname.equals("") && playerNumber.equals("none")  ) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose the game's parameters!");
		    		
		    	}
		    	
		    	else if(level!=0 && nickname.equals("") && playerNumber.equals("none")) {
					JOptionPane.showMessageDialog(contentPane, "please choose your color and insert your nickname");
	        	}
	        	
		    	else if(playerNumber.equals("none")&&!nickname.equals("")) {
		    		JOptionPane.showMessageDialog(contentPane, "please choose color");
		    	}
		    	else if(!playerNumber.equals("none")&&nickname.equals("")) {
		    		JOptionPane.showMessageDialog(contentPane, "please insert your nickname");
		    	}

		    	
		    	else if(level!=0 && !nickname.equals("") && !playerNumber.equals("none") ) {
		    		
		    		if(allNames.contains(nickname)) {
	    			  validName="no";
	    			  JOptionPane.showMessageDialog(contentPane, "this nickname is unvalid! try another one");
	        	 }

		    		else {
		    			
		    			noErrors=1;
		    			allNames.add(nickname);
		    			y=y+46;
				        if (isEasy && counter < playersNumber )  {
				            counter++;
				            currentPlayer.setText("player " + counter);
				        } else if (isMedium && counter < playersNumber) {
				            counter++;
				            currentPlayer.setText("player " + counter);
				        } else if (isHard && counter < playersNumber ) {
				            counter++;
				            currentPlayer.setText("player " + counter);
				        }
				        
				        if(playersCounter<=playersNumber) {
				        	playerID++;
				        	
				        //	System.out.println("A"+playerID);
				        	Player player=new Player(playerID,nickname,color);
				        	allPlayers.add(player);
				        	gameCreationController.setPlayers(allPlayers);
				            nicknameField.setText("");

					        if(playerNumber.equals("playerN1")) {
					        	count++;
					        	player1_1.setBounds(y, 364, 41, 78);
					        	//player1_1.setVisible(true); 
					        	selectedPlayer.setVisible(false);
					        	player1.setVisible(false);
					        	playerN1=false;
					        	
					        }
					        if(playerNumber.equals("playerN2")) {
					        	count++;
					        	player2_1.setBounds(y, 364, 41, 78);
					        	//setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player2.setVisible(false);
					        	playerN2=false;
					        }
					        if(playerNumber.equals("playerN3")) {
					        	count++;
					        	player3_1.setBounds(y, 364, 41, 78);
					        	//player3_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player3.setVisible(false);
					        	playerN3=false;
					        }
					        if(playerNumber.equals("playerN4")) {
					        	count++;
					        	player4_1.setBounds(y, 364, 41, 78);
					        	//player4_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player4.setVisible(false);
					        	playerN4=false;
					        	
					        }
					        if(playerNumber.equals("playerN5")) {
					        	count++;
					        	player5_1.setBounds(y, 364, 41, 78);
					        //	player5_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player5.setVisible(false);
					        	playerN5=false;
					        }
					        if(playerNumber.equals("playerN6")) {
					        	count++;
					        	player6_1.setBounds(y, 364, 41, 78);
					       // 	player6_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player6.setVisible(false);
					        	playerN6=false;
					        }
					        if(playerNumber.equals("playerN7")) {
					        	count++;
					        	player7_1.setBounds(y, 364, 41, 78);
					        //	player7_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player7.setVisible(false);
					        	playerN7=false;
					        }
					        if(playerNumber.equals("playerN8")) {
					        	count++;
					        	player8_1.setBounds(y, 364, 41, 78);
					       // 	player8_1.setVisible(true);
					        	selectedPlayer.setVisible(false);
					        	player8.setVisible(false);
					        	playerN8=false;
					        }
					        playerNumber="none";
					        
				        }
				        
				        if (playerID == playersNumber) {
			            difficltyLeve_1.setText("play");
			            
			        }
				        
				        
				       
				        if(difficltyLeve_1.getText().equals("play") && allPlayers.size()==playersNumber) {
				    		readyToStart=1;
				        	System.out.println("PLAY NOW START GAME");
				        	
				        	ghostGameBoardController gbc = ghostGameBoardController.getInstance();
			        		Board board = new Board(selectedDifficulty, allPlayers);
			        		board.generateBoard();
		                    board.initiateQuestionSquares();
		                    board.generateSnakesAndLadder();
			        		gbc.setGameBoard(board);
			        		ghostGame gbv = new ghostGame();
			        		gbv.setExtendedState(JFrame.MAXIMIZED_BOTH);
			        		gbv.setVisible(true);
			        		
				        	dispose();
				        	return;
				        }
		    		}
		    	}
		    }
		 //   }

			   
		});
		
		
    }
   
   public class TextField extends JTextField {

	    public String getHint() {
	        return hint;
	    }

	    public void setHint(String hint) {
	        this.hint = hint;
	        repaint();
	    }

	    private String hint = "";
	    private final Animator animator;
	    private float animate;
	    private boolean show = true;

	    public TextField() {
	        setOpaque(false);
	        setBorder(new EmptyBorder(0, 0, 0, 0));
	        setBackground(Color.black);
	        setForeground(Color.black);
	        setSelectionColor(Color.black);
	        animator = new Animator(350, new TimingTargetAdapter() {
	            @Override
	            public void timingEvent(float fraction) {
	                if (show) {
	                    animate = fraction;
	                } else {
	                    animate = 1f - fraction;
	                }
	                repaint();
	            }

	            @Override
	            public void end() {
	                show = !show;
	                repaint();
	            }

	        });
	        animator.setResolution(0);
	        animator.setAcceleration(.5f);
	        animator.setDeceleration(.5f);
	        getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                if (!getText().equals("")) {
	                    if (show) {
	                        if (animator.isRunning() == false) {
	                            stop();
	                            animator.start();
	                        }
	                    } else if (animator.isRunning()) {
	                        stop();
	                        animator.start();
	                    }
	                }
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                if (getText().equals("")) {
	                    stop();
	                    animator.start();
	                }
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {

	            }
	        });

	    }

	    private void stop() {
	        if (animator.isRunning()) {
	            float f = animator.getTimingFraction();
	            animator.stop();
	            animator.setStartFraction(1f - f);
	        } else {
	            animator.setStartFraction(0f);
	        }
	    }

	    @Override
	    public void paint(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2.setColor(new Color(0, 0, 0, 0));
	        g2.drawLine(0, getHeight() - 3, getWidth(), getHeight() - 3);
	        if (!hint.equals("")) {
	            int h = getHeight();
	            Insets ins = getInsets();
	            FontMetrics fm = g.getFontMetrics();
	            g2.setColor(new Color(0, 0, 0));
	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - animate));
	            g2.drawString(hint, ins.left + (animate * 30), h / 2 + fm.getAscent() / 2 - 1);
	        }
	        g2.dispose();
	        super.paint(g);
	    }
	}

		public class FancyBorderRadius {

		    public double getWidth() {
		        return width;
		    }

		    public void setWidth(double width) {
		        this.width = width;
		    }

		    public double getHeight() {
		        return height;
		    }

		    public void setHeight(double height) {
		        this.height = height;
		    }

		    public Border getBorder() {
		        return border;
		    }

		    public void setBorder(Border border) {
		        this.border = border;
		    }

		    public FancyBorderRadius(double width, double height, Border border) {
		        this.width = width;
		        this.height = height;
		        this.border = new Border();
		        this.border = border;
		    }

		    public FancyBorderRadius(double width, double height, String border) {
		        this(width, height, new Border(border));
		    }

		    public FancyBorderRadius() {
		    }

		    private double width;
		    private double height;
		    private Border border;

		    public Shape getShape() {
		        Area area = new Area(new Rectangle2D.Double(0, 0, width, height));
		        area.intersect(new Area(create1()));
		        area.intersect(new Area(create2()));
		        area.intersect(new Area(create3()));
		        area.intersect(new Area(create4()));
		        return area;
		    }

		    private Shape create1() {
		        double w = border.getTop().getRight() * this.width;
		        double h = border.getRight().getRight() * this.height;
		        Path2D path = new Path2D.Double();
		        path.moveTo(width - w, 0);
		        path.lineTo(0, 0);
		        path.lineTo(0, height);
		        path.lineTo(width, height);
		        path.lineTo(width, h);
		        Area area = new Area(path);
		        area.add(new Area(new Ellipse2D.Double(width - w * 2, 0, w * 2, h * 2)));
		        return area;
		    }

		    private Shape create2() {
		        double w = border.getTop().getLeft() * this.width;
		        double h = border.getLeft().getRight() * this.height;
		        Path2D path = new Path2D.Double();
		        path.moveTo(0, h);
		        path.lineTo(0, height);
		        path.lineTo(width, height);
		        path.lineTo(width, 0);
		        path.lineTo(w, 0);
		        Area area = new Area(path);
		        area.add(new Area(new Ellipse2D.Double(0, 0, w * 2, h * 2)));
		        return area;
		    }

		    private Shape create3() {
		        double w = border.getBottom().getLeft() * this.width;
		        double h = border.getLeft().getLeft() * this.height;
		        Path2D path = new Path2D.Double();
		        path.moveTo(w, height);
		        path.lineTo(width, height);
		        path.lineTo(width, 0);
		        path.lineTo(0, 0);
		        path.lineTo(0, height - h);
		        Area area = new Area(path);
		        area.add(new Area(new Ellipse2D.Double(0, height - h * 2, w * 2, h * 2)));
		        return area;
		    }

		    private Shape create4() {
		        double w = border.getBottom().getRight() * this.width;
		        double h = border.getRight().getLeft() * this.height;
		        Path2D path = new Path2D.Double();
		        path.moveTo(width, height - h);
		        path.lineTo(width, 0);
		        path.lineTo(0, 0);
		        path.lineTo(0, height);
		        path.lineTo(width - w, height);
		        Area area = new Area(path);
		        area.add(new Area(new Ellipse2D.Double(width - w * 2, height - h * 2, w * 2, h * 2)));
		        return area;
		    }

		    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
		        border.setBorder(tl, tr, br, bl, lr, rr, rl, ll);
		    }

		    public void setBorder(String text) {
		        border.setBorder(text);
		    }
		}
		public class Border {

		    public Point getTop() {
		        return top;
		    }

		    public void setTop(Point top) {
		        this.top = top;
		    }

		    public Point getBottom() {
		        return bottom;
		    }

		    public void setBottom(Point bottom) {
		        this.bottom = bottom;
		    }

		    public Point getLeft() {
		        return left;
		    }

		    public void setLeft(Point left) {
		        this.left = left;
		    }

		    public Point getRight() {
		        return right;
		    }

		    public void setRight(Point right) {
		        this.right = right;
		    }

		    public Border(String border) {
		        this();
		        init(border);
		    }

		    public Border() {
		        top = new Point();
		        bottom = new Point();
		        left = new Point();
		        right = new Point();
		    }

		    private void init(String border) {
		        setBorder(border);
		    }

		    private Point top;
		    private Point bottom;
		    private Point left;
		    private Point right;

		    @Override
		    public String toString() {
		        return convert(top.left) + " " + convert(top.right) + " " + convert(bottom.right) + " " + convert(bottom.left) + " / " + convert(left.right) + " " + convert(right.right) + " " + convert(right.left) + " " + convert(left.left);
		    }

		    public float[] toArray() {
		        return new float[]{top.left, top.right, bottom.right, bottom.left, left.right, right.right, right.left, left.left};
		    }

		    private String convert(float f) {
		        return (Math.round(f * 100 * 100) / 100) + "%";
		    }

		    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
		        top.setLeft(tl);
		        top.setRight(tr);
		        bottom.setRight(br);
		        bottom.setLeft(bl);
		        left.setRight(lr);
		        left.setLeft(ll);
		        right.setRight(rr);
		        right.setLeft(rl);
		    }

		    public void setBorder(String text) {
		        text = text.replace("%", "");
		        String arr[] = text.split("/");
		        if (arr.length == 2) {
		            String s1[] = arr[0].trim().split(" ");
		            String s2[] = arr[1].trim().split(" ");
		            setBorder(percent(s1[0]), percent(s1[1]), percent(s1[2]), percent(s1[3]), percent(s2[0]), percent(s2[1]), percent(s2[2]), percent(s2[3]));
		        } else {
		            String s1[] = arr[0].trim().split(" ");
		            float v1 = percent(s1[0]);
		            float v2 = percent(s1[1]);
		            top.setLeft(v1);
		            top.setRight(v2);
		            right.setLeft(v1);
		            right.setRight(v2);
		            bottom.setRight(v1);
		            bottom.setLeft(v2);
		            left.setRight(v1);
		            left.setLeft(v2);
		        }
		    }

		    private float percent(String f) {
		        return Float.parseFloat(f) / 100f;
		    }

		    protected class Point {

		        public float getLeft() {
		            return left;
		        }

		        public void setLeft(float left) {
		            this.left = left;
		        }

		        public float getRight() {
		            return right;
		        }

		        public void setRight(float right) {
		            this.right = right;
		        }

		        public Point(float left, float right) {
		            this.left = left;
		            this.right = right;
		        }

		        public Point() {
		        }

		        private float left;
		        private float right;
		    }
		}

		
		public class RippleEffect {

		    private final Component component;
		    private Color rippleColor = new Color(255, 255, 255);
		    private List<Effect> effects;

		    public RippleEffect(Component component) {
		        this.component = component;
		        init();
		    }

		    private void init() {
		        effects = new ArrayList<>();
		        component.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mousePressed(MouseEvent e) {
		                if (component.isEnabled()) {
		                    if (SwingUtilities.isLeftMouseButton(e)) {
		                        addEffect(e.getPoint());
		                    }
		                }
		            }
		        });
		    }

		    public void addEffect(Point location) {
		        effects.add(new Effect(component, location));
		    }

		    public void reder(Graphics g, Shape contain) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        for (int i = 0; i < effects.size(); i++) {
		            Effect effect = effects.get(i);
		            if (effect != null) {
		                effect.render(g2, contain);
		            }
		        }
		        g2.dispose();
		    }

		    private class Effect {

		        private final Component component;
		        private final Point location;
		        private Animator animator;
		        private float animate;

		        public Effect(Component component, Point location) {
		            this.component = component;
		            this.location = location;
		            init();
		        }

		        private void init() {
		            animator = new Animator(500, new TimingTargetAdapter() {
		                @Override
		                public void timingEvent(float fraction) {
		                    animate = fraction;
		                    component.repaint();
		                }

		                @Override
		                public void end() {
		                    effects.remove(Effect.this);
		                }
		            });
		            animator.setResolution(5);
		            // animator.setDeceleration(.5f);
		            animator.start();
		        }

		        public void render(Graphics2D g2, Shape contain) {
		            Area area = new Area(contain);
		            area.intersect(new Area(getShape(getSize(contain.getBounds2D()))));
		            g2.setColor(rippleColor);
		            float alpha = 0.3f;
		            if (animate >= 0.7f) {
		                double t = animate - 0.7f;
		                alpha = (float) (alpha - (alpha * (t / 0.3f)));
		            }
		            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		            g2.fill(area);
		        }

		        private Shape getShape(double size) {
		            double s = size * animate;
		            double x = location.getX();
		            double y = location.getY();
		            Shape shape = new Ellipse2D.Double(x - s, y - s, s * 2, s * 2);
		            return shape;
		        }

		        private double getSize(Rectangle2D rec) {
		            double size;
		            if (rec.getWidth() > rec.getHeight()) {
		                if (location.getX() < rec.getWidth() / 2) {
		                    size = rec.getWidth() - location.getX();
		                } else {
		                    size = location.getX();
		                }
		            } else {
		                if (location.getY() < rec.getHeight() / 2) {
		                    size = rec.getHeight() - location.getY();
		                } else {
		                    size = location.getY();
		                }
		            }
		            return size + (size * 0.1f);
		        }
		    }

		    public void setRippleColor(Color rippleColor) {
		        this.rippleColor = rippleColor;
		    }

		    public Color getRippleColor() {
		        return rippleColor;
		    }
		}






   
}