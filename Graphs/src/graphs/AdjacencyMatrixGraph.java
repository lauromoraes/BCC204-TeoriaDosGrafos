package graphs;
// Vinicius teste 2
// Prog 7.2 - pag 274
// Lista de adjac�ncia usando matriz
// Fon Fon
public class AdjacencyMatrixGraph implements Graph {
	private int mat[][]; //pesos do tipo inteiro
	private int numVertices;
	private int numArestas;
	private int pos[]; //posi��o atual ao se percorrer os adjs de um v�rtice v
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

	@Override
	public void setTotalNodes(int total) {
		this.numVertices = total;
	}

	@Override
	public int getTotalNodes() {
		return this.numVertices;
	}

	@Override
	public void setTotalEdges(int total) {
		this.numArestas = total;
	}

	@Override
	public int getTotalEdges() {
		return this.numArestas;
	}

	@Override
	public void insertNonOrientedEdge(int node1, int node2, int weight) {
		// Grafos nao orientados nao possuem lacos
		if(node1 != node2){
			this.insertEdge(node1, node2, weight);
			this.insertEdge(node2, node1, weight);
		}
	}

}
