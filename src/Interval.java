
public class Interval implements Comparable<Interval> {

	private Double min; 
	private Double max;
	private Boolean universal;
	private Boolean empty;
	
	
	/**
	 * Create a new Interval, if min is greater than or equal to max throws illegal argument exception. If universal is true
	 * min will be set to negative infinity and max will be set to positive infinity. If empty is true the interval will represent
	 * the empty set
	 * @param min Double minimum value of the tuple
	 * @param max Double maximum value of the tuple
	 * @param universal - if true the interval will represent all the real numbers
	 * @param empty -  if true the interval will represent the empty set
	 * @throws IllegalArgumentException if min is greater or equal to max
	 */
	public Interval(Double min, Double max, Boolean universal, Boolean empty) { 
		if (min >= max)
			throw new IllegalArgumentException("Min cannot be greater or equal to Max");
		
		if(universal) {
		this.min = Double.NEGATIVE_INFINITY;
		this.max = Double.POSITIVE_INFINITY;
		}
		else {
		this.min = min; 
		this.max = max; 
		}
		this.universal = universal;
		this.empty = empty;
	}
	
	/**
	 * Return the min value of the interval, if the set is empty the return an null value
	 * @return min
	 */
	public Double getMin() {
		return this.min;
	}

	/**
	 * Set new Minimum
	 * @param min Double new Minimum
	 */
	public void setMin(Double min) {
		this.min = min;
	}
	/**
	 * Return the max value of the interval, if the set is empty the return an null value
	 * @return max
	 */
	public Double getMax() {
		return this.max;
	}
	
	/**
	 * Set new Maximum
	 * @param max Double new Maximum
	 */
	public void setMax(Double max) {
		this.max = max;
	}
	
	/**
	 * Returns true if empty set
	 * @return true if empty set, false otherwise
	 */
	public boolean isUniversal() {
		return this.universal;
	}
	/**
	 * Returns true if empty set
	 * @return true if empty set, false otherwise
	 */
	public boolean isEmptySet() {
		return this.empty;
	}

	/**
	 * Verifies if two Intervals intersect and if they do returns a new Interval with the intersection of them. If they dont
	 * intersects then return and empty interval
	 * @param interval1 interval to compare
	 * @return Returns null if not intersect, otherwise returns a new tuple with the intersection of the two
	 */
	public Interval intersects(Interval interval1) {


		if(!(interval1.getMax() <= this.min || interval1.getMin() >= this.max)) {
			
			if(interval1.getMin() > this.min) {
				if(interval1.getMax() < this.max) {
					// case 1
					return new Interval(interval1.getMin(),interval1.getMax(),false,false);
				}
				else {
					// case 2
					return new Interval(interval1.getMin(),this.max,false,false);
				}
			}
			else {
				if(interval1.getMax() < this.max) {
					// case 3
					return new Interval(this.min ,interval1.getMax(),false,false);
				}
				else{
					// case 4
					return new Interval(this.min ,this.max, false,false);
				}
			}
		}

		else {
			// case 5 && 6
			return null;

		}
	}
	
	/**
	 * Takes 2 intervals and if they intersect, will return an interval set with one interval representing both intervals.
	 * If they don't intersect, it will return an interval set containing both intervals.
	 * @param interval1 interval one of to be joined.
	 * @param interval2 interval two to be joined.
	 * @return interval set containing an interval or intervals to represent the union operation
	 */
	public static IntervalSet union(Interval interval1, Interval interval2) {
		
		
		return null;
	}
	
	/**
	 * 
	 * 
	 * @param interval1 
	 * @param interval2 
	 * @return 
	 */
	public static IntervalSet complement(Interval interval) {
		
		
		return null;
	}
	
	
	/**
	 * This method will return true if the element is within the range of the target interval.
	 * @param element double number to find in range.
	 * @return ture if in range false otherwise.
	 */
	public Boolean contains(Double element ) {
		
		return true;
	}

	@Override
	public int compareTo( final Interval t) {
		if (this.min > t.min) {
			return -1;
		}else if (this.max < t.max) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object t) {
		if ( t instanceof Interval) {
			Interval t1 = (Interval) t;
			return this.max == t1.max && this.min == t1.min;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + this.min + ", " + this.max + "]";
	}


} 