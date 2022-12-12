
public class Road extends Object implements Comparable<Road>{
	private Town source;
	private Town destination;
	private int weight;
	private String name;

	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
		source.addTown(destination);
		
	}
	
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		source.addTown(destination);
		
	}
	
	public Road(Road r) {
		this.source = r.getSource();
		this.destination = r.getDestination();
		this.weight = r.getWeight();
		this.name = r.getName();
		source.addTown(destination);
		
	}
	
	public boolean contains(Town town) {
		if(town.equals(this.source) || town.equals(this.destination)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Road [source=" + source.toString() + ", destination=" + destination.toString() + ", weight=" + this.weight + ", name=" + this.name
				+ "]";
	}
	
	public String getName(){
		return this.name;
	}
	
	public Town getDestination() {
		return this.destination;	
	}
	
	public Town getSource() {
		return this.source;	
		}
	
	public int compareTo(Road o) {
		if(this.equals(o)) {
			return 0;

		}
		return 1;
	}
	public int hashCode() {
		return ((Integer)weight).hashCode();
	}
	
	public int getWeight() {
		return this.weight;
	}

	@Override
	public boolean equals(Object obj) {
		Road r= (Road)(obj);
		if((this.name.equals(r.getName()) && (this.weight == r.getWeight()))){
			if(this.source.equals(r.getSource()) || this.source.equals(r.getDestination())){
				if(this.destination.equals(r.getSource()) || this.destination.equals(r.getDestination())) {
					return true;
				}
			}
		}
		return false;
	}
}
