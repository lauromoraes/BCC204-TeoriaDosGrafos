package algorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import graphs.Graph;
import graphs.Graph.Edge;

public class DominantSet {
	private static class Entry {
		private int label;
		private int cost;
		private int links;
		public Entry(int label, int cost, int links) {
			this.label = label;
			this.cost = cost;
			this.links = links;
		}
		public int getLabel() { 
			return( this.label ); 
		}
		public int getCost() { 
			return( this.cost ); 
		}
		public int getLinks() { 
			return( this.links ); 
		}
		public void setLabel( int label ) {
			this.label = label;
		}
		public void setCost( int cost ) {
			this.cost = cost;
		}
		public void setLinks( int links ) {
			this.links = links;
		}
		public String toString() {
			return( "(" + this.label + ", " + this.cost + ", " + this.links + ")" );
		}
	}
	public DominantSet() {
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void greedMethod(Graph graph, double alpha, long seed) {
		
		// Inicializa o conjunto solucao vazio
		LinkedList<Entry> solution_set = new LinkedList<Entry>();
		
		// Inicializa o conjunto de candidatos base
		TreeMap<Integer, Entry> candidates = new TreeMap<Integer, Entry>();
		
		// Inicializa o conjunto de candidatos base
		LinkedList<Entry> restricted_candidate_list = new LinkedList<Entry>();
		
		// Preenche a lista de candidatos
		for( int i=0; i<graph.getTotalNodes(); i++ ) {
			int weight = graph.getNodeWeight(i);
			int links = graph.getTotalLinks(i);
			Entry entry = new Entry( i, weight, links );
			candidates.put( i, entry );
		}
		
		for(Map.Entry<Integer, Entry> e : candidates.entrySet()) {
			restricted_candidate_list.add( e.getValue() );
			System.out.print( e.getValue().toString() );
		}
		System.out.println();
		
		// Ordena pelos pesos
		Collections.sort(restricted_candidate_list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return( (Comparable) (evaluation(1, ((Entry) (o1)) )) ).compareTo( evaluation( 1, ((Entry) (o2)) ) );
			}
		});
		
		for(Entry e : restricted_candidate_list) {
			System.out.print( e.toString() );
		}
		System.out.println();
		
		Random rand = new Random();
		rand.setSeed(seed);
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
	public double evaluation(int evaluation_type, Entry entry) {
		try {
			int cost = entry.getCost();
			int links = entry.getLinks();
			switch(evaluation_type) { // Escolhe o tipo de funcao de avaliacao
				case 1:
					return( (double) (cost/links) );
				case 2:
					return( (double) ( (cost) / (links^2) ) );
				case 3:
					return( (double) ( Math.sqrt(cost) / (links^2) ) );
				default:
					return( (double) (0) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(-1); // ERRO
	}
	
//	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
//	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
//	        new Comparator<Map.Entry<K,V>>() {
//	            @Override
//	            public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
//	                int res = e1.getValue().compareTo(e2.getValue());
//	                if (e1.getKey().equals(e2.getKey())) {
//	                    return res;
//	                } else {
//	                    return res != 0 ? res : 1;
//	                }
//	            }
//	        }
//	    );
//	    sortedEntries.addAll(map.entrySet());
//	    return sortedEntries;
//	}
}
