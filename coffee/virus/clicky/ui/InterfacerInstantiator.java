package coffee.virus.clicky.ui;

import coffee.virus.clicky.interfaces.Interfacer;

import coffee.virus.clicky.ui.simple.SimpleUI;


public class InterfacerInstantiator {

	public static final int SIMPLEUI = 0;
	public static final int SPICYUI = 1;

	public static Interfacer getUI(int uiId) throws Exception {
		switch(uiId){
		case SIMPLEUI: return new SimpleUI();
		case SPICYUI: System.err.println("Sorry, SpicyUI is not available in your region");

		default: throw new Exception("Unsupported interface id (" + uiId + ")");
		}
	}

}

