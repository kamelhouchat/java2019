package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Onager extends Soldier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6645102720346637461L;

	public Onager(Pane layer, Image image, double x, double y ){
		super(layer, image, x, y, 0, 0, 0, 0, 0);
		setProduct_cost(Settings.ONAGER_PRODUCT_COST);
		setProduct_time(Settings.ONAGER_PRODUCT_TIME);
		setHealth(Settings.ONAGER_HEALTH);
		setDamage(Settings.ONAGER_DAMAGE);
		setSpeed(Settings.ONAGER_SPEED);
	}

}
