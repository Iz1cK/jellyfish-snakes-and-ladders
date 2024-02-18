package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.AdminAuthentication;
import Model.Admin;
import Model.Sysdata;

public class AdminView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5389683577053397511L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
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
	public AdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 650, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
		backPanel.setBounds(47, 44, 53, 45);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		backPanel.add(backIcon);
		backPanel.add(backButton);	
		
		Label lblUsername = new Label("Username");
		lblUsername.setBounds(200,400,100,30);
		TextField txtUsername = new TextField();
		txtUsername.setBounds(330,400,100,30);
		
		
		Label lblPassword = new Label("Password");
		lblPassword.setBounds(200,450,100,30);
		TextField txtPassword = new TextField();
		txtPassword.setBounds(330,450,100,30);
		
		
		
		JButton login = new JButton("Login");
		login.setBounds(265,500,80,30);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			if(AdminAuthentication.authenticateAdmin(txtUsername.getText(), txtPassword.getText()))
			{
			QuestionsView qv = new QuestionsView();
			qv.setVisible(true);
			dispose();
			}
			else 
			{
				JOptionPane.showMessageDialog(contentPane, "at least one field is incorrect");
			}
			
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource("/img/homeBackground.png")));
		lblNewLabel.setBounds(0, 0,650,650);
		lblNewLabel.add(lblUsername);
		lblNewLabel.add(txtUsername);
		lblNewLabel.add(lblPassword);
		lblNewLabel.add(txtPassword);
		lblNewLabel.add(login);
		lblNewLabel.add(backPanel);
		contentPane.add(lblNewLabel);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
