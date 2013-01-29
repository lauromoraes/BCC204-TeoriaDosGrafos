package graphs;
// Vinicius teste 2
// Prog 7.2 - pag 274
// Lista de adjacência usando matriz
// Fon Fon
public class AdjacencyMatrixGraph implements Graph {
	private int mat[][]; //pesos do tipo inteiro
	private int numVertices;
	private int pos[]; //posição atual ao se percorrer os adjs de um vértice v
	private String graphLabel;
	
	public AdjacencyMatrixGraph(int numVertices){
	}

	public AdjacencyMatrixGraph(String graphLabel, int numVertices){
	}
	
	@Override
	public void insertEdge(int node1, int node2, int weight) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adjacencyListEmpty(int node1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Edge adjacencyListFirst(int node1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge adjacencyNext(int node1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge removeEdge(int node1, int node2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalNodes() {
		return this.numVertices;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getGraphLabel() {
		return this.graphLabel;
	}

	@Override
	public void setGraphLabel(String graphLabel) {
		this.graphLabel = graphLabel; 
	}

}
