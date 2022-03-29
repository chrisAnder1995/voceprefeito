package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Ocorrencia;
import com.devidea.model.OcorrenciaViolencia;
import com.devidea.model.Usuario;
import com.devidea.model.ViolenciaTipo;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface OcorrenciaViolenciaBusiness {
	
	public OcorrenciaViolencia create(OcorrenciaViolencia entity);
	public OcorrenciaViolencia findById(Integer id);
	public List<OcorrenciaViolencia> findAll();
	public List<OcorrenciaViolencia> findAll(Integer page);
	public List<OcorrenciaViolencia> findAllFast();
	public OcorrenciaViolencia update(OcorrenciaViolencia entity);
	public void deleteByOcorrencia(Ocorrencia ocorrencia);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<OcorrenciaViolencia> list) throws JRException, IOException;
	public List<OcorrenciaViolencia> findByExample(OcorrenciaViolencia example, Integer... page);
	public List<ViolenciaTipo> findViolenciasByOcorrencia(Ocorrencia ocorrencia);
	
	// METHODS_BUSINESS
}
