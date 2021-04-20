package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Item;

import coffee.virus.clicky.items.*;


class ItemManager {

	/**
	 * Item factory.
	 * Get an item based on the given id.
	 * Just switching for now for simplicity, but might be nice in the future
	 * to build out an item list with more functionality.
	 *
	 * @param itemId The id of the item to produce
	 * @return The item that maps to the given id or NULL if not found
	 */
	public static Item get(int itemId){
		switch(itemId){

		case 0: return new Dummy();

		case 1: return new Assister(1, 10);
		case 2: return new Assister(1, 5);
		case 3: return new Assister(5, 10);

		default:
			System.err.println("ItemManager: No item found for id " + itemId);
		}

		return null;
	}
}

