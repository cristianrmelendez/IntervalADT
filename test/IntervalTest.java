
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntervalTest {


	@Test
	void IntervalConstructorGetter() {
		// Illegal argument exception
		assertThrows(IllegalArgumentException.class, ()->{
			new Interval(10.0,10.0);});

		// Regular case
		Interval interval1 = new Interval(10.99,11.0);
		assertEquals(10.99, interval1.getMin());
		assertEquals(11.0, interval1.getMax());

		// Regular case part 2
		Interval interval1pt2 = new Interval(-12345432123.99, 345654345411.0);
		assertEquals(-12345432123.99,  interval1pt2.getMin());
		assertEquals( 345654345411.0, interval1pt2.getMax());


		// -Inf to a number 
		Interval interval2 = new Interval(Double.NEGATIVE_INFINITY, 50.0);

		assertEquals(Double.NEGATIVE_INFINITY, interval2.getMin());
		assertEquals(50.0, interval2.getMax());

		// number to Inf 
		Interval interval3 = new Interval(50.0, Double.POSITIVE_INFINITY);

		assertEquals(50.0, interval3.getMin());
		assertEquals(Double.POSITIVE_INFINITY, interval3.getMax());

		// -Inf to Inf
		Interval interval4 = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

		assertEquals(Double.NEGATIVE_INFINITY, interval4.getMin());
		assertEquals(Double.POSITIVE_INFINITY, interval4.getMax());

	}

	@Test
	void secondConstructorTester() {

		// Test infinity
		Interval interval1 = new Interval(true,false);
		assertEquals(Double.NEGATIVE_INFINITY, interval1.getMin());
		assertEquals(Double.POSITIVE_INFINITY, interval1.getMax());

		// Test empty
		Interval interval2 = new Interval(false, true);
		assertThrows(UnsupportedOperationException.class, ()->{
			interval2.getMin();
		});
		assertThrows(UnsupportedOperationException.class, ()->{
			interval2.getMax();
		});


		// Test Illegal Argument exceptions
		assertThrows(IllegalArgumentException.class, ()->{
			new Interval(true,true);
		});

		assertThrows(IllegalArgumentException.class, ()->{
			new Interval(false,false);
		});


	}

	@Test 
	void itsEmptyTest() {

		Interval emptyInterval = new Interval(false, true);
		assertTrue(emptyInterval.isEmpty());

		Interval UniversalInterval = new Interval(true, false);
		assertFalse(UniversalInterval.isEmpty());

		Interval interval1pt2 = new Interval(-12345432123.99, 345654345411.0);
		assertFalse(interval1pt2.isEmpty());

	}

	@Test 
	void itsUniversalTest() {

		Interval UniversalInterval = new Interval(true, false);
		assertTrue(UniversalInterval.isUniversal());

		Interval emptyInterval = new Interval(false, true);
		assertFalse(emptyInterval.isUniversal());

		Interval interval1pt2 = new Interval(-12345432123.99, 345654345411.0);
		assertFalse(interval1pt2.isUniversal());

	}



	@Test
	void IntervalIntersectionTest() {

		// Case 1 this.min < min && max < this.max
		Interval interval1 = new Interval(-50.0, 50.0);
		Interval interval2 = new Interval(10.0,15.0);
		Interval resultInterval = new Interval(10.0,15.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 2 this.min < min < this.max < max
		interval1 = new Interval(-11.0, 50.0);
		interval2 = new Interval(-10.0,51.0);
		resultInterval = new Interval(-10.0, 50.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 3 min < this.min < max < this.max
		interval1 = new Interval(-19.9999, 50.0);
		interval2 = new Interval(-20.0, 30.0);
		resultInterval = new Interval(-19.9999, 30.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 4 min < this.min < this.max < max
		interval1 = new Interval(-9.9999, 10.0);
		interval2 = new Interval(-20.0, 30.0);
		resultInterval = new Interval(-9.9999, 10.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 5 min < max < this.min < this.max
		interval1 = new Interval(10.9999, 100.0);
		interval2 = new Interval(-20.0, 8.0);
		resultInterval = new Interval(false, true);	// The result we are supposed to get, EMPTY SET
		assertEquals(resultInterval, interval1.intersects(interval2));
		
		// Case 6  this.min < this.max < min < max
		interval1 = new Interval(-10.9999, 10.0);
		interval2 = new Interval(20.0, 80.0);
		resultInterval = new Interval(false, true);	// The result we are supposed to get, EMPTY SET
		assertEquals(resultInterval, interval1.intersects(interval2));


	}

	@Test
	void tupleEqualityTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(10.0,15.0);
		assertTrue(t1.equals(t2));
	}
}