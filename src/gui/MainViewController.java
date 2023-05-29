package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemVend;
	
	@FXML
	private MenuItem menuItemDep;
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void menuItemVendAct() {
		System.out.println("AçãoVend");
		
	}
	
	@FXML
	public void menuItemDepAct() {
		System.out.println("AçãoDep");
		
	}
	
	@FXML
	public void menuItemSobreAct() {
		System.out.println("AçãoSobre");
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}
	
	

}
