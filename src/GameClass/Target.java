package GameClass;

import java.util.ArrayList;

public class Target {
	 
	private Castle attacking;
	private Castle targetted;
	private int nb_onager = 0 ;
	private int nb_pikeman = 0 ;
	private int nb_knight = 0 ;
	private ArrayList<Onager> sent_onagers_list = new ArrayList<Onager>();
	private ArrayList<Pikeman> sent_pikeman_list = new ArrayList<Pikeman>();
	private ArrayList<Knight> sent_knight_list = new ArrayList<Knight>();
	
	public Target(Castle attacking, Castle targetted, int nb_onager, int nb_pikeman, int nb_knight,
			ArrayList<Onager> sent_onagers_list, ArrayList<Pikeman> sent_pikeman_list,
			ArrayList<Knight> sent_knight_list) {
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

	public ArrayList<Onager> getSent_onagers_list() {
		return sent_onagers_list;
	}


	public void setSent_onagers_list(ArrayList<Onager> sent_onagers_list) {
		this.sent_onagers_list = sent_onagers_list;
	}


	public ArrayList<Pikeman> getSent_pikeman_list() {
		return sent_pikeman_list;
	}


	public void setSent_pikeman_list(ArrayList<Pikeman> sent_pikeman_list) {
		this.sent_pikeman_list = sent_pikeman_list;
	}


	public ArrayList<Knight> getSent_knight_list() {
		return sent_knight_list;
	}

	public void setSent_knight_list(ArrayList<Knight> sent_knight_list) {
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
	
}
