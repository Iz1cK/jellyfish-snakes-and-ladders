package View;

import java.awt.Color;
import java.awt.Component;
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

	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}
	
    public QuestionsView() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(0, 0, 1350, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 //easy filter button
        JLabel easyLabel = new JLabel("EASY");
        easyLabel.setForeground(Color.WHITE);
        easyLabel.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 28));
        easyLabel.setBounds(1097, 268, 125, 51);
        contentPane.add(easyLabel);
        
		JLabel easyJLabel = new JLabel("");
		ImageIcon ImageIcon1 = new ImageIcon(QuestionsView.class.getResource("/img/greenWood.png"));
		ImageIcon test1= resized(ImageIcon1.getImage(), 232, 150);
		easyJLabel.setIcon(test1);
		// Set size to match content pane
		easyJLabel.setBounds(1028, 265, 232, 72);
		contentPane.add(easyJLabel);
		
		easyJLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/greenWood2.png"));
				ImageIcon test= resized(ImageIcon.getImage(), 232, 150);
				easyJLabel.setIcon(test);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/greenWood.png"));
			    ImageIcon test= resized(ImageIcon.getImage(), 232, 150);
			    easyJLabel.setIcon(test);
			    }
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String searchText = "easy".toLowerCase().trim();
                filterTableData(searchText);
				}
			});
		
		//medium filter button
		JLabel mediumLabel = new JLabel("MEDIUM");
        mediumLabel.setForeground(Color.WHITE);
        mediumLabel.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
        mediumLabel.setBounds(1058, 348, 257, 51);
        contentPane.add(mediumLabel);
        
		JLabel mediumJLabel = new JLabel("");
		ImageIcon ImageIcon4 = new ImageIcon(QuestionsView.class.getResource("/img/yellowWood.png"));
	    ImageIcon test4= resized(ImageIcon4.getImage(), 330, 150);
	    mediumJLabel.setIcon(test4);
	    // Set size to match content pane
	    mediumJLabel.setBounds(966, 335, 293, 72);
	    contentPane.add(mediumJLabel);
	    mediumJLabel.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/yellowWood2.png"));
	    		ImageIcon test= resized(ImageIcon.getImage(), 330, 150);
	    		mediumJLabel.setIcon(test);
	    		}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/yellowWood.png"));
	    		ImageIcon test= resized(ImageIcon.getImage(), 330, 150);
	    		mediumJLabel.setIcon(test);
				}
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		String searchText = "medium".toLowerCase().trim();
                filterTableData(searchText);
				}
			});
	        
	       //hard filter button
	        JLabel hardLabel = new JLabel("HARD");
	        hardLabel.setForeground(Color.WHITE);
	        hardLabel.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 30));
	        hardLabel.setBounds(1078, 417, 125, 51);
	        contentPane.add(hardLabel);
	        
			JLabel hardJLabel = new JLabel("");
			ImageIcon ImageIcon3 = new ImageIcon(QuestionsView.class.getResource("/img/orangeWood.png"));
			ImageIcon test3= resized(ImageIcon3.getImage(), 250, 150);
			hardJLabel.setIcon(test3);
			// Set size to match content pane
			hardJLabel.setBounds(1003, 404, 257, 88);
			contentPane.add(hardJLabel);
			hardJLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/orangeWood2.png"));
				    ImageIcon test= resized(ImageIcon.getImage(), 250, 150);
				    hardJLabel.setIcon(test);
					}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/orangeWood.png"));
				    ImageIcon test= resized(ImageIcon.getImage(), 250, 150);
				    hardJLabel.setIcon(test);
					}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String searchText = "hard".toLowerCase().trim();
	                filterTableData(searchText);
					}
				});
			
			//add question button
			JLabel addQuestion = new JLabel("");
			ImageIcon ImageIcon5 = new ImageIcon(QuestionsView.class.getResource("/img/addQ1.png"));
			ImageIcon test5= resized(ImageIcon5.getImage(), 100, 100);
			addQuestion.setIcon(test5);
			// Set size to match content pane
			addQuestion.setBounds(1078, 545, 142, 126);
			contentPane.add(addQuestion);
			addQuestion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/addQ2.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
					addQuestion.setIcon(test);
					}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon ImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/addQ1.png"));
					ImageIcon test= resized(ImageIcon.getImage(), 100, 100);
					addQuestion.setIcon(test);
					}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					AddQuestionView addQ= new AddQuestionView();
					addQ.setVisible(true);
					dispose();
					}
				});
			
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
	        searchField.setBounds(406, 167 , 293, 43);
	        searchField.setOpaque(false); 
	        searchField.setForeground(Color.WHITE);
	        contentPane.add(searchField);
	        
			JLabel searchJLabel = new JLabel("");
			ImageIcon searchImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/seachPanel.png"));
			ImageIcon searchtest= resized(searchImageIcon.getImage(), 400, 150);
			searchJLabel.setIcon(searchtest);
			// Set size to match content pane
			searchJLabel.setBounds(367, 140, 450, 88);
			contentPane.add(searchJLabel);
			
			//search button
			JLabel search = new JLabel("");
			ImageIcon searchImageIcon1 = new ImageIcon(QuestionsView.class.getResource("/img/search.png"));
			ImageIcon testS= resized(searchImageIcon1.getImage(), 80, 80);
			search.setIcon(testS);
			// Set size to match content pane
			search.setBounds(304, 148, 75, 72);
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
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	QuestionViewController.onEdit(row);
                dispose();
            }

            @Override
            public void onDelete(int row) {
            	QuestionViewController.onDelete(row);
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
	        ImageIcon backgroundImageIcon = new ImageIcon(QuestionsView.class.getResource("/img/QuestionTableView.png"));

	        // Resize the background image to fit the frame
	        Image backgroundImage = backgroundImageIcon.getImage();
	        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight()-40, Image.SCALE_SMOOTH);
	        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

	        backgrounde.setIcon(resizedBackgroundIcon);

	        // Set size to match content pane
	        backgrounde.setBounds(0, 0, getWidth(), getHeight()-40);

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
        table.setFont(new Font("Tahoma", Font.PLAIN, 17));
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
        table.setOpaque(false);
      
        table.setBackground(new Color(0, 0, 0, 0));
        jScrollPane1.setViewportView(table);
        jScrollPane1.setBounds(29, 170, 780, 440);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        contentPane.add(jScrollPane1);
        
        JLabel questionLabel = new JLabel("QUESTIONS");
        questionLabel.setLocation(400, 46);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(144)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(316)
        					.addComponent(questionLabel, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(405, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(41)
        			.addComponent(questionLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        			.addGap(149)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(81, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        pack();
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
            com.setFont(com.getFont().deriveFont(Font.BOLD, 14));
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
}