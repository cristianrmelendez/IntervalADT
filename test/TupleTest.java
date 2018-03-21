import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TupleTest {

	
	@Test
	void tupleConstructorGetter() {
		assertThrows(IllegalArgumentException.class, ()->{
			Tuple t = new Tuple(10.0,10.0);	
		});
		Tuple t = new Tuple(10.0,11.0);
		assertEquals((Double)11.0, t.getMax());
		assertEquals((Double)10.0, t.getMin());
	}
	
	@Test
	void tupleIntersectionTest() {
		Tuple t1 = new Tuple(10.0,15.0);
		Tuple t2 = new Tuple(11.0,17.0);
		Tuple t3 = new Tuple(11.0,15.0);
		assertTrue(t3.equals(t1.intersects(t2)));
	}
	
	@Test
	void tupleEqualityTest() {
		Tuple t1 = new Tuple(10.0,15.0);
		Tuple t2 = new Tuple(10.0,15.0);
		assertTrue(t1.equals(t2));
	}
}