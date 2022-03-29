package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Status;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface StatusBusiness {
	
	public Status create(Status entity);
	public Status findById(Integer id);
	public List<Status> findAll();
	public List<Status> findAll(Integer page);
	public List<Status> findAllFast();
	public Status update(Status entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Status> list) throws JRException, IOException;
	public List<Status> findByExample(Status example, Integer... page);
	// METHODS_BUSINESS
}
