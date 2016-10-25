public class Vertex implements Comparable<Vertex> {
	public int id;
	public float cost;

	public Vertex(int id, float cost){
		this.id = id;
		this.cost = cost;
	}

	@Override
	public int compareTo(Vertex o) {
		if (o.id == this.id) {
			return 0;
		} else if (this.cost > o.cost) {
			return 1;
		} else {
			return -1;
		}
	}
}
