package View;

import java.awt.AlphaComposite;
import java.awt.Color;
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
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.questionPopUpController;


public class MessageDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    public boolean correct;
	questionPopUpController controller= questionPopUpController.getInstance();
	
	AudioTest audio=new AudioTest();
	

    public MessageDialog(JFrame gbv) {
    	
        super(gbv, true);
        this.fram = new JFrame();
        initComponents();
        init();
        
    }

    


    
    private void init()  {
    	
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	//start

        background1 = new Background();
        cmdOK = new ButtonCustom();
        lbIcon = new JLabel();
        lbTitle = new JLabel();
        txt = new JTextPane();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        

        cmdOK.setText("OK");
      //  System.out.println("closing is here");
        cmdOK.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdOK.setPreferredSize(new Dimension(100, 50)); 
        cmdOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
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
                cmdOKActionPerformed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(SwingConstants.CENTER);
        if(controller.isCorrectAns==false) {
        lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/wrong.png"))); // NOI18N
        lbTitle.setForeground(new Color(245, 71, 71));
        correct=false;
        }
        else if(controller.isCorrectAns==true) {
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/img/correct.png"))); // NOI18N
            lbTitle.setForeground(new Color(0, 128, 0));
            correct=true;


        }


        lbTitle.setFont(new Font("sansserif", 3, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText("Message Title");

        txt.setEditable(false);
        txt.setFont(new Font("sansserif", 0, 20)); // NOI18N
        txt.setForeground(new Color(255, 255, 255));
        txt.setText("Message Text\nSimple");
        txt.setFocusable(false);

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background1Layout.createSequentialGroup()
                    .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt)
                .addGroup(background1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE) // Set the preferred size of the button
                    .addGap(0, 0, Short.MAX_VALUE)
                )
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
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE) // Set the preferred size of the button
                )
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

   
    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
       
        closeMessage();
    }//GEN-LAST:event_cmdOKActionPerformed

    public static enum MessageType {
        CANCEL, OK
    }
    
    class Background extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Background() {
            init();
        }

        private void init() {
            setOpaque(false);
            setBackground(new Color(0, 0, 0));
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
    
    class ButtonCustom extends JButton {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Background background1;
    private	ButtonCustom cmdOK;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}