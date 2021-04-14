package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Listener;

/**
 * TODO
 */
class Logic implements Listener {

	private Scorecard scorecard;


	public Logic(Scorecard card){
		this.scorecard = card;
	}


	public void actionPerformed(UserEvent e){}

}

