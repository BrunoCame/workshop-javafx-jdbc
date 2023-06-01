package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Departamento;
import model.service.DepartamentoService;

public class ListaDepartamentoController implements Initializable, DataChangeListener {

	private DepartamentoService service;
	
	@FXML
	private Button btNew;
	
	@FXML
	private TableView<Departamento> tableViewDepart;
	
	@FXML
	private TableColumn<Departamento, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departamento, String> tableColumnNome;
	
	@FXML
	private TableColumn<Departamento, Departamento> tableColumnEdit;
	
	private ObservableList<Departamento> obsList;
	
	public void btNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Departamento obj = new Departamento();
		createDialogMode(obj, "/gui/DepartamentoForm.fxml", parentStage);
		
	}

	public void SetDepartamentoS(DepartamentoService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
		
	}

	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepart.prefWidthProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service é nulo");
		}
		List<Departamento> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepart.setItems(obsList);
		initEditButtons();
	}
	
	private void createDialogMode(Departamento obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			DepartamentoFormaController controller = loader.getController();
			controller.setEntity(obj);
			controller.setService(new DepartamentoService());
			controller.subcreverDataChange(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Insira os dados do departamento");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();	
			
		}
		catch(IOException e) {
			Alerts.showAlerts("IO Exception", "Erro ao carregar a View", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
	private void initEditButtons() {
	    tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	    tableColumnEdit.setCellFactory(param -> new TableCell<Departamento, Departamento>() {
	        private final Button button = new Button("editar");

	        @Override
	        protected void updateItem(Departamento obj, boolean empty) {
	            super.updateItem(obj, empty);

	            if (obj == null) {
	                setGraphic(null);
	                return;
	            }
	            setGraphic(button);
	            button.setOnAction(evento -> createDialogMode(obj, "/gui/DepartamentoForm.fxml", Utils.currentStage(evento)));
	        }
	    });
	}
}
