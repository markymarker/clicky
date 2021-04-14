package coffee.virus.clicky;

/**
 * User event object.
 * This class holds the properties and methods related to an event generated as
 * a result of an action by the user.
 */
public class UserEvent {

	private String action;
	private int extra = 0;


	/**
	 * Create a new UserEvent.
	 *
	 * @param action The action string identifying this event
	 */
	public UserEvent(String action){
		this.action = action;
	}

	/**
	 * Create a new UserEvent.
	 *
	 * @param action The action string identifying this event
	 * @param extra An extra context-sensitive piece of information
	 */
	public UserEvent(String action, int extra){
		this.action = action;
		this.extra = extra;
	}


	/**
	 * Get the action.
	 *
	 * @return The action identifier
	 */
	public String getAction(){
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

