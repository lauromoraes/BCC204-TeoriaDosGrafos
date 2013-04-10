package extras;

public class Entry {
	private int label;
	private int cost;
	private int links;
	private double evaluation_cost;
	public Entry(int label, int cost, int links) {
		this.label = label;
		this.cost = cost;
		this.links = links;
		this.evaluation();
	}
	public Entry(int label, int cost, int links, int evaluation_type) {
		this.label = label;
		this.cost = cost;
		this.links = links;
		this.setEvaluationCost(evaluation_type);
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
	public double getEvaluationCost() { 
		return( this.evaluation_cost ); 
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
	public void setEvaluationCost( int evaluation_type ) {
		this.evaluation_cost = this.evaluation(evaluation_type);
	}
	public String toString() {
		return( "(" + this.label + ", " + this.cost + ", " + this.links + ", " + this.getEvaluationCost() + ")" );
	}
	
	public double evaluation(int evaluation_type) {
		try {
			int cost = this.getCost();
			int links = this.getLinks();
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
	public double evaluation() {
		try {
			return( (double) ((this.getCost())/(this.getLinks())) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(-1); // ERRO
	}
	
}
