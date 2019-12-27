package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Knight extends Soldier{

	public Knight(Pane layer, Image image, double x, double y, int product_cost, int product_time, int health,
			double damage, double speed) {
		super(layer, image, x, y, product_cost, product_time, health, damage, speed);
		setProduct_cost(Settings.KNIGHT_PRODUCT_COST);
		setProduct_time(Settings.KNIGHT_PRODUCT_TIME);
		setHealth(Settings.KNIGHT_HEALTH);
		setDamage(Settings.KNIGHT_DAMAGE);
		setSpeed(Settings.KNIGHT_SPEED);
	}

}
