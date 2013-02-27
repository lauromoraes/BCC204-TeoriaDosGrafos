package process;

import java.util.LinkedList;
import java.util.List;

import graphs.Graph;

public class TopologicalSorting {

	public static final byte white = 0;
	public static byte grey = 1;
	public static byte black = 2;
	private int d[];
	private int t[];
	private int origin[];
	private Graph graph;
	private List<Integer> orderedList;

	public TopologicalSorting(Graph graph) {
		this.graph = graph;
		int n = this.graph.totalNodes();
		this.d = new int[n];
		this.t = new int[n];
		this.origin = new int[n];
		this.orderedList = new LinkedList<Integer>();
	}
	private int visitDFS (int u, int time, int color[]) {
//		System.out.print(" ");
		color[u] = grey;
		this.d[u] = ++time;
		if(!this.graph.adjacencyListEmpty(u)) {
			Graph.Edge edge = this.graph.adjacencyListFirst(u);
			while(edge != null) {
				int node = edge.getNode2();
				if(color[node] == white) {
					this.origin[node] = u;
					time = this.visitDFS(node, time, color);
				}
				edge = this.graph.adjacencyNext(u);
			}
		}
		color[u] = black;
		this.t[u] = ++time;
//		System.out.println("TOP: " + u);
		this.orderedList.add(0, u);
//		System.out.println("+++ " + (u+1) );
		
		return time;
	}
	public void topologicalSorting() {
		int time = 0;
		int totalNodes = this.graph.totalNodes();
		int color[] = new int[totalNodes];
		for(int u=0; u<totalNodes; u++) {
			color[u] = white;
			this.origin[u] = -1;
		}
		for(int u=0; u<totalNodes; u++) {
			if(color[u] == white) {
				time = this.visitDFS(u, time, color);
			}
		}

		System.out.println("Lista ordenada");
		int u;

//		for(u=0; u<(totalNodes-1); u++) {
//			System.out.print( (this.orderedList.get(u)+1) + ", " );
//		}
//		System.out.println( (this.orderedList.get(u)+1) );

		for(u=0; u<totalNodes; u++) {
			System.out.print( (this.orderedList.get(u)+1) );
		}
		System.out.println();

		System.out.println( "Size: " + this.orderedList.size() );
	}
	public int getDistance (int node) {
		return this.d[node];
	}
	public int getTime(int node) {
		return this.d[node];
	}
	public int getOrigin (int node) {
		return this.origin[node];
	}
	public List<Integer> getOrderedList () {
		return this.orderedList;
	}
	public int getMaxTime() {
		int lenght = this.t.length;
		int pos = 0;
		for(int i=0; i<lenght; i++) {
			if(t[pos] < t[i]) {
				pos = i;
			}
		}
		return t[pos];
	}
}
