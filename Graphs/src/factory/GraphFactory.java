package factory;

import graphs.AdjacencyArrayGraph;
import graphs.AdjacencyListGraph;
import graphs.AdjacencyMatrixGraph;
import graphs.Graph;


public class GraphFactory {

	private static GraphFactory instance;

	public GraphFactory() {}

	// Lazy Initiation
	public static GraphFactory getFactory() {
		return instance == null ? instance = new GraphFactory() : instance;
	}

	public Graph getGraph(String typeName, String graphLabel, int totalNodes, int totalEdges) {
		Graph graph;
		switch(typeName.toLowerCase()){
		case "array":
			graph = new AdjacencyArrayGraph(graphLabel, totalNodes, totalEdges);
			break;
		case "list":
			graph = new AdjacencyListGraph(graphLabel, totalNodes);
			break;
		case "matrix":
			graph = new AdjacencyMatrixGraph(graphLabel, totalNodes);
			break;
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
			break;
		case "list":
			graph = new AdjacencyListGraph(totalNodes);
			break;
		case "matrix":
			graph = new AdjacencyMatrixGraph(totalNodes);
			break;
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
			break;
		case "list":
			graph = new AdjacencyListGraph(totalNodes);
			break;
		case "matrix":
			graph = new AdjacencyMatrixGraph(totalNodes);
			break;
		default:
			graph = null;
		}
		return graph;
	}
}
