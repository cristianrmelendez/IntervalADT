import java.util.ArrayList;
public class main {

	public static void main(String[] args) {

	Interval subset1 = new Interval(-23, 1);
	Interval subset2 = new Interval(-23, 10);
	
	Interval intersection = subset1.intersects(subset2);
	System.out.println(intersection);
	
	Interval t2 = new Interval(Double.NEGATIVE_INFINITY, 50.0);
	System.out.println(""+ t2.getMin());
	
	}

}
