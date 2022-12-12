import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	TownGraphManager graph;
	String[] town;
	String a, b, c;

	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		town = new String[3];
		 a = "A";
		 b = "B";
		 c = "C";
		graph.addTown(a);
		graph.addTown(b);
		graph.addTown(c);
	}

	@Test
	public void testAddRoad() {
		assertTrue(graph.addRoad(a, b, 3, "AtoB"));
		assertTrue(graph.addRoad(b, c, 3, "BtoC"));
		assertTrue(graph.addRoad(c, a, 3, "CtoA"));
		ArrayList<String> roads = graph.allRoads();
		assertEquals("AtoB", roads.get(0));
		assertEquals("BtoC", roads.get(1));
		assertEquals("CtoA", roads.get(2));
		
	}

	@Test
	public void testGetRoad() {
		graph.addRoad(a, b, 3, "AtoB");
		graph.addRoad(b, c, 3, "BtoC");
		graph.addRoad(c, a, 3, "CtoA");
		assertEquals("AtoB", graph.getRoad(a, b));
		assertEquals("CtoA", graph.getRoad(a, c));
		assertEquals("CtoA", graph.getRoad(c, a));
	}

	@Test
	public void testAddTown() {
		String a = "A";
		String b = "B";
		String c = "C";
		assertFalse(graph.addTown(a));
		assertFalse(graph.addTown(b));
		assertFalse(graph.addTown(c));
		assertTrue(graph.addTown("D"));
		assertTrue(graph.addTown("E"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		ArrayList<String> path = graph.getPath(town[1],"Town_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("A"));
		assertEquals(false, graph.containsTown("D"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertFalse(graph.containsRoadConnection(a, b));
		graph.addRoad(a, b, 3, "AtoB");
		assertTrue(graph.containsRoadConnection(a, b));
		assertFalse(graph.containsRoadConnection(c, "E"));
	}

	@Test
	public void testAllRoads() {
		graph.addRoad(a, b, 3, "AtoB");
		graph.addRoad(b, c, 3, "BtoC");
		graph.addRoad(c, a, 3, "CtoA");
		ArrayList<String> roads = graph.allRoads();
		assertEquals("AtoB", roads.get(0));
		assertEquals("BtoC", roads.get(1));
		assertEquals("CtoA", roads.get(2));
	}

	@Test
	public void testDeleteRoadConnection() {
		graph.addRoad(a, b, 3, "AtoB");
		graph.addRoad(b, c, 3, "BtoC");
		graph.addRoad(c, a, 3, "CtoA");
		assertEquals(true, graph.containsRoadConnection(a, b));
		graph.deleteRoadConnection(a, b, "AtoB");
		assertEquals(false, graph.containsRoadConnection(a, b));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("A"));
		graph.deleteTown("A");
		assertEquals(false, graph.containsTown("A"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("A", roads.get(0));
		assertEquals("B", roads.get(1));
		assertEquals("C", roads.get(2));
	}
	@Test
	public void testGetPath() {
		graph.addRoad(a, b, 3, "AtoB");
		graph.addRoad(b, c, 3, "BtoC");
		graph.addRoad(c, a, 3, "CtoA");
		ArrayList<String> path = graph.getPath(a,b);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("A via AtoB to B 3 mi",path.get(0).trim());

	}
	

}
