import java.util.ArrayList;

public class RandomMST {
	
	ArrayList <ArrayList<Float>> adjMatrix = new ArrayList<ArrayList<Float>>();
	
	public RandomMST(int noOfVertices) {
		for (int i = 0; i < noOfVertices; i++) {
			//New row in the adjacency matrix
			ArrayList <Float> row = new ArrayList<Float>();
			
			//Fill up edges in row
			for (int j = 0; j < noOfVertices; j++) {
				if (i == j){
					//Edges along the diagonal
					row.add((float) -1);
				} else {
					row.add((float) Math.random());
				}
			}
			
			// Add row to adjMatrix
			this.adjMatrix.add(row);
		}
	}
	
	public void primsAlgorithm() {
		//System.out.println(tree.adjMatrix.get(3).toString());
	}

	public static void main(String[] args) {
		int noOfVertices = Integer.parseInt(args[0]);
		RandomMST tree = new RandomMST(noOfVertices);

		tree.primsAlgorithm();
	}
}
