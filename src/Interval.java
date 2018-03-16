import java.util.ArrayList;

public class Interval {
	private ArrayList<Tuple> subsets;

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

	public String toString(){
		String stringTR = "[ ";

		for(int i = 0; i < this.subsets.size(); i++) {

			if(i < this.subsets.size() - 1) {
				stringTR = stringTR + "( " + this.subsets.get(i).getX() + " , " + this.subsets.get(i).getY() + " ) U ";
			}
			else {
				stringTR = stringTR + "( " + this.subsets.get(i).getX() + " , " + this.subsets.get(i).getY() + " ) ]";	
			}
		}


		return stringTR;
	}
}





