package coffee.virus.clicky;

/**
 * User event object.
 * This class holds the properties and methods related to an event generated as
 * a result of an action by the user.
 */
public class UserEvent {

	public static final int ACTION_CLICK = 0;
	public static final int ACTION_ASSIST = 1;
	public static final int ACTION_PURCHASE = 2;


	private int action;
	private int extra = 0;


	/**
	 * Create a new UserEvent.
	 *
	 * @param action The action code identifying this event
	 */
	public UserEvent(int action){
		this.action = action;
	}

	/**
	 * Create a new UserEvent.
	 *
	 * @param action The action code identifying this event
	 * @param extra An extra context-sensitive piece of information
	 */
	public UserEvent(int action, int extra){
		this.action = action;
		this.extra = extra;
	}


	/**
	 * Get the action.
	 *
	 * @return The action identifier
	 */
	public int getAction(){
		return action;
	}

	/**
	 * Get the extra.
	 *
	 * @return The extra piece of information
	 */
	public int getExtra(){
		return extra;
	}

}

