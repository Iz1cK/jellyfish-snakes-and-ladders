package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.SurpriseSquareController;

public class SurpriseSquarePopupView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SurpriseSquarePopupView frame = new SurpriseSquarePopupView("forward",null);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the frame.
		 * @param C 
		 */
		public SurpriseSquarePopupView(String direction, SurpriseSquareController C) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(650,408);
			this.setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			
			/*
			 *  button to close this page, call SurpriseSquareController 
			 *  to apply result of surprise square to the position
			 *  of the player on the board 
			 */
			JLabel lblGo = new JLabel("");
			lblGo.setBounds(-10,0,180,50);
			lblGo.setIcon(new ImageIcon(HomeView.class.getResource("/img/goButton"	+ ".png")));
			lblGo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					C.innitiateMove();
					dispose();
				}
			});

			
			
			JPanel panGoButton = new JPanel();
			panGoButton.setBorder(null);
			panGoButton.setLayout(null);
			panGoButton.setBounds(220,300,170,50);
			panGoButton.add(lblGo);

			
			
			
			/*
			 *  background and body of pop-up frame
			 */
					JLabel popUpIcon = new JLabel("");
					popUpIcon.setBounds(0, 0, 650,408);
					if(direction.equals("forward"))
						popUpIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/forwardsSurpriseSquare"	+ ".png")));
					else if(direction.equals("backward"))
						popUpIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/backwardsSurpriseSquare"	+ ".png")));
					
					JPanel popUp = new JPanel();
					popUp.setBounds(0,-12,634,381);
					popUp.setOpaque(false);
					popUp.add(popUpIcon);
					contentPane.setLayout(null);
					contentPane.add(panGoButton);
					contentPane.add(popUp);
			
			
			
			
		}

}
