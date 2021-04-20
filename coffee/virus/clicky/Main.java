package coffee.virus.clicky;

import coffee.virus.clicky.interfaces.Interfacer;

import coffee.virus.clicky.ui.simple.SimpleUI;

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

		Interfacer iface = new SimpleUI();

		Main clicky = new Main();
		clicky.init(iface);
		clicky.clickLoop();

		System.out.println("Hope you got some good clicks in!");
	}


	// //////////////// //
	// INSTANCE SECTION //

	private boolean running = false;

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
		logic = new Logic(scorecard, iface);

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

		int tmpcount = 0;
		int clicktastrophes = 0;
		StringBuilder msgs = new StringBuilder();
		long lasttickstart = System.currentTimeMillis();

		running = true;

		try {
			while(running){
				long diff = Math.min(1000, (lasttickstart + 1000) - System.currentTimeMillis());
				if(diff > 0){
					try { Thread.sleep(diff); }
					catch(Exception e){
						++clicktastrophes;
						msgs.append(e.getMessage() + "\n");
						if(clicktastrophes > 8) break;
					}
				}

				lasttickstart = System.currentTimeMillis();

				logic.runTick(interact);

				// TODO: Remove when more built out
				System.out.println(tmpcount++);
				if(tmpcount >= 5)
					throw new Exception("That's all for now, folks");
			}
		} catch(Exception e) {
			System.err.println("Massive click catastrophe occurred: " + e.getMessage());
		}

		if(clicktastrophes > 0){
			System.err.println("They wanted you to know:");
			System.err.println(msgs.toString());
		}

		running = false;
	}
}

