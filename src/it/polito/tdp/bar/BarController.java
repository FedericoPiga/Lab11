package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

	private Simulatore sim;

    @FXML
    void doSimulation(ActionEvent event) {
    	sim.init();
    	sim.run();
    	
    	txtResult.setText("Clienti totali: " + sim.getNumero_totale_clienti() + "\n");
    	txtResult.appendText("Clienti soddisfatti: " + sim.getNumero_clienti_soddisfatti() + "\n");
    	txtResult.appendText("Clienti insoddisfatti: " + sim.getNumero_clienti_insoddisfatti());


    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Untitled'.";

    }

	public void setSim(Simulatore sim) {
		this.sim = sim;
	}
}
