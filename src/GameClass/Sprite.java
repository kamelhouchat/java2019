package GameClass;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3676656290971406742L;

	private Pane layer ;

	private ImageView imageView;
	
    protected double x;
    protected double y;

    private double w;
    private double h;


    public Sprite(Pane layer, Image image, double x, double y) {
    	this.x = x;
		this.y = y;
		this.layer = layer;
		
		this.imageView = new ImageView(image); 	
		this.imageView.relocate(x, y);
		this.w = image.getWidth();
		this.h = image.getHeight();
		addToLayer();
	}
    
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    protected ImageView getView() {
        return imageView;
    }
    
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
    
    public boolean collidesWith(Sprite sprite) {
    	return getView().getBoundsInParent().intersects(sprite.getView().getBoundsInParent());
    }

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
