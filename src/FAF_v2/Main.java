package FAF_v2;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	JPanel cardPanel;
	CardLayout cardLayout;

	public Main() {
		super("Flip And Find");
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		cardPanel.add(new Login(cardLayout, cardPanel), "login");
		cardPanel.add(new Play(), "main");
		
		this.add(cardPanel);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
