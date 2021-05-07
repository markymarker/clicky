package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;

import coffee.virus.clicky.interfaces.Effect;

import coffee.virus.clicky.ui.spicy.elements.Digit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * The area that displays stats, a "display area", if you will.
 * This contains elements and logic for showing and updating the part of the ui
 * that helps the user tell if they're a champion or hot garbage.
 */
class DisplayArea implements Effect {

	// The best value for this number is probably 1-2 frames longer than the
	// magic number in the Digit class' change frame counter
	private static final int DEBOUNCE_TIME = 6;

	private static final int DIGITS_SCORE = 16;
	private static final int DIGITS_ASSISTS = 16;
	private static final int DIGITS_ITEMS = 6;


	private SpicyUI ui;

	private JPanel container;
	private JTextArea display;
	private Digit scoreDigits[] = new Digit[DIGITS_SCORE];

	private long displayedScore = 0;
	private int scoreDebounce = 0;


	/**
	 * Create a new one of those areas of display.
	 *
	 * @param ui The higher-up ui in charge of this display, and make it spicy
	 */
	public DisplayArea(SpicyUI ui){
		this.ui = ui;
	}


	/**
	 * Initialize, main screen turn on.
	 * Does the setup for the interface elements and the registering of things
	 * as appropriate. The caller should be calling this on the event thread.
	 *
	 * @return The main container to shove in the user's face
	 */
	public JPanel init(){
		// The top-level top-dawg stuff holder for this element
		container = new JPanel();
		container.setBackground(new Color(30, 30, 30));

		// Textarea-based stats display
		display = new JTextArea(4, 12);
		display.setEnabled(false);
		display.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		display.setBackground(new Color(32, 32, 42));
		//display.setForeground(new Color(180, 180, 220));
		container.add(display);

		// Fancy pants Digit-based score display
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		Dimension digitSize = new Dimension(16, 22);
		Font digitFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		for(int j = DIGITS_SCORE - 1; j >= 0; --j){
			Digit d = new Digit(0, digitSize, digitFont);
			scoreDigits[j] = d;
			scorePanel.add(d.getComponent());
			ui.addEffect(d);
		}
		container.add(scorePanel);

		// The token waste-of-space
		// Hopefully temporary -- 10k assists pushes a digit off screen
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(50, 10));
		spacer.setBackground(new Color(30, 30, 30));
		container.add(spacer);

		// See if we can set some values in right quick before we make a fool
		// of ourself on our big debut
		update();

		// Get added to the animation loop
		ui.addEffect(this);

		return container;
	}

	/**
	 * Freshen up your look.
	 * Reads the current state of the ui's scorecard, and updates what is being
	 * shown to the user to match. We keep no secrets here, dear user.
	 */
	public void update(){
		if(ui.scorecard == null) return;

		long points = ui.scorecard.clicks + ui.scorecard.assists;

		// With this way this debounce works, there is a slight but noticeable
		// delay after an event before the score updates. I kind of like it
		// though, goes with the glitchy look of the digits.
		//
		// If it turns out something more responsive is desired, a possible
		// option would be a split debounce, where this sets it to a low number
		// such as 2-3, then when the tick method detects it expire, it sets it
		// to a number such as 5-7 in addition to updating the display.
		if(scoreDebounce == 0 && displayedScore != points){
			scoreDebounce = DEBOUNCE_TIME;
		}

		display.setText("\n" +
			"Points: " + points + "\n" +
			"Assists: " + ui.scorecard.assists + ", Ticks: " + ui.scorecard.ticks + "\n" +
			"Items: " + ui.scorecard.getItemCount() + "\n"
		);
	}

	/**
	 * Set the digit elements for the score to the right numbers.
	 * Nothing too fancy, give it the current score and it'll go to town.
	 *
	 * @param score The score to set on the digits
	 */
	private void setScoreDigits(long score){
		if(displayedScore == score) return;
		displayedScore = score;

		for(int place = 0; place < DIGITS_SCORE; ++place){
			scoreDigits[place].changeTo((int)(score % 10));
			score /= 10;
		}
	}

	/**
	 * @see Effect
	 *
	 * TODO
	 * As with Digit, not sure if Effect is totally appropriate, but rolling
	 * with it for now
	 */
	@Override
	public boolean tick(long elapsed){
		// Decrement debounce if active, checking if it crosses from 1 to 0
		if(scoreDebounce > 0 && --scoreDebounce == 0){
			setScoreDigits(ui.scorecard.clicks + ui.scorecard.assists);
		}
		return true;
	}
	@Override
	public void draw(Graphics2D g){}

}

