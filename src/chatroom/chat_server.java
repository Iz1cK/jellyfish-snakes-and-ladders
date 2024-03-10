package chatroom;

import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.event.ActionEvent;

public class chat_server extends JFrame {

    private JPanel contentPane;
    private JTextField msg_text;
    private static JTextArea msg_area;
    static ServerSocket ss;
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
                    chat_server frame = new chat_server();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(() -> {
            String msgin = "";
            try {
                ss = new ServerSocket(1201);
                s = ss.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while (!msgin.equals("exit")) {
                    msgin = din.readUTF();
                    msg_area.setText(msg_area.getText().trim() + "\n player 2 :" + msgin);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Create the frame.
     */
    public chat_server() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 568, 410);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        msg_area = new JTextArea();
        msg_area.setBounds(102, 26, 331, 212);
        contentPane.add(msg_area);

        msg_text = new JTextField();
        msg_text.setBounds(105, 272, 232, 49);
        contentPane.add(msg_text);
        msg_text.setColumns(10);

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
        msg_send.setBounds(389, 285, 89, 23);
        contentPane.add(msg_send);
    }
}
