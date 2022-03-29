package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Agressor;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface AgressorBusiness {
	
	public Agressor create(Agressor entity);
	public Agressor findById(Integer id);
	public List<Agressor> findAll();
	public List<Agressor> findAll(Integer page);
	public List<Agressor> findAllFast();
	public Agressor update(Agressor entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Agressor> list) throws JRException, IOException;
	public List<Agressor> findByExample(Agressor example, Integer... page);
	// METHODS_BUSINESS
}
