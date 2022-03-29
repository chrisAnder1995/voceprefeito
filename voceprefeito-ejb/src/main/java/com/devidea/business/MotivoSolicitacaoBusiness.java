package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.MotivoSolicitacao;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface MotivoSolicitacaoBusiness {
	
	public MotivoSolicitacao create(MotivoSolicitacao entity);
	public MotivoSolicitacao findById(Integer id);
	public List<MotivoSolicitacao> findAll();
	public List<MotivoSolicitacao> findAll(Integer page);
	public List<MotivoSolicitacao> findAllFast();
	public MotivoSolicitacao update(MotivoSolicitacao entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<MotivoSolicitacao> list) throws JRException, IOException;
	public List<MotivoSolicitacao> findByExample(MotivoSolicitacao example, Integer... page);
	// METHODS_BUSINESS
}
