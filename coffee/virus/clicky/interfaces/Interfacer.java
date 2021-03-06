package coffee.virus.clicky.interfaces;

import coffee.virus.clicky.Interacter;
import coffee.virus.clicky.Scorecard;


/**
 * Interfacer interface.
 * This is the interface with the user. Implementers of this should provide a
 * visual aspect and the input methods for interaction.
 *
 * This extends the listener interface because implementers should listen at
 * least for when events occur to update the elements and values being shown
 * appropriately.
 */
public interface Interfacer extends Listener {

	/**
	 * Get access to the scorecard.
	 * Implementers will receive the scorecard for the game through this method
	 * as part of initialization, and can then interact with it freely.
	 *
	 * @param scorecard The scorecard to reference for the game
	 */
	void setScorecard(Scorecard scorecard);

	/**
	 * Get access to the interfacer's scorecard.
	 *
	 * @return The scorecard in use by the interfacer
	 */
	Scorecard getScorecard();

	/**
	 * Get access to the game actions.
	 * Implementers will receive the interaction object for the game through
	 * this method as part of initialization, and can then interact with it
	 * freely. The object received is what will be used to connect actions on
	 * the user interface with the appropriate effects in the game logic.
	 *
	 * @param interact The interacter to use for game actions
	 */
	void setInteracter(Interacter interact);

	/**
	 * Get access to the interfacer's interacter.
	 *
	 * @return The interacter in use by the interfacer
	 */
	Interacter getInteracter();

	/**
	 * Update the display.
	 * This will be called when state has updated and the ui should update in
	 * response. When this is called, implementers should re-read the state
	 * object they are holding and refresh any displays as appropriate.
	 *
	 * The return value here indicates whether the program should continue
	 * or exit. This is so that requests to exit based on an action in the
	 * implementer have a nice pathway back to the main execution function.
	 *
	 * @return TRUE to continue play, FALSE to request exit
	 */
	boolean update();

	/**
	 * Prepare for the show.
	 * If needed, any initialization of graphics, etc should happen in this
	 * method, which will be called as part of the initialization of the rest
	 * of the system. A default empty implementation is provided, as this will
	 * not be relevant to all implementers.
	 *
	 * If any implementers need special considerations in this, e.g. creating
	 * graphical resources on the event dispatch thread, then they will need
	 * to handle that themselves.
	 */
	default void init() throws Exception {}

	/**
	 * Prepare for exit.
	 * Close windows and clean up resources as necessary. A default empty
	 * implementation is provided, as this may not be relevant to all
	 * implementers.
	 */
	default void shutdown(){}

}

