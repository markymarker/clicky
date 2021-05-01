package coffee.virus.clicky.items;

import coffee.virus.clicky.Interacter;
import coffee.virus.clicky.interfaces.Item;


public class Assister implements Item {

	private int amount;
	private int frequency;
	private int countdown;


	/**
	 * Create a new assister item with the give qualities.
	 * The given frequency should be a positive number representing the number
	 * of ticks between triggerings of the assist bonus.
	 *
	 * @param a The amount of assists to give per effect
	 * @param f The frequency with which to apply the effect
	 */
	public Assister(int a, int f){
		amount = a;
		frequency = f;
		countdown = f;
	}


	public Assister clone() throws CloneNotSupportedException {
		return (Assister)super.clone();
	}


	public void tick(){
		if(--countdown <= 0) countdown = frequency;
	}

	public void executeEffect(Interacter i){
		if(countdown == frequency) i.addAssist(amount);
	}


// ///////// //
// DEV/DEBUG //

	public String toString(){
		return "Assister :: amt (" + amount +
			"), freq (" + frequency +
			"), countdown (" + countdown +
			")";
	}

}

