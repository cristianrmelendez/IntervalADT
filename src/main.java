import java.util.ArrayList;
public class main {

	public static void main(String[] args) {

	Interval subset1 = new Interval(-23.00, 10.00);
	Interval subset2 = new Interval(10.0, 230.00);
	
	Interval intersection = subset1.intersects(subset2);
	System.out.println(intersection);
	
	}

}
