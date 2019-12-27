package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pikeman extends Soldier{

	public Pikeman(Pane layer, Image image, double x, double y, int product_cost, int product_time, int health,
			double damage, double speed) {
		super(layer, image, x, y, product_cost, product_time, health, damage, speed);
		setProduct_cost(Settings.PIKEMAN_PRODUCT_COST);
		setProduct_time(Settings.PIKEMAN_PRODUCT_TIME);
		setHealth(Settings.PIKEMAN_HEALTH);
		setDamage(Settings.PIKEMAN_DAMAGE);
		setSpeed(Settings.PIKEMAN_SPEED);
	}



}
