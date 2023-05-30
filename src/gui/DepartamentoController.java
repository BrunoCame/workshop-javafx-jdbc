package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartamentoController implements Initializable {

	@FXML
	private TextField textId;
	
	@FXML
	private TextField textNome;
	
	@FXML
	private Label error;
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cancelar;
	
	@FXML
	public void btSaveACT() {
		System.out.println("salvou");
	}
	
	@FXML
    public void btDeletarACT() {
		System.out.println("deletou");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	
	
	

}
