package View;

import java.awt.EventQueue;

import Model.Sysdata;


public class Start {

	public static void main(String[] args) {
		
		Sysdata sysdata= Sysdata.getInstance();
		sysdata.readQuestions();
		sysdata.readAdmins();
		sysdata.readGameHistory();
		System.out.println(sysdata.questionsList.toString());
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
