package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Interfacer;
import coffee.virus.clicky.interfaces.Item;
import coffee.virus.clicky.interfaces.Listener;

/**
 * The brains of the operation.
 * This is the class that will respond to events and update the game state
 * accordingly. This needs access to the components that make up the state
 * of the game, but will not initialize any of them itself.
 */
class Logic implements Listener {

	private Interfacer interfacer;
	private Scorecard scorecard;


	/**
	 * Bring it to life.
	 * Create a new Logic that will use the given pieces of game state.
	 *
	 * @param card The scorecard to reference and update
	 * @param iface The interfacer to notify of updates
	 */
	public Logic(Scorecard card, Interfacer iface){
		this.scorecard = card;
		this.interfacer = iface;
	}


	/**
	 * Respond to a tick.
	 * The main game logic for time-based operations. This handles incrementing
	 * counts, running through actors, calling for updates, and anything else
	 * relevant to advancement of the game state.
	 */
	public void runTick(Interacter iact){
		++scorecard.ticks;

		for(Item i : scorecard.getItems()){
			i.tick();
			i.executeEffect(iact);
		}

		interfacer.update();
	}

	/**
	 * Respond to events.
	 * Implementation from Listener -- this is the event handling entry point.
	 *
	 * @param e The event to respond to
	 */
	public void actionPerformed(UserEvent e){
		boolean update = true;

		switch(e.getAction()){
		case UserEvent.ACTION_CLICK:
			++scorecard.clicks;
		break;

		case UserEvent.ACTION_ASSIST:
			scorecard.assists += e.getExtra();
		break;

		case UserEvent.ACTION_PURCHASE:
			scorecard.addItem(ItemManager.get(e.getExtra()));
		break;

		default:
			System.err.println("Logic: Unrecognized action (" + e.getAction() + ")");
			update = false;
		}

		if(update) interfacer.update();
	}

}

