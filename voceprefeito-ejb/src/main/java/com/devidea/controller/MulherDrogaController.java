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
import com.devidea.business.MulherDrogaBusiness;
import com.devidea.data.repository.MulherDrogaRepository;
import com.devidea.model.MulherDroga;
import com.devidea.model.Droga;
import com.devidea.model.LocalFile;
import com.devidea.model.Mulher;
import com.devidea.model.Usuario;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// IMPORTS_CONTROLLER

@Stateless
public class MulherDrogaController implements MulherDrogaBusiness{
	
	@EJB
	private MulherDrogaRepository mulherDrogaRepository;

	@EJB
	private LocalFileBusiness localFileBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public MulherDroga create(MulherDroga entity) {
		// CREATE_CONTROLLER_BEFORE
		entity = mulherDrogaRepository.create(entity);
		// CREATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public MulherDroga update(MulherDroga entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = mulherDrogaRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public MulherDroga findById(Integer id) {
		return mulherDrogaRepository.findById(id);
	}

	@Override
	public List<MulherDroga> findAll() {
		return mulherDrogaRepository.findAll();
	}

	@Override
	public List<MulherDroga> findAll(Integer page) {
		return mulherDrogaRepository.findAll(page);
	}

	@Override
	public List<MulherDroga> findAllFast() {
		return mulherDrogaRepository.findAll();
	}
	
	@Override
	public List<Droga> findDrogasByMulher(Mulher mulher) {
		return mulherDrogaRepository.findDrogasByMulher(mulher);
	}


	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<MulherDroga> mulherDrogaList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(mulherDrogaList != null) {	
			
			String reportName = "mulherDrogaList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mulherDrogaList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<MulherDroga> findByExample(MulherDroga example, Integer... page) {
		return mulherDrogaRepository.findByExample(example, page);
	}
	
	@Override
	public void deleteByMulher(Mulher mulher) {
		mulherDrogaRepository.deleteByMulher(mulher);
	}

	// METHODS_CONTROLLER
}
