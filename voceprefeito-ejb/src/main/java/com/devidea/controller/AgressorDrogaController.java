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
import com.devidea.business.AgressorDrogaBusiness;
import com.devidea.data.repository.AgressorDrogaRepository;
import com.devidea.model.Agressor;
import com.devidea.model.AgressorDroga;
import com.devidea.model.Droga;
import com.devidea.model.LocalFile;
import com.devidea.model.Usuario;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// IMPORTS_CONTROLLER

@Stateless
public class AgressorDrogaController implements AgressorDrogaBusiness{
	
	@EJB
	private AgressorDrogaRepository agressorDrogaRepository;

	@EJB
	private LocalFileBusiness localFileBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public AgressorDroga create(AgressorDroga entity) {
		// CREATE_CONTROLLER_BEFORE
		entity = agressorDrogaRepository.create(entity);
		// CREATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public AgressorDroga update(AgressorDroga entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = agressorDrogaRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public AgressorDroga findById(Integer id) {
		return agressorDrogaRepository.findById(id);
	}

	@Override
	public List<AgressorDroga> findAll() {
		return agressorDrogaRepository.findAll();
	}

	@Override
	public List<AgressorDroga> findAll(Integer page) {
		return agressorDrogaRepository.findAll(page);
	}

	@Override
	public List<AgressorDroga> findAllFast() {
		return agressorDrogaRepository.findAll();
	}
	
	@Override
	public void deleteByAgressor(Agressor agressor) {
		agressorDrogaRepository.deleteByAgressor(agressor);
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<AgressorDroga> agressorDrogaList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(agressorDrogaList != null) {	
			
			String reportName = "agressorDrogaList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(agressorDrogaList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<AgressorDroga> findByExample(AgressorDroga example, Integer... page) {
		return agressorDrogaRepository.findByExample(example, page);
	}
	

	@Override
	public List<Droga> findDrogasByAgressor(Agressor agressor) {
		return agressorDrogaRepository.findDrogasByAgressor(agressor);
	}


	// METHODS_CONTROLLER
}
