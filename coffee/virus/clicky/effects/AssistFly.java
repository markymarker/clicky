package coffee.virus.clicky.effects;

import java.awt.Dimension;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 * Assists go flying.
 * This effect gives the player the comforting reassurance of knowing
 * something's got their back. Even if that something is just a bunch of cold
 * robotic click assist machines.
 */
public class AssistFly extends FlyBase {

	private static final BufferedImage imgProto;
	private static final Dimension imgDim;


	/**
	 * Ugh, do I have to do everything.
	 */
	static {
		imgDim = new Dimension();
		imgProto = createText("Assist!", imgDim);
	}


	/**
	 * Special unique constructor.
	 *
	 * @param emitFrom The point where the event occurred
	 */
	public AssistFly(Point emitFrom){
		super(emitFrom, imgProto, imgDim);
	}

}

