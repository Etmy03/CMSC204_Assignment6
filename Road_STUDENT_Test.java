import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	Road road1;
	Road road2;
	Road road3;
	Town townA, townB, townC;
	
	Road road4;
	Road road5;
	

	@Before
	public void setUp() throws Exception {
		townA = new Town("A");
		townB = new Town("B");
		townC = new Town("C");
		
		road1 = new Road(townA, townB, 3, "AtoB");
		road2 = new Road(townB, townC, 3, "BtoC");
		road3 = new Road(townC, townA, 3, "CtoA");
		
		road4 = new Road(townA, townB, 3, "AtoB");
		road5 = new Road(townA, townB, 3, "BtoA");
	}

	@After
	public void tearDown() throws Exception {
		road1 = road2 = road3 = road4 = road5 = null;
		townA = townB = townC = null;
	}

	@Test
	public void testContains() {
		assertFalse(road1.contains(townC));
		assertTrue(road1.contains(townA));
	}
	@Test
	public void testToString() {
		assertEquals(road1.toString(), "Road [source=A, destination=B, weight=3, name=AtoB]");
		assertEquals(road2.toString(), "Road [source=B, destination=C, weight=3, name=BtoC]");
		assertEquals(road3.toString(), "Road [source=C, destination=A, weight=3, name=CtoA]");
	}
	@Test
	public void testGetName() {
		assertTrue(road1.getName().equals("AtoB"));
		assertTrue(road2.getName().equals("BtoC"));
		assertTrue(road3.getName().equals("CtoA"));
	}
	@Test
	public void testGetDestination() {
		assertEquals(townB, road1.getDestination());
		assertEquals(townC, road2.getDestination());
		assertEquals(townA, road3.getDestination());
		
		assertNotEquals(townA, road1.getDestination());
		assertNotEquals(townB, road2.getDestination());
		assertNotEquals(townC, road3.getDestination());
	}
	@Test
	public void testGetSource() {
		assertNotEquals(townB, road1.getSource());
		assertNotEquals(townC, road2.getSource());
		assertNotEquals(townA, road3.getSource());
		
		assertEquals(townA, road1.getSource());
		assertEquals(townB, road2.getSource());
		assertEquals(townC, road3.getSource());
	}
	@Test
	public void testCompareTo() {
		assertEquals(0, road1.compareTo(road4));
		assertEquals(1, road1.compareTo(road5));
	}
	@Test
	public void testGetWeight() {
		assertEquals(3, road1.getWeight());
	}
	@Test
	public void testEquals() {
		assertTrue(road1.equals(road4));
		assertFalse(road1.equals(road5));
	}


}
