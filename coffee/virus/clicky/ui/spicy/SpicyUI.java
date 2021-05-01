package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;
import coffee.virus.clicky.UserEvent;

import coffee.virus.clicky.interfaces.Interfacer;

import coffee.virus.clicky.ui.ActionHandler;

import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * Spicy UI.
 * More frilly than SimpleUI, maybe good for impressing the homies.
 * TODO
 */
public class SpicyUI implements Interfacer, Runnable {

	private boolean continueRunning = true;

	Scorecard scorecard;
	Interacter interacter;
	ActionHandler actionDude;

	private JFrame window;
	private DrawGlass glass;
	private AnimationThread anim;
	private DisplayArea display;
	private ControlArea controls;


	public SpicyUI(){
		actionDude = new ActionHandler(this);
		display = new DisplayArea(this);
		controls = new ControlArea(this);
	}


	public void init() throws Exception {
		javax.swing.SwingUtilities.invokeAndWait(this);
	}

	public void run(){
		window = new JFrame("Click To Continue");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);

		window.setLayout(new BorderLayout());
		window.add(display.init(), BorderLayout.NORTH);
		window.add(controls.init(), BorderLayout.CENTER);

		window.addWindowListener(new Windower());
		actionDude.registerEvents(window.getRootPane());

		glass = new DrawGlass();
		window.setGlassPane(glass);
		glass.setVisible(true);

		anim = new AnimationThread(glass);

		window.pack();
		window.setVisible(true);

		anim.start();
	}

	public void shutdown(){
		continueRunning = false;
		window.dispose();
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

	public Scorecard getScorecard(){
		return scorecard;
	}

	public void setInteracter(Interacter i){
		interacter = i;
	}

	public Interacter getInteracter(){
		return interacter;
	}

	public boolean update(){
		display.update();
		return continueRunning;
	}

	public void actionPerformed(UserEvent e){
		update();
	}


// ///////////////////// //
// WINDOW EVENT LISTENER //

	private class Windower extends WindowAdapter {
		public void windowClosed(WindowEvent e){
			continueRunning = false;
		}
	}


// ////////////////// //
// PANE FOR ANIMATION //

	private class DrawGlass extends JComponent {

		protected void paintComponent(Graphics g){
			g.drawImage(anim.frame, 0, 0, null);
		}
	}

}

