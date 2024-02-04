package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class QuestionsView extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionsView window = new QuestionsView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuestionsView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 822, 532);
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
		
		
		
		JButton newQuestionButton = new JButton();
		newQuestionButton.setBounds(0,0,85,63);
		newQuestionButton.setOpaque(false);
		newQuestionButton.setContentAreaFilled(false);
		newQuestionButton.setBorderPainted(false);
		newQuestionButton.setPreferredSize(new Dimension(0,0));
		newQuestionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//TODO add functionalitiy when controler for view is finished
			}
		});
		JLabel newQuestionIcon = new JLabel("");
		newQuestionIcon.setBounds(0, 0, 85, 63);
		newQuestionIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/addQuestion"	+ ".png")));
		
		JPanel newQuestionPanel = new JPanel();
		newQuestionPanel.setBounds(697, 396, 85, 63);
		newQuestionPanel.setOpaque(false);
		newQuestionPanel.setLayout(null);
		newQuestionPanel.add(newQuestionIcon);
		newQuestionPanel.add(newQuestionButton);
		
		
		
		
		
		
		String[] colName= new String[] {"#", "Question", "option1" , "option2", "option3", "option4", "Correct Answer", "Actions"};
		Object[][]rows = new Object[][] {};
		DefaultTableModel model= new DefaultTableModel(rows, colName);
		JTable table = new JTable(model);
		table.setOpaque(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setBounds(39,100,640,470);
        
     
		
		/* setting background */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeView.class.getResource("/img/questionsViewBackground.png")));
		lblNewLabel.setBounds(0, 0,822,532);
		lblNewLabel.add(backPanel);
		lblNewLabel.add(tablePanel);
		lblNewLabel.add(newQuestionPanel);
		contentPane.add(lblNewLabel);	
	}



}
