package it.polito.tdp.bar.model;

public class GruppoClienti {
	
	private int numPersone;
	private float tolleranza;
	private int time;
	private int durata;
	private Tavolo tavolo;
	
	public GruppoClienti(int numPersone, float tolleranza, int time, int durata) {
		super();
		this.numPersone = numPersone;
		this.tolleranza = tolleranza;
		this.time = time;
		this.durata = durata;
		this.tavolo = null;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public int getTime() {
		return time;
	}

	public int getDurata() {
		return durata;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
}
