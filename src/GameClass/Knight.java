package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Knight extends Soldier{

	private static final long serialVersionUID = 8725555800868618506L;

	/**
	 * Default constructor, which constructs a new Knight
	 * @param layer Pane
	 * @param image Image of Knight
	 * @param x Layouts x 
	 * @param y Layouts y
	 */
	public Knight(Pane layer, Image image, double x, double y) {
		super(layer, image, x, y, 0, 0, 0, 0, 0);
		setHealth(Settings.KNIGHT_HEALTH);
		setDamage(Settings.KNIGHT_DAMAGE);
		setSpeed(Settings.KNIGHT_SPEED);
	}

}
