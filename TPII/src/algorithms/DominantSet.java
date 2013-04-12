//package algorithms;
//
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.Random;
//import java.util.TreeMap;
//
//
//import extras.Entry;
//import extras.EntryComparator;
//
//import graphs.Graph;
//import graphs.Graph.Edge;
//
//public class DominantSet {
//	
//	private Graph graph;
//	Random rand;
//	private TreeMap<Integer, Entry> candidates;
//	private TreeMap<Integer, Entry> solution_set;
//	private TreeMap<Integer, Entry> blockeds;
//	private TreeMap<Integer, Entry> rcl;
//	private int evaluation_type;
//	private Entry selected;
//	private Entry rcl_min;
//	private Entry rcl_max;
//	private double rcl_bounds;
//	private double alpha;
//
//	public DominantSet() {
//		
//	}
//	public void greedMethod(Graph graph, double alpha, int evaluation_type, long seed) {
//		
//		// Define semente
//		this.rand = new Random();
//		rand.setSeed(seed);
//		
//		// Define o valor de alpha
//		this.alpha = alpha;
//		
//		// Aloca o grafo
//		this.graph = graph;
//		
//		// Define o tipo de comparacao de entre as entradas
//		this.evaluation_type = evaluation_type;
//		
//		// Inicializa o conjunto solucao vazio
//		this.solution_set = new TreeMap<Integer, Entry>();
//		
//		// Inicializa o conjunto de candidatos base
//		this.candidates = new TreeMap<Integer, Entry>();
//		
//		// Inicializa o conjunto de candidatos base
//		this.blockeds = new TreeMap<Integer, Entry>();
//		
//		// Inicializa o conjunto de candidatos restrito
//		this.rcl = new TreeMap<Integer, Entry>();
//		
//		// elemento minimo da RLC
//		this.rcl_max = new Entry(0,graph.getNodeWeight(0),graph.getTotalLinks(0), evaluation_type);
//		
//		// elemento maximo da RLC
//		this.rcl_min = new Entry(0,graph.getNodeWeight(0),graph.getTotalLinks(0), evaluation_type);
//		
//		// Preenche a lista de candidatos
//		for( int i=1; i<this.graph.getTotalNodes(); i++ ) {
//			
//			// Define os dados basicos das entradas
//			int weight = this.graph.getNodeWeight(i);
//			int links = this.graph.getTotalLinks(i);
//			
//			// Cria nova entrada
//			Entry entry = new Entry( i, weight, links, evaluation_type );
//			
//			// Atualiza o min e o max
//			if(this.rcl_min.getEvaluationCost() > entry.getEvaluationCost()) {
//				this.rcl_min = entry;
//			} else if(this.rcl_max.getEvaluationCost() < entry.getEvaluationCost()) {
//				this.rcl_max = entry;
//			}
//			
//			// Atualiza o conjunto com a entrada
//			this.candidates.put( i, entry );
//			
//			// Impressao para debug
//			//System.out.println( entry.toString() );
//		}
//		System.out.println("-------------------------");
//		
//		// Define as limite superior da lista restrita de candidatos
//		this.setRclBounds();
//		
//		// Preenche a RCL (Restricted candidates List - Lista Restrita de Candidatos)
//		for(Entry e : candidates.values()) {
//			if( e.getEvaluationCost() <= upper_bound ) {
//				rcl.put(e.getLabel(), e);
//				// Impressao para debug
//				System.out.println(e.toString());
//			}
//		}
//		
//		while(candidates.size() > 0) {
//			this.selectFromRCL();
//			this.adjustBounds();
//		}		
//	}
//	
//	public Entry selectFromRCL() {
//		// Seleciona aleatoriamente da RCL
//		int pos = this.rand.nextInt( this.rcl.size() );
//		for( Map.Entry<Integer, Entry> e : this.rcl.entrySet() ) {
//			if( (pos--) == 0 ) {
//				this.selected = e.getValue();
//				int key = this.selected.getLabel();
//				//System.out.println(">>>key:" + key);
//				this.rcl.remove(key);
//				
//				// Percorre os adjacentes diminuindo seus links
//				if( !this.graph.adjacencyListEmpty(key) ) {
//					Edge edge = this.graph.adjacencyListFirst(key);
//					do {
//						int adj_label = edge.getNode2();
//						//System.out.println("adj:" + adj_label);
//						if( key != adj_label ) {
//							Entry adj = this.candidates.get(adj_label);
//							if(adj != null) {
//								adj.setLinks( (adj.getLinks()-1) );
//								adj.evaluation(this.evaluation_type);
//								this.selected.setLinks( (this.selected.getLinks()-1) );
//							}
//						}
//					} while( (edge = this.graph.adjacencyNext(key)) != null );
//					this.selected.evaluation(this.evaluation_type);
//				}
//				
//				this.candidates.remove(key);
//				this.solution_set.put(key, this.selected);
//				// Retorna o elemento selecionado
//				return this.selected;
//			}
//		}
//		return null;
//	}
//	
//	public void adjustBounds() {
//		if(this.selected == this.rcl_min) {
//			Entry min = this.rcl.firstEntry().getValue();
//			if( min != null ) {
//				for( Map.Entry<Integer, Entry> e : this.rcl.entrySet() ) {
//					if( e.getValue().getEvaluationCost() < min.getEvaluationCost()  ) {
//						min = e.getValue();
//					}
//				}
//			} else if( (min = this.candidates.firstEntry().getValue()) != null ) {
//				for( Map.Entry<Integer, Entry> e : this.candidates.entrySet() ) {
//					if( e.getValue().getEvaluationCost() < min.getEvaluationCost()  ) {
//						min = e.getValue();
//					}
//				}
//			}
//			this.setRclBounds();
//		} else if( this.selected == this.rcl_max ) {
//			Entry max = this.rcl.firstEntry().getValue();
//			if( max != null ) {
//				for( Map.Entry<Integer, Entry> e : this.rcl.entrySet() ) {
//					if( e.getValue().getEvaluationCost() > max.getEvaluationCost()  ) {
//						max = e.getValue();
//					}
//				}
//			} else if( (max = this.candidates.firstEntry().getValue()) != null ) {
//				for( Map.Entry<Integer, Entry> e : this.candidates.entrySet() ) {
//					if( e.getValue().getEvaluationCost() > max.getEvaluationCost()  ) {
//						max = e.getValue();
//					}
//				}
//			}
//			this.setRclBounds();
//		}
//	}
//	
//	public void setRclBounds() {
//		double min = this.rcl_min.getEvaluationCost();
//		double max = this.rcl_max.getEvaluationCost();
//		this.rcl_bounds = min + ( this.alpha * ( max - min ) );
//		// Impressao para debug
//		//System.out.println(min + " - " + max + ", " + upper_bound);
//	}
//}
