package flipAndFind;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class FAFPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	String gameMode = "Easy";
	String allGameMode[] = { "Easy", "Medium", "Hard" };
	ArrayList<JButton> cards;
	Timer timer1;
	Timer timer3;
	JTextField time;
	JTextField attempts;
	JTextField pairsLeft;
	JTextField point;
	JButton selected;
	int[][] amountImagePerLevel = { { 3, 4 } };
	int[][] result;
	int x;
	int y;
	int attemptsLeft;
	int numPairsLeft;
	int numPoint = 1000;
	int timeLeft = 60;

	public FAFPanel() {
		super(null);

		int getIOfGameMode = indexOf(gameMode, allGameMode);
		int[] amountOfLevel = amountImagePerLevel[getIOfGameMode];
		this.x = amountOfLevel[0];
		this.y = amountOfLevel[1];

		attemptsLeft = x * y + 10;
		numPairsLeft = (x * y) / 2;
		result = generateRandom2DArray();
		time = createField(38, 30, 250, 90, "" + timeLeft);
		attempts = createField(317, 30, 370, 90, "Attempts: " + attemptsLeft);
		pairsLeft = createField(691, 30, 370, 90, "Pairs left: " + numPairsLeft);
		point = createField(1090, 30, 250, 90, "Point: " + numPoint);

		cards = createCard();
		for (JButton item : cards) {
			this.add(item);
		}

		timer1 = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					selected.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
					selected = null;
				}
				for (JButton btn : cards) {
					if (btn.getIcon() != null && !btn.getIcon().toString().contains("question")) {
						btn.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
					}
				}
				timer1.stop();
			}
		});

		timer3 = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLeft--;
				if (timeLeft >= 0) {
					time.setText("Time Left: " + timeLeft);
				} else {
					for (JButton card : cards) {
						card.setEnabled(false);
					}
					timer3.stop();
					time.setText("Ready");
				}
			}
		});
		timer3.start();
		this.add(time);
		this.add(attempts);
		this.add(pairsLeft);
		this.add(point);

	}

	public static <T> int indexOf(T needle, T[] haystack) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i] != null && haystack[i].equals(needle) || needle == null && haystack[i] == null)
				return i;
		}

		return -1;
	}

	public JTextField createField(int x, int y, int w, int h, String text) {
		Font font = new Font("Tahoma", Font.BOLD, 35);
		JTextField jf = new JTextField(text);
		jf.setBounds(x, y, w, h);
		jf.setHorizontalAlignment(JTextField.CENTER);
		jf.setFocusable(false);
		jf.setFont(font);
		return jf;
	}

	public ArrayList<JButton> createCard() {
		ArrayList<JButton> cards = new ArrayList<JButton>();
		for (int i = 0; i < this.x * this.y; i++) {
			int x = i / this.y;
			int y = i % this.y;
			String k = String.valueOf(result[x][y]);
			JButton card = new JButton(k);

			// Refactor:
			card.setBounds(231 + y * (40) + 200 * y, 191 + x * 43 + 150 * x, 200, 150);
			card.addActionListener(btnAction());
			card.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
			cards.add(card);
		}
		return cards;
	}

	public ActionListener btnAction() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer1.isRunning())
					return;
				JButton current = (JButton) e.getSource();
				if (!current.getIcon().toString().contains("question"))
					return;
				if (attemptsLeft == 0) {
					selected = null;
					for (JButton card : cards) {
						card.setEnabled(false);
					}
					attempts.setText("May thua roaiiiiii");
					return;
				}
				if (selected == null) {
					selected = current;
					current.setIcon(
							new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
				} else {
					attemptsLeft -= 1;
					attempts.setText("Attempts: " + attemptsLeft);
					if (selected.getText().trim().equalsIgnoreCase(current.getText().trim())) {
						numPoint += 40;
						numPairsLeft -= 1;
						pairsLeft.setText("Pairs left: " + numPairsLeft);
						point.setText("Point: " + numPoint);

						current.setIcon(
								new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
						current.setEnabled(false);
						selected.setEnabled(false);
						selected = null;
						if (numPairsLeft == 0) {
							pairsLeft.setText("May thang roaiiiiii");
						}
					} else {
						numPoint -= 50;
						point.setText("Point: " + numPoint);
						current.setIcon(
								new ImageIcon(getClass().getResource("/Images/" + current.getText().trim() + ".png")));
						timer1.start();
					}
				}
			}
		};
	}

	public int[][] generateRandom2DArray() {
		// Step 1: Create a list of numbers 1 to 6, each appearing twice
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= (this.x * this.y) / 2; i++) {
			numbers.add(i);
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		int[][] result = new int[this.x][this.y];
		int index = 0;
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				result[i][j] = numbers.get(index++);
			}
		}

		return result;
	}
}
