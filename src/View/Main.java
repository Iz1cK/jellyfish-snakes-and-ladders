package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JLabel diceImage = new JLabel(new ImageIcon(Main.class.getResource("/img/dice0.jpeg")));
        diceImage.setPreferredSize(new Dimension(200, 200));
        contentPane.add(diceImage, BorderLayout.CENTER);

        diceImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rollDice(diceImage);
            }
        });

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void rollDice(JLabel diceImage) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Running");
                Random random = new Random();
                try {
                    for (int i = 0; i < 15; i++) {
                        int diceNumber = random.nextInt(12) ; // Generate random number from 1 to 6
                        String imagePath = "/img/dice" + diceNumber + ".jpeg";
                        ImageIcon icon = new ImageIcon(Main.class.getResource(imagePath));
                        diceImage.setIcon(icon);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
