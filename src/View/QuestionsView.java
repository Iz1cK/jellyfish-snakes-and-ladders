package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import Controller.QuestionViewController;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class QuestionsView extends JFrame {
	private JPanel contentPane;
	private JTextField searchField;
	private JButton searchButton;
	private JScrollPane jScrollPane1;
	private JTable table;
	private static final long serialVersionUID = -2878161185923702132L;

    public QuestionsView() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 822, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backButton = new JButton();
		backButton.setBounds(0,0,53,94);
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
		backIcon.setBounds(0, -25, 53, 100);
		backIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/backQuestionView1.png")));
		 //contentPane.add(backIcon);	
		 
		JPanel backPanel = new JPanel();
		backPanel.setBounds(47, 44, 53, 53);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		backPanel.add(backIcon);
		backPanel.add(backButton);	
		contentPane.add(backPanel);
		
		JButton newQuestionButton = new JButton();
		newQuestionButton.setBounds(0,0,85,63);
		newQuestionButton.setOpaque(false);
		newQuestionButton.setContentAreaFilled(false);
		newQuestionButton.setBorderPainted(false);
		newQuestionButton.setPreferredSize(new Dimension(0,0));
		newQuestionButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//TODO add functionalitiy when controler for view is finished
				AddQuestionView addQ= new AddQuestionView();
				addQ.show();
				dispose();
			}
		});
		//contentPane.add(newQuestionButton);

		JLabel newQuestionIcon = new JLabel("");
		newQuestionIcon.setBounds(0, 0, 85, 63);
		newQuestionIcon.setIcon(new ImageIcon(HomeView.class.getResource("/img/addQuestion.png")));
		
		JPanel newQuestionPanel = new JPanel();
		newQuestionPanel.setBounds(730, 396, 85, 63);
		newQuestionPanel.setOpaque(false);
		newQuestionPanel.setLayout(null);
		newQuestionPanel.add(newQuestionIcon);
		newQuestionPanel.add(newQuestionButton);
		contentPane.add(newQuestionPanel);
	
		searchField = new JTextField();
        searchField.setBounds(200, 135, 200, 25);
        contentPane.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(420, 135, 100, 25);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase().trim();
                filterTableData(searchText);
            }			
        });
        contentPane.add(searchButton);
        
        initComponents();
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	QuestionViewController.onEdit(row);
                dispose();
            }

            @Override
            public void onDelete(int row) {
            	System.out.println("this is my row"+ row);
            	dispose();
            }

            @Override
            public void onView(int row) {
            	QuestionViewController.onView(row);
            	dispose();
            	}
            };
            table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
            table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
            table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            	/**
				 * 
				 */
				private static final long serialVersionUID = -1511493321397500091L;

				@Override
            	public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            		setHorizontalAlignment(SwingConstants.RIGHT);
            		return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            	}
            });
        
        /* setting background */
            JLabel backgrounde = new JLabel("");
	        ImageIcon backgroundImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/questionTableBackground.png"));

	        // Resize the background image to fit the frame
	        Image backgroundImage = backgroundImageIcon.getImage();
	        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

	        backgrounde.setIcon(resizedBackgroundIcon);

	        // Set size to match content pane
	        backgrounde.setBounds(0, 0, getWidth(), getHeight());

	        contentPane.add(backgrounde);	
	        
		
    }
    public void filterTableData(String searchText) {
		// TODO Auto-generated method stub
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	        table.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
	    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
    	jScrollPane1 = new JScrollPane();
        table = new JTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Object[][] newData= QuestionViewController.getTableData();
        
        // Column names
        String[] columnNames = {"Difficulty", "Question", "Correct Answer","Action"};

        // Create a new DefaultTableModel with newData and columnNames
        table.setModel(new DefaultTableModel(
            newData,
            columnNames
        ));

        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(56, 138, 112));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); // Set width of column 0 (Difficulty) to 100 pixels
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(110);
        //columnModel.getColumn(4).setPreferredWidth(60);
        //columnModel.getColumn(5).setPreferredWidth(60);
        //columnModel.getColumn(6).setPreferredWidth(55);
       // columnModel.getColumn(7).setPreferredWidth(110);
       
        jScrollPane1.setViewportView(table);
        jScrollPane1.setBounds(29, 170, 780, 440);
        contentPane.add(jScrollPane1);
        
        JButton newQuestionButton1 = new JButton("EASY");
        newQuestionButton1.setLocation(726, 200);
        newQuestionButton1.setForeground(Color.WHITE);
        newQuestionButton1.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/easyRank.png")));
        newQuestionButton1.setOpaque(false);
        newQuestionButton1.setContentAreaFilled(false);
        newQuestionButton1.setBorderPainted(false);
        newQuestionButton1.setPreferredSize(new Dimension(0,0));
        newQuestionButton1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//TODO add functionalitiy when controler for view is finished
        		String searchText = "easy".toLowerCase().trim();
                filterTableData(searchText);
        		}
        	});

        JButton newQuestionButton2 = new JButton("MEDIUM");
        newQuestionButton2.setSize(110, 36);
        newQuestionButton2.setLocation(726, 250);
        newQuestionButton2.setForeground(Color.WHITE);
        newQuestionButton2.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/medRank.png")));
        newQuestionButton2.setOpaque(false);
        newQuestionButton2.setContentAreaFilled(false);
        newQuestionButton2.setBorderPainted(false);
        newQuestionButton2.setPreferredSize(new Dimension(0,0));
        newQuestionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add functionality when controler for view is finished
                // Add your functionality here for medium difficulty
            	String searchText = "medium".toLowerCase().trim();
                filterTableData(searchText);
            }
        });

        JButton newQuestionButton3 = new JButton("HARD");
        newQuestionButton3.setLocation(726, 300);
        newQuestionButton3.setForeground(Color.WHITE);
        newQuestionButton3.setIcon(new ImageIcon(QuestionsView.class.getResource("/img/hardRank.png")));
        newQuestionButton3.setOpaque(false);
        newQuestionButton3.setContentAreaFilled(false);
        newQuestionButton3.setBorderPainted(false);
        newQuestionButton3.setPreferredSize(new Dimension(0,0));
        newQuestionButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add functionality when controler for view is finished
                // Add your functionality here for hard difficulty
            	String searchText = "hard".toLowerCase().trim();
                filterTableData(searchText);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(newQuestionButton1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        				.addComponent(newQuestionButton3, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        				.addComponent(newQuestionButton2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(174, Short.MAX_VALUE)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
        			.addGap(36))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(200)
        			.addComponent(newQuestionButton1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(newQuestionButton2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(newQuestionButton3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(203, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
   
    // End of variables declaration//GEN-END:variables
    class ActionButton extends JButton {
        /**
		 * 
		 */
		private static final long serialVersionUID = -7507840410275025890L;
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

    // Inner class for the panel containing action buttons
    class PanelAction extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = -459439724159862840L;
		private ActionButton cmdDelete;
        private ActionButton cmdEdit;
        private ActionButton cmdView;

        public PanelAction(TableActionEvent event, int row) {
            initComponents();
            initEvent(event, row);
        }

        public void initEvent(TableActionEvent event, int row) {
            cmdEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	
                    event.onEdit(row);
                }
            });
            cmdDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	event.onDelete(row);
                }
            });
            cmdView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    event.onView(row);
                }
            });
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {
            cmdEdit = new ActionButton();
            cmdDelete = new ActionButton();
            cmdView = new ActionButton();

            cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N

            cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N

            cmdView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view.png"))); // NOI18N

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmdView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmdView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }
    }
    

    // Inner class for cell editor
    class TableActionCellEditor extends DefaultCellEditor {
        /**
		 * 
		 */
		private static final long serialVersionUID = -297107065217334152L;
		private TableActionEvent event;

        public TableActionCellEditor(TableActionEvent event) {
            super(new JCheckBox());
            this.event = event;
        }

        public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
            PanelAction action = new PanelAction(event, row);
            action.setBackground(jtable.getSelectionBackground());
            return action;
        }
    }

    // Inner class for cell renderer
    class TableActionCellRender extends DefaultTableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 4723955739566072681L;

		@Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
            Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
            PanelAction action = new PanelAction(null, row);
            if (isSeleted == false && row % 2 == 0) {
                action.setBackground(Color.WHITE);
            } else {
                action.setBackground(com.getBackground());
            }
            return action;
        }
    }

    // Interface for table action events
    interface TableActionEvent {
        void onEdit(int row);
        void onDelete(int row);
        void onView(int row);
        
    }
    
    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
    	
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     /*   try {
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
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuestionsView().setVisible(true);
            }
        });
    }*/
}