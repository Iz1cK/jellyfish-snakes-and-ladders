package View;

import java.awt.EventQueue;

import Model.Sysdata;
 
public class Start {

	public static void main(String[] args) {
		
		Sysdata sysdata= Sysdata.getInstance();
		sysdata.readQuestions();
		sysdata.readAdmins();
		sysdata.readGameHistory();
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SplashScreen(null, true).setVisible(true);
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
