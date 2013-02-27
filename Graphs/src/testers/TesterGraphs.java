package testers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import process.DepthFirstSearch;
import process.TopologicalSorting;

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
			token = new StringTokenizer(line, " ");
			totalNodes = Integer.parseInt( token.nextToken() );

			// Reconhece o valor do numero de arestas
			totalEdges = Integer.parseInt( token.nextToken() );

			// Cria novo grafo
			graph = factory.getGraph(typeName, totalNodes, totalEdges);

			// Le as arestas => no origem, no destino, peso aresta
			while( ( line = buffer.readLine() ) != null ) {
				token = new StringTokenizer(line, " ");
				node1 = Integer.parseInt( token.nextToken() ) - 1;
				node2 = Integer.parseInt( token.nextToken() ) - 1;
				weight = Integer.parseInt( token.nextToken() );

				// Insere aresta orientada no grafo
				//graph.insertEdge(node1, node2, weight);

				// Insere aresta nao-orientada no grafo
//				System.out.println("<< " + (node1+1) + " - " + (node2+1) + " - "+weight);
				graph.insertEdge(node1, node2, weight);

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
	public static void testCase(String type, String in) {

		TesterGraphs tester = new TesterGraphs();
		GraphFactory factory = new GraphFactory();
		TopologicalSorting topSort;
		DepthFirstSearch dfs;
		Graph graph;

		/* Criando grafo */
		System.out.println();
		System.out.println("Testando grafo represantado por: " + type);
//		graph = tester.readFromFile("in01.txt", factory, type);
		graph = tester.readFromFile(in + ".gr", factory, type);
//		graph.print();

		/* Busca em Profundidade */
		dfs = new DepthFirstSearch(graph);
		dfs.depthFirstSearch();
		System.out.println("\nDFS = " + dfs.getMaxTime() + "\n");

		/* Ordenacao Topologica */
		topSort = new TopologicalSorting(graph);
		long tempoInicial = System.currentTimeMillis();
		topSort.topologicalSorting();
		long tempoFinal = System.currentTimeMillis();
		System.out.println("\nTempo = " + (tempoFinal - tempoInicial) / 1000.0 + "s");

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(in+"_topSort.txt"));
			List<Integer> l = topSort.getOrderedList();
			String outString = "";
			for( Integer i : l ) {
				outString += String.valueOf(i+1);
			}
			out.write(outString+'\0');
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main( String args[] ) throws Exception {

		/* Diferentes tipos de representacao de um grafo */
//		String[] types = { "list", "array", "matrix" };
		String[] types = { "list", "array" };

		/* Arquivos de entrada */
		String[] entries = { "gr_10_30_2_10", "gr_10_4950_2_10", "gr_50_124750_2_10", "gr_100_100000_2_10", "gr_200_19900_2_10", "gr_300_44850_2_10", "gr_10000_300000_2_10" };

		/* Testa para cada tipo de representacao todos os casos de teste */
//		for(int i=0; i < types.length; i++) {
//			for(int j=0; j < entries.length; j++) {
//				testCase(types[i], entries[j]);
//			}
//		}

		/* Testa um tipo de representacao com um caso de teste */
		testCase(types[0], entries[0]);

		System.out.printf("\n************* FIM *************\n");
	}	
}
