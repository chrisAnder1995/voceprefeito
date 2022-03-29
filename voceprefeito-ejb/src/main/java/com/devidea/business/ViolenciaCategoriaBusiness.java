package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.ViolenciaCategoria;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface ViolenciaCategoriaBusiness {
	
	public ViolenciaCategoria create(ViolenciaCategoria entity);
	public ViolenciaCategoria findById(Integer id);
	public List<ViolenciaCategoria> findAll();
	public List<ViolenciaCategoria> findAll(Integer page);
	public List<ViolenciaCategoria> findAllFast();
	public ViolenciaCategoria update(ViolenciaCategoria entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<ViolenciaCategoria> list) throws JRException, IOException;
	public List<ViolenciaCategoria> findByExample(ViolenciaCategoria example, Integer... page);
	// METHODS_BUSINESS
}
