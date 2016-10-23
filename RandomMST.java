import java.util.ArrayList;

public class RandomMST {
	
	public int noOfVertices;
	public ArrayList <ArrayList<Float>> adjMatrix = new ArrayList<ArrayList<Float>>();
	public ArrayList <ArrayList<Float>> F = new ArrayList<ArrayList<Float>>();
	public ArrayList<Float> C = new ArrayList<Float>();
	public ArrayList<Integer> E = new ArrayList<Integer>();
	public ArrayList<Integer> Q = new ArrayList<Integer>();
	
	public RandomMST(int noOfVertices) {
		this.noOfVertices = noOfVertices;
		for (int i = 0; i < noOfVertices; i++) {
			//New row in the adjacency matrix
			ArrayList <Float> row = new ArrayList<Float>();
			ArrayList <Float> rowF = new ArrayList<Float>();
			//Fill up edges in row
			for (int j = 0; j < noOfVertices; j++) {
				rowF.add((float) -1);
				if (i == j){
					//Edges along the diagonal
					row.add((float) -1);
				} else if (j < i) {
					//Matrix is diagonally symetric
					row.add(this.adjMatrix.get(j).get(i));
				} else {
					row.add((float) Math.random());
				}
			}
			
			// Add row to adjMatrix
			this.adjMatrix.add(row);    //0.87672, 0.3486, 0.76423, 0.14651, .....
			this.F.add(rowF);
			this.C.add(Float.MAX_VALUE); //%, %, %, %, .....
			this.E.add(-1);      //-1, -1, -1, -1, -1, .....
			this.Q.add(i+1);       //1, 2, 3, 4, ....... 100
		}
		
	}
	
	public void primsAlgorithm() {
		while (!this.Q.isEmpty()) {
			Float minQ = Float.MAX_VALUE;
			int minQIndex = 0;
			// Find and remove a vertex v from Q having the minimum possible value of C[v]
			for (int i = 0; i < this.Q.size(); i++) {
				if (this.C.get(this.Q.get(i) - 1) < minQ){
					minQ = this.C.get(this.Q.get(i) - 1);
					minQIndex = i;
				}
			}

			int vertexV = this.Q.remove(minQIndex);
			// Add v to F and, if E[v] is not the special flag value, also add E[v] to F
			if (this.E.get(vertexV - 1) != -1) {
				Float edgeWeight = this.adjMatrix.get(vertexV - 1).get(this.E.get(vertexV - 1) - 1);
				this.F.get(vertexV - 1).set(this.E.get(vertexV - 1) - 1, edgeWeight);
			}
			
			/* Loop over the edges vw connecting v to other vertices w. For each such edge, if w still
			 * belongs to Q and vw has smaller weight than C[w], perform the following steps:
																		Set C[w] to the cost of edge vw
																		Set E[w] to point to edge vw.*/
			ArrayList<Float> edgesVW = this.adjMatrix.get(vertexV - 1);
			for (int w = 0; w < edgesVW.size(); w++) {
				if ((edgesVW.get(w) != -1) && (this.Q.contains(w+1)) && (edgesVW.get(w) < this.C.get(w))) {
					this.C.set(w, edgesVW.get(w));
					this.E.set(w, vertexV);
				}
			}
		}
	}

	public static void main(String[] args) {
		int noOfVertices = Integer.parseInt(args[0]);
		RandomMST graph = new RandomMST(noOfVertices);
		System.out.println(graph.adjMatrix);
		graph.primsAlgorithm();
		System.out.println(graph.F);
	}
}
