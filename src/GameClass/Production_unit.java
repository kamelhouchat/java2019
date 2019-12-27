package GameClass;

public class Production_unit {
	private Soldier soldier ; 
	private int remaining_to_production ;
	
	public Production_unit(Soldier soldier, int remaining_to_production) {
		super();
		this.soldier = soldier;
		this.remaining_to_production = remaining_to_production;
	}
	
	public Soldier getSoldier() {
		return soldier;
	}
	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}
	public int getRemaining_to_production() {
		return remaining_to_production;
	}
	public void setRemaining_to_production(int remaining_to_production) {
		this.remaining_to_production = remaining_to_production;
	}
	
}
