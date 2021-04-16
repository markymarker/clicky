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

		// TODO: Replace with an actual interfacer once they're created
		Interfacer iface = new Interfacer(){
			public void setScorecard(Scorecard s){}
			public void setInteracter(Interacter i){}
		};

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
	 * Set up all the game components and hand everybody what they need. Once
	 * everybody is present, run any further sub-initialization routines. The
	 * Interfacer to be used is provided as a parameter so that the desired
	 * interface can be specified in an option that the option handler will
	 * then select the appropriate backing class for.
	 *
	 * @param iface I face? No, you face!
	 */
	private void init(Interfacer iface){
		interfacer = iface;

		scorecard = new Scorecard();
		interact = new Interacter();
		logic = new Logic(scorecard);

		interfacer.setScorecard(scorecard);
		interfacer.setInteracter(interact);

		interact.addListener(logic);

		iface.init();
	}

	/**
	 * Logic loop.
	 * TODO
	 */
	private void clickLoop(){
		System.out.println("No loop yet :(");
	}
}

