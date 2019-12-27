package GameClass;

import java.util.Random;

public class Door {

	private char direction ;

	public Door() {
		Random r = new Random();
		int generated = r.nextInt(4);
		switch (generated) {
			case 0 :
				this.direction = 'N';
				break ;
			case 1 : 
				this.direction = 'E';
				break ;
			case 2 : 
				this.direction = 'S';
				break ;
			case 3 : 
				this.direction = 'O';
				break ;
		}
	}

	public char getDirection() {
		return direction;
	}
	
}
