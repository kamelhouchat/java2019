package GameClass;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite implements Serializable{

	private static final long serialVersionUID = 3676656290971406742L;

	private Pane layer ;

	private ImageView imageView;
	
    protected double x;
    protected double y;
	private double w;
    private double h;
    
    protected double centerX;
    protected double centerY;

    /**
	 * Default ABSTRACT constructor, which constructs a new Sprite (Castle || Soldier)
	 * @param layer Pane
	 * @param image Image of Sprite
	 * @param x Layouts x 
	 * @param y Layouts y
     */
    public Sprite(Pane layer, Image image, double x, double y) {
    	this.x = x;
		this.y = y;
		this.layer = layer;
		
		this.imageView = new ImageView(image); 	
		this.imageView.relocate(x, y);
		this.w = image.getWidth();
		this.h = image.getHeight();
		//addToLayer();
	}
    
    public Pane getLayer() {
    	return layer ;
    }
    
    /**
     * Add image view to layer
     */
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    /**
     * Remove image view from layer
     */
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    protected ImageView getView() {
        return imageView;
    }
    
    protected void setView(Image image) {
    	imageView.setImage(image);
    }
    
    /**
     * Relocate imageView in pane
     */
    public void updateUI() {
        imageView.relocate(x, y);
    }
    
    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }
    
    /**
     * Test if we are colluding with sprite passed in parameter
     * @param sprite Sprite with whom we want to know if we are colluding 
     * @return true if we are in collusion else false
     */
    public boolean collidesWith(Sprite sprite) {
    	return getView().getBoundsInParent().intersects(sprite.getView().getBoundsInParent());
    }
    
    /**
     * Test if we are colluding with sprite passed in parameter
     * @param sprite Sprite with whom we want to know if we are colluding 
     * @return true if we are in collusion else false
     */
    public boolean collideWith_Near(Sprite sprite) {
    	return getView().getBoundsInParent().intersects(sprite.getX()-10,sprite.getY()-10,sprite.getWidth()+20,sprite.getHeight()+20);
    }

    /**
     * Test if the castle passed in parameter is the same
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprite other = (Sprite) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
    
}
