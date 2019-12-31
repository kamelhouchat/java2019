package GameClass;

public class Target {
	 
	private Castle attacking;
	private Castle targetted;
	private int nb_onager = 0 ;
	private int nb_pikeman = 0 ;
	private int nb_knight = 0 ;
	
	
	public Target(Castle attacking, Castle targetted, int nb_onager, int nb_pikemant, int nb_knight) {
		super();
		this.attacking = attacking;
		this.targetted = targetted;
		
		this.nb_onager = nb_onager;
		this.nb_pikeman = nb_pikemant;
		this.nb_knight = nb_knight;
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
