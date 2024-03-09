package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.GameBoardController;
import Model.Board;
import Model.COLORS;
import Model.DIFFICULTY;
import Model.Ladder;
import Model.Player;
import Model.QuesSquare;
import Model.Snake;
import Model.Square;
import Model.SurpriseSquare;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;


public class GameBoardView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3841291012560699747L;
	private JPanel contentPane;
    private JLabel backgroundImage;
    public static JLabel timerLabel;
    private JPanel overlayPanel;
    private JPanel playersPanel;
    private JPanel playerMarkerPanel;
    private JPanel settingsPannel;
    public static Timer timer;
    private long startTime;
    private ImageIcon originalIcon;
    private static ArrayList<Snake> snakes;
    private static ArrayList<Ladder> ladders;
    public static boolean rolling = false;
    public static boolean playerMoving = false;
    public static HashMap<Player, JLabel> playerLabels = new HashMap<>();
    private static final Color[] COLORSs = {
            new Color(238, 125, 166),
            new Color(170, 227, 250),
            new Color(243, 209, 147),
            new Color(222, 211, 208),
            Color.WHITE, 
           
    };
    private int colorIndex = 0;
    
    GameBoardController GBC = GameBoardController.getInstance();
    Font labelFont = new Font("Poppins", Font.BOLD, 36);
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameBoardView frame = new GameBoardView();
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
    public GameBoardView() {
    	GBC.setGameBoardView(this);
//        ArrayList<Player> aplayers = new ArrayList<>();
//        aplayers.add(new Player(0,"george",PLAYERCOLORS.BLUE));
////////        aplayers.add(new Player(1,"adeeb",PLAYERCOLORS.GREEN));
////////        aplayers.add(new Player(2,"lana",PLAYERCOLORS.RED));
//        aplayers.add(new Player(3,"aseel",PLAYERCOLORS.GREEN));
////////        aplayers.add(new Player(4,"ahmad",PLAYERCOLORS.WHITE));
////////        aplayers.add(new Player(5,"hamoodi",PLAYERCOLORS.YELLOW));
////////        aplayers.add(new Player(6,"mahmood",PLAYERCOLORS.ORANGE));
////////        aplayers.add(new Player(7,"hmada",PLAYERCOLORS.PINK));
//        Board aboard = new Board(DIFFICULTY.HARD,aplayers);
//        aboard.generateBoard();
//        aboard.initiateQuestionSquares();
//        aboard.generateSnakesAndLadder();
//        GBC.setGameBoard(aboard);

    	Board board = GBC.getGameBoard();
    	System.out.println(board);
    	ArrayList<Player> players = board.getPlayers();
    	DIFFICULTY diff = board.getDifficultyBoard();
    	HashMap<Player,Integer> playersPositions = board.getPlayersPositions();
    	
    	snakes=board.getSnakes();
    	ladders=board.getLadders();
        Square[][] squares = board.getSquares();
        
        squares[0][2] = new SurpriseSquare(0,2);
    	
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
        
        playersPanel = new JPanel();
        playersPanel.setBounds(getWidth(), 43, 248, 381);
        contentPane.add(playersPanel);
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        
        playersPanel.revalidate();
        playersPanel.repaint();

        updatePlayersList(players, playersPositions, board);
        
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
            boardPanel.setPreferredSize(new Dimension(450, 300));
            break;
        case MEDIUM: 
            boardPanel.setPreferredSize(new Dimension(750, 450));
            break;
        case HARD:
            boardPanel.setPreferredSize(new Dimension(900, 650));
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
        	        squarePanel.setBackground(COLORSs[colorIndex]);
        	        if(rows==10) {
        	        	Random rand = new Random();
            	        int randomIndex = rand.nextInt(5);
        	        	 colorIndex = randomIndex;
             	        System.out.println(colorIndex);	
        	        }
        	        else {
        	        // Increment the color index for the next square
        	        colorIndex = (colorIndex + 1) % COLORSs.length;
        	        System.out.println(colorIndex);
        	        }
        	        
        	        
        	       // squarePanel.setBackground(new Color(204, 153, 255));
        	        JLabel numberLabel = new JLabel(String.valueOf(index + 1), SwingConstants.LEFT);
        	        numberLabel.setFont(new Font("Poppins", Font.BOLD, 16));
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
        
        ImageIcon diceIcon = new ImageIcon(GameBoardView.class.getResource("/img/dice0.png"));
        JLabel diceLabel = new JLabel(diceIcon);
        
        diceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!rolling) {
                    rollDice(diceLabel, board, () -> {
                    	GBC.rollDice();
                        String imagePath = "/img/dice" + GameBoardController.diceRoll + ".png";
                        ImageIcon icon = new ImageIcon(Main.class.getResource(imagePath));
                        diceLabel.setIcon(icon);
                        GBC.playTurn();
                        if(playersPositions.get(board.getCurrentPlayerTurn()) >= board.getRows()*board.getRows()) {
                        	timer.stop();
                        	setVisible(false);
                        	dispose();
                        }
                    });
                }
            }
        });
        
        
        overlayPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSnakesAndLadders(g, board, boardPanel);
            }
        };
        overlayPanel.setBounds(boardPanel.getBounds());
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(null);
        
        playerMarkerPanel = new JPanel();
        playerMarkerPanel.setBounds(boardPanel.getBounds());
        playerMarkerPanel.setOpaque(false);
        playerMarkerPanel.setLayout(null);
        
        settingsPannel= new JPanel();
        settingsPannel.setBounds(boardPanel.getBounds());
        settingsPannel.setOpaque(false);
        settingsPannel.setLayout(null);
        
        JLabel pause= new JLabel();
        pause.setIcon(new ImageIcon(GameBoardView.class.getResource("/img/pause.png")));
        pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}});
        
        
        
        JLabel home= new JLabel();
        home.setIcon(new ImageIcon(GameBoardView.class.getResource("/img/endGame.png")));
        home.addMouseListener(new MouseAdapter() {
        	@Override 
        	public void mouseClicked(MouseEvent e) {
				int choice= JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                        "Confirmation", JOptionPane.YES_NO_OPTION); 
				if (choice == JOptionPane.YES_OPTION) { 
		           
		        } else if (choice == JOptionPane.NO_OPTION) { 

		        }
			}
        	
		});
        
        JLabel resume= new JLabel();
        resume.setIcon(new ImageIcon(GameBoardView.class.getResource("/img/play.png")));
        resume.addMouseListener(new MouseAdapter() {
        	@Override 
        	public void mouseClicked(MouseEvent e) {
			
			}
        	
		});

        contentPane.add(overlayPanel);
        contentPane.add(playerMarkerPanel);
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
                updatePlayerPositionsOnBoard(diff, players, playersPositions, squares.length, squares[0].length);
                diceLabel.setBounds(getWidth() - 560 + 100, getHeight() - 250, 200, 200);//getWidth() - 560 + playerPanelWidth/2
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
    }

    private void drawSnakesAndLadders(Graphics g, Board board, JPanel boardPanel) {
        Graphics2D g2d = (Graphics2D) g.create();
        int rows = board.getRows();
        int cols = rows;
        int cellWidth = boardPanel.getWidth() / cols;
        int cellHeight = boardPanel.getHeight() / rows;
        for (Snake snake : snakes) {
        	if (snake.getColor() == COLORS.RED) {
        		ImageIcon snakeIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + snake.getColor() + "snake.png"));
        		Image snakeImage = new ImageIcon(snakeIcon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH)).getImage();
        		int headX = calculateXPosition(snake.getHeadSquare().getColumn(), overlayPanel.getWidth() / cols, cols) + cellWidth/4;
        		int headY = calculateYPosition(snake.getHeadSquare().getRow(), overlayPanel.getHeight() / rows, rows) + cellHeight/2 - 10;
        		
        		AffineTransform transform = new AffineTransform();
        		transform.translate(headX, headY);
        		transform.scale(0.6, 0.6);
        		g2d.drawImage(snakeImage, transform, null);
        		
        	} else {
        		int headX = calculateXPosition(snake.getHeadSquare().getColumn(), overlayPanel.getWidth() / cols, cols) + cellWidth/2;
        		int headY = calculateYPosition(snake.getHeadSquare().getRow(), overlayPanel.getHeight() / rows, rows) + cellHeight/2;
        		int tailX = calculateXPosition(snake.getTailSquare().getColumn(), overlayPanel.getWidth() / cols, cols) + cellWidth/2;
        		int tailY = calculateYPosition(snake.getTailSquare().getRow(), overlayPanel.getHeight() / rows, rows) + cellHeight/2;
        		
        		ImageIcon snakeIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + snake.getColor() + "snake.png"));
        		Image snakeImage = snakeIcon.getImage();
        		
        		double distance = Math.sqrt(Math.pow(tailX - headX, 2) + Math.pow(tailY - headY, 2));
        		double angle = Math.atan2(tailY - headY, tailX - headX);
        		
        		double scale = distance / snakeImage.getHeight(null);
        		
        		AffineTransform transform = new AffineTransform();
        		transform.translate(headX, headY);
        		transform.rotate(angle - Math.PI/2);
        		transform.scale(0.3, scale);
        		transform.translate(-snakeImage.getWidth(null)/ 2, 0);
        		
        		g2d.drawImage(snakeImage, transform, null);
        	}
        }
        for (Ladder ladder : ladders) {
            int startX = calculateXPosition(ladder.getStartSquare().getColumn(), overlayPanel.getWidth() / cols, cols) + cellWidth/2;
            int startY = calculateYPosition(ladder.getStartSquare().getRow(), overlayPanel.getHeight() / rows, rows) + cellHeight/2;
            int destX = calculateXPosition(ladder.getDestSquare().getColumn(), overlayPanel.getWidth() / cols, cols) + cellWidth/2;
            int destY = calculateYPosition(ladder.getDestSquare().getRow(), overlayPanel.getHeight() / rows, rows) + cellHeight/2;

            ImageIcon ladderIcon = new ImageIcon(GameBoardView.class.getResource("/img/ladder.png"));
            Image ladderImage = ladderIcon.getImage();

            double distance = Math.sqrt(Math.pow(destX - startX, 2) + Math.pow(destY - startY, 2));
            double angle = Math.atan2(destY - startY, destX - startX);

            double scale = distance / ladderImage.getHeight(null);

            AffineTransform transform = new AffineTransform();
            transform.translate(startX, startY);
            transform.rotate(angle - Math.PI/2);
            transform.scale(0.15, scale);
            transform.translate(-ladderImage.getWidth(null)/ 2, 0);

            g2d.drawImage(ladderImage, transform, null);
        }
        g2d.dispose(); // Clean up
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
    
    public void updatePlayersList(ArrayList<Player> players, HashMap<Player,Integer> playersPositions, Board board) {
    	playersPanel.removeAll();
    	for (Player player : players) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
            playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            ImageIcon playerColorIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + player.getColor().toString().toLowerCase() + "player.png"));
            Image scaledPlayerIcon = playerColorIcon.getImage().getScaledInstance(25, 50, Image.SCALE_SMOOTH);
            JLabel playerColor = new JLabel(new ImageIcon(scaledPlayerIcon));

            JLabel playerLabel = new JLabel(player.getPlayername());
            playerLabel.setFont(labelFont);
            String playerPosition = playersPositions.get(player).toString();
            
            JLabel playerPositionLabel = new JLabel(playerPosition);
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
            if(player == board.getCurrentPlayerTurn()) {
            	playerPanel.add(playerTurn);
            }
            playersPanel.add(playerPanel);
            playersPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between player entries   
            playersPanel.revalidate();
            playersPanel.repaint();
        }
    }

    private String formatTime(long millis) {
        long minutes = (millis / 60000) % 60;
        long seconds = (millis / 1000) % 60;
        long hundredth = (millis / 10) % 100;
        return String.format("%02d:%02d.%02d", minutes, seconds, hundredth);
    }
    
    private int calculateXPosition(int squareX, int cellWidth, int cols) {
        // Implement logic to calculate X position based on the square's X coordinate
        return squareX * cellWidth;
    }

    private int calculateYPosition(int squareY, int cellHeight, int rows) {
        // Implement logic to calculate Y position based on the square's Y coordinate
        return (rows - squareY - 1) * cellHeight; // Assuming top-down layout
    }
    
    /**
     * Update the players positions on the board
     * !! still need to handle multiple players on the same square in Iteration 3
     */
    public void updatePlayerPositionsOnBoard(DIFFICULTY diff, ArrayList<Player> players, HashMap<Player,Integer> playersPositions, int rows, int cols) {
        
        for (Component comp : overlayPanel.getComponents()) {
            if (Boolean.TRUE.equals(((JComponent) comp).getClientProperty("playerMarker"))) {
                overlayPanel.remove(comp);
            }
        }
        
        int boardWidth = overlayPanel.getWidth();
        int boardHeight = overlayPanel.getHeight();

        int cellWidth = boardWidth / cols;
        int cellHeight = boardHeight / rows;

        // Create a map to count players per square
        HashMap<Integer, ArrayList<Player>> squareToPlayers = new HashMap<>();
        for (Player player : players) {
            Integer position = playersPositions.get(player);
            if (position != null) {
                squareToPlayers.putIfAbsent(position, new ArrayList<>());
                squareToPlayers.get(position).add(player);
            }
        }
        
        playerLabels.clear();

        // Iterate over each square and position players
        squareToPlayers.forEach((position, playersInSquare) -> {
            int actualRow = (position - 1) / cols;
            int actualCol = (position - 1) % cols;

            boolean evenRows = rows % 2 == 0;
            int displayRow = rows - 1 - actualRow;
            boolean reverseRow = evenRows ? displayRow % 2 == 0 : displayRow % 2 != 0;

            if (reverseRow) {
                actualCol = cols - 1 - actualCol;
            }

            // Determine number of rows for player placement (1 or 2)
            int numRowsForPlayers = playersInSquare.size() > 4 ? 2 : 1;
            int playersPerRow = (int) Math.ceil(playersInSquare.size() / (double) numRowsForPlayers);
            int rowOffset;
            int x,y;
            for (int i = 0; i < playersInSquare.size(); i++) {
                Player player = playersInSquare.get(i);
                ImageIcon playerIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + player.getColor().toString().toLowerCase() + "player.png"));
                JLabel playerMarker = new JLabel(new ImageIcon(playerIcon.getImage().getScaledInstance(15, 25, Image.SCALE_SMOOTH)));
                playerMarker.putClientProperty("playerMarker", true);
                // Adjust positioning based on difficulty
                switch(diff) {
                    case EASY:
                    	rowOffset = (i / playersPerRow) * cellHeight/2; // Adjust for 2nd row if needed
                        x = actualCol * cellWidth + ((i % playersPerRow) * cellWidth / playersPerRow) + cellWidth / playersPerRow / 2;
                        y = displayRow * cellHeight + rowOffset;
                        playerMarker.setBounds(x - cellWidth/2 + 25, y - 10, 15, 35);
                        break;
                    case MEDIUM: 
                    	rowOffset = (i / playersPerRow) * cellHeight/2 - cellHeight/2; // Adjust for 2nd row if needed
                        x = actualCol * cellWidth + ((i % playersPerRow) * cellWidth / playersPerRow) + cellWidth / playersPerRow / 2;
                        y = displayRow * cellHeight + rowOffset;
                        playerMarker.setBounds(x - cellWidth/2 + 30, y, 25, 50);
                        break;
                    case HARD:
                    	rowOffset = (i / playersPerRow) * cellHeight/2 - cellHeight/2; // Adjust for 2nd row if needed
                        x = actualCol * cellWidth + ((i % playersPerRow) * cellWidth / playersPerRow) + cellWidth / playersPerRow / 2;
                        y = displayRow * cellHeight + rowOffset;
                        playerMarker.setBounds(x - cellWidth/2 + 20, y, 35, 75);
                        break;
                }

                playerLabels.put(player, playerMarker);
                overlayPanel.add(playerMarker);
                overlayPanel.setComponentZOrder(playerMarker, 0);
            }
        });

        overlayPanel.revalidate();
        overlayPanel.repaint();
    }


    private static void rollDice(JLabel diceImage, Board board, Runnable callback) {
        new Thread(() -> {
            rolling = true;
            System.out.println("Thread Running");
            try {
                for (int i = 0; i < 15; i++) {
                	String diceRoll = board.rollDice();
                    String imagePath = "/img/dice" + diceRoll + ".png";
                    ImageIcon icon = new ImageIcon(Main.class.getResource(imagePath));
                    diceImage.setIcon(icon);
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                rolling = false;
                // Ensure the callback is executed in the Event Dispatch Thread
                SwingUtilities.invokeLater(callback);
            }
        }).start();
    }
    
    public void animatePlayerMovement(Player player, JLabel playerLabel, Board board, int previousPosition, Runnable onAnimationEnd) {
        HashMap<Player, Integer> playersPositions = board.getPlayersPositions();
        int position = playersPositions.get(player);
        System.out.println("moving player " + player.getPlayername() + "from " + previousPosition + " to " + position);
        
        Point previousCenter = getSquareCenter(board, previousPosition);
        Point newCenter = getSquareCenter(board, position);
        
        Point playerCurrentPos = playerLabel.getLocation();
        int offsetX = playerCurrentPos.x - previousCenter.x;
        int offsetY = playerCurrentPos.y - previousCenter.y;
        
        int targetX = newCenter.x + offsetX;
        int targetY = newCenter.y + offsetY;

        final int delay = 25;
        final int steps = 30;
        final int startX = playerLabel.getX();
        final int startY = playerLabel.getY();
        final double dx = (double) (targetX - startX) / steps;
        final double dy = (double) (targetY - startY) / steps;

        final Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
            private int currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep < steps) {
                    playerLabel.setLocation((int) (startX + dx * currentStep), (int) (startY + dy * currentStep));
                    currentStep++;
                } else {
                    playerLabel.setLocation(targetX, targetY);
                    timer.stop();
                    SwingUtilities.invokeLater(onAnimationEnd);
                }
            }
        });
        timer.start();
    }
    
    private Point getSquareCenter(Board board, int position) {
        int rows = board.getRows();
        int cols = board.getColumns();
        int overlayPanelWidth = overlayPanel.getWidth();
        int overlayPanelHeight = overlayPanel.getHeight();

        int cellWidth = overlayPanelWidth / cols;
        int cellHeight = overlayPanelHeight / rows;

        // Convert board position to row and column
        int row = (position - 1) / cols;
        int col = (position - 1) % cols;

        // Determine if the row should be reversed
        // Rows are zero-indexed here, so for display purposes, it might be rows - row - 1
        boolean evenRows = rows % 2 == 0;
        int displayRow = rows - 1 - row;
        boolean reverseRow = evenRows ? displayRow % 2 == 0 : displayRow % 2 != 0;
        if (reverseRow) {
            col = cols - 1 - col;
        }

        // Calculate center of the square relative to the overlayPanel
        int centerX = col * cellWidth + cellWidth / 2;
        int centerY = (rows - row - 1) * cellHeight + cellHeight / 2;

        return new Point(centerX, centerY);
    }

}
