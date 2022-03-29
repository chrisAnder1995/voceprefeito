package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.LocalOcorrencia;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface LocalOcorrenciaBusiness {
	
	public LocalOcorrencia create(LocalOcorrencia entity);
	public LocalOcorrencia findById(Integer id);
	public List<LocalOcorrencia> findAll();
	public List<LocalOcorrencia> findAll(Integer page);
	public List<LocalOcorrencia> findAllFast();
	public LocalOcorrencia update(LocalOcorrencia entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<LocalOcorrencia> list) throws JRException, IOException;
	public List<LocalOcorrencia> findByExample(LocalOcorrencia example, Integer... page);
	// METHODS_BUSINESS
}
