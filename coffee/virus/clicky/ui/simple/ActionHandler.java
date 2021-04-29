package coffee.virus.clicky.ui.simple;

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
class ActionHandler {

	private static final String ACTION_CLOSE = "Close Window";
	private static final String ACTION_CLICK = "Click";
	private static final String ACTION_DEBUG_DUMP = "Dump Debug Info";
	private static final String ACTION_PURCHASE_1 = "Buy Item 1";
	private static final String ACTION_PURCHASE_2 = "Buy Item 2";
	private static final String ACTION_PURCHASE_3 = "Buy Item 3";


	private SimpleUI ui;


	public ActionHandler(SimpleUI creator){
		this.ui = creator;
	}


	public void registerEvents(JComponent comp){
		InputMap inputs = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputs.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_CLOSE);
		inputs.put(KeyStroke.getKeyStroke("C"), ACTION_CLICK);
		inputs.put(KeyStroke.getKeyStroke("D"), ACTION_DEBUG_DUMP);
		inputs.put(KeyStroke.getKeyStroke("1"), ACTION_PURCHASE_1);
		inputs.put(KeyStroke.getKeyStroke("2"), ACTION_PURCHASE_2);
		inputs.put(KeyStroke.getKeyStroke("3"), ACTION_PURCHASE_3);

		ActionMap actions = comp.getActionMap();
		actions.put(ACTION_CLOSE, new Actioner(ACTION_CLOSE));
		actions.put(ACTION_CLICK, new Actioner(ACTION_CLICK));
		actions.put(ACTION_DEBUG_DUMP, new Actioner(ACTION_DEBUG_DUMP));
		actions.put(ACTION_PURCHASE_1, new Actioner(ACTION_PURCHASE_1));
		actions.put(ACTION_PURCHASE_2, new Actioner(ACTION_PURCHASE_2));
		actions.put(ACTION_PURCHASE_3, new Actioner(ACTION_PURCHASE_3));
	}

	private void handleAction(ActionEvent e, String cmd){
		switch(cmd){
		case ACTION_CLOSE: ui.shutdown();
		break;

		case ACTION_CLICK: ui.interacter.addClick();
		break;

		case ACTION_DEBUG_DUMP: System.out.println(ui.scorecard);
		break;

		case ACTION_PURCHASE_1: ui.interacter.purchaseItem(1);
		break;

		case ACTION_PURCHASE_2: ui.interacter.purchaseItem(2);
		break;

		case ACTION_PURCHASE_3: ui.interacter.purchaseItem(3);
		break;

		default:
			System.err.println("No action defined for [" + cmd + "]");
		}
	}


// //////////// //
// ACTION CLASS //

	private class Actioner extends AbstractAction{
		private String commandString;

		Actioner(String cmd){
			commandString = cmd;
		}

		public void actionPerformed(ActionEvent e){
			handleAction(e, commandString);
		}
	}
}

