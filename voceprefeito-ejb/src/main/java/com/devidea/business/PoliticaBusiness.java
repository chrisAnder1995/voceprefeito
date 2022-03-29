package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Politica;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface PoliticaBusiness {
	
	public Politica create(Politica entity);
	public Politica findById(Integer id);
	public List<Politica> findAll();
	public List<Politica> findAll(Integer page);
	public List<Politica> findAllFast();
	public Politica update(Politica entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Politica> list) throws JRException, IOException;
	public List<Politica> findByExample(Politica example, Integer... page);
	// METHODS_BUSINESS
}
