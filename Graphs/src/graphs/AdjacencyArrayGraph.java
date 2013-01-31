package graphs;
//testando o guit hub
// Prog 7.4 - pag 280
// Lista de adjacência usando arrajos
// Ailton
public class AdjacencyArrayGraph implements Graph {

	private int cab[], prox[], peso[];
	private int pos[];
	private int numVertex, proxDisponivel, numEdge;
	private String graphLabel;
	
	
	public AdjacencyArrayGraph(String graphLabel, int numVertex,int numEdge)
	{
		this.graphLabel = graphLabel;
		int tam = numVertex + 2*numEdge;
		this.numEdge = numEdge;
		this.cab = new int[tam];
		this.peso = new int[tam];
		this.prox = new int[tam];
		this.numVertex = numVertex;
		this.pos = new int[this.numVertex];
		for(int i=0; i<this.numVertex; i++)
		{
			this.prox[i] = 0;
			this.cab[i] = i;
			this.peso[i] = 0;
			this.pos[i] = i;
		}
		this.proxDisponivel = this.numVertex;
	}

	public AdjacencyArrayGraph(int numVertex,int numEdge)
	{
		int tam = numVertex + 2*numEdge;
		this.cab = new int[tam];
		this.peso = new int[tam];
		this.prox = new int[tam];
		this.numVertex = numVertex;
		this.pos = new int[this.numVertex];
		for(int i=0; i<this.numVertex; i++)
		{
			this.prox[i] = 0;
			this.cab[i] = i;
			this.peso[i] = 0;
			this.pos[i] = i;
		}
		this.proxDisponivel = this.numVertex;
	}
	
	
	
	@Override
	public void insertEdge(int node1, int node2, int weight) {
		
		if(this.proxDisponivel == this.cab.length)
		{
			System.out.println("Nao ha espaco disponivel para a aresta");
		}
		else
		{
			
			int ind = this.proxDisponivel++;
			this.prox[this.cab[node1]] = ind;
			this.cab[ind] = node2;
			this.cab[node1] = ind;
			this.prox[ind] = 0;
			this.peso[ind] = weight;
			
		}
		
	}

	@Override
	public boolean existsEdge(int node1, int node2) {
		
		for(int i= this.prox[node1]; i!=0; i=this.prox[i])
		{
			if(cab[i] == node2)
				return true;
		}
		return false;
	}
  
	@Override
	public boolean adjacencyListEmpty(int node1) {
		
		return (this.prox[node1] == 0);
		
	}

	@Override
	public Edge adjacencyListFirst(int node1) {
		this.pos[node1] = node1;
		return this.adjacencyNext(node1);
	
	}

	@Override
	public Edge adjacencyNext(int node1) {
		
		this.pos[node1] = this.prox[this.pos[node1]];
		if(this.pos[node1] == 0)
			return null;
		else
			return new Edge(node1, this.cab[pos[node1]], this.peso[pos[node1]]);
	}

	@Override
	public Edge removeEdge(int node1, int node2) throws Exception {
		
		int i;
		for(i= node1; this.prox[i] !=0;i= this.prox[i])
			if(this.cab[this.prox[i]] == node2)
				break;
		
		int ind = this.prox[i];
		if(this.cab[ind] == node2)
		{
			Edge edge = new Edge(node1, node2, this.peso[ind]);
			this.cab[ind] = this.cab.length;
			if(this.prox[ind] == 0)
				this.cab[node1] = i;
			
			this.prox[i] = this.prox[ind];
			
			return edge;
			
		}
		else
			return null;
			
		
	}

	@Override
	public int totalNodes() {
		return this.numVertex;
	}

	@Override
	public void print() {
		
		for(int i = 0; i < this.numVertex; i++)
		{
			System.out.println("Vertice " + i + ":");
			for(int j = this.prox[i]; j!=0; j=this.prox[j])
			{
				System.out.println(" " + this.cab[j] + " (" + this.peso[j] + ")");
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
		this.numVertex = total;
	}

	@Override
	public int getTotalNodes() {
		return this.numVertex;
	}

	@Override
	public void setTotalEdges(int total) {
		this.numEdge = total;
	}

	@Override
	public int getTotalEdges() {
		return this.numEdge;
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
