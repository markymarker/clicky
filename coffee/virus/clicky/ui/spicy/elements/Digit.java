package coffee.virus.clicky.ui.spicy.elements;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * A swanky display digit.
 * Shows a single digit and provides some pizazz when the digit is to be
 * changed.
 */
public class Digit implements Effect {

	private final DrawCanvas canvas;

	private int digit;
	private int targetDigit;
	private int changeFramesElapsed = 0;

	private Font digitFont;


	/**
	 * Have the production line pump out a shiny new digit display.
	 * The reason for the size and font parameters is because digits are
	 * probably always going to be put in groups. By having the creator
	 * instantiate these and pass them to the individual digits, there is
	 * an easy guarantee of consistency and lower overhead in terms of the
	 * amount of crap that needs to get allocated and instantiated (assuming
	 * the creator isn't doing something ridiculous, of course).
	 *
	 * @param d The initial value (0-9) to show
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
	 * @param d The 0-9 digit to change this display to
	 */
	public void changeTo(int d){
		targetDigit = dontTrustAnyone(d);
	}

	/**
	 * Update the display.
	 * We'll pass along your request.
	 */
	public void repaint(){
		canvas.repaint();
	}

	/**
	 * @see Effect
	 *
	 * TODO
	 * Not sure if Effect is appropriate for this, but going with it for
	 * testing and getting this off the ground
	 */
	@Override
	public boolean tick(long elapsed){ return true; }
	@Override
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

	private static final Color SEMI_RED = new Color(255, 0, 0, 180);
	private static final Color SEMI_CYAN = new Color(0, 255, 255, 180);

	/**
	 * The element for bringing the digit to life.
	 * This class extends canvas to provide the actual drawing capability for
	 * a digit.
	 */
	private final class DrawCanvas extends Canvas {

		private final Dimension size;


		public DrawCanvas(Dimension size){
			super();
			this.size = size;
			setPreferredSize(size);
		}

		/**
		 * Paint the digit on the display.
		 * This gives the digit a little bit of padding inside the canvas.
		 * Hopefully you sized things right when setting up your digit!
		 */
		@Override
		public void paint(Graphics g){
			int vert = size.height - 2;

			g.setColor(Color.GREEN);
			g.setFont(digitFont);

			if(digit != targetDigit){
				// Cool static-y stuff and random flipping between digits
				// TODO: Even cooler?

				int r = (int)Math.floor(Math.random() * 3);
				if(r == 1){
					g.setColor(Color.WHITE);
					g.drawString("S", 1, vert);  // "S" for Static!
				} else {
					g.setColor(r == 0 ? SEMI_RED : SEMI_CYAN);
					g.drawString("" + digit, 1, vert);
					g.setColor(r == 0 ? SEMI_CYAN : SEMI_RED);
					g.drawString("" + targetDigit, 1, vert);
				}
			} else {
				// Just draw the digit

				g.drawString("" + digit, 1, vert);
			}
		}
	}

}

