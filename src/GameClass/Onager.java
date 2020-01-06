package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Default constructor, which constructs a new Onager
 * @param layer Pane
 * @param image Image of Onager
 * @param x Layouts x 
 * @param y Layouts y
 */

public class Onager extends Soldier{


	private static final long serialVersionUID = -6645102720346637461L;

	public Onager(Pane layer, Image image, double x, double y ){
		super(layer, image, x, y, 0, 0, 0, 0, 0);
		setHealth(Settings.ONAGER_HEALTH);
		setDamage(Settings.ONAGER_DAMAGE);
		setSpeed(Settings.ONAGER_SPEED);
	}

}
