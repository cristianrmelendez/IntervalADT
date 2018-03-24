
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
		// To test both constructors
		Interval UniversalInterval = new Interval(true, false);
		assertTrue(UniversalInterval.isUniversal());
		
		UniversalInterval = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		assertTrue(UniversalInterval.isUniversal());
		
		
		Interval emptyInterval = new Interval(false, true);
		assertFalse(emptyInterval.isUniversal());

		Interval interval1pt2 = new Interval(-12345432123.99, 345654345411.0);
		assertFalse(interval1pt2.isUniversal());

	}



	@Test
	void IntervalIntersectionTest() {
		// Testing Cases and boundaries
		// Case 1 this.min < min && max < this.max
		Interval interval1 = new Interval(9.99, 15.00001);
		Interval interval2 = new Interval(10.0,15.0);
		Interval resultInterval = new Interval(10.0,15.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 2 this.min < min < this.max < max
		interval1 = new Interval(-10.001, 50.0);
		interval2 = new Interval(-10.0,50.001);
		resultInterval = new Interval(-10.0, 50.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 3 min < this.min < max < this.max
		interval1 = new Interval(-19.9999, 30.0001);
		interval2 = new Interval(-20.0, 30.0);
		resultInterval = new Interval(-19.9999, 30.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 4 min < this.min < this.max < max
		interval1 = new Interval(-9.9999, 10.0);
		interval2 = new Interval(-10.0, 10.00001);
		resultInterval = new Interval(-9.9999, 10.0);	// The result we are supposed to get
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 5 min < max < this.min < this.max
		interval1 = new Interval(10.9999, 100.0);
		interval2 = new Interval(-20.0, 10.9998);
		resultInterval = new Interval(false, true);	// The result we are supposed to get, EMPTY Interval
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 6  this.min < this.max < min < max
		interval1 = new Interval(-10.9999, 10.0);
		interval2 = new Interval(10.0001, 80.0);
		resultInterval = new Interval(false, true);	// The result we are supposed to get, EMPTY Interval
		assertEquals(resultInterval, interval1.intersects(interval2));

		// Case 7 when we try to find the intersection between an Interval and empty Interval
		interval1 = new Interval(false, true);
		interval2 = new Interval(10.0001, 80.0);
		resultInterval = new Interval(false, true);
		assertEquals(resultInterval, interval1.intersects(interval2));
		
		// Case 8 when we try to find the intersection between an empty Interval and Interval
		interval1 = new Interval(10.0001, 80.0);
		interval2 = new Interval(false, true);
		resultInterval = new Interval(false, true);
		assertEquals(resultInterval, interval1.intersects(interval2));



	}

	@Test
	void testContains() {

		// Test for a normal interval and its boundaries
		Interval normalInt = new Interval(-50.00, 50.00);
		assertTrue(normalInt.contains(-49.99999999));
		assertTrue(normalInt.contains(49.99999999));
		assertTrue(normalInt.contains(-50.00));
		assertTrue(normalInt.contains(50.00));
		assertFalse(normalInt.contains(-50.00001));
		assertFalse(normalInt.contains(50.00001));
		assertFalse(normalInt.contains(Double.POSITIVE_INFINITY));
		assertFalse(normalInt.contains(Double.NEGATIVE_INFINITY));

		// Test for an interval [-inf, number]
		Interval negativeInfToNumber = new Interval(Double.NEGATIVE_INFINITY, 50.00);
		assertTrue(negativeInfToNumber.contains(Double.NEGATIVE_INFINITY));
		assertTrue(negativeInfToNumber.contains(-500098789024242.00));
		assertTrue(negativeInfToNumber.contains(49.99999999));
		assertTrue(negativeInfToNumber.contains(50.00));
		assertFalse(negativeInfToNumber.contains(50.00001));
		assertFalse(negativeInfToNumber.contains(Double.POSITIVE_INFINITY));
		
		// Test for an interval [number, inf]
		Interval numberToPositiveInf = new Interval(50.00,Double.POSITIVE_INFINITY);
		assertTrue(numberToPositiveInf.contains(Double.POSITIVE_INFINITY));
		assertTrue(numberToPositiveInf.contains(500098789024242.00));
		assertTrue(numberToPositiveInf.contains(50.00001));
		assertTrue(numberToPositiveInf.contains(50.00));
		assertFalse(numberToPositiveInf.contains(49.99999999));
		assertFalse(numberToPositiveInf.contains(Double.NEGATIVE_INFINITY));

		// Test for an interval [-inf, inf]
		Interval infToInf = new Interval(true, false);
		assertTrue(infToInf.contains(50.00));
		assertTrue(infToInf.contains(-50.0000));
		assertTrue(infToInf.contains(Double.NEGATIVE_INFINITY));
		assertTrue(infToInf.contains(Double.POSITIVE_INFINITY));

		// Test for an empty set
		Interval emptySet = new Interval(false, true);
		assertFalse(emptySet.contains(50.00001));
		assertFalse(emptySet.contains(Double.POSITIVE_INFINITY));
		assertFalse(emptySet.contains(-50.99));
		assertFalse(emptySet.contains(Double.NEGATIVE_INFINITY));


	}


	@Test
	void tupleEqualityTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(10.0,15.0);
		assertTrue(t1.equals(t2));
	}
}