package GameClass;

import javafx.collections.SetChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public abstract class Soldier extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8131521196948972410L;
	private int product_cost ; 
	private int product_time ; 
	private int health	;
	private double damage ;
	private double speed ;
	
	private boolean removable = false;
	
    protected double dx;
    protected double dy;

	public Soldier(Pane layer, Image image, double x, double y, int product_cost, int product_time, int health,
			double damage, double speed) {
		super(layer, image, x, y);
		removeFromLayer();
		this.product_cost = product_cost;
		this.product_time = product_time;
		this.health = health;
		this.damage = damage;
		this.speed = speed ;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getProduct_cost() {
		return product_cost;
	}
	
	public void setProduct_cost(int product_cost) {
		this.product_cost = product_cost;
	}

	public int getProduct_time() {
		return product_time;
	}

	public void setProduct_time(int product_time) {
		this.product_time = product_time;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void move() {
        x += dx;
        y += dy * dx;
        this.updateUI();
    }
	
	public void moveInCircle(double a, double r, double cx, double cy) {//calcul a faire	
    	centerX = ( cx + r * Math.cos(a)) - 1;
    	centerY = ( cy + r * Math.sin(a)) - 1;
    	this.updateUI();
    }
	
	public boolean nearCastle(Sprite castle) {
    	return ( dist(castle.getCenterX(), this.getCenterX(), castle.getCenterY(), this.getCenterY())) <= 
    			dist(castle.getCenterX(), castle.getX(), castle.getCenterY(), castle.getY()) + dist(this.getCenterX(), this.getX(), this.getCenterY(), this.getY()) ;
    }
	
    public boolean isAlive() {
        return health > 0;
    }

    public void damagedBy( Soldier soldier) {
        health -= soldier.getDamage();
    }
    
    public void remove() {
        this.removable = true;
    }	
	
	public void checkRemovability() {
		if (!isAlive())
			remove();
	}

}
