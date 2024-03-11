package View;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import Controller.AdminAuthentication;

public class AdminView extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = -5389683577053397511L;
	private JPanel contentPane;
	private PasswordField txtPassword;
	private TextField txtUsername;
	private JCheckBox showPassword;
	private Button login;
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
	public ImageIcon resized(Image image, int weight, int height) {
		 Image backImage = image;
	        Image resized = backImage.getScaledInstance(weight, height, Image.SCALE_SMOOTH);
	        ImageIcon resizeds = new ImageIcon(resized);
		
		return resizeds;
		
	}

	/**
	 * Create the frame.
	 */
	public AdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 760);
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
		
		txtUsername = new TextField();
		txtUsername.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtUsername.setHint("USERNAME");
		txtUsername.setBounds(630, 294, 375, 42);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		/*TextField txtPassword = new TextField();
		contentPane.add(txtPassword);
		txtPassword.setBounds(304,378,190,30);*/
		
		txtPassword = new PasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setHint("PASSWORD");
		txtPassword.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		//txtPassword.setText("Password");
		txtPassword.setBounds(630, 437, 200, 60);
		contentPane.add(txtPassword);
		
		showPassword = new JCheckBox("show password ");
		showPassword.setForeground(Color.WHITE);
		showPassword.addActionListener(this);
		showPassword.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		showPassword.setBounds(650, 529, 221, 23);
		contentPane.add(showPassword);
		  // Set transparent background
        showPassword.setOpaque(false);
        showPassword.setBackground(new Color(0, 0, 0, 0)); // Transparent black

        // Add JCheckBox to contentPane
        contentPane.add(showPassword);
		
      
		 login= new Button();
		login.setText("LOGIN");
		login.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		login.setForeground(Color.black);
		contentPane.add(login);
		login.setBounds(550,580,400,70);
		login.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
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
		
		JLabel loginForm = new JLabel("LOGIN");
		loginForm.setForeground(Color.WHITE);
		loginForm.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
		loginForm.setBounds(656, 92, 235, 100);
		contentPane.add(loginForm);
		
		
		JLabel homePage = new JLabel();
		//	homePage.setIcon(new ImageIcon(ChooseGame1.class.getResource("/img/rectangle.png")));		
			homePage.setBounds(40, 47, 90, 90);
			contentPane.add(homePage);
			homePage.setLayout(null);
			
			ImageIcon ImageIconHome = new ImageIcon(ChooseGame1.class.getResource("/img/backIcon.png"));
			ImageIcon testHome= resized(ImageIconHome.getImage(), 90, 90);
			homePage.setIcon(testHome);
			
			homePage.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Open HomeView and close the current view
	                HomeView homeView = new HomeView();
	                homeView.setVisible(true);
	                dispose();
	            }
	        });
		
		

		/*
		 * background icon	
		 */
		 JLabel backgrounde = new JLabel("");
	        ImageIcon backgroundImageIcon = new ImageIcon(AdminView.class.getResource("/img/loginBackground.png"));

	        // Resize the background image to fit the frame
	        Image backgroundImage = backgroundImageIcon.getImage();
	        Image resizedBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon resizedBackgroundIcon = new ImageIcon(resizedBackgroundImage);

	        backgrounde.setIcon(resizedBackgroundIcon);

	        // Set size to match content pane
	        backgrounde.setBounds(0, 0, getWidth(), getHeight());

	        contentPane.add(backgrounde);	
	             
		
		
		
		  
		 
		
	}

@SuppressWarnings("serial")
public class TextField extends JTextField {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }

    private String hint = "";
    private final Animator animator;
    private float animate;
    private boolean show = true;

    public TextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.white);
        setForeground(Color.white);
        setSelectionColor(Color.WHITE);
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                repaint();
            }

            @Override
            public void end() {
                show = !show;
                repaint();
            }

        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!getText().equals("")) {
                    if (show) {
                        if (animator.isRunning() == false) {
                            stop();
                            animator.start();
                        }
                    } else if (animator.isRunning()) {
                        stop();
                        animator.start();
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getText().equals("")) {
                    stop();
                    animator.start();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }

    private void stop() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 0));
        g2.drawLine(0, getHeight() - 3, getWidth(), getHeight() - 3);
        if (!hint.equals("")) {
            int h = getHeight();
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g2.setColor(new Color(0, 0, 0));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - animate));
            g2.drawString(hint, ins.left + (animate * 30), h / 2 + fm.getAscent() / 2 - 1);
        }
        g2.dispose();
        super.paint(g);
    }
}

	@SuppressWarnings("serial")
	public class PasswordField extends JPasswordField {

	    public String getHint() {
	        return hint;
	    }

	    public void setHint(String hint) {
	        this.hint = hint;
	        repaint();
	    }

	    private String hint = "";
	    private final Animator animator;
	    private float animate;
	    private boolean show = true;

	    public PasswordField() {
	        setOpaque(false);
	        setBorder(new EmptyBorder(9, 1, 9, 1));
	        setBackground(Color.WHITE);
	        setForeground(Color.WHITE);
	        setSelectionColor(Color.WHITE);
	        animator = new Animator(350, new TimingTargetAdapter() {
	            @Override
	            public void timingEvent(float fraction) {
	                if (show) {
	                    animate = fraction;
	                } else {
	                    animate = 1f - fraction;
	                }
	                repaint();
	            }

	            @Override
	            public void end() {
	                show = !show;
	                repaint();
	            }

	        });
	        animator.setResolution(0);
	        animator.setAcceleration(.5f);
	        animator.setDeceleration(.5f);
	        getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                if (getPassword().length != 0) {
	                    if (show) {
	                        if (animator.isRunning() == false) {
	                            stop();
	                            animator.start();
	                        }
	                    } else if (animator.isRunning()) {
	                        stop();
	                        animator.start();
	                    }
	                }
	            }

	            @SuppressWarnings("deprecation")
				@Override
	            public void removeUpdate(DocumentEvent e) {
	                if (getText().equals("")) {
	                    stop();
	                    animator.start();
	                }
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {

	            }
	        });

	    }

	    private void stop() {
	        if (animator.isRunning()) {
	            float f = animator.getTimingFraction();
	            animator.stop();
	            animator.setStartFraction(1f - f);
	        } else {
	            animator.setStartFraction(0f);
	        }
	    }

	    @Override
	    public void paint(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g2.setColor(new Color(0, 0, 0, 0));
	        g2.drawLine(0, getHeight() - 3, getWidth(), getHeight() - 3);
	        if (!hint.equals("")) {
	            int h = getHeight();
	            Insets ins = getInsets();
	            FontMetrics fm = g.getFontMetrics();
	            g2.setColor(new Color(0, 0, 0));
	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - animate));
	            g2.drawString(hint, ins.left + (animate * 30), h / 2 + fm.getAscent() / 2 - 1);
	        }
	        g2.dispose();
	        super.paint(g);
	    }
	}
	
	@SuppressWarnings("serial")
	public class Button extends JButton {

	    private Shape shape;
	    private final RippleEffect rippleEffect;

	    public Button() {
	        rippleEffect = new RippleEffect(this);
	        setContentAreaFilled(false);
	        setBorder(new EmptyBorder(8, 5, 8, 5));
	        setCursor(new Cursor(Cursor.HAND_CURSOR));
	    }

	    @Override
	    protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(new Color(255, 255, 255, 80));
	        g2.fill(shape);
	        rippleEffect.reder(g2, shape);
	        g2.dispose();
	        super.paintComponent(grphcs);
	    }

	    @Override
	    public void setBounds(int i, int i1, int i2, int i3) {
	        super.setBounds(i, i1, i2, i3);
	        shape = new FancyBorderRadius(getWidth(), getHeight(), "15% 85% 63% 37% / 38% 29% 71% 62%").getShape();
	    }

	}
	public class FancyBorderRadius {

	    public double getWidth() {
	        return width;
	    }

	    public void setWidth(double width) {
	        this.width = width;
	    }

	    public double getHeight() {
	        return height;
	    }

	    public void setHeight(double height) {
	        this.height = height;
	    }

	    public Border getBorder() {
	        return border;
	    }

	    public void setBorder(Border border) {
	        this.border = border;
	    }

	    public FancyBorderRadius(double width, double height, Border border) {
	        this.width = width;
	        this.height = height;
	        this.border = new Border();
	        this.border = border;
	    }

	    public FancyBorderRadius(double width, double height, String border) {
	        this(width, height, new Border(border));
	    }

	    public FancyBorderRadius() {
	    }

	    private double width;
	    private double height;
	    private Border border;

	    public Shape getShape() {
	        Area area = new Area(new Rectangle2D.Double(0, 0, width, height));
	        area.intersect(new Area(create1()));
	        area.intersect(new Area(create2()));
	        area.intersect(new Area(create3()));
	        area.intersect(new Area(create4()));
	        return area;
	    }

	    private Shape create1() {
	        double w = border.getTop().getRight() * this.width;
	        double h = border.getRight().getRight() * this.height;
	        Path2D path = new Path2D.Double();
	        path.moveTo(width - w, 0);
	        path.lineTo(0, 0);
	        path.lineTo(0, height);
	        path.lineTo(width, height);
	        path.lineTo(width, h);
	        Area area = new Area(path);
	        area.add(new Area(new Ellipse2D.Double(width - w * 2, 0, w * 2, h * 2)));
	        return area;
	    }

	    private Shape create2() {
	        double w = border.getTop().getLeft() * this.width;
	        double h = border.getLeft().getRight() * this.height;
	        Path2D path = new Path2D.Double();
	        path.moveTo(0, h);
	        path.lineTo(0, height);
	        path.lineTo(width, height);
	        path.lineTo(width, 0);
	        path.lineTo(w, 0);
	        Area area = new Area(path);
	        area.add(new Area(new Ellipse2D.Double(0, 0, w * 2, h * 2)));
	        return area;
	    }

	    private Shape create3() {
	        double w = border.getBottom().getLeft() * this.width;
	        double h = border.getLeft().getLeft() * this.height;
	        Path2D path = new Path2D.Double();
	        path.moveTo(w, height);
	        path.lineTo(width, height);
	        path.lineTo(width, 0);
	        path.lineTo(0, 0);
	        path.lineTo(0, height - h);
	        Area area = new Area(path);
	        area.add(new Area(new Ellipse2D.Double(0, height - h * 2, w * 2, h * 2)));
	        return area;
	    }

	    private Shape create4() {
	        double w = border.getBottom().getRight() * this.width;
	        double h = border.getRight().getLeft() * this.height;
	        Path2D path = new Path2D.Double();
	        path.moveTo(width, height - h);
	        path.lineTo(width, 0);
	        path.lineTo(0, 0);
	        path.lineTo(0, height);
	        path.lineTo(width - w, height);
	        Area area = new Area(path);
	        area.add(new Area(new Ellipse2D.Double(width - w * 2, height - h * 2, w * 2, h * 2)));
	        return area;
	    }

	    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
	        border.setBorder(tl, tr, br, bl, lr, rr, rl, ll);
	    }

	    public void setBorder(String text) {
	        border.setBorder(text);
	    }
	}
	public class Border {

	    public Point getTop() {
	        return top;
	    }

	    public void setTop(Point top) {
	        this.top = top;
	    }

	    public Point getBottom() {
	        return bottom;
	    }

	    public void setBottom(Point bottom) {
	        this.bottom = bottom;
	    }

	    public Point getLeft() {
	        return left;
	    }

	    public void setLeft(Point left) {
	        this.left = left;
	    }

	    public Point getRight() {
	        return right;
	    }

	    public void setRight(Point right) {
	        this.right = right;
	    }

	    public Border(String border) {
	        this();
	        init(border);
	    }

	    public Border() {
	        top = new Point();
	        bottom = new Point();
	        left = new Point();
	        right = new Point();
	    }

	    private void init(String border) {
	        setBorder(border);
	    }

	    private Point top;
	    private Point bottom;
	    private Point left;
	    private Point right;

	    @Override
	    public String toString() {
	        return convert(top.left) + " " + convert(top.right) + " " + convert(bottom.right) + " " + convert(bottom.left) + " / " + convert(left.right) + " " + convert(right.right) + " " + convert(right.left) + " " + convert(left.left);
	    }

	    public float[] toArray() {
	        return new float[]{top.left, top.right, bottom.right, bottom.left, left.right, right.right, right.left, left.left};
	    }

	    private String convert(float f) {
	        return (Math.round(f * 100 * 100) / 100) + "%";
	    }

	    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
	        top.setLeft(tl);
	        top.setRight(tr);
	        bottom.setRight(br);
	        bottom.setLeft(bl);
	        left.setRight(lr);
	        left.setLeft(ll);
	        right.setRight(rr);
	        right.setLeft(rl);
	    }

	    public void setBorder(String text) {
	        text = text.replace("%", "");
	        String arr[] = text.split("/");
	        if (arr.length == 2) {
	            String s1[] = arr[0].trim().split(" ");
	            String s2[] = arr[1].trim().split(" ");
	            setBorder(percent(s1[0]), percent(s1[1]), percent(s1[2]), percent(s1[3]), percent(s2[0]), percent(s2[1]), percent(s2[2]), percent(s2[3]));
	        } else {
	            String s1[] = arr[0].trim().split(" ");
	            float v1 = percent(s1[0]);
	            float v2 = percent(s1[1]);
	            top.setLeft(v1);
	            top.setRight(v2);
	            right.setLeft(v1);
	            right.setRight(v2);
	            bottom.setRight(v1);
	            bottom.setLeft(v2);
	            left.setRight(v1);
	            left.setLeft(v2);
	        }
	    }

	    private float percent(String f) {
	        return Float.parseFloat(f) / 100f;
	    }

	    protected class Point {

	        public float getLeft() {
	            return left;
	        }

	        public void setLeft(float left) {
	            this.left = left;
	        }

	        public float getRight() {
	            return right;
	        }

	        public void setRight(float right) {
	            this.right = right;
	        }

	        public Point(float left, float right) {
	            this.left = left;
	            this.right = right;
	        }

	        public Point() {
	        }

	        private float left;
	        private float right;
	    }
	}

	
	public class RippleEffect {

	    private final Component component;
	    private Color rippleColor = new Color(255, 255, 255);
	    private List<Effect> effects;

	    public RippleEffect(Component component) {
	        this.component = component;
	        init();
	    }

	    private void init() {
	        effects = new ArrayList<>();
	        component.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (component.isEnabled()) {
	                    if (SwingUtilities.isLeftMouseButton(e)) {
	                        addEffect(e.getPoint());
	                    }
	                }
	            }
	        });
	    }

	    public void addEffect(Point location) {
	        effects.add(new Effect(component, location));
	    }

	    public void reder(Graphics g, Shape contain) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        for (int i = 0; i < effects.size(); i++) {
	            Effect effect = effects.get(i);
	            if (effect != null) {
	                effect.render(g2, contain);
	            }
	        }
	        g2.dispose();
	    }

	    private class Effect {

	        private final Component component;
	        private final Point location;
	        private Animator animator;
	        private float animate;

	        public Effect(Component component, Point location) {
	            this.component = component;
	            this.location = location;
	            init();
	        }

	        private void init() {
	            animator = new Animator(500, new TimingTargetAdapter() {
	                @Override
	                public void timingEvent(float fraction) {
	                    animate = fraction;
	                    component.repaint();
	                }

	                @Override
	                public void end() {
	                    effects.remove(Effect.this);
	                }
	            });
	            animator.setResolution(5);
	            // animator.setDeceleration(.5f);
	            animator.start();
	        }

	        public void render(Graphics2D g2, Shape contain) {
	            Area area = new Area(contain);
	            area.intersect(new Area(getShape(getSize(contain.getBounds2D()))));
	            g2.setColor(rippleColor);
	            float alpha = 0.3f;
	            if (animate >= 0.7f) {
	                double t = animate - 0.7f;
	                alpha = (float) (alpha - (alpha * (t / 0.3f)));
	            }
	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	            g2.fill(area);
	        }

	        private Shape getShape(double size) {
	            double s = size * animate;
	            double x = location.getX();
	            double y = location.getY();
	            Shape shape = new Ellipse2D.Double(x - s, y - s, s * 2, s * 2);
	            return shape;
	        }

	        private double getSize(Rectangle2D rec) {
	            double size;
	            if (rec.getWidth() > rec.getHeight()) {
	                if (location.getX() < rec.getWidth() / 2) {
	                    size = rec.getWidth() - location.getX();
	                } else {
	                    size = location.getX();
	                }
	            } else {
	                if (location.getY() < rec.getHeight() / 2) {
	                    size = rec.getHeight() - location.getY();
	                } else {
	                    size = location.getY();
	                }
	            }
	            return size + (size * 0.1f);
	        }
	    }

	    public void setRippleColor(Color rippleColor) {
	        this.rippleColor = rippleColor;
	    }

	    public Color getRippleColor() {
	        return rippleColor;
	    }
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
