import java.util.ArrayList;

public class Interval {
	
	private ArrayList<Tuple<Double, Double>> subsets;


	public Interval(double min, double max) {
		this.subsets = new ArrayList<>();
		Tuple<Double, Double> subset = new Tuple<Double, Double>(min, max);
		this.subsets.add(subset);	
	}

	public Interval(Tuple<Double, Double> subset) {
		this.subsets = new ArrayList<>();
		this.subsets.add(subset);	
	}

	public Interval(ArrayList<Tuple<Double, Double>> subsets) {
		this.subsets = new ArrayList<>();

		for(Tuple<Double, Double> subset: subsets) {
			this.subsets.add(subset);
		}
	}


	public ArrayList<Tuple<Double, Double>> getSubsets() {
		return subsets;
	}

	public void setSubsets(ArrayList<Tuple<Double, Double>> subsets) {
		this.subsets = subsets;
	}

	public String toString(){
		String stringTR = "[ ";

		for(int i = 0; i < this.subsets.size(); i++) {

			if(i < this.subsets.size() - 1) {
				stringTR = stringTR + "( " + this.subsets.get(i).getMin() + " , " + this.subsets.get(i).getMax() + " ) U ";
			}
			else {
				stringTR = stringTR + "( " + this.subsets.get(i).getMin() + " , " + this.subsets.get(i).getMax() + " ) ]";	
			}
		}


		return stringTR;
	}
}





