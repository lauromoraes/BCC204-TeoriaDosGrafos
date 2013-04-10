package graphs;


public interface Graph {

	// Defini��o da classe est�tica que ir� representar a aresta do grafo.
	public static class Edge {
		private int node1, node2, weight;
		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
		public int getNode1(){
			return this.node1;
		}
		public int getNode2(){
			return this.node2;
		}
		public int getWeight(){
			return this.weight;
		}
	}

	abstract public void	setNodeWeight(int node, int weight);
	abstract public int		getNodeWeight(int node);
	abstract public int[]	getNodesWeights();
	abstract public int		getTotalLinks(int node);
	abstract public void	insertEdge(int node1, int node2, int weight);
	abstract public void	insertEdge(int node1, int node2);
	abstract public void	insertNonOrientedEdge(int node1, int node2, int weight);
	abstract public boolean	existsEdge(int node1, int node2);
	abstract public boolean	adjacencyListEmpty(int node1);
	abstract public Edge	adjacencyListFirst(int node1);
	abstract public Edge	adjacencyNext(int node1);
	abstract public Edge	removeEdge(int node1, int node2) throws Exception;
	abstract public int		totalNodes();
	abstract public void	print();
	abstract public void	setGraphLabel(String graphLabel);
	abstract public String	getGraphLabel();
	abstract public void	setTotalNodes(int total);
	abstract public int		getTotalNodes();
	abstract public void	setTotalEdges(int total);
	abstract public int		getTotalEdges();
}
