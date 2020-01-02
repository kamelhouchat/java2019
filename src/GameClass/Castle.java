package GameClass;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Castle extends Sprite implements Serializable{
	
	private static final long serialVersionUID = 8091028447286769788L;
	private String duke ;
	private int treasure ;
	private int level ;
	
	private boolean taken = true ;
	private boolean my ;

	private Door door ;
	
	private int returned ;
	
	private ArrayList<Soldier> onagers_list = new ArrayList<Soldier>();
	private ArrayList<Soldier> pikeman_list = new ArrayList<Soldier>();
	private ArrayList<Soldier> knight_list = new ArrayList<Soldier>();
	
	private ArrayList<Production_unit> production_queue = new ArrayList<Production_unit>();
	
	Image onager_image = new Image(getClass().getResource("/images/onagre.png").toExternalForm(), 50, 50, true, true);
	Image pikeman_image = new Image(getClass().getResource("/images/pikemen.png").toExternalForm(), 50, 50, true, true);
	Image knight_image = new Image(getClass().getResource("/images/knight.png").toExternalForm(), 50, 50, true, true);
	
	
	public Castle(Pane layer, Image image, double x, double y, String duke, int treasure, 
			int level, boolean taken, Door door, int nbOnagres, int nbPikemans, int nbKnights, boolean my) {
		super(layer, image, x, y);
		this.removeFromLayer();
		this.duke = duke;
		this.treasure = treasure;
		this.level = level;
		this.taken = taken;
		this.door = door;
		this.my = my;
		for (int i = 0; i < nbOnagres; i++) {
			this.onagers_list.add(new Onager(layer, onager_image, this.getCenterX()-onager_image.getWidth()/2, this.getCenterY()-onager_image.getHeight()/2));
			//this.onagers_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbPikemans; i++) {
			this.pikeman_list.add(new Pikeman(layer, pikeman_image, this.getCenterX()-pikeman_image.getWidth()/2, this.getCenterY()-pikeman_image.getHeight()/2));
			//this.pikeman_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbKnights; i++) {
			this.knight_list.add(new Knight(layer, knight_image, this.getCenterX()-knight_image.getWidth()/2, this.getCenterY()-knight_image.getHeight()/2));
			//this.knight_list.get(i).removeFromLayer();
		}
		
		if (this.taken)
			this.returned = this.level * 10 ;
		else 
			this.returned = (this.level * 10)/10;
	}

		
	public ArrayList<Production_unit> getProduction_queue() {
		return production_queue;
	}


	public void setProduction_queue(ArrayList<Production_unit> production_queue) {
		this.production_queue = production_queue;
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

	public int getReturned() {
		return returned;
	}


	public void setReturned(int returned) {
		this.returned = returned;
	}


	public ArrayList<Soldier> getOnagers_list() {
		return onagers_list;
	}


	public void setOnagers_list(ArrayList<Soldier> onagers_list) {
		this.onagers_list = onagers_list;
	}


	public ArrayList<Soldier> getPikeman_list() {
		return pikeman_list;
	}


	public void setPikeman_list(ArrayList<Soldier> pikeman_list) {
		this.pikeman_list = pikeman_list;
	}


	public ArrayList<Soldier> getKnight_list() {
		return knight_list;
	}


	public void setKnight_list(ArrayList<Soldier> knight_list) {
		this.knight_list = knight_list;
	}


	public boolean isMy() {
		return my;
	}


	public void setMy(boolean my) {
		this.my = my;
	} 

	public void update_returned() {
		if (this.taken)
			this.returned = this.level * 10 ;
		else 
			this.returned = (this.level * 10)/10;
	}
	
	public void update_treasure() {
		this.treasure += this.returned;
	}

	public void increment_level() {
		this.level ++ ;
	}

}
