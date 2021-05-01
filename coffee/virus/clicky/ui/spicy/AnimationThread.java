package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.util.ArrayList;


class AnimationThread extends Thread {

	private volatile boolean listLocked = false;

	private ArrayList<Effect> effects;
	private ArrayList<Effect> effectAddQueue;

	Component drawTarget;
	BufferedImage frame;


	public AnimationThread(Component drawTarget){
		this.drawTarget = drawTarget;

		effects = new ArrayList<>(64);
		effectAddQueue = new ArrayList<>(16);
	}


	/**
	 * Add an effect to be animated.
	 *
	 * @param e The effect to add to the animations
	 */
	public void addEffect(Effect e){
		effectAddQueue.add(e);
	}


	/**
	 * Main thread logic.
	 * Implements the animation loop.
	 * Processing and drawing logic combined, fight me.
	 */
	public void run(){
		// Just a test for now

		Dimension d = drawTarget.getSize();
		BufferedImage f = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = f.createGraphics();

		g.setColor(java.awt.Color.RED);
		g.fillRect(10, 10, 30, 300);

		g.dispose();
		frame = f;
		drawTarget.repaint();
	}

}

