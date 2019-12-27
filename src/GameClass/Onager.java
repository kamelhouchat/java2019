package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Onager extends Soldier{

	public Onager(Pane layer, Image image, double x, double y, int product_cost, int product_time, int health,
			double damage, double speed) {
		super(layer, image, x, y, product_cost, product_time, health, damage, speed);
		setProduct_cost(Settings.ONAGER_PRODUCT_COST);
		setProduct_time(Settings.ONAGER_PRODUCT_TIME);
		setHealth(Settings.ONAGER_HEALTH);
		setDamage(Settings.ONAGER_DAMAGE);
		setSpeed(Settings.ONAGER_SPEED);
	}

	
	
}
