import java.util.PriorityQueue;

public class RandomMST2 {
	
	public int noOfVertices;
	public float[] cost;
	public PriorityQueue<Vertex> vertices = new PriorityQueue<Vertex>();

	public RandomMST2(int noOfVertices) {
		this.noOfVertices = noOfVertices;
		this.cost = new float[noOfVertices];
		
		this.vertices.add(new Vertex(0, 0));
		this.cost[0] = 0;
		for (int i = 1; i < this.noOfVertices; i++) {
			this.vertices.add(new Vertex(i, Float.MAX_VALUE));
			this.cost[i] = Float.MAX_VALUE;
		}
		
		while (!vertices.isEmpty()) {
			Vertex currentVertex = this.vertices.poll();
			for (int z = 0; z < this.noOfVertices; z++) {
				float weigthVZ = (float) Math.random();
				if (z != currentVertex.id && this.cost[z] > weigthVZ){
					this.cost[z] = weigthVZ;
					this.vertices.remove(new Vertex(z, this.cost[z]));
					this.vertices.add(new Vertex(z, weigthVZ));
				}
			}
		}
	}

	public static void main(String[] args) {
		RandomMST2 graph = new RandomMST2(Integer.parseInt(args[0]));
		float mstSize = 0;
		for(int i = 0; i < graph.noOfVertices; i++){
			mstSize += graph.cost[i];
		}
		System.out.println(mstSize);
	}

}
