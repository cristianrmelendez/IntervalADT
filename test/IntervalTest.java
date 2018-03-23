
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
		Interval t1 = new Interval(10.99,11.0);
		assertEquals(10.99, t1.getMin());
		assertEquals(11.0, t1.getMax());


		// -Inf to a number 
		Interval t2 = new Interval(Double.NEGATIVE_INFINITY, 50.0);

		assertEquals(Double.NEGATIVE_INFINITY, t2.getMin(), "This is wrong");
		assertEquals(50.0, t2.getMax());

		// number to Inf 
		Interval t3 = new Interval(50.0, Double.POSITIVE_INFINITY);

		assertEquals(50.0, t3.getMin(), "This is wrong");
		assertEquals(Double.POSITIVE_INFINITY, t3.getMax());

		// -Inf to Inf
		Interval t4 = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

		assertEquals(Double.NEGATIVE_INFINITY, t4.getMin(), "This is wrong");
		assertEquals(Double.POSITIVE_INFINITY, t4.getMax());

	}


	@Test
	void tupleIntersectionTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(11.0,17.0);
		Interval t3 = new Interval(11.0,15.0);
		assertTrue(t3.equals(t1.intersects(t2)));
	}

	@Test
	void tupleEqualityTest() {
		Interval t1 = new Interval(10.0,15.0);
		Interval t2 = new Interval(10.0,15.0);
		assertTrue(t1.equals(t2));
	}
}