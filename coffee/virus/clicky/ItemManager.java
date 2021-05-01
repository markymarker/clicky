package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Item;

import coffee.virus.clicky.items.*;

import java.util.ArrayList;


class ItemManager {

	private static ArrayList<ItemInfo> itemList;


	static {
		itemList = new ArrayList<>();
		int id = 0;

		/* 0 */ itemList.add(new ItemInfo(id++, "Dummy Item", new Dummy()));
		/* 1 */ itemList.add(new ItemInfo(id++, "Assister: 1 per 10", new Assister(1, 10)));
		/* 2 */ itemList.add(new ItemInfo(id++, "Assister: 5 per 10", new Assister(5, 10)));
		/* 3 */ itemList.add(new ItemInfo(id++, "Assister: 1 per 5", new Assister(1, 5)));
		/* 4 */ itemList.add(new ItemInfo(id++, "Assister: 5 per 5", new Assister(5, 5)));

		itemList.trimToSize();
	}


	/**
	 * Item factory.
	 * Get an item based on the given id.
	 *
	 * @param itemId The id of the item to produce
	 * @return The item that maps to the given id or NULL if not found
	 */
	public static Item get(int itemId){
		try { return itemList.get(itemId).create(); }
		catch(IndexOutOfBoundsException e) {
			System.err.println("ItemManager: No item found for id " + itemId);
		} catch(CloneNotSupportedException e) {
			System.err.println("Unable to create item (" + itemId + ")");
		}

		return null;
	}
}

