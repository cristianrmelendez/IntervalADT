
public class Tuple implements Comparable<Tuple> {
	
	private Double min; 
	private Double max;
	//private boolean includedMin
	//private boolean includedMax
	
	public Tuple(Double min, Double max) { 
		if (min >= max)
			throw new IllegalArgumentException("Min cannot be greater or equal to Max");
		this.min = min; 
		this.max = max; 
	}

	public Double getMin() {
		return this.min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return this.max;
	}

	public void setMax(Double max) {
		this.max = max;
	}
	
	/**
	 * Verifies if two tuples intersect and if they do returns a new tuple with the intersection of them.
	 * @param t1 first tuple to compare
	 * @return Returns null if not intersect, otherwise returns a new tuple with the intersection of the two
	 */
	public Tuple intersects(Tuple t) {
		if (this.max > t.min)
			return new Tuple(this.min, t.max);
		return null;
	}
	
	@Override
    public int compareTo( final Tuple t) {
        if (this.min > t.min) {
        		return -1;
        }else if (this.max < t.max) {
        		return 1;
        } else {
        		return 0;
        }
    }
	
	public String toString() {
		return "(" + this.min + ", " + this.max + ")";
	}
		

} 