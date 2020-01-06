package GameClass;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Target {
	
	private int troup_out_door = 0 ;
	private boolean out_of_door = false ;
	private Castle attacking;
	private Castle targetted;
	private int nb_onager = 0 ;
	private int nb_pikeman = 0 ;
	private int nb_knight = 0 ;
	private ArrayList<Soldier> sent_onagers_list = new ArrayList<Soldier>();
	private ArrayList<Soldier> sent_pikeman_list = new ArrayList<Soldier>();
	private ArrayList<Soldier> sent_knight_list = new ArrayList<Soldier>();
	
	/**
	 * Default constructor, which constructs a new Target
	 * @param attacking The attacking castle
	 * @param targetted The castle target
	 * @param nb_onager The number of Onager we want to send
	 * @param nb_pikeman The number of Pikeman we want to send
	 * @param nb_knight The number of Knight we want to send
	 * @param sent_onagers_list The list of Onager we send
	 * @param sent_pikeman_list The list of Pikeman we send
	 * @param sent_knight_list The list of Knight we send
	 */
	public Target(Castle attacking, Castle targetted, int nb_onager, int nb_pikeman, int nb_knight,
			ArrayList<Soldier> sent_onagers_list, ArrayList<Soldier> sent_pikeman_list,
			ArrayList<Soldier> sent_knight_list) {
		super();
		this.attacking = attacking;
		this.targetted = targetted;
		this.nb_onager = nb_onager;
		this.nb_pikeman = nb_pikeman;
		this.nb_knight = nb_knight;
		this.sent_onagers_list = sent_onagers_list;
		this.sent_pikeman_list = sent_pikeman_list;
		this.sent_knight_list = sent_knight_list;
		
	}

	public ArrayList<Soldier> getSent_onagers_list() {
		return sent_onagers_list;
	}
	public ArrayList<Soldier> getSent_pikeman_list() {
		return sent_pikeman_list;
	}
	public ArrayList<Soldier> getSent_knight_list() {
		return sent_knight_list;
	}
	public Castle getAttacking() {
		return attacking;
	}
	public Castle getTargetted() {
		return targetted;
	}
	public int getNb_onager() {
		return nb_onager;
	}
	public void setNb_onager(int nb_onager) {
		this.nb_onager = nb_onager;
	}
	public int getNb_pikeman() {
		return nb_pikeman;
	}
	public void setNb_pikeman(int nb_pikemant) {
		this.nb_pikeman = nb_pikemant;
	}
	public int getNb_knight() {
		return nb_knight;
	}
	public void setNb_knight(int nb_knight) {
		this.nb_knight = nb_knight;
	}		
	public boolean isOut_of_door() {
		return out_of_door;
	}

	/**
	 * Function to send troops, type by type, from the slowest to the fastest, 
	 * depending on the position of the door of the attacking castle.
	 * 
	 * It puts the value of out_of_door on true whene it finishes.
	 * 
	 */
	public void send_out_of_door() {
		switch (attacking.getDoor().getDirection()) {
		case 'S' :
			if (!sent_onagers_list.isEmpty() && troup_out_door == 0) 
				sent_onagers_list.forEach(onager -> {
					onager.moveDown();
					if (onager.getY() > attacking.getCenterY()+Settings.CASTLE_WIDTH )
						troup_out_door = sent_onagers_list.size() ;
				});
			if (!sent_pikeman_list.isEmpty() && troup_out_door == sent_onagers_list.size()) 
				sent_pikeman_list.forEach(pikeman -> {
					pikeman.moveDown();
					if (pikeman.getY() > attacking.getCenterY()+Settings.CASTLE_WIDTH)
						troup_out_door = sent_onagers_list.size() + sent_pikeman_list.size();
				});
			if (!sent_knight_list.isEmpty() && troup_out_door == sent_onagers_list.size() + sent_pikeman_list.size()) 
				sent_knight_list.forEach(knight -> {
					knight.moveDown();
					if (knight.getY() > attacking.getCenterY()+Settings.CASTLE_WIDTH)
						troup_out_door = sent_onagers_list.size() + sent_pikeman_list.size() + sent_knight_list.size();
				});
			break ;
		case 'E' : 
			if (!sent_onagers_list.isEmpty() && troup_out_door == 0) 
				sent_onagers_list.forEach(onager -> {
					onager.moveRight();
					if (onager.getX() > attacking.getCenterX()+Settings.CASTLE_WIDTH )
						troup_out_door = sent_onagers_list.size();
				});
			if (!sent_pikeman_list.isEmpty() && troup_out_door == sent_onagers_list.size()) 
				sent_pikeman_list.forEach(pikeman -> {
					pikeman.moveRight();
					if (pikeman.getX() > attacking.getCenterX()+Settings.CASTLE_WIDTH)
						troup_out_door = sent_pikeman_list.size() + sent_onagers_list.size() ;
				});
			if (!sent_knight_list.isEmpty() && troup_out_door == sent_onagers_list.size() + sent_pikeman_list.size()) 
				sent_knight_list.forEach(knight -> {
					knight.moveRight();
					if (knight.getX() > attacking.getCenterX()+Settings.CASTLE_WIDTH)
						troup_out_door = sent_pikeman_list.size() + sent_onagers_list.size() + sent_knight_list.size();
				});
			break ;
		case 'N' :
			if (!sent_onagers_list.isEmpty() && troup_out_door == 0) 
				sent_onagers_list.forEach(onager -> {
					onager.moveUp();
					if (onager.getY() < attacking.getCenterY()-Settings.CASTLE_WIDTH )
						troup_out_door = sent_onagers_list.size();
				});
			if (!sent_pikeman_list.isEmpty() && troup_out_door == sent_onagers_list.size()) 
				sent_pikeman_list.forEach(pikeman -> {
					pikeman.moveUp();
					if (pikeman.getY() < attacking.getCenterY()-Settings.CASTLE_WIDTH)
						troup_out_door = sent_onagers_list.size() + sent_pikeman_list.size();
				});
			if (!sent_knight_list.isEmpty() && troup_out_door == sent_onagers_list.size() + sent_pikeman_list.size()) 
				sent_knight_list.forEach(knight -> {
					knight.moveUp();
					if (knight.getY() < attacking.getCenterY()-Settings.CASTLE_WIDTH)
						troup_out_door = sent_pikeman_list.size() + sent_onagers_list.size() + sent_knight_list.size();
				});
			break ;
		case 'O' :
			if (!sent_onagers_list.isEmpty() && troup_out_door == 0) 
				sent_onagers_list.forEach(onager -> {
					onager.moveLeft();
					if (onager.getX() < attacking.getCenterX()-Settings.CASTLE_WIDTH )
						troup_out_door = sent_onagers_list.size();
				});
			if (!sent_pikeman_list.isEmpty() && troup_out_door == sent_onagers_list.size()) 
				sent_pikeman_list.forEach(pikeman -> {
					pikeman.moveLeft();
					if (pikeman.getX() < attacking.getCenterX()-Settings.CASTLE_WIDTH)
						troup_out_door = sent_onagers_list.size() + sent_pikeman_list.size();
				});
			if (!sent_knight_list.isEmpty() && troup_out_door == sent_onagers_list.size() + sent_pikeman_list.size()) 
				sent_knight_list.forEach(knight -> {
					knight.moveLeft();
					if (knight.getX() < attacking.getCenterX()-Settings.CASTLE_WIDTH)
						troup_out_door = sent_onagers_list.size() + sent_pikeman_list.size() + sent_knight_list.size();
				});
			break ;
		}
		this.out_of_door = this.troup_out_door == sent_knight_list.size() + sent_onagers_list.size() + sent_pikeman_list.size();
	}
	
	/**
	 * Move the troops to the castle target
	 */
	public void send_soldiers() {
		sent_knight_list.forEach(knight -> knight.moveTo(targetted));	
		sent_onagers_list.forEach(onager -> onager.moveTo(targetted));
		sent_pikeman_list.forEach(pikeman -> pikeman.moveTo(targetted));
	}
	
	/**
	 * Damage the castle target by soldier, and apply each point of damage of the soldier in a random way on the troops of castle
	 * If the soldiers of the attacked castle are all dead the attacker wins the castle
	 * @param soldier The soldier who attacks
	 */
	public void damageTarget(Soldier soldier) {
		if (!targetted.damagedBy(soldier)) {
			targetted.getProduction_queue().clear();
			targetted.setDuke(attacking.getDuke());
			if (attacking.isMy()) {
				targetted.setMy(true);
				switch (targetted.getDoor().getDirection()) {
				case ('N'):
					targetted.setView(new Image(getClass().getResource("/images/my_castleN.jpg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_WIDTH, true, true));
					break;
				case ('E'):
					targetted.setView(new Image(getClass().getResource("/images/my_castleE.jpg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_WIDTH, true, true));
					break;
				case ('S'):
					targetted.setView(new Image(getClass().getResource("/images/my_castleS.jpg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_WIDTH, true, true));
					break;
				case ('O'):
					targetted.setView(new Image(getClass().getResource("/images/my_castleO.jpg").toExternalForm(), Settings.CASTLE_WIDTH, Settings.CASTLE_WIDTH, true, true));
					break;
				}
			}
		}
		soldier.setRemovable(true);
	}
}
