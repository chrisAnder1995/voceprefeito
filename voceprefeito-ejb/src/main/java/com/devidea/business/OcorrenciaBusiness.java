package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Ocorrencia;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface OcorrenciaBusiness {
	
	public Ocorrencia create(Ocorrencia entity);
	public Ocorrencia findById(Integer id);
	public List<Ocorrencia> findAll();
	public List<Ocorrencia> findAll(Integer page);
	public List<Ocorrencia> findAllFast();
	public Ocorrencia update(Ocorrencia entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Ocorrencia> list) throws JRException, IOException;
	public List<Ocorrencia> findByExample(Ocorrencia example, Integer... page);
	// METHODS_BUSINESS
}
