package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Board;
import Model.DIFFICULTY;
import Model.PLAYERCOLORS;
import Model.Player;
import Model.QuesSquare;
import Model.Square;
import Model.SurpriseSquare;

import java.awt.Font;
import java.awt.GridLayout;


public class GameBoardView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3841291012560699747L;
	private JPanel contentPane;
    private JLabel backgroundImage;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;
    private ImageIcon originalIcon;
//    private JLabel diceLabel;
    
    //Temp data for testing frontend without controller
    private static HashMap<Player,Integer> playersPositions;
    private static ArrayList<Player> players;
    private static Player currentPlayer;
    private static Square[][] squares;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    Player player1 = new Player(0,"Kuala",PLAYERCOLORS.ORANGE);
                    Player player2 = new Player(1,"Mori",PLAYERCOLORS.WHITE);
                    Player player3 = new Player(2,"Mario",PLAYERCOLORS.PINK);
                    
                    players = new ArrayList<>();
                    players.add(player1);
                    players.add(player2);
                    players.add(player3);
                    
                    currentPlayer = player2;
                    
                    playersPositions = new HashMap<>();
                    playersPositions.put(player1, 35);
                    playersPositions.put(player2, 29);
                    playersPositions.put(player3, 2);
                    
                    Board board = new Board(DIFFICULTY.MEDIUM);
                    
                    board.generateBoard();
                    
                    squares = board.getSquares();
                    
                    System.out.println(squares.length);
                    
                    GameBoardView frame = new GameBoardView(board.getDifficultyBoard());
                  frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    public GameBoardView(DIFFICULTY diff) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        originalIcon = new ImageIcon(GameBoardView.class.getResource("/img/gameBoardBackground.png"));
        backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0, 1280, 720);
        updateLabelImage(getWidth(), getHeight());
        
        JPanel playersPanel = new JPanel();
        playersPanel.setBounds(getWidth(), 43, 248, 381);
        contentPane.add(playersPanel);
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        
        playersPanel.revalidate();
        playersPanel.repaint();
        
        Font labelFont = new Font("Poppins", Font.BOLD, 36);

        for (Player player : players) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
            playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            ImageIcon playerColorIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + player.getColor().toString().toLowerCase() + "player.png"));
            Image scaledPlayerIcon = playerColorIcon.getImage().getScaledInstance(25, 50, Image.SCALE_SMOOTH);
            JLabel playerColor = new JLabel(new ImageIcon(scaledPlayerIcon));

            JLabel playerLabel = new JLabel(player.getPlayername());
            playerLabel.setFont(labelFont);
            
            JLabel playerPositionLabel = new JLabel(playersPositions.get(player).toString());
            playerPositionLabel.setFont(labelFont);
            playerLabel.setFont(labelFont);
            
            ImageIcon yourTurn = new ImageIcon(GameBoardView.class.getResource("/img/yourturn.png"));
            Image scaledYourTurn = yourTurn.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel playerTurn = new JLabel(new ImageIcon(scaledYourTurn));

            playerPanel.add(playerColor);
            playerPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between icon and name
            playerPanel.add(playerLabel);
            playerPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between name and position
            playerPanel.add(playerPositionLabel);
            playerPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between position and player turn
            if(player == currentPlayer) {
            	playerPanel.add(playerTurn);
            }
            playersPanel.add(playerPanel);
            playersPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between player entries   
        }
        
        JPanel timerPanel = new JPanel();
        timerPanel.setBounds((int) (getWidth() + 100), (int) (getHeight() - 100), 400, 50);
        
        // Timer setup
        timerLabel = new JLabel("00:00.00", SwingConstants.CENTER);
        timerLabel.setFont(labelFont);
        timerPanel.add(timerLabel);
        contentPane.add(timerPanel);
        
        JPanel boardPanel = new JPanel();
 
        switch(diff) {
        case EASY:
            boardPanel.setPreferredSize(new Dimension(739, 436));
            break;
        case MEDIUM: 
            boardPanel.setPreferredSize(new Dimension(950, 600));
            break;
        case HARD:
            boardPanel.setPreferredSize(new Dimension(1150, 750));
            break;
        }
        
        boardPanel.removeAll();
        if(squares != null) {
        	boardPanel.setLayout(new GridLayout(squares.length, squares[0].length));
        	
        	int rows = squares.length;
        	int cols = squares[0].length;
        	
        	int totalSquares = rows * cols;

        	for (int row = 0; row < rows; row++) {
        	    for (int col = 0; col < cols; col++) {
        	        int index;
        	        boolean isRowReversed = (rows - row - 1) % 2 != 0;

        	        if (isRowReversed) {
        	            index = totalSquares - (row * cols) - col - 1;
        	        } else {
        	            index = totalSquares - ((row + 1) * cols) + col;
        	        }

        	        JPanel squarePanel = new JPanel(new BorderLayout());
        	        squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        	        
        	        JLabel symbolLabel = new JLabel();
        	       
        	        if (squares[rows - row - 1][col] instanceof QuesSquare) {
        	            ImageIcon questionSquareIcon = new ImageIcon(GameBoardView.class.getResource("/img/questionSquare.png"));
        	            Image scaledQuestionIcon = questionSquareIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        	            symbolLabel = new JLabel(new ImageIcon(scaledQuestionIcon));
        	            
        	        } else if (squares[rows - row - 1][col] instanceof SurpriseSquare) {
        	        	ImageIcon surpriseSquareIcon = new ImageIcon(GameBoardView.class.getResource("/img/surpriseSquare.png"));
        	            Image scaledSurpriseIcon = surpriseSquareIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        	            symbolLabel = new JLabel(new ImageIcon(scaledSurpriseIcon));
        	        }
        	        
        	        squarePanel.add(symbolLabel, BorderLayout.CENTER);
        	        JLabel numberLabel = new JLabel(String.valueOf(index + 1), SwingConstants.LEFT);
        	        numberLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        	        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	        numberPanel.add(numberLabel);
        	        numberPanel.setOpaque(false);
        	        numberPanel.setPreferredSize(new Dimension(squarePanel.getWidth(), numberLabel.getPreferredSize().height + 5)); // Add some padding for visual clearance
        	        squarePanel.add(numberPanel, BorderLayout.SOUTH);
        	        
        	        boardPanel.add(squarePanel);
        	    }
        	}
        	
	        boardPanel.revalidate();
	        boardPanel.repaint();
	        boardPanel.setBackground(Color.CYAN);
        	this.setVisible(true);
        }
        
        ImageIcon diceIcon = new ImageIcon(GameBoardView.class.getResource("/img/dice.png"));
        Image scaledDiceIcon = diceIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel diceLabel = new JLabel(new ImageIcon(scaledDiceIcon));
        
        
        JPanel overlayPanel = new JPanel();
        overlayPanel.setBounds(boardPanel.getBounds());
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(null);

        contentPane.add(overlayPanel);
        contentPane.add(diceLabel);
        contentPane.add(boardPanel);
        contentPane.add(backgroundImage);
        
        
        startTime = System.currentTimeMillis();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                String format = formatTime(elapsedTime);
                timerLabel.setText(format);
            }
        });
        timer.start();
 
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
            	int x = (contentPane.getWidth() - boardPanel.getPreferredSize().width) / 4;
                int y = (contentPane.getHeight() - boardPanel.getPreferredSize().height) / 3;

                boardPanel.setBounds(x, y, boardPanel.getPreferredSize().width, boardPanel.getPreferredSize().height);
                overlayPanel.setBounds(boardPanel.getBounds());
                backgroundImage.setSize(getWidth(), getHeight());
                updateLabelImage(getWidth(), getHeight());
                timerPanel.setBounds(x, getHeight() - 110, 200, 50);
                timerPanel.revalidate();
                timerPanel.repaint();
                playersPanel.setBounds(getWidth() - 560, y, 300, 381);
                updatePlayerPositionsOnBoard(diff, overlayPanel, squares.length, squares[0].length);
                diceLabel.setBounds(getWidth() - 560 + 100, getHeight() - 250, 100, 100);//getWidth() - 560 + playerPanelWidth/2
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
    }

    /**
     * Update the label's icon with a scaled version of the original image.
     * Scales the image to fit the current dimensions of the label.
     */
    private void updateLabelImage(int width, int height) {
        if (originalIcon != null) {
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            backgroundImage.setIcon(new ImageIcon(scaledImage));
        }
    }
    
    private String formatTime(long millis) {
        long minutes = (millis / 60000) % 60;
        long seconds = (millis / 1000) % 60;
        long hundredth = (millis / 10) % 100;
        return String.format("%02d:%02d.%02d", minutes, seconds, hundredth);
    }
    
    /**
     * Update the players positions on the board
     * !! still need to handle multiple players on the same square in Iteration 3
     */
    private void updatePlayerPositionsOnBoard(DIFFICULTY diff, JPanel boardPanel, int rows, int cols) {
        int boardWidth = boardPanel.getWidth();
        int boardHeight = boardPanel.getHeight();
        
        System.out.println(boardWidth + " " + boardHeight);

        int cellWidth = boardWidth / cols;
        int cellHeight = boardHeight / rows;
        
        System.out.println(cellWidth + " " + cellHeight);

        boardPanel.removeAll();

        for (Player player : players) {
            Integer position = playersPositions.get(player);
            if (position != null) {
                int actualRow = (position - 1) / cols;
                int actualCol = (position - 1) % cols;

                int displayRow = rows - 1 - actualRow;

                if (displayRow % 2 != 0) {
                    actualCol = cols - 1 - actualCol;
                }

                int x = actualCol * cellWidth + cellWidth / 2;
                int y = displayRow * cellHeight + cellHeight / 2;
                
                System.out.println(x + " " + y);

                ImageIcon playerIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + player.getColor().toString().toLowerCase() + "player.png"));
                JLabel playerMarker = new JLabel(new ImageIcon(playerIcon.getImage().getScaledInstance(25, 40, Image.SCALE_SMOOTH)));

                switch(diff) {
	                case EASY:
	                	playerMarker.setBounds(x - cellWidth/2 + 45, y - cellHeight/2 + 5, 25, 45);
	                	break;
	                case MEDIUM: 
	                	playerMarker.setBounds(x - cellWidth/2 + 45/2, y - cellHeight/2 - 5, 35, 63);
	                	break;
	                case HARD:
	                	playerMarker.setBounds(x - cellWidth/2 + 45/2, y - cellHeight/2 - 5, 45, 81);
	                	break;
                }
              
                boardPanel.add(playerMarker);
                boardPanel.setComponentZOrder(playerMarker, 0);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }
}
