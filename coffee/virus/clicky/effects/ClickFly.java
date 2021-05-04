package coffee.virus.clicky.effects;

import java.awt.Dimension;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 * Clicks go flying.
 * This effect increases accessibility by providing a textual indicator for
 * when a click has occurred.
 *
 * The way that position and movement is tracked is based around a (0, 0)
 * origin internally, then at the time of drawing, position is shifted to use
 * the point of emission as the origin.
 */
public class ClickFly extends FlyBase {

	private static final BufferedImage imgProto;
	private static final Dimension imgDim;
	private static final Dimension imgDimHalf;


	/**
	 * I gueeeesss I can do some of the setup work, old man.
	 */
	static {
		imgDim = new Dimension();
		imgDimHalf = new Dimension();
		imgProto = createText("Click!", imgDim, imgDimHalf);
	}


	/**
	 * Special unique constructor.
	 * @see FlyBase
	 */
	public ClickFly(Point emitFrom){
		super(emitFrom, imgProto, imgDim, imgDimHalf);
	}

}

