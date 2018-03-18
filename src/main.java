import java.util.ArrayList;
public class main {

	public static void main(String[] args) {

	Tuple subset1 = new Tuple(-23.00, 0.00);
	Tuple subset2 = new Tuple(1.0, 100.00);
	ArrayList<Tuple> subsets = new ArrayList<>();
	subsets.add(subset1);
	subsets.add(subset2);
	Interval MyInterval = new Interval(subsets);
	System.out.println(MyInterval.toString());
	
	}

}
