import java.util.ArrayList;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Tuple subset1 = new Tuple(-23,0);
	Tuple subset2 = new Tuple(1,100);
	ArrayList<Tuple> subsets = new ArrayList<>();
	subsets.add(subset1);
	subsets.add(subset2);
	Interval MyInterval = new Interval(subsets);
	System.out.println(MyInterval.toString());
	
	}

}
