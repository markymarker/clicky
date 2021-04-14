package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Interfacer;

/**
 * Main class, entry point.
 * Interprets options and gets things going.
 */
class Main {

// ////////////// //
// STATIC SECTION //

	/**
	 * Program entry point.
	 * Enters the program. Exits the program. Is the program.
	 */
	public static void main(String[] args){
		// TODO: Add options support

		Interfacer iface = new Interfacer(){ public void setScorecard(Scorecard s){} }; // TODO

		Main clicky = new Main();
		clicky.init(iface);
		clicky.clickLoop();

		System.out.println("Hope you got some good clicks in!");
	}


	// //////////////// //
	// INSTANCE SECTION //

	private Logic logic;
	private Interfacer interfacer;
	private Interacter interact;
	private Scorecard scorecard;


	/**
	 * Initialize.
	 * TODO
	 */
	private void init(Interfacer iface){
		interfacer = iface;

		scorecard = new Scorecard();
		interact = new Interacter();
		logic = new Logic(scorecard);

		interfacer.setScorecard(scorecard);

		interact.addListener(logic);
	}

	/**
	 * Logic loop.
	 * TODO
	 */
	private void clickLoop(){
		System.out.println("No loop yet :(");
	}
}

