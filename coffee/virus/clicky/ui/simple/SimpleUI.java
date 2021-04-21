package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import coffee.virus.clicky.interfaces.Interfacer;

import java.awt.BorderLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


/**
 * Simple UI.
 * Not too frilly, maybe good workhorse for testing.
 * TODO
 */
public class SimpleUI implements Interfacer, Runnable {

	private Scorecard scorecard;
	private Interacter interacter;

	private JFrame window;
	private DisplayArea display;
	private ControlArea controls;


	public SimpleUI(){
		display = new DisplayArea();
		controls = new ControlArea();
	}


	public void init() throws Exception {
		javax.swing.SwingUtilities.invokeAndWait(this);
	}

	public void run(){
		window = new JFrame("Click To Continue");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);

		window.setLayout(new BorderLayout());
		window.add(display.init(), BorderLayout.NORTH);
		window.add(controls.init(), BorderLayout.SOUTH);

		window.addWindowListener(new Windower());

		window.pack();
		window.setVisible(true);
	}

	public void shutdown(){
		window.dispose();
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
		display.setScorecard(s);
		controls.setScorecard(s);
	}

	public void setInteracter(Interacter i){
		interacter = i;
		controls.setInteracter(i);
	}

	public void update(){
		// TODO: Update counts, fields, etc.
	}


// ///////////////////// //
// WINDOW EVENT LISTENER //

	private class Windower extends WindowAdapter {
		public void windowClosed(WindowEvent e){
			// TODO: Notify Main in a nice fashion
		}
	}

}

