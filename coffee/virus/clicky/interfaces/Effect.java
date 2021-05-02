package coffee.virus.clicky.interfaces;

import java.awt.Graphics2D;


/**
 * A graphical special effect.
 * Classes can implement this interface to let the world know that they are one
 * fancy, flashy mofo, and everyone should drop what they're doing and pay
 * attention to them.
 */
public interface Effect {

	/**
	 * Update the state.
	 * Run an update tick to advance the state of the effect by the given
	 * amount of time.
	 *
	 * @param elapsed The amount of time to update by
	 * @return TRUE if effect still active, FALSE if finished
	 */
	public boolean tick(long elapsed);

	/**
	 * Draw the effect.
	 * Yo, effect, use the provided graphics object and draw yourself. Easy,
	 * right?
	 *
	 * @param g The graphics object to draw with
	 */
	public void draw(Graphics2D g);

}

