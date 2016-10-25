import java.util.ArrayList;

public class RandomMST {
	
	public float mstSize = 0;
	public float[] C;
	public int[] E;
	public ArrayList<Integer> Q;
	
	public RandomMST(int noOfVertices) {
		this.C = new float[noOfVertices];
		this.E = new int[noOfVertices];
		this.Q = new ArrayList<Integer>();

		for (int i = 0; i < noOfVertices; i++) {
			this.C[i] = Float.MAX_VALUE;
			this.E[i] = -1;
			this.Q.add(i + 1);
		}
	}
	
	public void primsAlgorithm() {
		while (!this.Q.isEmpty()) {
			float minQ = 2;
			int minQIndex = 0;
			// Find and remove a vertex v from Q having the minimum possible value of C[v]
			for (int i = 0; i < this.Q.size(); i++) {
				if (this.C[this.Q.get(i) - 1] < minQ){
					minQ = this.C[this.Q.get(i) - 1];
					minQIndex = i;
				}
			}

			int vertexV = this.Q.remove(minQIndex);
			// Add v to F and, if E[v] is not the special flag value, also add E[v] to F
			if (this.E[vertexV - 1] != -1) {
				this.mstSize += this.C[vertexV - 1];
			}
			
			/* Loop over the edges vw connecting v to other vertices w. For each such edge, if w still
			 * belongs to Q and vw has smaller weight than C[w], perform the following steps:
																		Set C[w] to the cost of edge vw
																		Set E[w] to point to edge vw.*/
			for (int w = 0; w < this.Q.size(); w++) {
				int column = this.Q.get(w) - 1;
				float edgeVW = (float) Math.random();
				if (edgeVW < C[column]) {
					this.C[column] = edgeVW;
					this.E[column] = vertexV;
				}				
			}
		}
	}

	public static void main(String[] args) {
		RandomMST graph = new RandomMST(Integer.parseInt(args[0]));
		graph.primsAlgorithm();
		System.out.println(graph.mstSize);
	}
}
