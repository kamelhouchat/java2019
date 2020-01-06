package GameClass;

public class Production_unit {
	private char type_soldier ; 
	private int remaining_to_production ;
	private int level_to_product;
	private boolean is_level ;
	private boolean is_soldier ;
	
	/**
	 * Constructor, which constructs a new soldier production unit
	 * @param type_soldier a type of soldier : 'O' -> Onager | 'K' -> Knight | 'P' -> Pikeman
	 * @param remaining_to_production remaining turns before production
	 */
	public Production_unit(char type_soldier, int remaining_to_production) {
		super();
		this.type_soldier = type_soldier ;
		this.remaining_to_production = remaining_to_production;
		is_level = false ;
		is_soldier = true ;
	}
	
	/**
	 * Constructor, which constructs a new level production unit
	 * @param level The level to product
	 * @param remaining_to_production remaining turns before production
	 */
	public Production_unit(int level, int remaining_to_production) {
		super();
		this.level_to_product = level;
		this.remaining_to_production = remaining_to_production;
		is_soldier = false ;
		is_level = true ;
	}
	
	
	public boolean is_level() {
		return is_level;
	}
	public boolean is_soldier() {
		return is_soldier;
	}
	public int getLevel_to_product() {
		return level_to_product;
	}
	public void setLevel_to_product(int level_to_product) {
		this.level_to_product = level_to_product;
	}
	public char getType_soldier() {
		return type_soldier;
	}
	public void setType_soldier(char type_soldier) {
		this.type_soldier = type_soldier;
	}
	public int getRemaining_to_production() {
		return remaining_to_production;
	}
	public void setRemaining_to_production(int remaining_to_production) {
		this.remaining_to_production = remaining_to_production;
	}
	
	/**
	 * Function to decrease the number of laps remaining before production and test if we can start production
	 * @return true if we can start production else false
	 */
	public boolean check_end_of_production_and_decrementing() {
		 this.remaining_to_production -- ;
		 if (remaining_to_production == 0) 
			 return true ;
		 return false;
	}
	
}
