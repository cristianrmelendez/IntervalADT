
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
	 * This method will unified two intervals and will return an intervalSet containing them.
	 */
	public IntervalSet union(Interval interval1) {
		
		
		
		return new IntervalSet(0,0);
	}
	
	
	/**
	 * This method will return true if the element enter as parameter is located in the range between min and max.
	 * @param element
	 * @return
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

	public String toString() {
		return "[" + this.min + ", " + this.max + "]";
	}


} 