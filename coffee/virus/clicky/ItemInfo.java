package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Item;

import java.util.HashSet;


/**
 * Container for information and other details for an item.
 * Easily-collectable container used to hold the details for items in the game
 * along with a prototype for creating new instances.
 */
class ItemInfo {

// //////////////// //
// INSTANCE SECTION //

	private int id;
	private String name;
	private Item prototype;


	/**
	 * Create an item info container with the given details.
	 * The given id should be globally unique, but this constraint is the
	 * responsibility of the item manager to enforce.  The provided prototype
	 * will be cloned to create new instances of the item.
	 *
	 * @param itemId The id of the item in the system
	 * @param itemName The display name to give the item
	 * @param itemProto A prototype version of the item
	 */
	public ItemInfo(int itemId, String itemName, Item itemProto){
		id = itemId;
		name = itemName;
		prototype = itemProto;
	}


	public Item create() throws CloneNotSupportedException {
		return prototype.clone();
	}

}

