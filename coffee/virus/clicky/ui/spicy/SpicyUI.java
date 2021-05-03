package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;
import coffee.virus.clicky.UserEvent;

import coffee.virus.clicky.effects.ClickFly;
import coffee.virus.clicky.effects.Highlight;

import coffee.virus.clicky.interfaces.Effect;
import coffee.virus.clicky.interfaces.Interfacer;

import coffee.virus.clicky.ui.ActionHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

	private Highlight clickBHighlight = null;


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

		glass = new DrawGlass();
		window.setGlassPane(glass);
		glass.setVisible(true);

		anim = new AnimationThread(glass);

		window.setLayout(new BorderLayout());
		window.add(display.init(), BorderLayout.NORTH);
		window.add(controls.init(), BorderLayout.CENTER);

		Windower wlis = new Windower();
		window.addWindowListener(wlis);
		window.addComponentListener(wlis);
		actionDude.registerEvents(window.getRootPane());

		window.pack();
		window.setVisible(true);

		anim.start();
		highlightClickButton(true);
	}

	public void shutdown(){
		continueRunning = false;
		anim.shutdown();
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

	public void addEffect(Effect e){
		anim.addEffect(e);
	}

	public void actionPerformed(UserEvent e){
		update();

		switch(e.getAction()){
		case UserEvent.ACTION_CLICK:
			Point mp = window.getMousePosition();
			if(mp != null) addEffect(new ClickFly(mp));
			if(clickBHighlight.isEnabled()) highlightClickButton(false);
		break;

		case UserEvent.ACTION_PURCHASE:
			if(e.getExtra() == 0) highlightClickButton(true);
		break;
		}
	}


// /////////// //
// FANCY STUFF //

	/**
	 * Set the highlight on the click button on or off.
	 * If the effect has not yet been created, this will handle creating it.
	 *
	 * @param on TRUE to turn effect on, FALSE for off
	 */
	private void highlightClickButton(boolean on){
		if(clickBHighlight == null){
			Point cbp = new Point();
			Dimension cbs = new Dimension();
			controls.whereClickButton(cbp, cbs);

			Point cpos = new Point();
			controls.whereYouAt(cpos);
			cbp.x += cpos.x;
			cbp.y += cpos.y;

			clickBHighlight = new Highlight(cbp, cbs);
			clickBHighlight.setEnabled(false);
		}

		boolean pState = clickBHighlight.isEnabled();
		if(pState == on) return;
		// Only bother with the below if there's a change in state
		clickBHighlight.setEnabled(on);
		if(on) addEffect(clickBHighlight);
	}


// ///////////////////// //
// WINDOW EVENT LISTENER //

	private class Windower extends WindowAdapter implements ComponentListener {

		/**
		 * Listen for the window close event.
		 * Notice when we need to perform cleanup, etc.
		 */
		public void windowClosed(WindowEvent e){
			continueRunning = false;
		}

		/**
		 * Listen for the window resize event.
		 * Notice when we need to adjust positions, re-compute things, etc.
		 */
		public void componentResized(ComponentEvent e){
			boolean pState = clickBHighlight.isEnabled();
			clickBHighlight.setEnabled(false);
			clickBHighlight = null;
			highlightClickButton(pState);
		}

		public void componentHidden(ComponentEvent e){}
		public void componentMoved(ComponentEvent e){}
		public void componentShown(ComponentEvent e){}
	}


// ////////////////// //
// PANE FOR ANIMATION //

	class DrawGlass extends JComponent {

		private Image frame;


		public void setFrame(Image f){
			frame = f;
		}

		protected void paintComponent(Graphics g){
			g.drawImage(frame, 0, 0, null);
		}
	}

}

