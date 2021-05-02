package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.util.ArrayList;


class AnimationThread extends Thread {

	private volatile boolean running = false;
	private volatile boolean listLocked = false;

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
		running = true;

		// So we can assume this runs at something around 20 FPS
		final int sleepTarget = 50;
		long lastFrame = System.currentTimeMillis() - sleepTarget;

		// Test stuff: start
		int x = 0;
		int y = 80;
		boolean xpos = true;
		boolean ypos = true;
		coffee.virus.clicky.effects.ClickFly cf1 = new coffee.virus.clicky.effects.ClickFly(
			new java.awt.Point(50, 50)
		);
		coffee.virus.clicky.effects.ClickFly cf2 = new coffee.virus.clicky.effects.ClickFly(
			new java.awt.Point(50, 50)
		);
		// Test stuff: end

		while(running){
			// Perhaps we'll see about moving effects just with interpolation
			// 500 ms max => keep choppiness in a little bit of check
			final long timeElapsed = Math.min(System.currentTimeMillis() - lastFrame, 500);

			Dimension d = drawTarget.getSize();
			BufferedImage f = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = f.createGraphics();

			// TODO
			// Test stuff: start
			int move = 5 * sleepTarget / (int)timeElapsed;
			if(x > d.width - 40) xpos = false; else if(x < 0) xpos = true;
			if(y > d.height - 40) ypos = false; else if(y < 0) ypos = true;
			x = xpos ? x + move : x - move;
			y = ypos ? y + move : y - move;
			g.setColor(java.awt.Color.RED);
			g.fillRect(x, y, 40, 40);

			if(!cf1.tick(timeElapsed)) cf1 = new coffee.virus.clicky.effects.ClickFly(
				new java.awt.Point(100, 100)
			);
			if(!cf2.tick(timeElapsed)) cf2 = new coffee.virus.clicky.effects.ClickFly(
				new java.awt.Point(100, 100)
			);
			cf1.draw(g);
			cf2.draw(g);
			// Test stuff: end

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

