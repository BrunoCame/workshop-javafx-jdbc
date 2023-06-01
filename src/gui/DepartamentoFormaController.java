package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listener.DataChangeListener;
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
import model.exceptions.ValidacaoExcessao;
import model.service.DepartamentoService;

public class DepartamentoFormaController implements Initializable {

	private Departamento entity;
	
	private DepartamentoService service;
	
	private List<DataChangeListener> dataChange = new ArrayList<>();
	
	@FXML
	private TextField textId;
	
	@FXML
	private TextField textNome;
	
	@FXML
	private Label labelError;
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cancelar;
	
	public void subcreverDataChange(DataChangeListener dcl) {
		dataChange.add(dcl);	
	}

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
		    notificarDataChange();
		    Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlerts("Erro ao salvar objeto", null, e.getMessage(), AlertType.ERROR);
		}
		catch(ValidacaoExcessao e) {
			setErrorMessage(e.getErrors());
		}
	}
	
	private void notificarDataChange() {
		for(DataChangeListener data : dataChange) {
			data.onDataChanged();
		}
		
	}

	private Departamento getFormData() {
		Departamento obj = new Departamento();
		
		ValidacaoExcessao excessao = new ValidacaoExcessao("Erro ao validar");
		
		obj.setId(Utils.tryParseToInt(textId.getText()));
		
		if(textNome.getText() == null || textNome.getText().trim().equals("")) {
			excessao.addErrors("nome", "Obrigatório");
		}
		obj.setName(textNome.getText());
		
		if(excessao.getErrors().size() > 0) {
			throw excessao;
		}
		
		return obj;
	}

	@FXML
    public void btCancelarACT(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@FXML
	public void setErrorMessage(Map<String, String> error) {
		Set<String> campo = error.keySet();
		
		if(campo.contains("nome")) {
			labelError.setText(error.get("nome"));
		}	
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
