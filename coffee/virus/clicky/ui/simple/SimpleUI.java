package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import coffee.virus.clicky.interfaces.Interfacer;

import javax.swing.JFrame;


/**
 * Simple UI.
 * Not too frilly, maybe good workhorse for testing.
 * TODO
 */
public class SimpleUI implements Interfacer {

	private Scorecard scorecard;
	private Interacter interacter;

	private JFrame window;
	private DisplayArea display;
	private ControlArea controls;


	public SimpleUI(){
		display = new DisplayArea();
		controls = new ControlArea();
	}


	public void init(){
		window = new JFrame("Click To Continue");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);

		display.init();
		controls.init();

		// TODO: Any other swing init on event thread
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

}

