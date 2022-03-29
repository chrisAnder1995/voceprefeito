package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.ViolenciaTipo;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface ViolenciaTipoBusiness {
	
	public ViolenciaTipo create(ViolenciaTipo entity);
	public ViolenciaTipo findById(Integer id);
	public List<ViolenciaTipo> findAll();
	public List<ViolenciaTipo> findAll(Integer page);
	public List<ViolenciaTipo> findAllFast();
	public ViolenciaTipo update(ViolenciaTipo entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<ViolenciaTipo> list) throws JRException, IOException;
	public List<ViolenciaTipo> findByExample(ViolenciaTipo example, Integer... page);
	// METHODS_BUSINESS
}
