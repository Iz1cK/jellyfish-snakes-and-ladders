package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ResumeGame extends javax.swing.JDialog {

    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private JButton homeIcon;
    private JButton backGame;
    private JButton continuePlay;
    private MessageType messageType = MessageType.CANCEL;

    public ResumeGame(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }

    public ImageIcon resized(Image image, int weight, int height) {
 		 Image backImage = image;
 	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
 	        ImageIcon resizeds = new ImageIcon(resized);
 		
 		return resizeds;
 		
 	}
    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        setBounds(300, 300, 500, 200);
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
      //  doc.setParagraphAttributes(0, doc.getLength(), center, false);
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
     //   txt.setText(message);
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
        lbTitle = new javax.swing.JLabel();
        homeIcon= new JButton();
        backGame= new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

        
    	ImageIcon ImageIconback = new ImageIcon(ChooseGame1.class.getResource("/img/resume.png"));
    	ImageIcon testBack= resized(ImageIconback.getImage(), 90, 90);
    	
    	backGame.setIcon(testBack);
    	backGame.setBounds(100,80,90,90);
    	backGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });
    	backGame.setOpaque(false); // Make button background transparent
    	backGame.setContentAreaFilled(false); // Make content area transparent
    	
    	// If you also want to remove the border:
    	backGame.setBorderPainted(false);
    	
    	background1.add(backGame);
    	
    	ImageIcon ImageIconHome = new ImageIcon(ChooseGame1.class.getResource("/img/home1.png"));
    	ImageIcon testHome= resized(ImageIconHome.getImage(), 90, 90);
    	
    	homeIcon.setIcon(testHome);
    	homeIcon.setBounds(200,80,90,90);
    	homeIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cmdCancelActionPerformed(evt);
            }
        });
    	homeIcon.setOpaque(false); // Make button background transparent
    	homeIcon.setContentAreaFilled(false); // Make content area transparent
    	
    	// If you also want to remove the border:
    	homeIcon.setBorderPainted(false);
    	
    	background1.add(homeIcon);

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(245, 71, 71));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Message Title");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1Layout.setHorizontalGroup(
        	background1Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lbTitle, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
        );
        background1Layout.setVerticalGroup(
        	background1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, background1Layout.createSequentialGroup()
        			.addGap(94)
        			.addComponent(lbTitle)
        			.addContainerGap(140, Short.MAX_VALUE))
        );
        background1.setLayout(background1Layout);

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

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }//GEN-LAST:event_cmdOKActionPerformed

    public static enum MessageType {
        CANCEL, OK
    }
    
    public class ButtonCustom extends JButton {

        private Color background = new Color(69, 191, 71);
        private Color colorHover = new Color(76, 206, 78);
        private Color colorPressed = new Color(63, 175, 65);
        private boolean mouseOver = false;

        public ButtonCustom() {
            init();
        }

        private void init() {
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBackground(background);
            setForeground(Color.WHITE);
            setOpaque(true);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseOver = true;
                    ButtonCustom.super.setBackground(getColorHover());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOver = false;
                    ButtonCustom.super.setBackground(background);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ButtonCustom.super.setBackground(getColorPressed());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (mouseOver) {
                        ButtonCustom.super.setBackground(getColorHover());
                    } else {
                        ButtonCustom.super.setBackground(background);
                    }
                }
            });
        }

        @Override
        public void setBackground(Color bg) {
            background = bg;
            super.setBackground(bg);
        }

        public Color getColorHover() {
            return colorHover;
        }

        public void setColorHover(Color colorHover) {
            this.colorHover = colorHover;
        }

        public Color getColorPressed() {
            return colorPressed;
        }

        public void setColorPressed(Color colorPressed) {
            this.colorPressed = colorPressed;
        }
    }
    public class Glass extends JComponent {

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Background background1;
    private javax.swing.JLabel lbTitle;
}
