package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo>{
	
	private int numPosti;
	private boolean disponibile;
	
	
	public Tavolo(int numPosti, boolean disponibile) {
		super();
		this.numPosti = numPosti;
		this.disponibile = disponibile;
	}


	public boolean isDisponibile() {
		return disponibile;
	}


	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}


	public int getNumPosti() {
		return numPosti;
	}


	@Override
	public int compareTo(Tavolo o) {
		return this.numPosti - o.numPosti;
	}
	
	
	
	
}
