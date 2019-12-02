package src.models.comun;

import java.util.Random;

public class Tools {

	private Random rnd;
	private static Tools instance;  

	private Tools() {
		rnd = new Random();
	}

	public static Tools getInstance() {
		if (instance == null) {
			instance = new Tools();
		}
		return instance;
	}
	
	public Integer getRandomNumber() { 
	    
	    int number = this.rnd.nextInt(999999); 
	    String num = String.format("%06d", number);
	    
	    return Integer.valueOf(num);
	}
}
