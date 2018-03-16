import java.util.ArrayList;
public class main {

	public static void main(String[] args) {

	Tuple<Double, Double> subset1 = new Tuple<Double, Double>(-23.00, 0.00);
	Tuple<Double, Double> subset2 = new Tuple<Double, Double>(1.0, 100.00);
	ArrayList<Tuple<Double, Double>> subsets = new ArrayList<>();
	subsets.add(subset1);
	subsets.add(subset2);
	Interval MyInterval = new Interval(subsets);
	System.out.println(MyInterval.toString());
	
	}

}
