package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Departamento;
import model.service.DepartamentoService;

public class DepartamentoFormaController implements Initializable {

	private Departamento entity;
	
	private DepartamentoService service;
	
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
	

	public void setEntity(Departamento entity) {
		this.entity = entity;
	}
	
	public void setService(DepartamentoService service) {
		this.service = service;
	}

	@FXML
	public void btSaveACT(ActionEvent event) {
		
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		try {	
		    entity = getFormData();
		    service.saveOrUpadate(entity);
		    Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlerts("Erro ao salvar objeto", null, e.getMessage(), AlertType.ERROR);
		}	
	}
	
	private Departamento getFormData() {
		Departamento obj = new Departamento();
		obj.setId(Utils.tryParseToInt(textId.getText()));
		obj.setName(textNome.getText());
		
		return obj;
	}

	@FXML
    public void btCancelarACT(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity is null");
		}
		textId.setText(String.valueOf(entity.getId()));
		textNome.setText(entity.getName());
	}
	
	

}
