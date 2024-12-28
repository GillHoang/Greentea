package FAF_v2;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
	private static final long serialVersionUID = 1L;

	JTextField userField;
	JPasswordField passField;

	public Login(CardLayout cardLayout, JPanel cardPanel) {
		super(null);
		Font font = new Font("Tahoma", Font.BOLD, 30);

		JLabel labelUsername = new JLabel("Username: ");
		labelUsername.setBounds(605, 211, 183, 39);
		labelUsername.setFont(font);

		userField = new JTextField();
		userField.setBounds(510, 268, 361, 70);
		userField.setFont(font);

		JLabel labelPassword = new JLabel("Password: ");
		labelPassword.setBounds(605, 355, 172, 41);
		labelPassword.setFont(font);

		passField = new JPasswordField();
		passField.setBounds(510, 414, 361, 70);
		passField.setFont(font);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(510, 502, 361, 70);
		loginButton.setFont(font);

		this.add(loginButton);
		this.add(labelUsername);
		this.add(labelPassword);
		this.add(userField);
		this.add(passField);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = new String(passField.getPassword());

				if (username.equals("admin") && password.equals("password")) {
					cardLayout.show(cardPanel, "main");
				} else {
					JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
