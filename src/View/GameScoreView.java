package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import Controller.GameHistroyController;
import Controller.GameScoreController;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


public class GameScoreView extends JFrame {


	private JPanel contentPane;
	private JScrollPane jScrollPane1;
	private JTable table;
	private JTextField searchField;
	private static final long serialVersionUID = -2878161185923702132L;
	
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
	
	public void filterTableData(String searchText) {
		// TODO Auto-generated method stub
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	        table.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
	    }
    /**
     * Creates new form Test
     */
    public GameScoreView() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//home button
		JLabel home = new JLabel("");
		ImageIcon ImageIcon6 = new ImageIcon(QuestionsView.class.getResource("/img/home1.png"));
		ImageIcon test6= resized(ImageIcon6.getImage(), 80, 80);
		home.setIcon(test6);
		// Set size to match content pane
		home.setBounds(41, 24, 75, 72);
		contentPane.add(home);
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/home2.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 80, 80);
				home.setIcon(test);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/home1.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 80, 80);
				home.setIcon(test);
				}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				HomeView homeview = new HomeView();
				homeview.setVisible(true);
				dispose();
				}
			});
		
		 //search filter 
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 23));
        searchField.setBounds(550, 170 , 293, 43);
        searchField.setOpaque(false); 
        searchField.setForeground(Color.WHITE);
        contentPane.add(searchField);
        
		JLabel searchJLabel = new JLabel("");
		ImageIcon searchImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/seachPanel.png"));
		ImageIcon searchtest= resized(searchImageIcon.getImage(), 400, 150);
		searchJLabel.setIcon(searchtest);
		// Set size to match content pane
		searchJLabel.setBounds(500, 150, 450, 88);
		contentPane.add(searchJLabel);
		
		//search button
		JLabel search = new JLabel("");
		ImageIcon searchImageIcon1 = new ImageIcon(QuestionsView.class.getResource("/img/search.png"));
		ImageIcon testS= resized(searchImageIcon1.getImage(), 80, 80);
		search.setIcon(testS);
		// Set size to match content pane
		search.setBounds(437, 150, 75, 72);
		contentPane.add(search);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/search1.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 80, 80);
				search.setIcon(test);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/search.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 80, 80);
				search.setIcon(test);
				}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String searchText = searchField.getText().toLowerCase().trim();
                filterTableData(searchText);
				}
			});
        
        initComponents();
        
        /* setting background */
        JLabel backgrounde = new JLabel("");
        ImageIcon backgroundImageIcon = new ImageIcon(GameScoreView.class.getResource("/img/historyBackground.png"));

        // Resize the background image to fit the frame
        Image backgroundImage = backgroundImageIcon.getImage();
        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth()-10, getHeight()-100, Image.SCALE_SMOOTH);
        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

        backgrounde.setIcon(resizedBackgroundIcon);

        // Set size to match content pane
        backgrounde.setBounds(0, 0, getWidth()-10, getHeight()-100);

        contentPane.add(backgrounde);	
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
    	jScrollPane1 = new JScrollPane();
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Object[][] newData= GameScoreController.getTableData();
        
        // Column names
        String[] columnNames =  {"#", "Game Difficulty", "Player" , "Score", "Duration"};

        // Create a new DefaultTableModel with newData and columnNames
        table.setModel(new DefaultTableModel(
            newData,
            columnNames
        ));

        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(56, 138, 112));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // Set width of column 0 (Difficulty) to 100 pixels
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(315);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
       
        jScrollPane1.setViewportView(table);
        jScrollPane1.setBounds(20, 60, 780, 440);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        contentPane.add(jScrollPane1);
        
        JLabel lblGameHistory = new JLabel("GAME HISTORY");
        lblGameHistory.setLocation(600, 205);
        lblGameHistory.setVerticalAlignment(SwingConstants.TOP);
        lblGameHistory.setHorizontalAlignment(SwingConstants.CENTER);
       
        lblGameHistory.setForeground(Color.WHITE);
        lblGameHistory.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 45));
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(contentPane);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(438)
        			.addComponent(lblGameHistory, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(291, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(337, Short.MAX_VALUE)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE)
        			.addGap(303))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(45)
        			.addComponent(lblGameHistory, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
        			.addGap(125))
        );

        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1350, 760));

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
   
    // End of variables declaration//GEN-END:variables
    class ActionButton extends JButton {
        /**
		 * 
		 */
		private static final long serialVersionUID = 443761283009162516L;
		private boolean mousePress;

        public ActionButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(3, 3, 3, 3));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    mousePress = true;
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    mousePress = false;
                }
            });
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int size = Math.min(width, height);
            int x = (width - size) / 2;
            int y = (height - size) / 2;
            if (mousePress) {
                g2.setColor(new Color(158, 158, 158));
            } else {
                g2.setColor(new Color(199, 199, 199));
            }
            g2.fill(new Ellipse2D.Double(x, y, size, size));
            g2.dispose();
            super.paintComponent(grphcs);
        }
    }

   


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuestionsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameScoreView().setVisible(true);
            }
        });
    }
}