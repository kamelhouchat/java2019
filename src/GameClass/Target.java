package GameClass;

import java.util.ArrayList;

public class Target {
	
	private int troop_size = 0 ; 
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
		this.troop_size = this.sent_knight_list.size() + this.sent_onagers_list.size() + this.sent_pikeman_list.size() ;
		
	}

	public ArrayList<Soldier> getSent_onagers_list() {
		return sent_onagers_list;
	}


	public void setSent_onagers_list(ArrayList<Soldier> sent_onagers_list) {
		this.sent_onagers_list = sent_onagers_list;
	}
	
	public ArrayList<Soldier> getSent_pikeman_list() {
		return sent_pikeman_list;
	}

	public void setSent_pikeman_list(ArrayList<Soldier> sent_pikeman_list) {
		this.sent_pikeman_list = sent_pikeman_list;
	}

	public ArrayList<Soldier> getSent_knight_list() {
		return sent_knight_list;
	}

	public void setSent_knight_list(ArrayList<Soldier> sent_knight_list) {
		this.sent_knight_list = sent_knight_list;
	}

	public Castle getAttacking() {
		return attacking;
	}
	
	public void setAttacking(Castle attacking) {
		this.attacking = attacking;
	}
	public Castle getTargetted() {
		return targetted;
	}
	public void setTargetted(Castle targetted) {
		this.targetted = targetted;
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
	
	
	
	public void send_target_to_castle() {			
		sent_knight_list.forEach(knight -> {
			if (!knight.collidesWith(this.targetted))
				knight.moveTo(targetted);
			else {
				if (this.targetted.isMy()) {
					targetted.getKnight_list().add(new Knight(targetted.getLayer(), targetted.knight_image, targetted.getCenterX()-targetted.knight_image.getWidth()/2, targetted.getCenterY()-targetted.knight_image.getHeight()/2));
					knight.setRemovable(true);
				}
				else
					damageTarget(knight);
			}
		});	
		sent_onagers_list.forEach(onager -> {
			if (!onager.collidesWith(this.targetted))
				onager.moveTo(targetted);
			else {
				if (this.targetted.isMy()) {
					targetted.getOnagers_list().add(new Onager(targetted.getLayer(), targetted.onager_image, targetted.getCenterX()-targetted.onager_image.getWidth()/2, targetted.getCenterY()-targetted.onager_image.getHeight()/2));
					onager.setRemovable(true);
				}
				else
					damageTarget(onager);
			}
		});
		sent_pikeman_list.forEach(pikeman -> {
			if (!pikeman.collidesWith(this.targetted))
				pikeman.moveTo(targetted);
			else {
				if (this.targetted.isMy()) {
					targetted.getPikeman_list().add(new Pikeman(targetted.getLayer(), targetted.pikeman_image, targetted.getCenterX()-targetted.pikeman_image.getWidth()/2, targetted.getCenterY()-targetted.pikeman_image.getHeight()/2));
					pikeman.setRemovable(true);
				}
				else
					damageTarget(pikeman);
			}
				
		});
	}
	
	public void damageTarget(Soldier soldier) {
		if (!targetted.damagedBy(soldier)) {
			targetted.getProduction_queue().clear();
			targetted.setDuke(attacking.getDuke());
			if (attacking.isMy()) {
				targetted.setMy(true);
			}
		}
		soldier.setRemovable(true);
	}
}
