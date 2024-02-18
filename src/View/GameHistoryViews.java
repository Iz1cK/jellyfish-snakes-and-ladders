package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import Controller.GameHistroyController;


public class GameHistoryViews extends JFrame {


	private JPanel contentPane;
	private JScrollPane jScrollPane1;
	private JTable table;
	private static final long serialVersionUID = -2878161185923702132L;

    /**
     * Creates new form Test
     */
    public GameHistoryViews() {
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
		backIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/backQuestionView1.png")));
		
		JPanel backPanel = new JPanel();
		backPanel.setBounds(47, 44, 53, 45);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		backPanel.add(backIcon);
		backPanel.add(backButton);	
        
        initComponents();
        
        /* setting background */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/gameHistoryBackground1.png")));
		lblNewLabel.setBounds(0, 0,822,532);
		lblNewLabel.add(backPanel);
		contentPane.add(lblNewLabel);	
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
    	jScrollPane1 = new JScrollPane();
        table = new JTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Object[][] newData= GameHistroyController.getTableData();
        
        // Column names
        String[] columnNames =  {"#", "Game Difficulty", "Players" , "Winner", "Duration"};

        // Create a new DefaultTableModel with newData and columnNames
        table.setModel(new DefaultTableModel(
            newData,
            columnNames
        ));

        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(56, 138, 112));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30); // Set width of column 0 (Difficulty) to 100 pixels
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(157);
        columnModel.getColumn(3).setPreferredWidth(90);
        columnModel.getColumn(4).setPreferredWidth(90);
       
        jScrollPane1.setViewportView(table);
        jScrollPane1.setBounds(20, 60, 780, 440);
        contentPane.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(163, Short.MAX_VALUE)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
        			.addGap(211))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(97)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(60, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
   
    // End of variables declaration//GEN-END:variables
    class ActionButton extends JButton {
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
                new GameHistoryViews().setVisible(true);
            }
        });
    }
}