package GameClass;

import java.util.ArrayList;

public class Target {
	 
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
	
	public void send_target_to_castle() {			
		
		if (this.getAttacking().getX() < this.getTargetted().getX()) {
			double x1 = this.getAttacking().getX();
			double x2 = this.getTargetted().getX();
			double y1 = this.getAttacking().getY();
			double y2 = this.getTargetted().getY();
			
			this.check_collision(this.getSent_knight_list(), Settings.KNIGHT_SPEED, y1, y2, x1, x2, 'K');
			this.check_collision(this.getSent_onagers_list(), Settings.ONAGER_SPEED, y1, y2, x1, x2, 'O');
			this.check_collision(this.getSent_pikeman_list(), Settings.PIKEMAN_SPEED, y1, y2, x1, x2, 'P');
		}
		
		else if (this.getAttacking().getX() > this.getTargetted().getX() ) {
			double x2 = this.getAttacking().getX();
			double x1 = this.getTargetted().getX();
			double y2 = this.getAttacking().getY();
			double y1 = this.getTargetted().getY();
			
			this.check_collision(this.getSent_knight_list(), -Settings.KNIGHT_SPEED, y1, y2, x1, x2, 'K');
			this.check_collision(this.getSent_onagers_list(), -Settings.ONAGER_SPEED, y1, y2, x1, x2, 'O');
			this.check_collision(this.getSent_pikeman_list(), -Settings.PIKEMAN_SPEED, y1, y2, x1, x2, 'P');
		}
			
	}
	
	public void check_collision(ArrayList<Soldier> soldiers, double speed, double y1, double y2, double x1, double x2, char type) {
		
		soldiers.forEach(soldier -> {
			soldier.setDx(speed);
			soldier.setDy((y1-y2)/(x1-x2));
			if (!soldier.collidesWith(this.targetted)) {
				soldier.move();
			}
			else {
				//soldier.moveInCircle(90, Settings.CASTLE_WIDTH, this.targetted.getCenterX(), this.targetted.getCenterY());
				if (this.targetted.isMy()) {
					switch (type) {
					case 'O':
						targetted.getOnagers_list().add(new Onager(targetted.getLayer(), targetted.onager_image, targetted.getCenterX()-targetted.onager_image.getWidth()/2, targetted.getCenterY()-targetted.onager_image.getHeight()/2));
						break ;
					case 'P':
						targetted.getPikeman_list().add(new Pikeman(targetted.getLayer(), targetted.pikeman_image, targetted.getCenterX()-targetted.pikeman_image.getWidth()/2, targetted.getCenterY()-targetted.pikeman_image.getHeight()/2));
						break ;
					case 'K':
						targetted.getKnight_list().add(new Knight(targetted.getLayer(), targetted.knight_image, targetted.getCenterX()-targetted.knight_image.getWidth()/2, targetted.getCenterY()-targetted.knight_image.getHeight()/2));
						break ;
					}
					soldier.setRemovable(true);
				}
				else {
					
				}
			}	
		});
	}
	
	
}
