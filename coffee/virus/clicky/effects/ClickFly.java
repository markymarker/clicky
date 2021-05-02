package coffee.virus.clicky.effects;

import coffee.virus.clicky.interfaces.Effect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 * Clicks go flying.
 * This effect increases accessibility by providing a textual indicator for
 * when a click has occured.
 *
 * The way that position and movement is tracked is based around a (0, 0)
 * origin internally, then at the time of drawing, position is shifted to use
 * the point of emission as the origin.
 */
public class ClickFly implements Effect {

// ////////////// //
// STATIC SECTION //

	private static final String TEXT = "Click!";
	private static final int LIFETIME = 5;  // 0.25 s at 20 FPS

	private static final BufferedImage imgProto;
	private static final Dimension imgDim;
	private static final Dimension imgDimHalf;


	static {
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 14);

		// Ugh...
		BufferedImage tempBI = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D tempG = tempBI.createGraphics();
		tempG.setFont(f);
		FontMetrics fm = tempG.getFontMetrics();
		tempG.dispose();
		tempG = null; tempBI = null;

		imgDim = new Dimension(
			fm.stringWidth(TEXT),
			fm.getHeight()
		);
		imgDimHalf = new Dimension(
			imgDim.width / 2,
			imgDim.height / 2
		);
		imgProto = new BufferedImage(
			imgDim.width,
			imgDim.height,
			BufferedImage.TYPE_INT_ARGB
		);

		Graphics2D g = imgProto.createGraphics();
		g.setFont(f);
		g.setColor(new Color(255, 200, 20));
		g.drawString(TEXT, 0, fm.getAscent());
		g.dispose();
	}


// //////////////// //
// INSTANCE SECTION //

	private int scaleAdd = 0;
	private long timeAlive = 0;
	private Point origin;
	private Point position;
	private Point destination;
	private Point speed;


	/**
	 * Create a new one of these.
	 * Does some constructing, as one might expect.
	 *
	 * @param emitFrom The point where the event occurred
	 */
	public ClickFly(Point emitFrom){
		origin = new Point(
			emitFrom.x - imgDimHalf.width,
			emitFrom.y - imgDimHalf.height
		);

		position = new Point(0, 0);

		destination = new Point(
			(int)Math.floor(Math.random() * 80) + 20,
			(int)Math.floor(Math.random() * 80) + 20
		);
		if(Math.random() < 0.5) destination.x *= -1;
		if(Math.random() < 0.5) destination.y *= -1;

		speed = new Point(
			destination.x / LIFETIME,
			destination.y / LIFETIME
		);
	}


	/**
	 * @see Effect
	 */
	public boolean tick(long elapsed){
		if(timeAlive > LIFETIME) return false;

		// Starting off with linear movement
		// May be cool to get fancy with easing and gravity and such

		if(Math.abs(position.x) < Math.abs(destination.x)){
			position.x += speed.x;
		}

		if(Math.abs(position.y) < Math.abs(destination.y)){
			position.y += speed.y;
		}

		if(timeAlive > 0 && timeAlive % 2 == 0) scaleAdd += 3;
		++timeAlive;

		return true;
	}

	/**
	 * @see Effect
	 */
	public void draw(Graphics2D g){
		g.drawImage(
			imgProto.getScaledInstance(-1, imgDim.height + scaleAdd, BufferedImage.SCALE_FAST),
			position.x + origin.x,
			position.y + origin.y,
			null
		);
	}

}

