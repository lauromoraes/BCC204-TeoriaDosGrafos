//package algorithms;
//
//import extras.Entry;
//import extras.EntryComparator;
//import graphs.Graph.Edge;
//
//import java.util.Collections;
//
//public class Temp {
//	//System.out.println(restricted_candidate_list.size() + " - " + solution_set.size() );
//	int pos = rand.nextInt( restricted_candidate_list.size() );
//	Entry selected = restricted_candidate_list.remove(pos);
//	// Remove os adjacentes a selected a atualiza novos valores e ordens
//	int node1 = selected.getLabel();
//	candidates.remove(node1);	// Remove dos candidatos
//	Edge e1, e2;
//	if( !graph.adjacencyListEmpty(node1) ) {
//		e1 = graph.adjacencyListFirst(node1);
//		
//		// Diminui a contagem de links dos nos adjacentes ao adjacentes do no solucao
//		do {
//			int node2 = e1.getNode2();	// Rotulo do vertice adjacente
//			if( !graph.adjacencyListEmpty(node2) ) {
//				e2 = graph.adjacencyListFirst(node2);
//				do {
//					int adj = e2.getNode2();
//					Entry toReduce;
//					if( ((toReduce = candidates.get(adj)) != null) && (node2 != adj) )  {
//						int links = toReduce.getLinks();
//						System.out.print("LINKS (" + node1 + ", " + node2 + ", " + toReduce.getLabel() + ") = " + links + " - ");
//						toReduce.setLinks(links-1);
//						System.out.println(toReduce.getLinks());
//						toReduce.setEvaluationCost(evaluation_type);
//						if( toReduce.getEvaluationCost() < upper_bound  ) {
//							restricted_candidate_list.add(toReduce);
//						}
//					} else if ( (toReduce = blockeds.get(adj)) != null ) {
//						int links = toReduce.getLinks(); 
//						toReduce.setLinks(links-1);
//					} else if ( (toReduce = solution_set.get(adj)) != null ) {
//						int links = toReduce.getLinks(); 
//						toReduce.setLinks(links-1);
//					}
//				} while( (e2 = graph.adjacencyNext(node2)) != null );
//			}
//			Entry toBlock = candidates.remove(node2);	// Remove o no do conjunto de candidatos
//			
//			if (candidates.get(node2) != null ) {
//				System.out.print("AQUI!!! ");
//				candidates.get(node2).toString();
//			}
//			int p = restricted_candidate_list.indexOf(toBlock);
//			if(p!=(-1)) {
//				restricted_candidate_list.remove(pos);
//				System.out.println("POS: "+p+", "+toBlock.getLabel());
//			}
//			blockeds.put(node2, toBlock);	// Insere o no nos bloqueados
//		} while( (e1 = graph.adjacencyNext(node1)) != null );
//	}
//	solution_set.put(node1, selected);	// Adiciona a solucao
//	if(selected == entry_min) {
//		Collections.sort( restricted_candidate_list, new EntryComparator() );
//		entry_min = restricted_candidate_list.getFirst();
//		
//		min = entry_min.getEvaluationCost();
//		upper_bound = min + ( alpha * ( max - min ) );
//		
//		for(Entry c : candidates.values()) {
//			if( c.getEvaluationCost() < upper_bound ) {
//				restricted_candidate_list.add(c);
//				// Impressao para debug
//				System.out.println(c.toString());
//			}
//		}
//	} else if(selected == entry_max) {
//		Collections.sort( restricted_candidate_list, new EntryComparator() );
//		entry_max = restricted_candidate_list.getLast();
//		
//		max = entry_max.getEvaluationCost();
//		upper_bound = min + ( alpha * ( max - min ) );
//		
//		for(Entry c : candidates.values()) {
//			if( c.getEvaluationCost() < upper_bound ) {
//				restricted_candidate_list.add(c);
//				// Impressao para debug
//				System.out.println(c.toString());
//			}
//		}
//	}
//}













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
