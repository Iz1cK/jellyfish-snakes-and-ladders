package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.GameBoardController;
import Model.Board;
import Model.DIFFICULTY;
import Model.Ladder;
import Model.PLAYERCOLORS;
import Model.Player;
import Model.QuesSquare;
import Model.Snake;
import Model.Square;
import Model.SurpriseSquare;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
    
    private static ArrayList<Snake> snakes;
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
//    public GameBoardView(ArrayList<Player> playerss, DIFFICULTY diff, boolean isMain) {
    public GameBoardView() {
    	
    	GameBoardController GBC = GameBoardController.getInstance();
    	Board board = GBC.getGameBoard();
    	ArrayList<Player> players = board.getPlayers();
    	DIFFICULTY diff = board.getDifficultyBoard();
    	HashMap<Player,Integer> playersPositions = board.getPlayersPositions();
        
        Square[][] squares = board.getSquares();
        System.out.println(squares[0][2]);
        snakes = new ArrayList<>();
        Snake snake1 = new Snake("0", squares[1][4], squares[0][2]);
        snakes.add(snake1);
    	
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
                updatePlayerPositionsOnBoard(diff, players, playersPositions, overlayPanel, squares.length, squares[0].length);
                addSnakesAndLadders(overlayPanel, squares.length, squares[0].length, board);
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
    
    private void addSnakesAndLadders(JPanel boardPanel, int rows, int cols, Board board) {
        // Assuming you have lists of snakes and ladders in your Board model
//        ArrayList<Snake> snakes = board.getSnakes();
//        ArrayList<Ladder> ladders = board.getLadders();

        int cellWidth = boardPanel.getWidth() / cols;
        int cellHeight = boardPanel.getHeight() / rows;
        System.out.println("cellWidth: " + cellWidth + "\ncellHeight: " + cellHeight);

        for (Snake snake : snakes) {
            // Calculate position for snake image based on head and tail squares
            int headX = calculateXPosition(snake.getHeadSquare().getColumn(), cellWidth, cols);
            int headY = calculateYPosition(snake.getHeadSquare().getRow(), cellHeight, rows);

            int tailX = calculateXPosition(snake.getTailSquare().getColumn(), cellWidth, cols);
            int tailY = calculateYPosition(snake.getTailSquare().getRow(), cellHeight, rows);
            
            System.out.println("HeadX: " + headX + "\nTailX: " + tailX);
            System.out.println("HeadY: " + headY + "\nTailY: " + tailY);
            
            int deltaX = tailX - headX;
            int deltaY = tailY - headY;

            double angleInRadians = Math.atan2(deltaY, deltaX);
            double angleInDegrees = Math.toDegrees(angleInRadians);

            double rotationAngle = 180 - angleInDegrees;

            // Load and add snake image
            ImageIcon snakeIcon = new ImageIcon(GameBoardView.class.getResource("/img/sSnake.png"));
            Image snakeImg = snakeIcon.getImage();
            BufferedImage buffSnakeImg = toBufferedImage(snakeImg);
            BufferedImage rotatedBuffSnakeImg = rotate(buffSnakeImg, angleInDegrees);
            Image newSnakeImg = rotatedBuffSnakeImg;
            JLabel snakeLabel = new JLabel(new ImageIcon(newSnakeImg.getScaledInstance(cellWidth, cellHeight * Math.abs(headY - tailY), Image.SCALE_SMOOTH)));

            snakeLabel.setBounds(Math.min(headX, tailX), Math.min(headY, tailY), cellWidth, cellHeight * Math.abs(headY - tailY));
            boardPanel.add(snakeLabel);
        }

//        for (Ladder ladder : ladders) {
//            // Similar calculation for ladders
//        }

        boardPanel.revalidate();
        boardPanel.repaint();
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
    private void updatePlayerPositionsOnBoard(DIFFICULTY diff, ArrayList<Player> players, HashMap<Player,Integer> playersPositions, JPanel boardPanel, int rows, int cols) {
        int boardWidth = boardPanel.getWidth();
        int boardHeight = boardPanel.getHeight();

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

        // Iterate over each square and position players
        squareToPlayers.forEach((position, playersInSquare) -> {
            int actualRow = (position - 1) / cols;
            int actualCol = (position - 1) % cols;

            int displayRow = rows - 1 - actualRow;
            if (displayRow % 2 != 0) {
                actualCol = cols - 1 - actualCol;
            }

            for (int i = 0; i < playersInSquare.size(); i++) {
                Player player = playersInSquare.get(i);
                int x = actualCol * cellWidth + (i * cellWidth / playersInSquare.size()) + cellWidth / playersInSquare.size() / 2;
                int y = displayRow * cellHeight + cellHeight / 2;

                ImageIcon playerIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + player.getColor().toString().toLowerCase() + "player.png"));
                JLabel playerMarker = new JLabel(new ImageIcon(playerIcon.getImage().getScaledInstance(15, 25, Image.SCALE_SMOOTH)));

                // Adjust positioning based on difficulty
                switch(diff) {
                    case EASY:
                        playerMarker.setBounds(x - cellWidth/2 + 25, y - 10, 15, 35);
                        break;
                    case MEDIUM: 
                        playerMarker.setBounds(x + 30, y, 25, 50);
                        break;
                    case HARD:
                        playerMarker.setBounds(x + 15/2, y, 35, 75);
                        break;
                }

                boardPanel.add(playerMarker);
                boardPanel.setComponentZOrder(playerMarker, 0);
            }
        });

        boardPanel.revalidate();
        boardPanel.repaint();
    }
    
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double radians = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radians)), cos = Math.abs(Math.cos(radians));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w * cos + h * sin), newh = (int)Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(radians, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
