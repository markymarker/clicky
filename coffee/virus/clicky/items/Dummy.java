package coffee.virus.clicky.items;

import coffee.virus.clicky.Interacter;
import coffee.virus.clicky.interfaces.Item;


public class Dummy implements Item {

	public Dummy clone() throws CloneNotSupportedException {
		return (Dummy)super.clone();
	}

	public void tick(){}
	public void executeEffect(Interacter i){}


// ///////// //
// DEV/DEBUG //

	public String toString(){
		return "Dummy item";
	}

}

