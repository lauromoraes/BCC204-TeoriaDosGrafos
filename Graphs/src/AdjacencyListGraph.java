
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

	private List<Cell>[] adj;	// Arranjo de listas.
	private int totalNodes;		// Número total de nós no grafo.

	@SuppressWarnings("unchecked")
	public AdjacencyListGraph(int totalNodes) {
		this.totalNodes = totalNodes;
		this.adj = new LinkedList[totalNodes];
		for(int i=0; i<this.totalNodes; i++) {
			this.adj[i] = new LinkedList<Cell>();
		}
	}

	@Override
	public void insertEdge(int node1, int node2, int weight) {
		Cell item = new Cell(node2, weight);
		this.adj[node1].add(item);
	}

	@Override
	public boolean existsEdge(int node1, int node2) {
		Cell item = new Cell(node2, 0);
		return (this.adj[node1].contains(item));
	}

	@Override
	public boolean adjacencyListEmpty(int node1) {
		return (this.adj[node1].isEmpty());
	}

	@Override
	public Edge adjacencyListFirst(int node1) {
		Cell item = (Cell) this.adj[node1].get(0);
		return item != null ? new Edge(node1, item.getNode(), item.getWeight()) : null;
	}

	@Override
	public Edge adjacencyNext(int node1) {
		// Mudar e testar!!!
		Cell item = (Cell) this.adj[node1].iterator().next(); // Definir
		return item != null ? new Edge(node1, item.getNode(), item.getWeight()) : null;
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
		for(int i=0; i<this.totalNodes; i++) {
			System.out.println("Node "+i+":");
			Cell item = (Cell) this.adj[i].get(0);
			while(item != null) {
				System.out.println(" "+item.getNode()+" ("+item.getWeight()+")");
				//item = (Celula) this.adj[i].iterator().next();
			}
		}
	}

}
