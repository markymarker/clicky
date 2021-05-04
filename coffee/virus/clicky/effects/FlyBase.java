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
 * Words go flying.
 * This effect dazzles and wows by launching a bit of the ole text about. A
 * real crowd pleaser. Of course, even though this class does all the work, it
 * doesn't like to take the credit; it's happy just to sit behind the scenes
 * and support the young go-getter classes that extend it.
 *
 * The way that position and movement is tracked is based around a (0, 0)
 * origin internally, then at the time of drawing, position is shifted to use
 * the point of emission as the origin.
 */
public abstract class FlyBase implements Effect {

// ////////////// //
// STATIC SECTION //

	private static final int LIFETIME = 5;  // 0.25 s at 20 FPS


	/**
	 * Generate the stuff.
	 * Subclasses should call this method to do the heavy lifting in generating
	 * the image and dimensions to be used in the effect.
	 *
	 * @param text The text to use
	 * @param imgDim Where to store the dimensions of the generated image
	 * @param imgDimHalf Where to store the half dimensions of the generated image
	 * @return The generated image
	 */
	protected static final BufferedImage createText(String text, Dimension imgDim, Dimension imgDimHalf){
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 14);

		// Ugh...
		BufferedImage tempBI = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D tempG = tempBI.createGraphics();
		tempG.setFont(f);
		FontMetrics fm = tempG.getFontMetrics();
		tempG.dispose();
		tempG = null; tempBI = null;

		imgDim.setSize(
			fm.stringWidth(text),
			fm.getHeight()
		);
		imgDimHalf.setSize(
			imgDim.width / 2,
			imgDim.height / 2
		);
		BufferedImage imgProto = new BufferedImage(
			imgDim.width,
			imgDim.height,
			BufferedImage.TYPE_INT_ARGB
		);

		Graphics2D g = imgProto.createGraphics();
		g.setFont(f);
		g.setColor(new Color(255, 200, 20));
		g.drawString(text, 0, fm.getAscent());
		g.dispose();

		return imgProto;
	}


// //////////////// //
// INSTANCE SECTION //

	private final BufferedImage imgProto;
	private final Dimension imgDim;
	private final Dimension imgDimHalf;
	private final Point origin;
	private final Point position;
	private final Point destination;
	private final Point speed;

	private int scaleAdd = 0;
	private long timeAlive = 0;


	/**
	 * Create a new one of these.
	 * Does some constructing, as one might expect.
	 *
	 * @param emitFrom The point where the event occurred
	 */
	public FlyBase(Point emitFrom, BufferedImage p, Dimension d, Dimension h){
		imgProto = p;
		imgDim = d;
		imgDimHalf = h;

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
	public final boolean tick(long elapsed){
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
	public final void draw(Graphics2D g){
		g.drawImage(
			imgProto.getScaledInstance(-1, imgDim.height + scaleAdd, BufferedImage.SCALE_FAST),
			position.x + origin.x,
			position.y + origin.y,
			null
		);
	}

}

