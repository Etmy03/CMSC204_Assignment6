import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;
	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[3];
		  
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddVertex() {
		assertTrue(graph.addVertex(new Town("A")));
	}
	
	@Test
	public void testAddEdge() {
		Town a = new Town("A");
		Town b = new Town("B");
		Town c = new Town("C");
		Road ab = new Road(a, b, 8, "AtoB");
		Road ac = new Road(a, c, 5, "AtoC");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		Road newAB = graph.addEdge(a, b, 8, "AtoB");
		//graph.addEdge(a, b, 8, "AtoB");
		Road newAC = graph.addEdge(a, c, 5, "AtoC");
		//graph.addEdge(a, c, 5, "AtoB");
		assertTrue(ab.equals(newAB));
		assertTrue(ac.equals(newAC));
		
	}
	
	@Test
	public void testGetEdge() {
		Town a = new Town("A");
		Town b = new Town("B");
		Town c = new Town("C");
		Road ab = new Road(a, b, 8, "AtoB");
		Road ac = new Road(a, c, 5, "AtoC");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addEdge(a, b, 8, "AtoB");
		graph.addEdge(a, c, 5, "AtoC");
		//Road newAC = graph.getEdge(a, b);
		assertEquals(ab.toString(), graph.getEdge(a, b).toString());
		assertEquals(ac.toString(), graph.getEdge(a, c).toString());

	}
	
	
}
