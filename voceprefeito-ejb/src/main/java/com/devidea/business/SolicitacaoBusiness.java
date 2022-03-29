package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Solicitacao;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface SolicitacaoBusiness {
	
	public Solicitacao create(Solicitacao entity);
	public Solicitacao findById(Integer id);
	public List<Solicitacao> findAll();
	public List<Solicitacao> findAll(Integer page);
	public List<Solicitacao> findAllFast();
	public Solicitacao update(Solicitacao entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Solicitacao> list) throws JRException, IOException;
	public List<Solicitacao> findByExample(Solicitacao example, Integer... page);
	// METHODS_BUSINESS
}
