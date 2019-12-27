package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Castle extends Sprite{
	
	private String duke ;
	private int treasure ;
	private int level ;
	
	private boolean taken = true ;

	private Door door ;
	private Castle target ;
	private int nb_soldiers_target ;
	
	
	public Castle(Pane layer, Image image, double x, double y, String duke, int treasure, 
			int level, boolean taken, Door door) {
		super(layer, image, x, y);
		this.duke = duke;
		this.treasure = treasure;
		this.level = level;
		this.taken = taken;
		this.door = door;
	}


	public String getDuke() {
		return duke;
	}


	public void setDuke(String duke) {
		this.duke = duke;
	}


	public int getTreasure() {
		return treasure;
	}


	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public boolean isTaken() {
		return taken;
	}


	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Door getDoor() {
		return door;
	}


	public void setDoor(Door door) {
		this.door = door;
	}
	
	public Castle getTarget() {
		return target;
	}
	
	public void setTarget(Castle target) {
		this.target = target;
	}
	
	public int getNb_soldiers() {
		return nb_soldiers_target;
	}
	
	public void setNb_soldiers(int nb_soldiers) {
		this.nb_soldiers_target = nb_soldiers;
	} 
	

	
}
