import java.util.ArrayList;

public class Town extends Object implements Comparable<Town>{

	private String name;
	private ArrayList<Town> adjacentTowns = new ArrayList<Town>();
	
	public Town(String name) {
		this.name = name;
		
	}
	public Town(Town templateTown) {
		this.name = templateTown.getName();
		//this.adjacentTowns = templateTown.adjacentTowns;
		
	}
	public String getName(){
		return this.name;
	}
	
	public boolean addTown(Town t) {
		adjacentTowns.add(t);
		return true;
		
	}
		
	/*public boolean hasTown(Town t) {
		if(adjacentTowns.size() == 0) {
			return false;
		}
		for(int i=0; i<)
		adjacentTowns.add(new Town(t));
		return true;
		
	}*/
	
	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.getName());
	}
	
	public String toString(){
		return this.name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Town o = (Town)(obj);
		if(this.name.equals(o.getName())) {
			/*if(this.adjacentTowns.toString().equals(obj.getAdjacentTowns().toString())) {
				return true;
			}*/
			return true;
		}
		return false;
	}

}
