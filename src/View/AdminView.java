package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.AdminAuthentication;


public class AdminView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5389683577053397511L;
	private JPanel contentPane;
	private JPasswordField txtPassword;
	private TextField txtUsername;
	private JCheckBox showPassword;

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
		
		JLabel usernameIcon = new JLabel("New label");
		usernameIcon.setIcon(new ImageIcon(AdminView.class.getResource("/img/username.png")));
		usernameIcon.setBounds(189, 372, 52, 50);
		contentPane.add(usernameIcon);
		JLabel passwordIcon = new JLabel("New label");
		passwordIcon.setIcon(new ImageIcon(AdminView.class.getResource("/img/password.png")));
		passwordIcon.setBounds(189, 422, 52, 57);
		contentPane.add(passwordIcon);
		txtUsername = new TextField();
		txtUsername.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		txtUsername.setText("username");
		txtUsername.setBounds(262, 372, 169, 42);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		/*TextField txtPassword = new TextField();
		contentPane.add(txtPassword);
		txtPassword.setBounds(304,378,190,30);*/
		
		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setText("Password");
		txtPassword.setBounds(262, 437, 169, 42);
		contentPane.add(txtPassword);
		
		showPassword = new JCheckBox("show password ");
		showPassword.setForeground(Color.WHITE);
		showPassword.addActionListener(this);
		showPassword.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		showPassword.setBounds(262, 478, 161, 23);
		contentPane.add(showPassword);
		  // Set transparent background
        showPassword.setOpaque(false);
        showPassword.setBackground(new Color(0, 0, 0, 0)); // Transparent black

        // Add JCheckBox to contentPane
        contentPane.add(showPassword);
		
		
		JButton login = new JButton("Login");
		contentPane.add(login);
		login.setBounds(294,525,80,30);
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
		
		JLabel loginForm = new JLabel("LOGIN FORM");
		loginForm.setForeground(Color.WHITE);
		loginForm.setFont(new Font("Kristen ITC", Font.PLAIN, 25));
		loginForm.setBounds(232, 254, 235, 100);
		contentPane.add(loginForm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource("/img/homeBackground.png")));
		lblNewLabel.setBounds(0, 0,650,650);
		lblNewLabel.add(backPanel);
		contentPane.add(lblNewLabel);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== showPassword) {
			if(showPassword.isSelected()) {
				/*i use the method setEchoChar and he transit the echo to char*/
				txtPassword.setEchoChar((char)0);
			}
			else {
				/*else if he press again the show password the password will transit to the â—�*/
				txtPassword.setEchoChar('*');
			}
		}
		
	}
}
