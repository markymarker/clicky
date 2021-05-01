package coffee.virus.clicky.ui;

import coffee.virus.clicky.interfaces.Interfacer;

import coffee.virus.clicky.ui.simple.SimpleUI;
import coffee.virus.clicky.ui.spicy.SpicyUI;


public class InterfacerInstantiator {

	public static final int SIMPLEUI = 0;
	public static final int SPICYUI = 1;
	public static final int MEMEUI = 420;


	public static Interfacer getUI(int uiId) throws Exception {
		switch(uiId){
		case SIMPLEUI: return new SimpleUI();
		case SPICYUI: return new SpicyUI();

		case MEMEUI: System.err.println("Sorry, MemeUI is not available in your region");
		default: throw new Exception("Unsupported interface id (" + uiId + ")");
		}
	}

}

