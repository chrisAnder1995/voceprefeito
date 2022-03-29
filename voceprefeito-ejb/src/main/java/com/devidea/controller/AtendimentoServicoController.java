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
import com.devidea.business.AtendimentoServicoBusiness;
import com.devidea.data.repository.AtendimentoServicoRepository;
import com.devidea.model.Atendimento;
import com.devidea.model.AtendimentoServico;
import com.devidea.model.LocalFile;
import com.devidea.model.Servico;
import com.devidea.model.Usuario;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// IMPORTS_CONTROLLER

@Stateless
public class AtendimentoServicoController implements AtendimentoServicoBusiness{
	
	@EJB
	private AtendimentoServicoRepository atendimentoServicoRepository;

	@EJB
	private LocalFileBusiness localFileBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public AtendimentoServico create(AtendimentoServico entity) {
		// CREATE_CONTROLLER_BEFORE
		entity = atendimentoServicoRepository.create(entity);
		// CREATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public AtendimentoServico update(AtendimentoServico entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = atendimentoServicoRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public AtendimentoServico findById(Integer id) {
		return atendimentoServicoRepository.findById(id);
	}

	@Override
	public List<AtendimentoServico> findAll() {
		return atendimentoServicoRepository.findAll();
	}

	@Override
	public List<AtendimentoServico> findAll(Integer page) {
		return atendimentoServicoRepository.findAll(page);
	}

	@Override
	public List<AtendimentoServico> findAllFast() {
		return atendimentoServicoRepository.findAll();
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<AtendimentoServico> atendimentoServicoList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(atendimentoServicoList != null) {	
			
			String reportName = "atendimentoServicoList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(atendimentoServicoList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<AtendimentoServico> findByExample(AtendimentoServico example, Integer... page) {
		return atendimentoServicoRepository.findByExample(example, page);
	}

	@Override
	public void deleteByAtendimento(Atendimento entity) {
		atendimentoServicoRepository.deleteByAtendimento(entity);
	}
	
	@Override
	public List<Servico> findServicosByAtendimento(Atendimento atendimento) {
		return atendimentoServicoRepository.findServicosByAtendimento(atendimento);
	}

	// METHODS_CONTROLLER
}
