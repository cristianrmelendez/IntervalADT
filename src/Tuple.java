



public class Tuple<X, Y> { 
	public X min; 
	public Y max; 

	public Tuple(X min, Y max) { 
		this.min = min; 
		this.max = max; 
	}

	public X getMin() {
		return this.min;
	}

	public void setMin(X min) {
		this.min = min;
	}

	public Y getMax() {
		return this.max;
	}

	public void setMax(Y max) {
		this.max = max;
	}


} 