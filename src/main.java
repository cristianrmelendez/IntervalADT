import java.util.ArrayList;
public class main {

	public static void main(String[] args) {

	Tuple subset1 = new Tuple(-23.00, 10.00);
	Tuple subset2 = new Tuple(10.0, 230.00);
	
	Tuple intersection = subset1.intersects(subset2);
	System.out.println(intersection);
	
	}

}
