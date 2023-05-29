package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
		loadAbout("/gui/About.fxml");	
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
			
	}
	
	private synchronized void loadAbout(String absoluteName) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());

		} catch (IOException e) {
			Alerts.showAlerts("IOException", "Erorr", e.getMessage(), AlertType.ERROR);

		}
	}
	

}
