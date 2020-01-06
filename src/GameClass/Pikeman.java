package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pikeman extends Soldier {

	private static final long serialVersionUID = 3881272652796476451L;

	/**
	 * Default constructor, which constructs a new Pikeman
	 * @param layer Pane
	 * @param image Image of Pikeman
	 * @param x Layouts x 
	 * @param y Layouts y
	 */
	
	public Pikeman(Pane layer, Image image, double x, double y) {
		super(layer, image, x, y, 0, 0, 0, 0, 0);
		setProduct_cost(Settings.PIKEMAN_PRODUCT_COST);
		setProduct_time(Settings.PIKEMAN_PRODUCT_TIME);
		setHealth(Settings.PIKEMAN_HEALTH);
		setDamage(Settings.PIKEMAN_DAMAGE);
		setSpeed(Settings.PIKEMAN_SPEED);
	}



}
