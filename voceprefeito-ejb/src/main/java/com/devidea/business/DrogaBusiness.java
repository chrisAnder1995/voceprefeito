package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Droga;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface DrogaBusiness {
	
	public Droga create(Droga entity);
	public Droga findById(Integer id);
	public List<Droga> findAll();
	public List<Droga> findAll(Integer page);
	public List<Droga> findAllFast();
	public Droga update(Droga entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Droga> list) throws JRException, IOException;
	public List<Droga> findByExample(Droga example, Integer... page);
	// METHODS_BUSINESS
}
