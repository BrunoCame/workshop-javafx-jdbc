package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Departamento;

public class DepartamentoService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Departamento> findAll(){
		return dao.findAll();
	}

}
