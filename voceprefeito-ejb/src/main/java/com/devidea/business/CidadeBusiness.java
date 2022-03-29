package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Cidade;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface CidadeBusiness {
	
	public Cidade create(Cidade entity);
	public Cidade findById(Integer id);
	public List<Cidade> findAll();
	public List<Cidade> findAll(Integer page);
	public List<Cidade> findAllFast();
	public Cidade update(Cidade entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Cidade> list) throws JRException, IOException;
	public List<Cidade> findByExample(Cidade example, Integer... page);
	// METHODS_BUSINESS
}
