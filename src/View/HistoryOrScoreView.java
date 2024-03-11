package View;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class HistoryOrScoreView extends JFrame {

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
					HistoryOrScoreView frame = new HistoryOrScoreView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//method to resized the image
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
	
	/**
	 * Create the frame.
	 */
	public HistoryOrScoreView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        JLabel playgame1 = new JLabel("");
		contentPane.add(playgame1);
		 ImageIcon ImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/playerGame1.png"));
		 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
	     playgame1.setIcon(test);
	     // Set size to match content pane
	     playgame1.setBounds(420, 156, 457, 242);
	     contentPane.add(playgame1);	
	     playgame1.addMouseListener(new MouseAdapter() {
	    	 @Override
	    	 public void mouseEntered(MouseEvent e) {
	    		 ImageIcon ImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/playersGame.png"));
			     ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
			     playgame1.setIcon(test);
					}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/playerGame1.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 500, 212);
					playgame1.setIcon(test);
					}
				@Override
				public void mouseClicked(MouseEvent arg0) {
			    		GameHistoryViews chooseGame = new GameHistoryViews();
			    		chooseGame.setVisible(true);
			    		dispose();
			    	
				 }
			});
	     
	     
				
		 JLabel exitIcon = new JLabel("");	
		 contentPane.add(exitIcon);				
		 ImageIcon ImageExit = new ImageIcon(HistoryOrScoreView.class.getResource("/img/ghostGame.png"));				
		 ImageIcon testExit= resized(ImageExit.getImage(), 500, 212);	
		 exitIcon.setIcon(testExit);								
		 // Set size to match content pane				
		 exitIcon.setBounds(430, 419, 457, 212);	
		 contentPane.add(exitIcon);						
		 exitIcon.addMouseListener(new MouseAdapter() {					
			 @Override					
			 public void mouseEntered(MouseEvent e) {						
				 ImageIcon ImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/ghostGame2.png"));					    
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);					    
				 exitIcon.setIcon(test);						
			 }					
			 @Override					
			 public void mouseExited(MouseEvent e) {						
				 ImageIcon ImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/ghostGame.png"));						
				 ImageIcon test= resized(ImageIcon.getImage(), 500, 212);						
				 exitIcon.setIcon(test);						
			 }					
			 @Override					
			 public void mouseClicked(MouseEvent arg0) {
				 GameScoreView chooseGame = new GameScoreView();
		    		chooseGame.setVisible(true);
		    		dispose();
									
			 }					
		 });
		
		JLabel lblInstructionsIcon = new JLabel("");
		lblInstructionsIcon.setIcon(new ImageIcon(HistoryOrScoreView.class.getResource("/img/circle_crop.png")));
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
		 JLabel backgrounde = new JLabel("");
	        ImageIcon backgroundImageIcon = new ImageIcon(HistoryOrScoreView.class.getResource("/img/snakeAndLadder.jpg"));

	        // Resize the background image to fit the frame
	        Image backgroundImage = backgroundImageIcon.getImage();
	        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

	        backgrounde.setIcon(resizedBackgroundIcon);

	        // Set size to match content pane
	        backgrounde.setBounds(0, 0, getWidth(), getHeight());

	        contentPane.add(backgrounde);	
	        
		
	}	

}
