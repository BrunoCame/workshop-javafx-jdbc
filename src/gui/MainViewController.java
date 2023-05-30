package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.service.DepartamentoService;

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
		loadView("/gui/ListaDepartamento.fxml", (ListaDepartamentoController controler) -> {
			controler.SetDepartamentoS(new DepartamentoService());
			controler.updateTableView();
		});	
	}
	
	@FXML
	public void menuItemSobreAct() {
		loadView("/gui/About.fxml", x -> {});	
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
			
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializeAction) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
			T controler = loader.getController();
			initializeAction.accept(controler);
		
		} catch (IOException e) {
			Alerts.showAlerts("IOException", "Erorr", e.getMessage(), AlertType.ERROR);

		}
	}
	

}
