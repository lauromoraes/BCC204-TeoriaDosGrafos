package graphs;
// Vinicius teste 2
// Prog 7.2 - pag 274
// Lista de adjac�ncia usando matriz
// Fon Fon
public class AdjacencyMatrixGraph implements Graph {
	private int mat[][]; //pesos do tipo inteiro
	private int numVertices;
	private int pos[]; //posi��o atual ao se percorrer os adjs de um v�rtice v
	
	public AdjacencyMatrixGraph(int numVertices){
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

}
