package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Listener;

/**
 * The brains of the operation.
 * This is the class that will respond to events and update the game state
 * accordingly. This needs access to the components that make up the state
 * of the game, but will not initialize any of them itself.
 */
class Logic implements Listener {

	private Scorecard scorecard;


	/**
	 * Bring it to life.
	 * Create a new Logic that will use the given pieces of game state.
	 *
	 * @param card The scorecard to reference and update
	 */
	public Logic(Scorecard card){
		this.scorecard = card;
	}


	/**
	 * Respond to events.
	 * Implementation from Listener -- this is the event handling entry point.
	 *
	 * @param e The event to respond to
	 */
	public void actionPerformed(UserEvent e){
		switch(e.getAction()){
		case Interacter.ACTION_CLICK:
			++scorecard.clicks;
		break;

		case Interacter.ACTION_PURCHASE:
			scorecard.addItem(ItemManager.get(e.getExtra()));
		break;

		default:
			System.err.println("Logic: Unrecognized action (" + e.getAction() + ")");
		}
	}

}

