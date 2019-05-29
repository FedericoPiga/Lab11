package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Evento.TipoEvento;

public class Simulatore {
	
	//coda eventi
	private PriorityQueue<Evento> queue = new PriorityQueue<>();
	
	//stato mondo
	private List<Tavolo> tavoli;
	private List<GruppoClienti> gruppiClienti;
	
	//parametri di simulazione
	private int NT_10 = 2;
	private int NT_8 = 4;
	private int NT_6 = 4;
	private int NT_4 = 5;
	private int N_GRUPPI = 2000;
	private Duration INTERVALLO;
	
	//statistiche da calcolare
	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	
	//variabili interne
	private Random rand = new Random();
	
	public Simulatore() {
		this.tavoli = new ArrayList<Tavolo>();
		this.gruppiClienti = new ArrayList<GruppoClienti>();
	}
	
	
	
	public void init() {
		
		gruppiClienti.clear();
		tavoli.clear();
		
		for(int i = 0; i < NT_10; i++) {
			tavoli.add(new Tavolo(10, true));
		}
		
		for(int i = 0; i < NT_8; i++) {
			tavoli.add(new Tavolo(8, true));
		}
		
		for(int i = 0; i < NT_6; i++) {
			tavoli.add(new Tavolo(6, true));
		}
		
		for(int i = 0; i < NT_4; i++) {
			tavoli.add(new Tavolo(4, true));
		}
		
		Collections.sort(tavoli);
		int oraInizio = 0;
		
		for(int i = 0; i < N_GRUPPI; i++) {
			gruppiClienti.add(new GruppoClienti(rand.nextInt(10) + 1, rand.nextFloat(), oraInizio, rand.nextInt(61) + 60));
		}
		
		queue.clear();
		

		
		for(GruppoClienti gc : gruppiClienti) {
			queue.add(new Evento(gc, TipoEvento.ARRIVO_GRUPPO_CLIENTI, gc.getTime()));
			oraInizio += rand.nextInt(10) + 1;
		}
		
		numero_totale_clienti = 0;    
		numero_clienti_soddisfatti = 0;
		numero_clienti_insoddisfatti = 0;
		
	}
	
	public void run() {
		
		while(!queue.isEmpty()) {
			Evento ev = queue.poll();
			
			GruppoClienti gc = ev.getGruppo();
			
			switch(ev.getTipo()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				numero_totale_clienti += gc.getNumPersone();
				
				for(Tavolo t : tavoli) {
					if(t.isDisponibile() && t.getNumPosti() >= gc.getNumPersone()*0.5) {
						t.setDisponibile(false);
						gc.setTavolo(t);
						queue.add(new Evento(gc, TipoEvento.PARTENZA_GRUPPO_CLIENTI, gc.getTime() + gc.getDurata()));
						break;
					}
				}
				if(gc.getTavolo() == null) {
					if(rand.nextFloat() < gc.getTolleranza())
						numero_clienti_soddisfatti += gc.getNumPersone();
					else
						numero_clienti_insoddisfatti += gc.getNumPersone();
				}
				
				break;

			case PARTENZA_GRUPPO_CLIENTI:
				numero_clienti_soddisfatti += gc.getNumPersone();
				
				if(gc.getTavolo() != null)
					gc.getTavolo().setDisponibile(true);
				
				break;
			}
		}
	}

	public int getNumero_totale_clienti() {
		return numero_totale_clienti;
	}



	public int getNumero_clienti_soddisfatti() {
		return numero_clienti_soddisfatti;
	}



	public int getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}
	
}
