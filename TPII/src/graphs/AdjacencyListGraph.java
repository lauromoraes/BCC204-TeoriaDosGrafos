package graphs;

import java.util.LinkedList;
import java.util.List;


public class AdjacencyListGraph implements Graph {
	
	private static class Cell {

		private int node;
		private int weight;

		public Cell(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		public Cell(int node) {
			this.node = node;
		}
		public boolean equals(Object obj) {
			Cell item = (Cell) obj;
			return (this.node == item.node);
		}
		public int getNode() {
			return node;
		}
		public int getWeight() {
			return weight;
		}
	}

	private String graphLabel;
	private List<Cell>[] adj;	// Arranjo de listas.
	private int[] pos;			// Array de posicoes.
	private int totalNodes;		// Número total de nós no grafo.
	private int totalEdges;		// Número total de arestas no grafo.
	private int nodesWeigths[];

	@SuppressWarnings("unchecked")
	public AdjacencyListGraph(String graphLabel, int totalNodes) {
		this.totalNodes = totalNodes;
		this.graphLabel = graphLabel; 
		this.adj = new LinkedList[this.totalNodes];
		this.pos = new int[this.totalNodes];
		this.nodesWeigths = new int[totalNodes];
		for(int i=0; i <= (this.totalNodes+1); i++) {
			this.adj[i] = new LinkedList<Cell>();
			this.pos[i] = 0;
		}
	}

	@SuppressWarnings("unchecked")
	public AdjacencyListGraph(int totalNodes) {
		this.totalNodes = totalNodes; 
		this.adj = new LinkedList[this.totalNodes];
		this.pos = new int[this.totalNodes];
		this.nodesWeigths = new int[totalNodes];
		for(int i=0; i<this.totalNodes; i++) {
			this.adj[i] = new LinkedList<Cell>();
			this.pos[i] = 0;
		}
	}

	@Override
	public void insertEdge(int node1, int node2, int weight) {
//		System.out.println(">> " + node1 + " - " + node2 + " - " + weight);
		Cell item = new Cell( (node2), weight);
		this.adj[node1].add(item);
	}

	@Override
	public boolean existsEdge(int node1, int node2) {
		Cell item = new Cell(node2, 0);
		return (this.adj[node1].contains(item));
	}

	@Override
	public boolean adjacencyListEmpty(int node1) {
//		System.out.print("<<>> " + node1 + " - ");
//		System.out.println(this.adj[node1].isEmpty());
		return (this.adj[node1].isEmpty());
	}

	@Override
	public Edge adjacencyListFirst(int node1) {
		this.pos[node1] = 0;
		if( this.adj[node1].size() <= 0 )
			return null;
		Cell item = (Cell) this.adj[node1].get( this.pos[node1] );
		if(item != null) {
			return ( new Edge(node1, item.getNode(), item.getWeight()) );
		}
		return null;
	}

	@Override
	public Edge adjacencyNext(int node1) {
		int pos = ++(this.pos[node1]);
		if( this.adj[node1].size() <= pos )
			return null;
		Cell item = (Cell) this.adj[node1].get( pos );
		if(item != null) {
			return ( new Edge(node1, item.getNode(), item.getWeight()) );
		}
		return null;
	}

	@Override
	public Edge removeEdge(int node1, int node2) throws Exception {
		Cell key = new Cell(node2, 0);
		// Arrumar
		return (this.adj[node1].remove(key) ? null : null);
	}

	@Override
	public int totalNodes() {
		return (this.totalNodes);
	}

	@Override
	public void print() {
		int i, listSize;
		Cell item;
		for(int j=0; j<this.totalNodes; j++) {
			listSize = this.adj[j].size();
			System.out.println( "Node " + (j+1) + ": " );
			for( i=0; i < listSize; i++) {
				item = (Cell) this.adj[j].get(i);
				System.out.println( " " + (item.getNode()+1) + " (" + item.getWeight() + ")" );
			}
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
		this.totalNodes = total;
	}

	@Override
	public int getTotalNodes() {
		return this.totalNodes;
	}

	@Override
	public void setTotalEdges(int total) {
		this.totalEdges = total;
	}

	@Override
	public int getTotalEdges() {
		return this.totalEdges;
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
		Cell item = new Cell(node2);
		this.adj[node1].add(item);
	}

	@Override
	public int[] getNodesWeights() {
		return(this.nodesWeigths);
	}

	@Override
	public int getTotalLinks(int node) {
		return( this.adj[node].size() );
	}
}