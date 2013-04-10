package algorithms;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;


import extras.Entry;

import graphs.Graph;

public class DominantSet {

	public DominantSet() {
		
	}
	public void greedMethod(Graph graph, double alpha, long seed) {
		
		// Define semente
		Random rand = new Random();
		rand.setSeed(seed);
		
		// Define o tipo de comparacao de entre as entradas
		int evaluation_type = 1;
		
		// Inicializa o conjunto solucao vazio
		LinkedList<Entry> solution_set = new LinkedList<Entry>();
		
		// Inicializa o conjunto de candidatos base
		TreeMap<Integer, Entry> candidates = new TreeMap<Integer, Entry>();
		
		// Inicializa o conjunto de candidatos restrito
		LinkedList<Entry> restricted_candidate_list = new LinkedList<Entry>();
		
		Entry entry_min, entry_max;
		entry_min = entry_max = new Entry(0,graph.getNodeWeight(0),graph.getTotalLinks(0), evaluation_type);
		
		// Preenche a lista de candidatos
		for( int i=1; i<graph.getTotalNodes(); i++ ) {
			
			// Define os dados basicos das entradas
			int weight = graph.getNodeWeight(i);
			int links = graph.getTotalLinks(i);
			
			// Cria nova entrada
			Entry entry = new Entry( i, weight, links, evaluation_type );
			
			// Atualiza o min e o max
			if(entry_min.getEvaluationCost() > entry.getEvaluationCost()) {
				entry_min = entry;
			} else if(entry_max.getEvaluationCost() < entry.getEvaluationCost()) {
				entry_max = entry;
			}
			
			// Atualiza o conjunto com a entrada
			candidates.put( i, entry );
			
			// Impressao para debug
			System.out.println( entry.toString() );
		}
		System.out.println("-------------------------");
		
		// Define as limite superior da lista restrita de candidatos
		double min = entry_min.getEvaluationCost();
		double max = entry_max.getEvaluationCost();
		double upper_bound = min + ( alpha * ( max - min ) );
		// Impressao para debug
		System.out.println(min + " - " + max + ", " + upper_bound);
		
		// Preenche a RCL (Restricted candidates List - Lista Restrita de Candidatos)
		for(Entry e : candidates.values()) {
			if( e.getEvaluationCost() < upper_bound ) {
				restricted_candidate_list.add(e);
				// Impressao para debug
				System.out.println(e.toString());
			}
		}
		
		while(candidates.size() > 0) {
			int pos = rand.nextInt( restricted_candidate_list.size() );
			Entry selected = restricted_candidate_list.remove(pos);
			// Remove os adjacentes a selected a atualiza novos valores e ordens
			
		}	
	
		// Ordena pelos pesos
//		Collections.sort( restricted_candidate_list, new EntryComparator(evaluation_type) );
//		
//		for(Entry e : restricted_candidate_list) {
//			System.out.println( e.toString() + " - " + e.getEvaluationCost() );
//		}
//		System.out.println();
		
		

		
		
		
//		synchronized (candidate_set) {
//			while(candidate_set.size() > 0) {
//				double index_min = candidate_set.peekFirst().getSecond();
//				double index_max = candidate_set.peekLast().getSecond();
//				double upper_bound = index_min + ( alpha * ( index_max - index_min ) );
//				
//				int cont = 0;
//				while( ( candidate_set.get(cont).getSecond() < upper_bound ) &&  ( cont < candidate_set.size() ) ) {
//					//System.out.println(cont);
//					cont++;
//				}
//				
//				
//				int tam = Math.max(1, cont );
//				//System.out.println("TAM = " + tam + ", " + candidate_set.size());
//				int pos = rand.nextInt( tam );
//				int key = candidate_set.get(pos).getFirst();
//				Entry element = new Entry( key, candidate_set.get(pos).getSecond() );
//				solution_set.add(element);
//				
//				// Remove todos os adjacentes ao vertice
//				Edge edge = graph.adjacencyListFirst(key);
//				while(edge != null) {
//					int adj = edge.getNode2();
//					System.out.println(key + " - " + adj);
//					int adj_pos = Collections.binarySearch(candidate_set, adj, new Comparator() { 
//						public int compare(Object o1, Object o2) {
//							return ((Comparable) ((Entry) (o1)).getFirst())
//			                        .compareTo(((Integer) (o2)));
//						}
//					});
//					System.out.println(key + " - " + adj_pos);
//					candidate_set.remove( adj_pos );
//					edge = graph.adjacencyNext(key);
//				}
//				
//				candidate_set.remove( pos );
//				
//			}
//			Entry element = new Entry( candidate_set.peekFirst().getFirst(), candidate_set.peekFirst().getSecond() );
//			solution_set.add(element);
//			candidate_set.removeFirst();
//		}
//		System.out.println("Candidate: " + candidate_set.size());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//		for(Entry entry : solution_set ) {
//			System.out.println( entry.getFirst() + " => " + entry.getSecond() );
//		}
		
		
	}
}
