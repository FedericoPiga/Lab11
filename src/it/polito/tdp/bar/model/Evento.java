package it.polito.tdp.bar.model;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento {
		ARRIVO_GRUPPO_CLIENTI,
		PARTENZA_GRUPPO_CLIENTI,
	}
	
	private TipoEvento tipo;
	private GruppoClienti gruppo;
	private int time;
	
	public Evento(GruppoClienti gruppo, TipoEvento tipo, int time) {
		super();
		this.gruppo = gruppo;
		this.tipo = tipo;
		this.time = time;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}
	
	public GruppoClienti getGruppo() {
		return gruppo;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}

}
