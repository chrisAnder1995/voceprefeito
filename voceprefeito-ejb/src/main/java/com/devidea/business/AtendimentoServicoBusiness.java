package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Atendimento;
import com.devidea.model.AtendimentoServico;
import com.devidea.model.Servico;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface AtendimentoServicoBusiness {
	
	public AtendimentoServico create(AtendimentoServico entity);
	public AtendimentoServico findById(Integer id);
	public List<AtendimentoServico> findAll();
	public List<AtendimentoServico> findAll(Integer page);
	public List<AtendimentoServico> findAllFast();
	public AtendimentoServico update(AtendimentoServico entity);
	public void deleteByAtendimento(Atendimento entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<AtendimentoServico> list) throws JRException, IOException;
	public List<AtendimentoServico> findByExample(AtendimentoServico example, Integer... page);
	public List<Servico> findServicosByAtendimento(Atendimento atendimento);
	
}
