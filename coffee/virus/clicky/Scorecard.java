package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Item;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A value holder for game data and stats.
 * Score fields are public and can be accessed directly for reading and writing.
 * Access to other, more complicated fields must go through methods.
 */
public class Scorecard {

// ////////////////////////// //
// PUBLICLY ACCESSIBLE FIELDS //

	public long ticks = 0;
	public long clicks = 0;
	public long assists = 0;


// ///////// //
// INTERNALS //

	private ArrayList<Item> items;


	public Scorecard(){
		items = new ArrayList<>(50);
	}


	public void addItem(Item i){
		if(i != null) items.add(i);
	}

	public Collection<Item> getItems(){
		return items;
	}

}

