package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Configuracao;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface ConfiguracaoBusiness {
	
	public Configuracao create(Configuracao entity);
	public Configuracao findById(Integer id);
	public List<Configuracao> findAll();
	public List<Configuracao> findAll(Integer page);
	public List<Configuracao> findAllFast();
	public Configuracao update(Configuracao entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Configuracao> list) throws JRException, IOException;
	public List<Configuracao> findByExample(Configuracao example, Integer... page);
	// METHODS_BUSINESS
}
