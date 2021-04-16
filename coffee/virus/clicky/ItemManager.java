package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Item;

class ItemManager {

	/**
	 * Item factory.
	 * Get an item based on the given id.
	 * Just switching for now for simplicity, but might be nice in the future
	 * to build out an item list with more functionality.
	 */
	public static Item get(int itemId){
		switch(itemId){

		default:
			System.err.println("ItemManager: No item found for id " + itemId);
		}

		// TODO: Create a phoney no-effect item instead of using null
		return null;
	}
}

