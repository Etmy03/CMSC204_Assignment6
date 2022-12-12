import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
	Town town1, town2, town3;

	@Before
	public void setUp() throws Exception {
		town1 = new Town("A");
		
		town2 = new Town("B"); 
		
		town3 = new Town("A");
		
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = null;
	}

	//addTown
	@Test
	public void testAddTown() {
		assertTrue(town2.addTown(new Town("1")));
		assertTrue(town2.addTown(new Town("2")));
		assertTrue(town2.addTown(new Town("3")));
		assertTrue(town2.addTown(new Town("4")));
	}
	
	
	//compareTo
	@Test
	public void testCompareTo() {
		assertEquals(1,town1.compareTo(town2));
		assertEquals(0,town1.compareTo(town1));
	}
	
	//equals
	@Test
	public void testEquals() {
		assertFalse(town1.equals(town2));
		assertTrue(town1.equals(town3));
	}
	
	//equals
		@Test
		public void testToString() {
			assertTrue(town1.toString().equals("A"));
			assertTrue(town2.toString().equals("B"));
		}
}
