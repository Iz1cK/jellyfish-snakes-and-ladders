package chatroom;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chat_client extends JFrame {

    private JPanel contentPane;
    private JTextField msg_text;
    private static JTextArea msg_area;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    chat_client frame = new chat_client();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(() -> {
            try {
                s = new Socket("127.0.0.1", 1201);
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                String msgin = "";
                while (!msgin.equals("exit")) {
                    msgin = din.readUTF();
                    msg_area.setText(msg_area.getText().trim() + "\n player 1 :   " + msgin);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Create the frame.
     */
    public chat_client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 353);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        msg_text = new JTextField();
        msg_text.setBounds(76, 248, 189, 55);
        contentPane.add(msg_text);
        msg_text.setColumns(10);

        msg_area = new JTextArea();
        msg_area.setBounds(76, 21, 365, 196);
        contentPane.add(msg_area);

        JButton msg_send = new JButton("Send");
        msg_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String msgout = "";
                    msgout = msg_text.getText().trim();
                    dout.writeUTF(msgout);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }

            }
        });
        msg_send.setBounds(331, 264, 89, 23);
        contentPane.add(msg_send);
    }
}
