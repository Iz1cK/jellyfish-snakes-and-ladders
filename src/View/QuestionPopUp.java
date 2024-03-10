package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import Controller.questionPopUpController;
import Utils.QuestionCallback;

public class QuestionPopUp extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType;
    private Background background1;
    public ButtonCustom cmdobt1;
    public ButtonCustom cmdobt2;
    public ButtonCustom cmdobt3;
    public ButtonCustom cmdobt4;
    private JLabel lbIcon;
    private JLabel lbTitle;
    private JTextPane txt;
    public int correctAns;
    public int difficulty;
    public boolean isCorrect;
	questionPopUpController controller= questionPopUpController.getInstance();
	private QuestionCallback callback;
	private int level;


	AudioTest audio= new AudioTest();
    // End of variables declaration//GEN-END:variables
    public QuestionPopUp(QuestionCallback callback, int level, JFrame gameBoardView) {
    	super(gameBoardView,true);
		this.fram = gameBoardView;
		this.level = level;
		this.callback = callback;
		initComponents();
        init();
	}

 /*   public MessageDialog(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }*/
 
	private void init() {
//		setModalityType(ModalityType.APPLICATION_MODAL);
    	setBackground(new Color(0, 0, 0, 0));
    	 setBounds(300, 300, 1000, 500);
        StyledDocument doc = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        txt.setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }

        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    public void showMessage(String title, String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true);
        lbTitle.setText(title);
        txt.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }

    public void closeMessage() {
        startAnimator(false);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new Background();
        
        cmdobt1=new ButtonCustom();
        cmdobt2=new ButtonCustom(); 
        cmdobt3=new ButtonCustom(); 
        cmdobt4=new ButtonCustom(); 

        
        lbIcon = new JLabel();
        lbTitle = new JLabel();
        txt = new JTextPane();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));


        
      //  cmdobt1.setText("answer1");
        cmdobt1.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdobt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cmdObt1ActionPerformed(evt);
            }
        });
      //  cmdobt2.setText("answer2");
        cmdobt2.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdobt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cmdObt2ActionPerformed(evt);
            }
        });
      //  cmdobt3.setText("answer3");
        cmdobt3.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdobt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cmdObt3ActionPerformed(evt);
            }
        });
     //   cmdobt4.setText("answer4");
        cmdobt4.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdobt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cmdObt4ActionPerformed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(SwingConstants.CENTER);
        if(level==1) {
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/easyquestion.png")));
            lbTitle.setForeground(new Color(255, 255, 0));
        }
        else if(level==2) {
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/mediumquestion.png"))); // NOI18N
            lbTitle.setForeground(new Color(0, 128, 0));

        }
        if(level==3) {
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/icon.png"))); // NOI18N
            lbTitle.setForeground(new Color(245, 71, 71));

        }

        lbTitle.setFont(new Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText("Message Title");

        txt.setEditable(false);
        txt.setFont(new Font("sansserif", 0, 14)); // NOI18N
        txt.setForeground(new Color(76, 76, 76));
        txt.setText("Message Text\nSimple");
        txt.setFocusable(false);
        
        
     // Adjust preferred height for buttons
     // Adjust preferred width for buttons
     // Adjust preferred width for buttons
        int buttonWidth = 150;

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE).addGap(3, 3, 3)
                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt)
                        .addGroup(javax.swing.GroupLayout.Alignment.CENTER, background1Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmdobt1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 650)
                                .addComponent(cmdobt2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 650)
                                .addComponent(cmdobt3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 650)
                                .addComponent(cmdobt4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 650))))
                    .addGap(3, 3, 3))
        );

        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(lbTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(background1Layout.createSequentialGroup()
                            .addComponent(cmdobt1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmdobt2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmdobt3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmdobt4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, 60))
                        .addGroup(background1Layout.createSequentialGroup()
                            .addGap(106, 106, 106)))) // Adjusted alignment
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        pack();
        
    }// </editor-fold>//GEN-END:initComponents

 
    private void cmdObt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.ANS1;
    }
    private void cmdObt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.ANS2;
    }
    private void cmdObt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.ANS3;
    }
    private void cmdObt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.ANS4;
    }

    public static enum MessageType {
        ANS1, ANS2, ANS3, ANS4
    }
    
   /* class Background extends JPanel {

        /**
		 * 
		 */
/*		private static final long serialVersionUID = 1L;

		public Background() {
            init();
        }

        private void init() {
            setOpaque(false);
            setBackground(new Color(245, 245, 245));
            setPreferredSize(new Dimension(700, 400)); 
            setForeground(new Color(118, 118, 118));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int x = 0;
            int y = 40;
            int width = getWidth();
            int height = getHeight();
            int iconSpace = 7;
            int totalIconSpace = iconSpace * 2;
            int iconSize = y * 2;
            int iconX = (width - (iconSize + totalIconSpace)) / 2;
            int iconY = 0;
            Area area = new Area(new Rectangle2D.Double(x, y, width, height - y));
            area.subtract(new Area(new Ellipse2D.Double(iconX, iconY - iconSpace, iconSize + totalIconSpace, iconSize + totalIconSpace)));
            area.add(new Area(new Ellipse2D.Double(iconX + iconSpace, 0, iconSize, iconSize)));
            g2.setColor(getBackground());
            g2.fill(area);
            g2.dispose();
            super.paintComponent(g);
        }
    }
    */
    class Glass extends JComponent {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
            repaint();
        }

        public Glass() {
        }

        private float alpha = 0f;

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(Color.gray);
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    public class ButtonCustom extends JButton {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private boolean answerChosen = false;

        public ButtonCustom() {
        	
        	
            init();
        }

        private void init() {
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(true);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleClick();
                }
            });
        }

        private void handleClick() {
            setBackground(Color.BLUE);
            
            answerChosen = true; // Mark that an answer has been chosen
            setCorrect();
            disableAllButtons(); // Disable all buttons
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showFeedback();
                    if (callback != null) {
                    	callback.onQuestionAnswered(isCorrect);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        private void showFeedback() {
            if (isCorrect) {
            	
                setBackground(Color.GREEN);
               
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	
                    	try {
							AudioTest.sounds("S", "correctanswer.wav");
						} catch (UnsupportedAudioFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        MessageDialog obj = new MessageDialog(fram);
                        
                        if(difficulty==1) {
                        obj.showMessage("CORRECT", "You get a Tier 1 powerup!");
                        }
                        else if (difficulty==2) {
                        	obj.showMessage("CORRECT", "You get a Tier 2 powerup!");	
                        }
                        
                        else if (difficulty==3) {
                        	obj.showMessage("CORRECT", "You get a Tier 3 powerup!");	
                        }
                       closeMessage();
                    }
                });
                timer.setRepeats(false);
                timer.start();
                
               // JOptionPane.showMessageDialog(null, "Your answer is correct", "Feedback", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                setBackground(Color.RED);
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	try {
							AudioTest.sounds("S", "uncorrectanswer.wav");
							 Timer stopTimer = new Timer(2000, new ActionListener() {
								 public void actionPerformed(ActionEvent e) {
									 try {
										AudioTest.sounds("P", "correctanswer.wav");
									} catch (UnsupportedAudioFileException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (LineUnavailableException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								 }
							 });
							 stopTimer.setRepeats(false);
							 stopTimer.start();
						} catch (UnsupportedAudioFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    	MessageDialog obj = new MessageDialog(fram);
                    	if(difficulty ==1) {
                        obj.showMessage("WRONG", "You did not get a powerup!");
                        }
                    	else if(difficulty ==2) {
                            obj.showMessage("WRONG", "You did not get a powerup!");
                            }
                    	else if(difficulty ==3) {
                            obj.showMessage("WRONG", "You did not get a powerup!");
                            }
                        closeMessage();
                    }
                });
                timer.setRepeats(false);
                timer.start();
               
               // dispose();
               // JOptionPane.showMessageDialog(null, "Your answer is wrong", "Feedback", JOptionPane.ERROR_MESSAGE);
             // closeMessage();
            }
            
            //closeMessage();
        }

        private void disableAllButtons() {
            for (Component c : getParent().getComponents()) {
                if (c instanceof ButtonCustom) {
                    ((ButtonCustom) c).setEnabled(false);
                }
            }
        } 

        public void setCorrect() {
	        if((getMessageType() == MessageType.ANS1 && correctAns==1)
	        		|| (getMessageType() == MessageType.ANS2 && correctAns==2)
	        		|| (getMessageType() == MessageType.ANS3 && correctAns==3)
	        		|| (getMessageType() == MessageType.ANS4 && correctAns==4)) {
	        	isCorrect=true;
	        	controller.setCorrectAns(isCorrect);
	        }
	        else {
	        	isCorrect=false;
	        	controller.setCorrectAns(isCorrect);
	        }
        }

        public boolean isAnswerChosen() {
            return answerChosen;
        }
    }
}