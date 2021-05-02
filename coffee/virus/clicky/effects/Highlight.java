package coffee.virus.clicky.effects;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;


public class Highlight implements Effect {

	private static final int BASE_R = 245;
	private static final int BASE_G = 40;
	private static final int BASE_B = 10;
	private static final int MAX_ALPHA = 240;


	private boolean show = true;
	private Point position;
	private Dimension size;
	private int hlWidth = 10;
	private int alpha = MAX_ALPHA;
	private boolean fade = true;


	/**
	 * Create a new one of these.
	 * This will put a highlight around a rectangular area.  The given
	 * parameters are the position an d size of the interior of the highlight,
	 * the area that the highlight will surround.
	 *
	 * @param pos The coordinates for the box
	 * @param dim The size of the box
	 */
	public Highlight(Point pos, Dimension dim){
		position = new Point(pos);
		size = new Dimension(dim);
	}


	/**
	 * Set the effect to be either active or inactive.
	 *
	 * @param b TRUE to enable the effect, FALSE to disable
	 */
	public void setEnabled(boolean b){
		show = b;
	}

	/**
	 * Find out if the effect is enabled.
	 * Unexpected, I know.
	 *
	 * @return TRUE if effect enabled, FALSE if not
	 */
	public boolean isEnabled(){
		return show;
	}

	/**
	 * @see Effect
	 */
	public boolean tick(long elapsed){
		if(fade) alpha -= 22;
		else alpha += 22;

		// Doing comparisons w/o "=" should sorta cause slight linger on each
		// extreme (in some cases)
		if(alpha > MAX_ALPHA){
			alpha = MAX_ALPHA;
			fade = true;
		} else if(alpha < 0) {
			alpha = 0;
			fade = false;
		}

		return show;
	}

	/**
	 * @see Effect
	 *
	 * This is expecting that the background of the provided graphics object is
	 * set to be transparent.
	 */
	public void draw(Graphics2D g){
		g.setColor(new Color(BASE_R, BASE_G, BASE_B, alpha));
		g.fillRect(
			position.x - hlWidth,
			position.y - hlWidth,
			size.width + hlWidth * 2,
			size.height + hlWidth * 2
		);
		g.clearRect(
			position.x,
			position.y,
			size.width,
			size.height
		);
	}

}

