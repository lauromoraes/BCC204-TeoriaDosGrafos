package factory;

import graphs.AdjacencyArrayGraph;
import graphs.AdjacencyListGraph;
import graphs.AdjacencyMatrixGraph;
import graphs.Graph;


public class GraphFactory {

	private static GraphFactory instance;

	private GraphFactory() {}

	// Lazy Initiation
	public static GraphFactory getFactory() {
		return instance == null ? instance = new GraphFactory() : instance;
	}

	public Graph getGraph(String typeName, String graphLabel, int totalNodes, int totalEdges) {
		Graph graph;
		switch(typeName.toLowerCase()){
		case "array":
			graph = new AdjacencyArrayGraph(graphLabel, totalNodes, totalEdges);
		case "list":
			graph = new AdjacencyListGraph(graphLabel, totalNodes);
		case "matrix":
			graph = new AdjacencyMatrixGraph(graphLabel, totalNodes);
		default:
			graph = null;
		}
		return graph;
	}

	public Graph getGraph(String typeName, int totalNodes, int totalEdges) {
		Graph graph;
		switch(typeName.toLowerCase()){
		case "array":
			graph = new AdjacencyArrayGraph(totalNodes, totalEdges);
		case "list":
			graph = new AdjacencyListGraph(totalNodes);
		case "matrix":
			graph = new AdjacencyMatrixGraph(totalNodes);
		default:
			graph = null;
		}
		return graph;
	}

	public Graph getGraph(String typeName, int totalNodes) {
		Graph graph;
		switch(typeName.toLowerCase()){
		case "array":
			graph = new AdjacencyArrayGraph(totalNodes, 0);
		case "list":
			graph = new AdjacencyListGraph(totalNodes);
		case "matrix":
			graph = new AdjacencyMatrixGraph(totalNodes);
		default:
			graph = null;
		}
		return graph;
	}
}
