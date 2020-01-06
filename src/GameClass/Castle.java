package GameClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Castle extends Sprite implements Serializable{
	
	Random rnd = new Random();
	
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
	
	Image onager_image = new Image(getClass().getResource("/images/onagre.png").toExternalForm(), Settings.SOLDIER_WIDTH, Settings.SOLDIER_WIDTH, true, true);
	Image pikeman_image = new Image(getClass().getResource("/images/pikemen.png").toExternalForm(), Settings.SOLDIER_WIDTH, Settings.SOLDIER_WIDTH, true, true);
	Image knight_image = new Image(getClass().getResource("/images/knight.png").toExternalForm(), Settings.SOLDIER_WIDTH, Settings.SOLDIER_WIDTH, true, true);
	
	/**
	 * Default constructor, which constructs a new Castle
	 * @param layer Pane
	 * @param image Image of castle
	 * @param x Layouts x 
	 * @param y Layouts y
	 * @param duke The name of the duc
	 * @param treasure Treasure
	 * @param level Level of the castle
	 * @param taken Boolean to tell if the castle is taken
	 * @param door Door instance that generate a random door
	 * @param nbOnagres Number of onagers
	 * @param nbPikemans Number of pikemen
	 * @param nbKnights Number of knights
	 * @param my Boolean to tell if the castle belongs to the player
	 */
	public Castle(Pane layer, Image image, double x, double y, String duke, int treasure, 
			int level, boolean taken, Door door, int nbOnagres, int nbPikemans, int nbKnights, boolean my) {
		super(layer, image, x, y);
		this.duke = duke;
		this.treasure = treasure;
		this.level = level;
		this.taken = taken;
		this.door = door;
		this.my = my;
		for (int i = 0; i < nbOnagres; i++) 
			this.onagers_list.add(new Onager(layer, onager_image, this.getCenterX()-onager_image.getWidth()/2, this.getCenterY()-onager_image.getHeight()/2));

		
		for (int i = 0; i < nbPikemans; i++) 
			this.pikeman_list.add(new Pikeman(layer, pikeman_image, this.getCenterX()-pikeman_image.getWidth()/2, this.getCenterY()-pikeman_image.getHeight()/2));

		
		for (int i = 0; i < nbKnights; i++) 
			this.knight_list.add(new Knight(layer, knight_image, this.getCenterX()-knight_image.getWidth()/2, this.getCenterY()-knight_image.getHeight()/2));

		
		if (this.taken)
			this.returned = this.level * 10 ;
		else 
			this.returned = this.level;
	}

		
	public ArrayList<Production_unit> getProduction_queue() {
		return production_queue;
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

	public int getReturned() {
		return returned;
	}

	public void setReturned(int returned) {
		this.returned = returned;
	}

	public ArrayList<Soldier> getOnagers_list() {
		return onagers_list;
	}

	public ArrayList<Soldier> getPikeman_list() {
		return pikeman_list;
	}

	public ArrayList<Soldier> getKnight_list() {
		return knight_list;
	}

	public boolean isMy() {
		return my;
	}

	public void setMy(boolean my) {
		this.my = my;
	} 

	/**
	 * Update returned whene the level of the castle is changed
	 */
	public void update_returned() {
		if (this.taken)
			this.returned = this.level * 10 ;
		else 
			this.returned = (this.level * 10)/10;
	}
	
	/**
	 * Update treasure compared to returned
	 */
	public void update_treasure() {
		this.treasure += this.returned;
	}

	/**
	 * Increase level by 1
	 */
	public void increment_level() {
		this.level ++ ;
	}
	
	/**
	 * Damage the first onager of the onagers list
	 */
	public void damage_onager() {
		onagers_list.get(0).damaged();
		onagers_list.get(0).checkRemovability();
	}
	
	/**
	 * Damage the first knight of the knight list
	 */
	public void damage_knight() {
		knight_list.get(0).damaged();
		knight_list.get(0).checkRemovability();
	}
	
	/**
	 * Damage the first pikeman of the pikmen list
	 */
	public void damage_pikeman() {
		pikeman_list.get(0).damaged();
		pikeman_list.get(0).checkRemovability();
	}
	
	/**
	 * Apply each point of damage of the soldier in a random way on the troops of castle
	 * @param soldier The soldier who attacks
	 * @return true if the castle has enough troops to resist the attack, else false
	 */
	public boolean damagedBy(Soldier soldier) {
		int generated = 0 ;
		for (int i = 0; i < soldier.getDamage() ; i++) {
			generated = rnd.nextInt(3);
			if (generated == 0) {
				if (!onagers_list.isEmpty()) damage_onager();
				else if (!knight_list.isEmpty()) damage_knight();
				else if (!pikeman_list.isEmpty()) damage_pikeman();
				else return false ;
			}
			else if (generated == 1) {
				if (!knight_list.isEmpty()) damage_knight();
				else if (!onagers_list.isEmpty()) damage_onager();
				else if (!pikeman_list.isEmpty()) damage_pikeman();
				else return false ;
			}
			else {
				if (!pikeman_list.isEmpty()) damage_pikeman();
				else if (!onagers_list.isEmpty()) damage_onager();
				else if (!knight_list.isEmpty()) damage_knight();
				else return false ;
			}
		}
		return true ; 
	}

}
