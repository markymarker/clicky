package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.util.ArrayList;


class AnimationThread extends Thread {

	private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	private volatile boolean running = false;

	private ArrayList<Effect> effects;
	private ArrayList<Effect> effectAddQueue;

	SpicyUI.DrawGlass drawTarget;


	public AnimationThread(SpicyUI.DrawGlass drawTarget){
		this.drawTarget = drawTarget;

		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			public void uncaughtException(Thread t, Throwable e){
				System.err.println("Something went sideways on the animation thread");
				e.printStackTrace();
			}
		});

		effects = new ArrayList<>(64);
		effectAddQueue = new ArrayList<>(16);
	}


	/**
	 * Stop it.
	 * You stop that animating right now, young thread!
	 * Okay, I guess you can finish one last frame, but only one!
	 */
	public void shutdown(){
		running = false;
	}

	/**
	 * Add an effect to be animated.
	 * Needs to synchronize with other methods accessing the effect add queue.
	 *
	 * @param e The effect to add to the animations
	 */
	public synchronized void addEffect(Effect e){
		effectAddQueue.add(e);
	}

	/**
	 * Shift all the items in the effect add queue to the effect list proper.
	 * Needs to synchronize with other methods accessing the effect add queue.
	 */
	private synchronized void transferEffects(){
		if(!effectAddQueue.isEmpty()){
			effects.addAll(effectAddQueue);
			effectAddQueue.clear();
		}
	}


	/**
	 * Main thread logic.
	 * Implements the animation loop.
	 * Processing and drawing logic combined, fight me.
	 */
	public void run(){
		running = true;

		// So we can assume this runs at something around 20 FPS
		final int sleepTarget = 50;
		long lastFrame = System.currentTimeMillis() - sleepTarget;

		while(running){
			transferEffects();

			Dimension d = drawTarget.getSize();
			BufferedImage f = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = f.createGraphics();
			g.setBackground(TRANSPARENT);

			// Perhaps we'll see about moving effects just with interpolation
			// 500 ms max => keep choppiness in a little bit of check
			final long timeElapsed = Math.min(System.currentTimeMillis() - lastFrame, 500);

			ArrayList<Effect> toRemove = new ArrayList<>(effects.size());
			for(Effect e : effects){
				if(!e.tick(timeElapsed)){
					toRemove.add(e);
					continue;
				}
				e.draw(g);
			}
			effects.removeAll(toRemove);
			toRemove = null;

			g.dispose();
			drawTarget.setFrame(f);
			drawTarget.repaint();

			lastFrame = System.currentTimeMillis();

			// Slow down there, champ!
			try { Thread.sleep(sleepTarget); }
			catch (Exception e) { /* Eh, whatever; who needs sleep anyway. */ }
		}
	}

}

