package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Listener;

import java.util.ArrayList;

/**
 * Class that abstracts interaction.
 * This class defines the available interactions and provides and interface to
 * trigger them.
 */
public class Interacter {

	private ArrayList<Listener> listeners;


	public Interacter(){
		listeners = new ArrayList<>();
	}


// /////////////// //
// PRIVATE METHODS //

	/**
	 * Send event to all.
	 * Run through the list of registered listeners and send the event to each
	 * of them in turn.
	 *
	 * @param e The event to dispatch to listeners
	 */
	private void dispatchEvent(UserEvent e){
		for(Listener l : listeners) l.actionPerformed(e);
	}


// ////////////// //
// PUBLIC METHODS //

	/**
	 * Register a listener.
	 * Adds the given listener to the registry to receive events.
	 *
	 * @param l The listener to register
	 */
	public void addListener(Listener l){
		listeners.add(l);
	}

	/**
	 * Unregister a listener.
	 * Removes all listeners from the registry that evaluate as equal to the
	 * given listener.
	 *
	 * @param l The listener to remove all instances of in the registry
	 */
	public void removeListener(Listener l){
		while(listeners.remove(l)) /* no-op */ ;
	}


	// ######################### //
	// ### AVAILABLE ACTIONS ### //

	/**
	 * Click event.
	 * Dispatches a click event.
	 */
	public void addClick(){
		dispatchEvent(new UserEvent(UserEvent.ACTION_CLICK));
	}

	/**
	 * Assist event.
	 * Dispatches an assist event with the given quantity.
	 *
	 * @param amount The amount of assist points to add
	 */
	public void addAssist(int amount){
		dispatchEvent(new UserEvent(UserEvent.ACTION_ASSIST, amount));
	}

	/**
	 * Purchase event.
	 * Dispatches a purchase event.
	 *
	 * @param itemId The id of the item that was purchased in this event
	 */
	public void purchaseItem(int itemId){
		dispatchEvent(new UserEvent(UserEvent.ACTION_PURCHASE, itemId));
	}

}

