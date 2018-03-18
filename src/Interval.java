import java.util.ArrayList;

public class Interval {
	
	private ArrayList<Tuple> subsets;


	public Interval(double min, double max) {
		this.subsets = new ArrayList<>();
		Tuple subset = new Tuple(min, max);
		this.subsets.add(subset);	
	}

	public Interval(Tuple subset) {
		this.subsets = new ArrayList<>();
		this.subsets.add(subset);	
	}

	public Interval(ArrayList<Tuple> subsets) {
		this.subsets = new ArrayList<>();

		for(Tuple subset: subsets) {
			this.subsets.add(subset);
		}
	}


	public ArrayList<Tuple> getSubsets() {
		return subsets;
	}

	public void setSubsets(ArrayList<Tuple> subsets) {
		this.subsets = subsets;
	}
	
	/**
	 * Insert a new tuple in ascending order to the subsets list. If the tuple intersects another tuple,
	 *  a new tuple will be generated and replace the existing tuple.
	 * @param t new tuple to insert.
	 * @throws IllegalArgumentException if a tuple with same amount min and max exists.
	 */
	public void addTuple(Tuple t) {
		int i = 0;
		int compare = 0;
		Tuple intersect;
		while(i < this.subsets.size()-1) {
			this.subsets.get(i);
			intersect = this.subsets.get(i).intersects(t);
			if (intersect != null) {
				this.subsets.set(i, intersect);
				break;
			} else {
				compare = this.subsets.get(i).compareTo(t);
				if (compare > 0) {
					
				} else if (compare < 0) {
					
				} else {
					throw new IllegalArgumentException("Tuple Already in subsets");
				}
			}
			i++;
		}
	}
	
	public String toString(){
		String stringTR = "[ ";

		for(int i = 0; i < this.subsets.size(); i++) {

			if(i < this.subsets.size() - 1) {
				stringTR += this.subsets.get(i).toString() + " U ";
			}
			else {
				stringTR += this.subsets.get(i).toString() + " ]";	
			}
		}


		return stringTR;
	}
}





