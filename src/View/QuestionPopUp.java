package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import javax.swing.JOptionPane;

import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.AddQuestionController;
import Controller.questionPopUpController;

public class QuestionPopUp extends JDialog {

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
    // End of variables declaration//GEN-END:variables
    public QuestionPopUp() {
    	super();
		this.fram = new JFrame();
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
    	setBackground(new Color(0, 0, 0, 0));
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

    @SuppressWarnings("unchecked")
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
        lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/icon.png"))); // NOI18N

        lbTitle.setFont(new Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setForeground(new Color(245, 71, 71));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText("Message Title");

        txt.setEditable(false);
        txt.setFont(new Font("sansserif", 0, 14)); // NOI18N
        txt.setForeground(new Color(76, 76, 76));
        txt.setText("Message Text\nSimple");
        txt.setFocusable(false);

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt)
                        .addGroup(background1Layout.createSequentialGroup()
                            .addComponent(cmdobt1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE).addGap(3, 3, 3)
                            .addComponent(cmdobt2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                        .addGroup(background1Layout.createSequentialGroup()
                            .addComponent(cmdobt3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE).addGap(3, 3, 3)
                            .addComponent(cmdobt4, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                    .addGap(3, 3, 3))
        );

        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                    .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(lbTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdobt1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdobt2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdobt3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdobt4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3))
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
    
    class Background extends JPanel {

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
    
    class Glass extends JComponent {

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
            System.out.println("width "+ getWidth());
            System.out.println("height "+ getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    public class ButtonCustom extends JButton {
    	private boolean isCorrect = false;
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
                        MessageDialog obj = new MessageDialog();
                        obj.showMessage("correct", "stay in your position");
                        closeMessage();

                    }
                });
                timer.setRepeats(false);
                timer.start();
               // JOptionPane.showMessageDialog(null, "Your answer is correct", "Feedback", JOptionPane.INFORMATION_MESSAGE);
               // closeMessage();
            } else {
                setBackground(Color.RED);
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	MessageDialog obj = new MessageDialog();
                        obj.showMessage("wrong", "take one step back");
                        closeMessage();

                    }
                });
                timer.setRepeats(false);
                timer.start();
               // JOptionPane.showMessageDialog(null, "Your answer is wrong", "Feedback", JOptionPane.ERROR_MESSAGE);
             //   closeMessage();
            }
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
        }
        else {
        	isCorrect=false;
        }
        }

        public boolean isAnswerChosen() {
            return answerChosen;
        }
    }
}
