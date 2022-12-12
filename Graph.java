import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E<T>. Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 */
public class Graph implements GraphInterface<Town, Road>{
	public class myValue{
		Road myRoad;
		Town des;
		public myValue(Town des, Road r) {
			this.des = des;
			myRoad = r;
		}
	}
	protected Map<Town, LinkedList<myValue>> map = new HashMap<>();
	protected Set<Road> roadSet = new HashSet<Road>();
	protected Set<Town> townSet = new HashSet<Town>();
	protected ArrayList<String> shortStrArr = new ArrayList<String>();
	protected ArrayList<Road> shortRoadArr = new ArrayList<Road>();
	

	 //~ Methods ----------------------------------------------------------------

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(map.containsKey(sourceVertex)) {
			LinkedList<myValue> list = map.get(sourceVertex);
			for (myValue v : list) {
			    if(v.des.equals(destinationVertex)) {
			    	return new Road(v.myRoad);
			    }
			}
		}
		return null;
		
	}

	 /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException{
		if(!map.containsKey(sourceVertex) && !map.containsKey(destinationVertex)) {
			addVertex(sourceVertex);
			addVertex(destinationVertex);
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		map.get(sourceVertex).add(new myValue(destinationVertex, road));
		road = new Road(destinationVertex, sourceVertex, weight, description);
		map.get(destinationVertex).add(new myValue(sourceVertex, road));
		return road;
		
	}

	 /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if(map.containsKey(v)) {
			return false;
		}
		
		map.put(v, new LinkedList<myValue>());
		return true;
		
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(map.containsKey(sourceVertex)) {
			LinkedList<myValue> list = map.get(sourceVertex);
			for (myValue v : list) {
			    if(v.des.equals(destinationVertex)) {
			    	return true;
			    }
			}
		}
		return false;
	}

	 /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		for (Town town : map.keySet()) {
			if(town.equals(v)) {
				return true;
			}
        }
		
		return false;
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		roadSet.clear();
		for (Town t : map.keySet()) {
			LinkedList<myValue> list = map.get(t);
			for (myValue v : list) {
				roadSet.add(v.myRoad);
			}
		}
		return roadSet;
		
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		edgeSet();
		Set<Road> newSet = new HashSet<Road>();
		Iterator<Road> t = roadSet.iterator();
		while(t.hasNext()) {
			Road road = t.next();
			   if(road.getSource().equals(vertex) || road.getDestination().equals(vertex)) {
				   newSet.add(road);
			   }
		}
		return newSet;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = new Road(destinationVertex, sourceVertex, weight, description);
		if(map.containsKey(sourceVertex)) {
			LinkedList<myValue> list = map.get(sourceVertex);
			for (myValue v : list) {
			    if(v.des.equals(destinationVertex)) {
			    	map.get(sourceVertex).remove(v);
			    	return road;
			    }
			}
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(map.get(v).equals(null)) {
			return false;
		}
		if(map.containsKey(v)) {
			for (Town t : map.keySet()) {
				removeEdge(t, v, 0, " ");
	        }
			map.keySet().remove(v);
			return true;
		}
		return false;
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		townSet.clear();
		for (Town t : map.keySet()) {
			townSet.add(t);
		}
		return townSet;
	}
	
	

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		shortRoadArr = new ArrayList<>(edgeSet());
		for(Road r : shortRoadArr) {
			if(r.contains(destinationVertex)) {
				shortStrArr.add(r.getSource() + " via " + r.getName() + " to " + r.getDestination() + " " + r.getWeight() + " mi");
			}
		}
		return shortStrArr;
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		

			
	}

	
}

