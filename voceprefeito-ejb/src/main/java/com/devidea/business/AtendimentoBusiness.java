package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Atendimento;
import com.devidea.model.ListagemPojo;
import com.devidea.model.Servico;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface AtendimentoBusiness {
	
	public Atendimento create(Atendimento entity);
	public Atendimento findById(Integer id);
	public List<Atendimento> findAll();
	public List<Atendimento> findAll(Integer page);
	public List<Atendimento> findAllFast();
	public Atendimento update(Atendimento entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Atendimento> list) throws JRException, IOException;
	public byte[] generatePDF(Integer atendimentoId, HttpServletRequest servletRequest) throws JRException, IOException;
	public List<Servico> findServicosByAtendimento(Atendimento atendimento);
	public byte[] auxilioLegalPDF(Integer id, HttpServletRequest httpServletResquest) throws JRException, IOException;
	public List<Atendimento> findByExample(Atendimento example, Integer listPage, Integer listPageSize);
	
	// METHODS_BUSINESS
}
