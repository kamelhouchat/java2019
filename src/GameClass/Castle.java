package GameClass;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Castle extends Sprite{
	
	private String duke ;
	private int treasure ;
	private int level ;
	
	private boolean taken = true ;
	private boolean mine ;

	private Door door ;
	private Castle target ;
	private int nb_soldiers_target ;
	
	private int returned ;
	
	private ArrayList<Onager> onagers_list = new ArrayList<Onager>();
	private ArrayList<Pikeman> pikeman_list = new ArrayList<Pikeman>();
	private ArrayList<Knight> knight_list = new ArrayList<Knight>();
	
	private Image onager_image = new Image(getClass().getResource("/images/onagre.png").toExternalForm(), 30, 30, true, true);
	private Image pikeman_image = new Image(getClass().getResource("/images/pikemen.png").toExternalForm(), 30, 30, true, true);
	private Image knight_image = new Image(getClass().getResource("/images/knight.png").toExternalForm(), 30, 30, true, true);
	
	
	public Castle(Pane layer, Image image, double x, double y, String duke, int treasure, 
			int level, boolean taken, Door door, int nbOnagres, int nbPikemans, int nbKnights, boolean mine) {
		super(layer, image, x, y);
		this.duke = duke;
		this.treasure = treasure;
		this.level = level;
		this.taken = taken;
		this.door = door;
		
		for (int i = 0; i < nbOnagres; i++) {
			this.onagers_list.add(new Onager(layer, onager_image, x+onager_image.getWidth()/2, y+onager_image.getHeight()/2, 0, 0, 0, 0, 0));
			this.onagers_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbPikemans; i++) {
			this.pikeman_list.add(new Pikeman(layer, pikeman_image, x+pikeman_image.getWidth()/2, y+pikeman_image.getHeight()/2, 0, 0, 0, 0, 0));
			this.pikeman_list.get(i).removeFromLayer();
		}
		
		for (int i = 0; i < nbKnights; i++) {
			this.knight_list.add(new Knight(layer, knight_image, x+knight_image.getWidth()/2, y+knight_image.getHeight()/2, 0, 0, 0, 0, 0));
			this.knight_list.get(i).removeFromLayer();
		}
		
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


	public boolean isMine() {
		return mine;
	}


	public void setMine(boolean mine) {
		this.mine = mine;
	} 

}
