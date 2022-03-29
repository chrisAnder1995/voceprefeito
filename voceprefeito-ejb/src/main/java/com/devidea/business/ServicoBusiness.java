package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Servico;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface ServicoBusiness {
	
	public Servico create(Servico entity);
	public Servico findById(Integer id);
	public List<Servico> findAll();
	public List<Servico> findAll(Integer page);
	public List<Servico> findAllFast();
	public Servico update(Servico entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Servico> list) throws JRException, IOException;
	public List<Servico> findByExample(Servico example, Integer... page);
	// METHODS_BUSINESS
}
