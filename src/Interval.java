
public class Interval implements Comparable<Interval> {

	private double min; 
	private double max;
	private Boolean empty;
	
	
	/**
	 * Create a new Interval, if min is greater than or equal to max throws illegal argument exception. 
	 * @param min Double minimum value of the tuple
	 * @param max Double maximum value of the tuple
	 * @param universal - if true the interval will represent all the real numbers
	 * @param empty -  if true the interval will represent the empty set
	 * @throws IllegalArgumentException if min is greater or equal to max
	 */
	public Interval(double min, double max) { 
		if (min >= max)
			throw new IllegalArgumentException("Min cannot be greater or equal to Max");
		
		this.min = min;
		this.max = max;
		this.empty = false;
	}
	
	
	
	/**
	 * If universal is true min will be set to negative infinity and max will be set to positive infinity.
	 * If empty is true the interval will represent the empty set
	 */
	public Interval(Boolean universal, Boolean empty) {
		if(universal && empty)
			throw new IllegalArgumentException("An Interval cannot be the Universal set and the empty set at the same time");
		
		if (universal == false && empty == false)
			throw new IllegalArgumentException("Must specified one, universal or empty");
		
		if(universal) {
			this.min = Double.NEGATIVE_INFINITY;
			this.max = Double.POSITIVE_INFINITY;
			this.empty = false;
			}
			else {
			this.min = 0; 
			this.max = 0; 
			this.empty = true;
			}
	}
	
	/**
	 * Return the min value of the interval, if the set is empty the return an null value
	 * @return min
	 */
	public double getMin() throws UnsupportedOperationException {
		if (this.empty)
			throw new UnsupportedOperationException("Empty set does not have min");
		
		else return this.min;
	}

	
	/**
	 * Return the max value of the interval, if the set is empty the return an null value
	 * @return max
	 */
	public double getMax() throws UnsupportedOperationException {
		if (this.empty)
			throw new UnsupportedOperationException("Empty set does not have max"); 
		
		else return this.max;
	}
	
	
	/**
	 * Returns true if empty set
	 * @return true if empty set, false otherwise
	 */
	public boolean isUniversal() {
		return this.min == Double.NEGATIVE_INFINITY && this.max == Double.POSITIVE_INFINITY;
	}
	
	
	/**
	 * Returns true if empty set
	 * @return true if empty set, false otherwise
	 */
	public boolean isEmpty() {
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
					return new Interval(interval1.getMin(),interval1.getMax());
				}
				else {
					// case 2
					return new Interval(interval1.getMin(),this.max);
				}
			}
			else {
				if(interval1.getMax() < this.max) {
					// case 3
					return new Interval(this.min ,interval1.getMax());
				}
				else{
					// case 4
					return new Interval(this.min ,this.max);
				}
			}
		}

		else {
			// case 5 && 6
			return new Interval(false, true);

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
	public Boolean contains(double element ) {
		
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