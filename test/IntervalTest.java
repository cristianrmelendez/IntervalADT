
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntervalTest {


	@Test
	void IntervalConstructorGetter() {
		// Illegal argument exception
		assertThrows(IllegalArgumentException.class, ()->{
			Interval t = new Interval(10.0,10.0);});

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
		
		Interval interval1 = new Interval(true,false);
		assertEquals(Double.NEGATIVE_INFINITY, interval1.getMin());
		assertEquals(Double.POSITIVE_INFINITY, interval1.getMax());
		
		
		
		
		
		
		
	}
	
	


	@Test
	void intervalIntersectionTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(11.0,17.0);
		Interval t3 = new Interval(11.0,15.0);
		assertTrue(t3.equals(t1.intersects(t2)));
	}

	@Test
	void intervalEqualityTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(10.0,15.0);
		assertTrue(t1.equals(t2));
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
}