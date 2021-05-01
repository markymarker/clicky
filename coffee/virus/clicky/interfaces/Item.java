package coffee.virus.clicky.interfaces;

import coffee.virus.clicky.Interacter;

public interface Item extends Cloneable {

	void executeEffect(Interacter i);

	void tick();

	Item clone() throws CloneNotSupportedException;

}

