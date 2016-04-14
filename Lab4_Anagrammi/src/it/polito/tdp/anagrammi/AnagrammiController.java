package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.DAO.AnagrammaDAO;
import it.polito.tdp.anagrammiModello.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AnagrammiController {
	
	Model model;
	List<Text> testi = new LinkedList<Text>();
	AnagrammaDAO dao = new AnagrammaDAO();
	

    public void setModel(Model model) {
		this.model = model;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

   
    @FXML
    private Button btnIterazione;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnRicorsivo;

    @FXML
    private TextFlow txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    void doIterazione(ActionEvent event) {
    	testi.clear();
    	this.txtResult.getChildren().clear();
    	boolean controllo=model.controlloInput(this.txtInput.getText());
    	if(!controllo){
    		Text messaggio = new Text("Input non corretto");
    		this.testi.add(messaggio);
    		
    		this.txtResult.getChildren().add(messaggio);return;}
    	List<String> parole = model.anagrammaIter(this.txtInput.getText());
    	if(parole.isEmpty()){
    		Text messaggio1 = new Text("Nessun risultato");
    		this.txtResult.getChildren().add(messaggio1);return;}
    	for(String parola:parole){
    		boolean verifica = dao.cercaInDAO(parola);
    		Text t = new Text(parola);
    		if(!verifica){t.setFill(Color.RED);}
    		testi.add(t);
    	}
    	
    	this.txtResult.getChildren().addAll(testi);

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtResult.getChildren().clear();
    	this.txtInput.clear();
    }

    @FXML
    void doRicorsivo(ActionEvent event) {
    	testi.clear();
    	this.txtResult.getChildren().clear();
    	boolean controllo=model.controlloInput(this.txtInput.getText());
    	if(!controllo){
    		Text messaggio = new Text("Input non corretto");
    		this.testi.add(messaggio);
    		
    		this.txtResult.getChildren().add(messaggio);return;}
    	List<String> parole = model.anagramma(this.txtInput.getText());
    	if(parole.isEmpty()){
    		Text messaggio1 = new Text("Nessun risultato");
    		this.txtResult.getChildren().add(messaggio1);return;}
    	for(String parola:parole){
    		boolean verifica = dao.cercaInDAO(parola);
    		Text t = new Text(parola);
    		if(!verifica){t.setFill(Color.RED);}
    		testi.add(t);
    	}
    	
    	this.txtResult.getChildren().addAll(testi);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnRicorsivo != null : "fx:id=\"btnRicorsivo\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnIterazione != null : "fx:id=\"btnIterazione\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Anagrammi.fxml'.";

    }
}

