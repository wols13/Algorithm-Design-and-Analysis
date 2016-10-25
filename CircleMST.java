import java.util.ArrayList;

public class CircleMST {
	
	public float mstSize = 0;
	public float[] C;
	public int[] E;
	public ArrayList<Integer> Q;
	public double[][] cordinates;
	
	public CircleMST(int noOfVertices) {
		this.C = new float[noOfVertices];
		this.E = new int[noOfVertices];
		this.Q = new ArrayList<Integer>();
		this.cordinates = new double[noOfVertices][2];

		for (int i = 0; i < noOfVertices; i++) {
			this.C[i] = Float.MAX_VALUE;
			this.E[i] = -1;
			this.Q.add(i + 1);
			double[] newCordinates = this.newPoint();
			this.cordinates[i][0] =  newCordinates[0];
			this.cordinates[i][1] =  newCordinates[1];
		}
	}
	
	public void primsAlgorithm() {
		while (!this.Q.isEmpty()) {
			float minQ = 2;
			int minQIndex = 0;
			// Find and remove a vertex v from Q having the minimum possible value of C[v]
			for (int i = 0; i < this.Q.size(); i++) {
				int column = this.Q.get(i) - 1;
				if (this.C[column] < minQ){
					minQ = this.C[column];
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
				float edgeVW = (float) getDistance(this.cordinates[vertexV - 1], this.cordinates[column]);
				if (edgeVW < C[column]) {
					this.C[column] = edgeVW;
					this.E[column] = vertexV;
				}				
			}
		}
	}

	public double[] newPoint(){
		double y = Math.random();
		double result[] = new double[2];
		
		double angle = Math.toRadians(360 * Math.random());
		
		result[0] = Math.sin(angle) * -y;
		result[1] = Math.cos(angle) * y;
		
		return result;
	}
	
	public double getDistance(double[] cordinate1, double[] cordinate2) {
		return Math.sqrt(Math.pow((cordinate1[0] - cordinate2[0]), 2) + Math.pow((cordinate1[1] - cordinate2[1]), 2));
	}

	public static void main(String[] args) {
		CircleMST graph = new CircleMST(Integer.parseInt(args[0]));
		graph.primsAlgorithm();
		System.out.println(graph.mstSize);
	}
}