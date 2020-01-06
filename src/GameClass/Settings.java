package GameClass;

public class Settings {
	
	/**
	 * The scene width must be greater then 650
	 */
	
	public static double SCENE_WIDTH = 750;
    public static double SCENE_HEIGHT = SCENE_WIDTH-(SCENE_WIDTH/3) ;//500;
	public static final double STATUS_BAR_HEIGHT = SCENE_HEIGHT/5;
	public static final double CASTLE_WIDTH = ( SCENE_WIDTH+SCENE_HEIGHT ) / 25;
	public static final double SOLDIER_WIDTH = CASTLE_WIDTH/2 ;

	public static final int PIKEMAN_PRODUCT_COST = 100;
	public static final int PIKEMAN_PRODUCT_TIME = 5;
    public static final double PIKEMAN_SPEED = 2.0;
    public static final int    PIKEMAN_HEALTH = 1;
    public static final double PIKEMAN_DAMAGE = 1.0;
    

    public static final int KNIGHT_PRODUCT_COST = 500;
	public static final int KNIGHT_PRODUCT_TIME = 20;
    public static final double KNIGHT_SPEED = 6.0;
    public static final int    KNIGHT_HEALTH = 3;
    public static final double KNIGHT_DAMAGE = 5.0;
    
    
    public static final int ONAGER_PRODUCT_COST = 1000;
	public static final int ONAGER_PRODUCT_TIME = 50;
    public static final double ONAGER_SPEED = 1.0;
    public static final int    ONAGER_HEALTH = 5;
    public static final double ONAGER_DAMAGE = 10.0;
    
    /**
     * TOTAL_PLAYERS -> Number of castles
     * MIN_DISTANCE_2_CASTLES -> Minimum distance between two castles
     * TIME_PER_LAP -> The time between two towers
     */
    public static final int TOTAL_PLAYERS = 5;
    public static final int MIN_DISTANCE_2_CASTLES = (int) (SCENE_HEIGHT+SCENE_WIDTH)/10 ;//100;
    static final long TIME_PER_LAP = 60_000_000 ; //1000 * 1000 * 1000 ;//
   
}
