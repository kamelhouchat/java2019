package GameClass;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public abstract class Soldier extends Sprite {

	private static final long serialVersionUID = -8131521196948972410L;
	private int health	;
	private double damage ;
	private double speed ;
	
	private boolean removable = false;
	
    protected double dx;
    protected double dy;

    /**
	 * Default ABSTRACT constructor, which constructs a new Soldier
	 * @param layer Pane
	 * @param image Image of Soldier
	 * @param x Layouts x 
	 * @param y Layouts y
     * @param product_cost The price of the soldier in florins
     * @param product_time The number of turns necessary for the production of the soldier
     * @param health The health of the soldier
     * @param damage The damage of the soldier (When he attacks a castle)
     * @param speed The spped of the soldier
     */
	public Soldier(Pane layer, Image image, double x, double y, int product_cost, int product_time, int health,
			double damage, double speed) {
		super(layer, image, x, y);
		removeFromLayer();
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

	/**
	 * Move a soldier on a slope after assigning a value to Dx and Dy
	 */
	public void move() {
        x += dx;
        y += dy * dx;
        this.updateUI();
    }
	
	/**
	 * Move a solider in line after assigning a value to Dx and Dy
	 */
	public void move_in_line() {
        x += dx;
        y += dy;
        this.updateUI();
    }
	
	/**
	 * Assign a speed of soldier to Dx and call move_in_line to move Left 
	 */
	public void moveLeft() {
		setDx(-getSpeed());
		move_in_line();
	}
	
	/**
	 * Assign a speed of soldier to Dx and call move_in_line to move Right 
	 */
	public void moveRight() {
		setDx(getSpeed());
		move_in_line();
	}
	
	/**
	 * Assign a speed of soldier to Dy and call move_in_line to move Up 
	 */
	public void moveUp() {
		setDy(-getSpeed());
		move_in_line();
	}
	
	/**
	 * Assign a speed of soldier to Dy and call move_in_line to move Down
	 */
	public void moveDown() {
		setDy(getSpeed());
		move_in_line();
	}
	
	/**
	 * Calculate the line between the soldier and the castle passed in parameter and move one step in this line
	 * @param castle The castle where we want to go
	 */
	public void moveTo(Castle castle) {
		if (this.x == castle.getX()) {
			if (this.y > castle.getY()) moveUp();
			else moveDown();
			return ;
		}
		else if (this.y == castle.getY()) {
			if (this.x > castle.getX()) moveLeft();
			else moveRight();
			return ;
		}
		else if (this.x < castle.getX()) {
			this.dx = this.speed ;
			this.dy = (this.y-castle.getY())/(this.x-castle.getX());
		}
		else if (this.x > castle.getX()){
			this.dx = -this.speed ;
			this.dy = (castle.getY()-this.y)/(castle.getX()-this.x);
		}
		move();
	}
	
	/**
	 * Test if the soldier is still alive
	 * @return true if yes else false
	 */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Apply all the damage points of the soldier passed in parameter
     * @param soldier The soldier who attacks
     */
    public void damagedBy( Soldier soldier) {
        health -= soldier.getDamage();
    }
    
    /**
     * Set removability to true 
     */
    public void remove() {
        this.removable = true;
    }	
	
    /**
     * Check if the soldier is not alive, if true, then set call remove
     */
	public void checkRemovability() {
		if (!isAlive())
			remove();
	}
	
	/**
	 * Apply a single point of damage
	 */
	public void damaged() {
		health -= 1 ;
	}

}
