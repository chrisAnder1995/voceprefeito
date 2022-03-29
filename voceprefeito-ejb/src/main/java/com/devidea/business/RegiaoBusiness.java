package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Regiao;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface RegiaoBusiness {
	
	public Regiao create(Regiao entity);
	public Regiao findById(Integer id);
	public List<Regiao> findAll();
	public List<Regiao> findAll(Integer page);
	public List<Regiao> findAllFast();
	public Regiao update(Regiao entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Regiao> list) throws JRException, IOException;
	public List<Regiao> findByExample(Regiao example, Integer... page);
	// METHODS_BUSINESS
}
