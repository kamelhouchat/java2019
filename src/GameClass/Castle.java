package GameClass;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Castle extends Sprite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091028447286769788L;
	private String duke ;
	private int treasure ;
	private int level ;
	
	private boolean taken = true ;
	private boolean my ;

	private Door door ;
	
	private Castle target ;
	private int nb_onager_target = 0 ;
	private int nb_pikeman_target = 0 ;
	private int nb_knight_target = 0 ;
	
	private int returned ;
	
	private ArrayList<Onager> onagers_list = new ArrayList<Onager>();
	private ArrayList<Pikeman> pikeman_list = new ArrayList<Pikeman>();
	private ArrayList<Knight> knight_list = new ArrayList<Knight>();
	
	private ArrayList<Production_unit> production_queue = new ArrayList<Production_unit>();
	
	Image onager_image = new Image(getClass().getResource("/images/onagre.png").toExternalForm(), 30, 30, true, true);
	Image pikeman_image = new Image(getClass().getResource("/images/pikemen.png").toExternalForm(), 30, 30, true, true);
	Image knight_image = new Image(getClass().getResource("/images/knight.png").toExternalForm(), 30, 30, true, true);
	
	
	public Castle(Pane layer, Image image, double x, double y, String duke, int treasure, 
			int level, boolean taken, Door door, int nbOnagres, int nbPikemans, int nbKnights, boolean my) {
		super(layer, image, x, y);
		this.duke = duke;
		this.treasure = treasure;
		this.level = level;
		this.taken = taken;
		this.door = door;
		this.my = my;
		
		for (int i = 0; i < nbOnagres; i++) {
			this.onagers_list.add(new Onager(layer, onager_image, x, y));
			this.onagers_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbPikemans; i++) {
			this.pikeman_list.add(new Pikeman(layer, pikeman_image, x, y));
			this.pikeman_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbKnights; i++) {
			this.knight_list.add(new Knight(layer, knight_image, x, y));
			this.knight_list.get(i).removeFromLayer();
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
	
	public Castle getTarget() {
		return target;
	}
	
	public void setTarget(Castle target) {
		this.target = target;
	}
	
	
	public int getNb_onager_target() {
		return nb_onager_target;
	}


	public void setNb_onager_target(int nb_onager_target) {
		this.nb_onager_target = nb_onager_target;
	}


	public int getNb_pikeman_target() {
		return nb_pikeman_target;
	}


	public void setNb_pikeman_target(int nb_pikeman_target) {
		this.nb_pikeman_target = nb_pikeman_target;
	}


	public int getNb_knight_target() {
		return nb_knight_target;
	}


	public void setNb_knight_target(int nb_knight_target) {
		this.nb_knight_target = nb_knight_target;
	}


	public int getReturned() {
		return returned;
	}


	public void setReturned(int returned) {
		this.returned = returned;
	}


	public ArrayList<Onager> getOnagers_list() {
		return onagers_list;
	}


	public void setOnagers_list(ArrayList<Onager> onagers_list) {
		this.onagers_list = onagers_list;
	}


	public ArrayList<Pikeman> getPikeman_list() {
		return pikeman_list;
	}


	public void setPikeman_list(ArrayList<Pikeman> pikeman_list) {
		this.pikeman_list = pikeman_list;
	}


	public ArrayList<Knight> getKnight_list() {
		return knight_list;
	}


	public void setKnight_list(ArrayList<Knight> knight_list) {
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
