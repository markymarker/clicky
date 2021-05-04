package coffee.virus.clicky.effects;

import java.awt.Dimension;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 * Clicks go flying.
 * This effect increases accessibility by providing a textual indicator for
 * when a click has occurred.
 */
public class ClickFly extends FlyBase {

	private static final BufferedImage imgProto;
	private static final Dimension imgDim;


	/**
	 * I gueeeesss I can do some of the setup work, old man.
	 */
	static {
		imgDim = new Dimension();
		imgProto = createText("Click!", imgDim);
	}


	/**
	 * Special unique constructor.
	 *
	 * @param emitFrom The point where the event occurred
	 */
	public ClickFly(Point emitFrom){
		super(emitFrom, imgProto, imgDim);
	}

}

