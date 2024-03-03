package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.GameBoardController;
import Model.Board;
import Model.DIFFICULTY;
import Model.Game;
import Model.PLAYERCOLORS;
import Model.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class FinalPage extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 8299696692660486572L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	ArrayList<Player> aplayers = new ArrayList<>();
                    aplayers.add(new Player(0,"george",PLAYERCOLORS.BLUE));
                    aplayers.add(new Player(0,"aseel",PLAYERCOLORS.ORANGE));
                    
                    Board board = new Board(DIFFICULTY.EASY, aplayers);
                    board.generateBoard();
                    board.initiateQuestionSquares();
                    board.generateSnakesAndLadder();
                    
                    HashMap<Player,Integer> playersposs = board.getPlayersPositions();
                    playersposs.put(aplayers.get(0), 23);
                    board.setPlayersPositions(playersposs);
                    
                    FinalPage frame = new FinalPage(new Game(DIFFICULTY.EASY, board, aplayers.get(0), aplayers, "19:10.02"));
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
    public FinalPage(Game game) {
    	Font labelFont = new Font("Poppins", Font.PLAIN, 36);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 750, 540);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
                
        JLabel timerLabel = new JLabel(game.getDuration(), SwingConstants.CENTER);
        timerLabel.setBounds(514, 359, 163, 37);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(labelFont);
        contentPane.add(timerLabel);
        
        JLabel home = new JLabel();
        home.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		HomeView hv = new HomeView();
        		hv.setVisible(true);
        		dispose();
        	}
        });
        home.setIcon(new ImageIcon(FinalPage.class.getResource("/img/home.png")));
        home.setBounds(40, 427, 181, 63);
        contentPane.add(home);
        
        JLabel winnerName = new JLabel("New label");
        winnerName.setBounds(293, 213, 184, 62);
        winnerName.setFont(labelFont);
        winnerName.setForeground(Color.WHITE);
        winnerName.setText(game.getWinningPlayer());
        contentPane.add(winnerName);
        
        JLabel difficultyImage = new JLabel("New label");
        difficultyImage.setBounds(129, 359, 57, 57);
        String path = game.getDifficulty() == 1 ? "easy" : game.getDifficulty() == 2 ? "medium" : game.getDifficulty() == 3 ? "hard" : "easy";
        ImageIcon difficultyIcon = new ImageIcon(GameBoardView.class.getResource("/img/" + path + ".png"));
        difficultyImage.setIcon(difficultyIcon);
        contentPane.add(difficultyImage);

        
        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(FinalPage.class.getResource("/img/logo.png")));
        logo.setBounds(10, -63, 1000, 350); // Adjusted bounds to fit the frame
        contentPane.add(logo);
        
        JLabel winner = new JLabel("");
        winner.setIcon(new ImageIcon(FinalPage.class.getResource("/img/winner.png")));
        winner.setBounds(262,-5, 1000, 350); // Adjusted bounds to fit the frame
        contentPane.add(winner);
        
        
        JLabel left = new JLabel();
        left.setIcon(new ImageIcon(FinalPage.class.getResource("/img/final.png")));
        left.setBounds(439, 105, 295, 187);
       contentPane.add(left);

        JLabel right = new JLabel();
        right.setIcon(new ImageIcon(FinalPage.class.getResource("/img/final.png")));
        right.setBounds(30, 93, 295, 223);
        contentPane.add(right);
        
        JLabel gameDuration = new JLabel("game duration:");
        gameDuration.setForeground(Color.WHITE); // Set the foreground color to white
        gameDuration.setFont(new Font("Poppins", Font.BOLD, 25)); // Set the font family, size, and weight
        gameDuration.setBounds(449, 298, 300, 60); // Adjusted bounds to fit the text
        gameDuration.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(gameDuration);
        
        JLabel gameDifficulty = new JLabel("game difficulty:");
        gameDifficulty.setForeground(Color.WHITE); // Set the foreground color to white
        gameDifficulty.setFont(new Font("Poppins", Font.BOLD, 25)); // Set the font family, size, and weight
        gameDifficulty.setBounds(73, 298, 200, 60); // Adjusted bounds to fit the text
        gameDifficulty.setHorizontalAlignment(SwingConstants.CENTER); // Set text alignment to center
        contentPane.add(gameDifficulty);
        
        JLabel gameDifficulty_1 = new JLabel("for player");
        gameDifficulty_1.setHorizontalAlignment(SwingConstants.CENTER);
        gameDifficulty_1.setForeground(Color.WHITE);
        gameDifficulty_1.setFont(new Font("Dialog", Font.BOLD, 25));
        gameDifficulty_1.setBounds(262, 160, 200, 60);
        contentPane.add(gameDifficulty_1);
        
        
        JLabel lblNewLabe3 = new JLabel();
        lblNewLabe3.setIcon(new ImageIcon(FinalPage.class.getResource("/img/rectangle.png")));
        lblNewLabe3.setBounds(30, 64, 675, 369);
       // lblNewLabe3.add(left);
       // lblNewLabe3.add(right);
        contentPane.add(lblNewLabe3);

        JLabel again = new JLabel();
        again.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		GameBoardController gbc = GameBoardController.getInstance();
        		Board board = game.getBoardgame();
        		Board newBoard = new Board(board.getDifficultyBoard(), board.getPlayers());
        		newBoard.generateBoard();
        		newBoard.initiateQuestionSquares();
        		newBoard.generateSnakesAndLadder();
        		gbc.setGameBoard(newBoard);
        		GameBoardView gbv = new GameBoardView();
        		gbv.setExtendedState(JFrame.MAXIMIZED_BOTH);
        		gbv.setVisible(true);
        		dispose();
        	}
        });
        again.setIcon(new ImageIcon(FinalPage.class.getResource("/img/again.png")));
        again.setBounds(278, 427, 176, 63);
        contentPane.add(again);

        JLabel history = new JLabel();
        history.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		GameHistoryViews ghv = new GameHistoryViews();
        		ghv.setVisible(true);
        		dispose();
        	}
        });
        history.setIcon(new ImageIcon(FinalPage.class.getResource("/img/history.png")));
        history.setBounds(496, 427, 181, 63);
        contentPane.add(history);

        /*backgrounds*/
        
//        JLabel logo = new JLabel("");
//        logo.setIcon(new ImageIcon(FinalPage.class.getResource("/img/logo.png")));
//        logo.setBounds(10, -63, 1000, 350); // Adjusted bounds to fit the frame
//        contentPane.add(logo);
//        
//        JLabel lblNewLabe3 = new JLabel();
//        lblNewLabe3.setIcon(new ImageIcon(FinalPage.class.getResource("/img/rectangle.png")));
//        lblNewLabe3.setBounds(63, 78, 535, 324);
//        contentPane.add(lblNewLabe3);


        JLabel lblNewLabe2 = new JLabel();
        lblNewLabe2.setIcon(new ImageIcon(FinalPage.class.getResource("/img/rectangle.png")));
        lblNewLabe2.setBounds(0, 0, 750, 540);
        contentPane.add(lblNewLabe2);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(FinalPage.class.getResource("/img/background.png")));
        lblNewLabel.setBounds(0, 0, 750, 540);
        contentPane.add(lblNewLabel);
    }
}
