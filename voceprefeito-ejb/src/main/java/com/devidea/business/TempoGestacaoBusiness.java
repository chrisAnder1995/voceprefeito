package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.TempoGestacao;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface TempoGestacaoBusiness {
	
	public TempoGestacao create(TempoGestacao entity);
	public TempoGestacao findById(Integer id);
	public List<TempoGestacao> findAll();
	public List<TempoGestacao> findAll(Integer page);
	public List<TempoGestacao> findAllFast();
	public TempoGestacao update(TempoGestacao entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<TempoGestacao> list) throws JRException, IOException;
	public List<TempoGestacao> findByExample(TempoGestacao example, Integer... page);
	// METHODS_BUSINESS
}
