import java.util.ArrayList;

public class IntervalSet {
	
	private ArrayList<Interval> intervals;


	public IntervalSet(double min, double max) {
		this.intervals = new ArrayList<>();
		Interval subset = new Interval(min, max);
		this.intervals.add(subset);	
	}

	public IntervalSet(Interval subset) {
		this.intervals = new ArrayList<>();
		this.intervals.add(subset);	
	}

	public IntervalSet(ArrayList<Interval> subsets) {
		this.intervals = new ArrayList<>();

		for(Interval subset: subsets) {
			this.intervals.add(subset);
		}
	}


	public ArrayList<Interval> getSubsets() {
		return intervals;
	}

	public void setSubsets(ArrayList<Interval> subsets) {
		this.intervals = subsets;
	}
	
	/**
	 * Insert a new tuple in ascending order to the subsets list. If the tuple intersects another tuple,
	 *  a new tuple will be generated and replace the existing tuple.
	 * @param t new tuple to insert.
	 * @throws IllegalArgumentException if a tuple with same amount min and max exists.
	 */
	public void addTuple(Interval t) {
		int i = 0;
		int compare = 0;
		Interval intersect;
		while(i < this.intervals.size()-1) {
			this.intervals.get(i);
			intersect = this.intervals.get(i).intersects(t);
			if (intersect != null) {
				this.intervals.set(i, intersect);
				break;
			} else {
				compare = this.intervals.get(i).compareTo(t);
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

		for(int i = 0; i < this.intervals.size(); i++) {

			if(i < this.intervals.size() - 1) {
				stringTR += this.intervals.get(i).toString() + " U ";
			}
			else {
				stringTR += this.intervals.get(i).toString() + " ]";	
			}
		}


		return stringTR;
	}
}





