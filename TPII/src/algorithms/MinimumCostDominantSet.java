package algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import extras.Entry;
import extras.EntryComparator;
import graphs.Graph;
import graphs.Graph.Edge;

public class MinimumCostDominantSet {
	
	private Graph graph;
	private double alpha;
	private Random rand;
	private long seed;
	private int evaluation_type;
	private int total_cost;
	private int best_cost;
	private int max_iterations;
	
	private Entry selected;
	private int selected_pos;
	private Entry rcl_min;
	private Entry rcl_max;
	private LinkedList<Entry> candidates_list;
	private LinkedList<Entry> neighbors_list;
	private LinkedList<Entry> solution_list;
	private LinkedList<Entry> blockeds_list;
	private LinkedList<Entry> best_list;
	private double rcl_threshold;
	private int rcl_bound;
	
	public MinimumCostDominantSet( Graph graph, int evaluation_type, long seed ) {
		this.graph = graph;
		this.evaluation_type = evaluation_type;
		this.seed = seed;
		this.rand = new Random();
		this.rand.setSeed(this.seed);
		this.total_cost = 0;
		this.max_iterations = 100;
		this.best_cost = -1;
	}
	
	public int getCost() {
		return( this.total_cost );
	}
	
	public int getBestCost() {
		return( this.best_cost);
	}
	
	public void setSeed( int seed ) {
		this.rand.setSeed(seed);
	}
	
	public void setBestSet( LinkedList<Entry> set ) {
		if( (this.best_cost == -1) || (this.best_cost > this.total_cost) ) {
			this.best_cost = this.total_cost;
			this.best_list = new LinkedList<Entry>();
			for(Entry e : set) {
				this.best_list.add(e);
			}
		}
	}
	
	public LinkedList<Entry> constructGreed( double alpha ) {
		this.total_cost = 0;
		this.alpha = alpha;
		this.solution_list = new LinkedList<Entry>();
		this.blockeds_list = new LinkedList<Entry>();
		this.createCandidatesList();
		this.defineRclBound();
		
		while(this.candidates_list.size() > 0) {
			this.selectFromRcl();
			this.removeSelectedFromCandidates();
			this.defineRclBound();
		}
		
//		for( Entry e : this.solution_list ) {
//			System.out.println( e.toString() );
//		}
		//System.out.println( "Total: " + this.total_cost );
		
		this.setBestSet( this.solution_list );
		
		return( this.solution_list );
	}
	
	public LinkedList<Entry> createCandidatesList() {
		this.candidates_list = new LinkedList<Entry>();
		
		for(int i = 0; i < this.graph.getTotalNodes(); i++) {
			int w = this.graph.getNodeWeight(i);
			int l = this.graph.getTotalLinks(i);
			
			Entry e = new Entry( i, w, l, this.evaluation_type );
			this.candidates_list.add(e);
			//System.out.println( e.toString() );
		}
		//System.out.println("----------------------------");
		
		Collections.sort( this.candidates_list, new EntryComparator(this.evaluation_type) );
		
//		for(Entry e : this.candidates_list) {
//			System.out.println( e.toString() );
//		}		
		//System.out.println("----------------------------");
		
		return this.candidates_list;
	}
	
	public int defineRclBound() {
		if(this.candidates_list.size() > 0) {
			this.rcl_min = this.candidates_list.getFirst();
			this.rcl_max = this.candidates_list.getLast();
			this.rcl_threshold = this.rcl_min.getEvaluationCost() + ( this.alpha * ( (this.rcl_max.getEvaluationCost()) - (this.rcl_min.getEvaluationCost()) ) );
			this.rcl_bound = 0;
			for(int i=0; i<this.candidates_list.size(); i++) {
				if( this.candidates_list.get(i).getEvaluationCost() <= this.rcl_threshold ) {
					this.rcl_bound++;
				} else {
					return( this.rcl_bound );
				}
			}
		}
		return(-1);
	}
	
	public Entry selectFromRcl() {
		this.selected_pos = this.rand.nextInt( this.rcl_bound );
		this.selected = this.candidates_list.get( this.selected_pos );
		this.total_cost += this.selected.getCost();
		return this.selected;
	}
	
	public Entry selectFromCandidates() {
		if(this.candidates_list.size() > 0) {
			this.selected_pos = this.rand.nextInt( this.candidates_list.size() );
			this.selected = this.candidates_list.get( this.selected_pos );
			this.total_cost += this.selected.getCost();
			return this.selected;
		}
		return null;
	}
	
	public Entry selectFromSolution() {
		int pos = this.rand.nextInt( this.solution_list.size() );
		Entry e = this.solution_list.get( pos );
		this.total_cost -= e.getCost();
		return e;
	}
	
	public void removeSelectedFromCandidates() {
		LinkedList<Integer> adjs = new LinkedList<Integer>();
		int node = this.selected.getLabel();
		//System.out.println("node: " + node);
		this.candidates_list.remove( this.selected_pos );
		this.solution_list.add( this.selected );
		if( !this.graph.adjacencyListEmpty(node) ) {
			
			Edge e = this.graph.adjacencyListFirst(node);
			do {
				adjs.add( e.getNode2() );
			} while( (e = this.graph.adjacencyNext(node)) != null );
			
			while( adjs.size() > 0 ) {
				int adj = adjs.pop();
				//System.out.println("adj: " + adj + " - " + this.candidates_list.size());
				int cont = 0;
				for(Entry c : this.candidates_list) {
					if( c.getLabel() == adj ) {
						this.candidates_list.remove( cont );
						this.blockeds_list.add( c );
						break;
					} else {
						cont++;
					}
				}
			}
		}
	}
	
	public void removeSelectedFromNeighbors() {
		LinkedList<Integer> adjs = new LinkedList<Integer>();
		int node = this.selected.getLabel();
		//System.out.println("node: " + node + " - " + this.selected_pos + " - " + this.neighbors_list.size() );
		this.neighbors_list.remove( this.selected_pos );
		if( !this.graph.adjacencyListEmpty(node) ) {
			Edge e = this.graph.adjacencyListFirst(node);
			do {
				adjs.add( e.getNode2() );
			} while( (e = this.graph.adjacencyNext(node)) != null );
			
			while( adjs.size() > 0 ) {
				int adj = adjs.pop();
				//System.out.println("adj: " + adj + " - " + this.candidates_list.size());
				int cont = 0;
				for(Entry c : this.blockeds_list) {
					if( c.getLabel() == adj ) {
						this.blockeds_list.remove( cont );
						this.candidates_list.add( c );
						break;
					} else {
						cont++;
					}
				}
			}
		}
		this.blockeds_list.add( this.selected );
	}
	
	public LinkedList<Entry> localSearch() {
		// Deep copy of a list
		this.neighbors_list = new LinkedList<Entry>();
		for( Entry e : this.solution_list ) {
			this.neighbors_list.add(e);
		}
		
		int iteration = 0;
		
		while( iteration++ < this.max_iterations ) {
			this.removeFromSolution();
			if( this.selectFromCandidates() != null ) {
			//this.removeSelectedFromCandidates();
			this.removeSelectedFromNeighbors();
			System.out.println( "ENTER" );
			}
		}
		
		int cost = 0;
		for(Entry e : this.neighbors_list) {
			cost += e.getCost();
			System.out.println( e.toString() );
		}
		System.out.println( cost );
		
		return( this.neighbors_list );
	}
	
	public void removeFromSolution() {
		Entry entry = this.selectFromSolution();
		LinkedList<Integer> adjs = new LinkedList<Integer>();
		int node = entry.getLabel();
		
		if( !this.graph.adjacencyListEmpty(node) ) {
			Edge e = this.graph.adjacencyListFirst(node);
			do {
				adjs.add( e.getNode2() );
			} while( (e = this.graph.adjacencyNext(node)) != null );
			
			while( adjs.size() > 0 ) {
				int adj = adjs.pop();
				//System.out.println("adj: " + adj + " - " + this.candidates_list.size());
				int cont = 0;
				for(Entry c : this.blockeds_list) {
					if( (c.getLabel() == adj) && (this.canEnterCandidate(c)) ) {
						this.blockeds_list.remove( cont );
						this.candidates_list.add( c );
						break;
					} else {
						cont++;
					}
				}
			}
		}
		this.blockeds_list.add(entry);
	}
	
	public boolean canEnterCandidate( Entry entry ) {
		LinkedList<Integer> adjs = new LinkedList<Integer>();
		int node = entry.getLabel();
		if( !this.graph.adjacencyListEmpty(node) ) {
			Edge e = this.graph.adjacencyListFirst(node);
			do {
				adjs.add( e.getNode2() );
			} while( (e = this.graph.adjacencyNext(node)) != null );
			while( adjs.size() > 0 ) {
				int adj = adjs.pop();
				for( Entry c : this.neighbors_list ) {
					if( c.getLabel() == adj ) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
