package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

import factory.GraphFactory;
import graphs.Graph;

public class DataFilesInterface {
	int num_nodes;
	int num_edges;
	
	GraphFactory factory;
	
	private int nodesWeigths[];
	private LinkedList<Integer>[] edges;
	
	/* Construtor */
	public DataFilesInterface() {}
	
	public void getNumNodes() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Faz leitura pesos dos vertices */
	@SuppressWarnings("unchecked")
	public void readData(String fileName) {
		try {
			File file = new File(fileName);;
			if( !file.isFile() ) {
				System.out.println("ERRO");
				System.exit(-1);
			}
			BufferedReader buffer = new BufferedReader( new FileReader(file) );
			/* Reconhece o valor do numero de vertices */
			String line = buffer.readLine();
			StringTokenizer token = new StringTokenizer(line, " ");
			this.num_nodes = Integer.parseInt( token.nextToken() );
			this.nodesWeigths = new int[this.num_nodes];
			
			/* Le valores dos pesos dos vertices */
			for(int i=0; i<this.num_nodes; i++) { // Percorre todas a linhas onde seta valorado os pesos dos vertices
				if( (line = buffer.readLine()) == null ) // Verifica se existe a linha a ser lida
					break;
				token = new StringTokenizer(line, " ");
				this.nodesWeigths[i] = Integer.parseInt( token.nextToken() ); // Adiciona o peso de vertice a posicao do arranjo correspondente ao vertice
			}
			
			/* Mapeia em um array de lista todas as arestas */
			this.edges = new LinkedList[this.num_nodes];
			int cont = 0; // Contador com o total de arestas identificadas
			
			/* Percorre todas as linhas */
			for(int row=0, col; row<this.num_nodes; row++) {
				if( (line = buffer.readLine()) == null ) // Verifica se ha a nova linha no arquivo
					break;
				this.edges[row] = new LinkedList<Integer>(); // Aloca nova lista no arranjo, representado a linha o novo vertice mapeado
				col = 0;
				token = new StringTokenizer(line, " "); // Divide a linha pelos espacos em branco
				
				/* Percorre todas as colunas */
				while( token.hasMoreTokens() ) { // Enquanto ouver mais colunas na linha
					if( token.nextToken().equals("1") ) { // Na matriz binaria, onde possui 1, e onde ha arestas
						if( row != col ) { // Ignora-se os lacos
							System.out.println(cont + " = " + (row+1) + " - " + (col+1) );
							this.edges[row].add(col); // Adiciona no arranjo de listas a aresta
							cont++; // Incrementa-se o total de arestas encontradas
						}
					}
					col++;
				}
			}
			
			this.num_edges = cont; // Atribui o total de arestas encontradas

			if(true) {
//				for(int i=0; i<this.num_nodes; i++) {
//					System.out.print(this.nodesWeigths[i] + "\n");
//				}
				System.out.print("num edges = " + cont + "\n");
			}
			
			buffer.close(); // Fecha o buffer
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Graph> generateGraphs(String[] graphsTypes) {
		Map<String, Graph> graphs= new HashMap<String, Graph>();
		this.factory = new GraphFactory();
		for(int i=0; i<graphsTypes.length; i++) {
			graphs.put(graphsTypes[i], this.factory.getGraph(graphsTypes[i], this.num_nodes, this.num_edges));
			for(int j=0; j<this.num_nodes; j++) {
				graphs.get( graphsTypes[i] ).setNodeWeight( j, this.nodesWeigths[j] );
				//System.out.println( j + " - " + this.nodesWeigths[j] );
				for(int k=0; k<this.edges[j].size(); k++) {
					graphs.get( graphsTypes[i] ).insertEdge(j, this.edges[j].get(k));
				}
			}
			//this.graphs.get(this.graphsTypes[i]).print();
		}
		return(graphs);
	}
}
