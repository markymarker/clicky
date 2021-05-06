package coffee.virus.clicky.ui.spicy.elements;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Digit implements Effect {

	private final DrawCanvas canvas;

	private int digit;
	private int targetDigit;
	private int changeFramesElapsed = 0;

	private Font digitFont;


	/**
	 * Have the production line pump out a shiny new digit display.
	 *
	 * @param d The initial value (0-10) to show
	 * @param size The desired size of the component
	 * @param font The font to use in drawing the digit
	 */
	public Digit(int d, Dimension size, Font font){
		canvas = new DrawCanvas(size);
		digit = targetDigit = dontTrustAnyone(d);
		digitFont = font;
	}


	/**
	 * Get the visual element of this digit.
	 * You can then add it to all the rest of the crap in your pile of junk you
	 * call a GUI.
	 *
	 * @return A lovely thing-a-majig
	 */
	public Component getComponent(){
		return canvas;
	}

	/**
	 * Change the value displayed by this digit.
	 * Valid values are 0-9. For any value outside this range, only the digit
	 * from the ones place will be used.
	 *
	 * @param d The 0-10 digit to change this display to
	 */
	public void changeTo(int d){
		targetDigit = dontTrustAnyone(d);
	}

	/**
	 * Update the display.
	 * We'll pass along your message.
	 */
	public void repaint(){
		canvas.repaint();
	}

	// TODO
	// Not sure if Effect is appropriate for this, but going with it for
	// testing and getting this off the ground
	public boolean tick(long elapsed){ return true; }
	public void draw(Graphics2D g){
		if(digit == targetDigit) return;

		// Magic number to say when the party's over
		if(++changeFramesElapsed >= 4){
			digit = targetDigit;
			changeFramesElapsed = 0;
		}

		canvas.repaint();
	}


// /////////////////// //
// NOTHING TO SEE HERE //

	/**
	 * Can't trust anyone.
	 * Stupid users, they're all out to get me. Can't trust their input, can't
	 * trust their requests, can't trust their faces. Can I even trust myself?
	 *
	 * @param d I'm on to you, you snake
	 */
	private int dontTrustAnyone(int d){
		return Math.abs(d) % 10;
	}


// ////////////// //
// CANVAS ELEMENT //

	private final class DrawCanvas extends Canvas {

		private Dimension size;


		public DrawCanvas(Dimension size){
			super();
			this.size = size;
			setPreferredSize(size);
		}

		public void paint(Graphics g){
			g.setColor(Color.GREEN);
			g.setFont(digitFont);

			if(digit != targetDigit){
				// TODO: Cool static-y stuff and random flipping between digits

				g.setColor(Color.RED);
				g.drawString("" + digit, 0, size.height - 2);
				g.setColor(Color.CYAN);
				g.drawString("" + targetDigit, 0, size.height - 2);
			} else {
				// TODO: Just draw the digit

				g.drawString("" + digit, 0, size.height - 2);
			}

			// Test stuff
			g.setColor(Color.BLUE);
			g.drawRect(0, 0, size.width - 1, size.height - 1);
		}
	}

}

