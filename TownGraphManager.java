import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {
	Graph myGraph = new Graph();

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		myGraph.addEdge(new Town(town1), new Town(town2), weight, roadName);
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		String s = myGraph.getEdge(new Town(town1), new Town(town2)).getName();
		return s;
	}

	@Override
	public boolean addTown(String v) {
		boolean b = myGraph.addVertex(new Town(v));
		return b;
	}

	@Override
	public Town getTown(String name) {
		Set<Town> s = new HashSet<Town>(myGraph.vertexSet());
		for (Town t: s){
			if(t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		boolean b = myGraph.containsVertex(new Town(v));
		return b;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		boolean b = myGraph.containsEdge(new Town(town1), new Town(town2));
		return b;
	}

	@Override
	public ArrayList<String> allRoads() {
		Set<Road> s = myGraph.edgeSet();
		ArrayList<String> arr = new ArrayList<String>();
		for(Road r : s) {
			arr.add(r.getName());
		}
		Collections.sort(arr);
		return arr;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Road r = myGraph.removeEdge(new Town(town1), new Town(town2), 0, road);
		return true;
	}

	@Override
	public boolean deleteTown(String v) {
		boolean b = myGraph.removeVertex(new Town(v));
		return b;
	}

	@Override
	public ArrayList<String> allTowns() {
		Set<Town> s = myGraph.vertexSet();
		ArrayList<String> a = new ArrayList<String>();
		for(Town t : s) {
			a.add(t.toString());
		}
		Collections.sort(a);
		return a;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> a = myGraph.shortestPath(new Town(town1), new Town(town2));
		return a;
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException{
		
		Scanner input;
		try {
			input = new Scanner(selectedFile);
			String[] s;
			while(input.hasNext()) {
				s = input.nextLine().split(";");
				addRoad(s[3],  s[4], Integer.parseInt(s[2]),  s[1]);
			}
		} 
		catch (java.io.FileNotFoundException e) {
			e.getMessage();
		}
		
		
	}

}
