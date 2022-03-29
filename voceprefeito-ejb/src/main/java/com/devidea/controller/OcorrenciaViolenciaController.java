package com.devidea.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.devidea.business.LocalFileBusiness;
import com.devidea.business.OcorrenciaViolenciaBusiness;
import com.devidea.data.repository.OcorrenciaViolenciaRepository;
import com.devidea.model.OcorrenciaViolencia;
import com.devidea.model.LocalFile;
import com.devidea.model.Ocorrencia;
import com.devidea.model.Usuario;
import com.devidea.model.ViolenciaTipo;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// IMPORTS_CONTROLLER

@Stateless
public class OcorrenciaViolenciaController implements OcorrenciaViolenciaBusiness{
	
	@EJB
	private OcorrenciaViolenciaRepository ocorrenciaViolenciaRepository;

	@EJB
	private LocalFileBusiness localFileBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public OcorrenciaViolencia create(OcorrenciaViolencia entity) {
		// CREATE_CONTROLLER_BEFORE
		entity = ocorrenciaViolenciaRepository.create(entity);
		// CREATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public OcorrenciaViolencia update(OcorrenciaViolencia entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = ocorrenciaViolenciaRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public OcorrenciaViolencia findById(Integer id) {
		return ocorrenciaViolenciaRepository.findById(id);
	}

	@Override
	public List<OcorrenciaViolencia> findAll() {
		return ocorrenciaViolenciaRepository.findAll();
	}

	@Override
	public List<OcorrenciaViolencia> findAll(Integer page) {
		return ocorrenciaViolenciaRepository.findAll(page);
	}

	@Override
	public List<OcorrenciaViolencia> findAllFast() {
		return ocorrenciaViolenciaRepository.findAll();
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<OcorrenciaViolencia> ocorrenciaViolenciaList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(ocorrenciaViolenciaList != null) {	
			
			String reportName = "ocorrenciaViolenciaList";
			
			String realPath = httpServletResquest.getSession().getServletContext().getRealPath("/");
			realPath = realPath.replace("web.war", "ejb.jar");
			
			String reportLocation = realPath + PDFUtil.JRXML_PATH;
			String jasperLocation = realPath + PDFUtil.JASPER_PATH;
			String softwareLogoPath = realPath + PDFUtil.SOFTWARE_LOGO_PATH;
			String devideaLogoPath = realPath + PDFUtil.DEVIDEA_LOGO_PATH;
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("user_name", usuario.getFullName());
			parameters.put("logo_image_path", softwareLogoPath);
			parameters.put("logo_devidea_path", devideaLogoPath);
			parameters.put("report_jasper_path", jasperLocation);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ocorrenciaViolenciaList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}
	
	@Override
	public List<ViolenciaTipo> findViolenciasByOcorrencia(Ocorrencia ocorrencia) {
		return ocorrenciaViolenciaRepository.findViolenciasByOcorrencia(ocorrencia);
	}
	
	@Override
	public void deleteByOcorrencia(Ocorrencia ocorrencia) {
		ocorrenciaViolenciaRepository.deleteByOcorrencia(ocorrencia);
	}

	@Override
	public List<OcorrenciaViolencia> findByExample(OcorrenciaViolencia example, Integer... page) {
		return ocorrenciaViolenciaRepository.findByExample(example, page);
	}

	// METHODS_CONTROLLER
}
