package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;

import coffee.virus.clicky.ui.spicy.elements.Digit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;


class DisplayArea {

	private static final int DIGITS_SCORE = 16;
	private static final int DIGITS_ASSISTS = 16;
	private static final int DIGITS_ITEMS = 6;


	private SpicyUI ui;

	private JPanel container;
	private JTextArea display;
	private Digit scoreDigits[] = new Digit[DIGITS_SCORE];

	private long displayedScore = 0;


	public DisplayArea(SpicyUI ui){
		this.ui = ui;
	}


	public JPanel init(){
		container = new JPanel();
		container.setBackground(new Color(30, 30, 30));

		display = new JTextArea(4, 12);
		display.setEnabled(false);
		display.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		display.setBackground(new Color(32, 32, 42));
		//display.setForeground(new Color(180, 180, 220));
		container.add(display);

		Dimension digitSize = new Dimension(15, 25);
		Font digitFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		for(int j = DIGITS_SCORE - 1; j >= 0; --j){
			Digit d = new Digit(0, digitSize, digitFont);
			scoreDigits[j] = d;
			container.add(d.getComponent());
			ui.addEffect(d);
		}

		update();
		return container;
	}

	public void update(){
		if(ui.scorecard == null) return;

		long points = ui.scorecard.clicks + ui.scorecard.assists;
		setScoreDigits(points);

		display.setText("\n" +
			"Points: " + points + "\n" +
			"Assists: " + ui.scorecard.assists + ", Ticks: " + ui.scorecard.ticks + "\n" +
			"Items: " + ui.scorecard.getItemCount() + "\n"
		);
	}

	// TODO: This could probably do with some debounce
	private void setScoreDigits(long score){
		if(displayedScore == score) return;
		displayedScore = score;

		for(int place = 0; place < DIGITS_SCORE; ++place){
			scoreDigits[place].changeTo((int)(score % 10));
			score /= 10;
		}
	}

}

