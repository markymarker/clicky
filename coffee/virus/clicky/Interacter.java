package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Listener;

import java.util.ArrayList;

/**
 * Class that abstracts interaction.
 * This class defines the available interactions and provides and interface to
 * trigger them.
 */
public class Interacter {

	public static final int ACTION_CLICK = 0;
	public static final int ACTION_PURCHASE = 1;


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
		dispatchEvent(new UserEvent(ACTION_CLICK));
	}

	/**
	 * Purchase event.
	 * Dispatches a purchase event.
	 *
	 * @param itemId The id of the item that was purchased in this event
	 */
	public void purchaseItem(int itemId){
		dispatchEvent(new UserEvent(ACTION_PURCHASE, itemId));
	}

}

