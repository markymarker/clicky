package coffee.virus.clicky.effects;

import java.awt.Dimension;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 * Assists go flying.
 * This effect gives the player the comforting reassurance of knowing
 * something's got their back. Even if that something is just a bunch of cold
 * robotic click assist machines.
 *
 * The way that position and movement is tracked is based around a (0, 0)
 * origin internally, then at the time of drawing, position is shifted to use
 * the point of emission as the origin.
 */
public class AssistFly extends FlyBase {

	private static final BufferedImage imgProto;
	private static final Dimension imgDim;
	private static final Dimension imgDimHalf;


	/**
	 * Ugh, do I have to do everything.
	 */
	static {
		imgDim = new Dimension();
		imgDimHalf = new Dimension();
		imgProto = createText("Assist!", imgDim, imgDimHalf);
	}


	/**
	 * Special unique constructor.
	 * @see FlyBase
	 */
	public AssistFly(Point emitFrom){
		super(emitFrom, imgProto, imgDim, imgDimHalf);
	}

}

