
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
	void testComplement() {

		// Case 1 When we have a normal interval
		Interval interval1 = new Interval(-50.0, 50.0);

		IntervalSet intervalSet = interval1.complement();

		Interval expectedResult1 = new Interval(Double.NEGATIVE_INFINITY, -50.001);
		Interval expectedResult2 = new Interval(50.001, Double.POSITIVE_INFINITY);


		Interval result1 = intervalSet.getIntervals().get(0);
		Interval result2 = intervalSet.getIntervals().get(1);


		assertEquals(expectedResult1, result1);
		assertEquals(expectedResult2, result2);


		// Case 2 [-inf, number]
		interval1  = new Interval(Double.NEGATIVE_INFINITY, 50.0);

		intervalSet = interval1.complement();

		expectedResult1 = new Interval(50.001, Double.POSITIVE_INFINITY);

		result1 = intervalSet.getIntervals().get(0);

		assertEquals(expectedResult1, result1);


		// Case 3 [number, inf ]
		interval1  = new Interval(50.0, Double.POSITIVE_INFINITY);

		intervalSet = interval1.complement();

		expectedResult1 = new Interval(Double.NEGATIVE_INFINITY, 49.999);

		result1 = intervalSet.getIntervals().get(0);

		assertEquals(expectedResult1, result1);


		// Case 4 [ -inf, inf ] 
		interval1  = new Interval(true,false);

		intervalSet = interval1.complement();

		expectedResult1 = new Interval(false, true);

		result1 = intervalSet.getIntervals().get(0);

		assertEquals(expectedResult1, result1);

		// Case 5 EMPTY Interval
		interval1  = new Interval(false, true);

		intervalSet = interval1.complement();

		expectedResult1 = new Interval(true, false);

		result1 = intervalSet.getIntervals().get(0);

		assertEquals(expectedResult1, result1);

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
	void intervalEqualityTest() {
		Interval interval1 = new Interval(10.0,15.0);
		Interval interval2 = new Interval(10.0,15.0);
		assertTrue(interval1.equals(interval2));

		Interval interval3 = new Interval(103.0,154.0);
		assertFalse(interval1.equals(interval3));


	}

	@Test
	void intervalUnionTest() {
		//case 1 (10,15) U (18,20) = [ (10,15) , (18,20) ]
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(18.0,20.0);
		IntervalSet set = Interval.union(t1, t2);
		IntervalSet set2 = new IntervalSet(t1, t2);
		assertEquals(set2, set);

		//case 2 (10,15) U (10,15) throws
		assertThrows(IllegalArgumentException.class, () -> {
			Interval.union(t1, t1);
		});

		//case 3 Null U Null throws
		assertThrows(IllegalArgumentException.class, () -> {
			Interval.union(null, null);
		});

		//case 4 (10,15) U (14,21) = [ (10,21) ]
		Interval t3 = new Interval(14.0, 21.0);
		IntervalSet set3 = Interval.union(t1, t3);
		IntervalSet set4 = new IntervalSet(new Interval(10.0,21.0));
		assertEquals(set4, set3);

		//case 5 (-inf,+inf) U (18,20) = [ (-inf,+inf) ]
		Interval t4 = new Interval(true, false);
		IntervalSet set5 = new IntervalSet(t4);
		IntervalSet set6 = Interval.union(t4, t2);
		assertEquals(set5, set6);

		//case 6 (-inf,20) U (30,+inf) = [ (-inf,20) , (30,+inf) ]
		Interval t5 = new Interval(Double.NEGATIVE_INFINITY, 20);
		Interval t6 = new Interval(30, Double.POSITIVE_INFINITY);
		IntervalSet set7 = Interval.union(t5, t6);
		IntervalSet set8 = new IntervalSet(t5, t6);
		assertEquals(set8, set7);

		//case 7 ø U (-inf,+inf) = [ (-inf,+inf) ]
		Interval t8 = new Interval(false,true);
		IntervalSet set9 = Interval.union(t8, t4);
		IntervalSet set10 = new IntervalSet(t4);
		assertEquals(set10,set9);

		//case 8 ø U (10,15) = [ (10,21) ]
		IntervalSet set11 = new IntervalSet(t1);
		IntervalSet set12 = Interval.union(t1, t8);
		assertEquals(set11, set12);

	}

	@Test
	void compareToTest() {

		// Testing Cases and boundaries
		// Case 1 this.min < min && max < this.max
		Interval interval1 = new Interval(9.99, 15.00001);
		Interval interval2 = new Interval(10.0,15.0);
		assertEquals(-1, interval1.compareTo(interval2));

		// Case 2 this.min < min < this.max < max
		interval1 = new Interval(-10.001, 50.0);
		interval2 = new Interval(-10.0,50.001);

		assertEquals(-1, interval1.compareTo(interval2));

		// Case 3 min < this.min < max < this.max
		interval1 = new Interval(-19.9999, 30.0001);
		interval2 = new Interval(-20.0, 30.0);

		assertEquals(1, interval1.compareTo(interval2));

		// Case 4 min < this.min < this.max < max
		interval1 = new Interval(-9.9999, 10.0);
		interval2 = new Interval(-10.0, 10.00001);

		assertEquals(1, interval1.compareTo(interval2));

		// Case 5 min < max < this.min < this.max
		interval1 = new Interval(10.9999, 100.0);
		interval2 = new Interval(-20.0, 10.9998);

		assertEquals(1, interval1.compareTo(interval2));

		// Case 6  this.min < this.max < min < max
		interval1 = new Interval(-10.9999, 10.0);
		interval2 = new Interval(10.0001, 80.0);

		assertEquals(-1, interval1.compareTo(interval2));

		// Case 7 when we have equal mins and we use the max to decide   this.min == min  && this.max < max
		interval1 = new Interval(10.00, 10.0);
		interval2 = new Interval(10.00, 80.0);

		assertEquals(-1, interval1.compareTo(interval2));

		// Case 8 when we have equal mins and we use the max to decide   this.min == min && max < this.max
		interval1 = new Interval(10.00, 10.0);
		interval2 = new Interval(10.00, 80.0);

		assertEquals(1, interval1.compareTo(interval2));


		// Case 9 when we compare with an empty set
		Interval emptyInterval = new Interval(false, true);
		assertThrows(UnsupportedOperationException.class, ()->{
			emptyInterval.compareTo(new Interval(10.00, 80.0)
					);
		});

		// Case 10 when we compare with an empty set
		assertThrows(UnsupportedOperationException.class, ()->{
			(new Interval(10.00, 80.0)).compareTo(emptyInterval 
					);
		});
	}	

}