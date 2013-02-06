package testers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import graphs.Graph;
import factory.GraphFactory;

public class TesterGraphs {

	public TesterGraphs() {}

	// Metodo de leitura para o caso da entrada ser consistente
	public Graph readFromFile(String fileName, GraphFactory factory, String typeName) {
		File file = new File(fileName);
		Graph graph = null;
		if( !file.isFile() ) {
			System.out.println("ERRO");
			System.exit(-1);
		}
		try {

			BufferedReader buffer = new BufferedReader( new FileReader(file) );
			String line;
			int totalNodes, totalEdges, node1, node2, weight;
			StringTokenizer token;

			// Reconhece o valor do numero de vertices
			line = buffer.readLine();
			token = new StringTokenizer(line);
			totalNodes = Integer.parseInt( token.nextToken() );

			// Reconhece o valor do numero de arestas
			line = buffer.readLine();
			token = new StringTokenizer(line);
			totalEdges = Integer.parseInt( token.nextToken() );

			// Cria novo grafo
			graph = factory.getGraph(typeName, totalNodes, totalEdges);

			// Le as arestas => no origem, no destino, peso aresta
			while( ( line = buffer.readLine() ) != null ) {
				token = new StringTokenizer(line, " ");
				node1 = Integer.parseInt( token.nextToken() );
				node2 = Integer.parseInt( token.nextToken() );
				weight = Integer.parseInt( token.nextToken() );
				// Insere aresta no grafo
				graph.insertEdge(node1, node2, weight);
				//System.out.println( node1 + ", " + node2 + ", " + weight);
				
			}
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}

// ===================== SE HOUVER MAIS DE UM CASO DE TESTE POR ARQUIVO, ADAPTAR ESTE METODO ======================== 
//	public void readFromFile(String fileName) {
//		File file = new File(fileName);
//		if( !file.isFile() ) {
//			System.out.println("ERRO");
//			System.exit(-1);
//		}
//		try {
//
//			BufferedReader buffer = new BufferedReader( new FileReader(file) );
//			String line;
//			int data, node1, node2, weight;
//			StringTokenizer token;
//
//			while( ( line = buffer.readLine() ) != null ) {
//				token = new StringTokenizer(line, " ");
//				// Reconhece o valor do numero de vertices
//				if(token.countTokens() == 1) {
//					data = Integer.parseInt( token.nextToken() );
//					System.out.println( data + " Nodes" );
//					// Reconhece o valor do numero de arestas
//					if ( ( line = buffer.readLine() ) != null ) {
//						token = new StringTokenizer(line, " ");
//						if(token.countTokens() == 1) {
//							data = Integer.parseInt( token.nextToken() );
//							System.out.println( data + " Edges");
//							// Le as arestas => no origem, no destino, peso aresta
//							while( ( line = buffer.readLine() ) != null ) {
//								token = new StringTokenizer(line, " ");
//								if(token.countTokens() == 3) {
//									// Separa e paga os valores para criar uma aresta
//									while( token.hasMoreElements() ) {
//										node1 = Integer.parseInt( token.nextToken() );
//										node2 = Integer.parseInt( token.nextToken() );
//										weight = Integer.parseInt( token.nextToken() );
//										System.out.println( node1 + ", " + node2 + ", " + weight);
//										// Criar aresta
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//
//			buffer.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main( String args[] ) throws Exception {
		
		//String path = "C:\\Users\\Lauro Moraes\\Documents\\GitHub\\BCC204-TeoriaDosGrafos\\Graphs\\src\\testers\\";
		TesterGraphs tester = new TesterGraphs();
		GraphFactory factory = new GraphFactory(); 

		Graph graph;

		System.out.println("Testando grafo represantado por: listas de adjacencia.");
		graph = tester.readFromFile("in01.txt", factory, "list");
		graph.print();
		System.out.println();

		System.out.println("Testando grafo represantado por: vetores de adjacencia.");
		graph = tester.readFromFile("in01.txt", factory, "array");
		graph.print();
		System.out.println();

//		System.out.println("Testando grafo represantado por: matrix de adjacencia.");
//		graph = tester.readFromFile("in01.txt", factory, "matrix");
//		graph.print();
//		System.out.println();
		

		System.out.println("FIM");
	}	
}
