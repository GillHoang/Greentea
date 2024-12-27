package flipAndFind;

import javax.swing.JFrame;

public class FAF extends JFrame {
	private static final long serialVersionUID = 1L;
	FAFPanel game = new FAFPanel();

	public FAF() {
		super("Tro choi lat hinh");

		this.add(game);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FAF();
	}
}
