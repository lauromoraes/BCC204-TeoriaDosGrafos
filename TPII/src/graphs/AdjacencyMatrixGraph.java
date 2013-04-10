package graphs;
// Vinicius teste 2
// Prog 7.2 - pag 274
// Lista de adjacência usando matriz
// Fon Fon
public class AdjacencyMatrixGraph implements Graph {
	private int mat[][]; // pesos do tipo inteiro
	private int numVertices;
	private int numArestas;
	private int pos[]; // posição atual ao se percorrer os adjs de um vértice arbitrario
	private String graphLabel;
	private int nodesWeigths[];
	
	public AdjacencyMatrixGraph(int numVertices){
		this.mat = new int[numVertices][numVertices];
		this.pos = new int [numVertices];
		this.numVertices = numVertices;
		this.nodesWeigths = new int[numVertices];
		for(int i = 0; i < this.numVertices; i++)
		{
			for(int j = 0; j < this.numVertices; j++)
				this.mat[i][j] = 0;
			this.pos[i] = -1;
		}
	}
	
	@Override
	public void insertEdge(int node1, int node2, int weight) {
		this.mat[node1][node2] = weight;
	}

	@Override
	public boolean existsEdge(int node1, int node2) {
		
		return (this.mat[node1][node2] > 0);
	}

	@Override
	public boolean adjacencyListEmpty(int node1) {
		for (int i = 0; i < this.numVertices; i++)
			if(this.mat[node1][i] > 0)
				return false;
		return true;
	}

	@Override
	public Edge adjacencyListFirst(int node1) {
		// retorna a primeira aresta que o vertice node1 participa ou null se a lista de adjacencia for vazia.
		this.pos[node1] = -1;
		return this.adjacencyNext(node1);
	}

	@Override
	public Edge adjacencyNext(int node1) {
		// retorna a proxima aresta que o vertice node1 participa ou null se a lista de adjacencia estiver no fim.
		this.pos[node1]++;
		
		while( (this.pos[node1] < this.numVertices) && (this.mat[node1][this.pos[node1]] == 0) )
			this.pos[node1]++;
		
		if(this.pos[node1] == this.numVertices)
			return null;
		else return new Edge(node1, this.pos[node1], this.mat[node1][this.pos[node1]]);
	}

	@Override
	public Edge removeEdge(int node1, int node2) throws Exception {
		if(this.mat[node1][node2] == 0)
			return null; //Aresta nao existe
		else
		{
			Edge edge = new Edge (node1, node2, this.mat[node1][node2]);
			this.mat[node1][node2] = 0;
			return edge;
		}
	}

	@Override
	public int totalNodes() {
		return this.numVertices;
	}

	@Override
	public void print() {
		System.out.print ("  ");
		for(int i = 0; i < this.numVertices; i++)
			System.out.print (i + "  ");
		System.out.println();
		for(int i = 0; i < this.numVertices; i++)
		{
			System.out.print (i + "  ");
			
			for(int j = 0; j < this.numVertices; j++)
				System.out.print (this.mat[i][j] + "  ");
			
			System.out.println();
		}
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

	@Override
	public void setNodeWeight(int node, int weight) {
		this.nodesWeigths[node] = weight;
	}

	@Override
	public int getNodeWeight(int node) {
		return(this.nodesWeigths[node]);
	}

	@Override
	public void insertEdge(int node1, int node2) {
		this.mat[node1][node2] = 1;
	}

	@Override
	public int[] getNodesWeights() {
		return(this.nodesWeigths);
	}

	@Override
	public int getTotalLinks(int node) {
		int total = 0;
		for(int i =0; i<this.numVertices; i++) {
			if(this.mat[node][i] != 0) {
				total++;
			}
		}
		return total;
	}

}
