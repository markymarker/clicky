package coffee.virus.clicky.ui;

import coffee.virus.clicky.interfaces.Interfacer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


/**
 * Action handler.
 * Deals with action-related things for the UI.
 * Will take care of registering and responding to actions, then will call into
 * method(s) on the UI for the effects that should occur.
 */
public class ActionHandler {

	private static final String ASTRING_CLOSE = "Close Window";
	private static final String ASTRING_CLICK = "Click";
	private static final String ASTRING_DEBUG_DUMP = "Dump Debug Info";
	private static final String ASTRING_PURCHASE_0 = "Buy Item 0";
	private static final String ASTRING_PURCHASE_1 = "Buy Item 1";
	private static final String ASTRING_PURCHASE_2 = "Buy Item 2";
	private static final String ASTRING_PURCHASE_3 = "Buy Item 3";
	private static final String ASTRING_PURCHASE_4 = "Buy Item 4";
	private static final String ASTRING_PURCHASE_5 = "Buy Item 5";


	// (These are purposely not static)
	public final Actioner ACTION_CLOSE = new Actioner(ASTRING_CLOSE);
	public final Actioner ACTION_CLICK = new Actioner(ASTRING_CLICK);
	public final Actioner ACTION_DEBUG_DUMP = new Actioner(ASTRING_DEBUG_DUMP);
	public final Actioner ACTION_PURCHASE_0 = new Actioner(ASTRING_PURCHASE_0);
	public final Actioner ACTION_PURCHASE_1 = new Actioner(ASTRING_PURCHASE_1);
	public final Actioner ACTION_PURCHASE_2 = new Actioner(ASTRING_PURCHASE_2);
	public final Actioner ACTION_PURCHASE_3 = new Actioner(ASTRING_PURCHASE_3);
	public final Actioner ACTION_PURCHASE_4 = new Actioner(ASTRING_PURCHASE_4);
	public final Actioner ACTION_PURCHASE_5 = new Actioner(ASTRING_PURCHASE_5);

	private Interfacer ui;


	public ActionHandler(Interfacer creator){
		this.ui = creator;
	}


	public void registerEvents(JComponent comp){
		InputMap inputs = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputs.put(KeyStroke.getKeyStroke("ESCAPE"), ASTRING_CLOSE);
		inputs.put(KeyStroke.getKeyStroke("C"), ASTRING_CLICK);
		inputs.put(KeyStroke.getKeyStroke("D"), ASTRING_DEBUG_DUMP);
		inputs.put(KeyStroke.getKeyStroke("0"), ASTRING_PURCHASE_0);
		inputs.put(KeyStroke.getKeyStroke("1"), ASTRING_PURCHASE_1);
		inputs.put(KeyStroke.getKeyStroke("2"), ASTRING_PURCHASE_2);
		inputs.put(KeyStroke.getKeyStroke("3"), ASTRING_PURCHASE_3);
		inputs.put(KeyStroke.getKeyStroke("4"), ASTRING_PURCHASE_4);
		inputs.put(KeyStroke.getKeyStroke("5"), ASTRING_PURCHASE_5);

		ActionMap actions = comp.getActionMap();
		actions.put(ASTRING_CLOSE, ACTION_CLOSE);
		actions.put(ASTRING_CLICK, ACTION_CLICK);
		actions.put(ASTRING_DEBUG_DUMP, ACTION_DEBUG_DUMP);
		actions.put(ASTRING_PURCHASE_0, ACTION_PURCHASE_0);
		actions.put(ASTRING_PURCHASE_1, ACTION_PURCHASE_1);
		actions.put(ASTRING_PURCHASE_2, ACTION_PURCHASE_2);
		actions.put(ASTRING_PURCHASE_3, ACTION_PURCHASE_3);
		actions.put(ASTRING_PURCHASE_4, ACTION_PURCHASE_4);
		actions.put(ASTRING_PURCHASE_5, ACTION_PURCHASE_5);
	}

	private void handleAction(ActionEvent e, String cmd){
		switch(cmd){
		case ASTRING_CLOSE: ui.shutdown();
		break;

		case ASTRING_CLICK: ui.getInteracter().addClick();
		break;

		case ASTRING_DEBUG_DUMP: System.out.println(ui.getScorecard());
		break;

		case ASTRING_PURCHASE_0: ui.getInteracter().purchaseItem(0);
		break;

		case ASTRING_PURCHASE_1: ui.getInteracter().purchaseItem(1);
		break;

		case ASTRING_PURCHASE_2: ui.getInteracter().purchaseItem(2);
		break;

		case ASTRING_PURCHASE_3: ui.getInteracter().purchaseItem(3);
		break;

		case ASTRING_PURCHASE_4: ui.getInteracter().purchaseItem(4);
		break;

		case ASTRING_PURCHASE_5: ui.getInteracter().purchaseItem(5);
		break;

		default:
			System.err.println("No action defined for [" + cmd + "]");
		}
	}


// //////////// //
// ACTION CLASS //

	private class Actioner extends AbstractAction {
		private String commandString;

		Actioner(String cmd){
			commandString = cmd;
			putValue(AbstractAction.NAME, cmd);
		}

		public void actionPerformed(ActionEvent e){
			handleAction(e, commandString);
		}
	}
}

