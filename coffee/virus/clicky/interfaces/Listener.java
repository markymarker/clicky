package coffee.virus.clicky.interfaces;

import coffee.virus.clicky.UserEvent;

/**
 * Listener interface.
 * Any listeners who wish to be registered in the interaction system should
 * implement this interface. This interface allows one to receive user-
 * generated events through the actionPerformed method being called with the
 * event that occurred.
 */
public interface Listener {

	/**
	 * The main event.
	 * An action was performed, an event was generated, and something cool
	 * should happen.
	 *
	 * @param e The event that was generated as a result of the action
	 */
	void actionPerformed(UserEvent e);

}

