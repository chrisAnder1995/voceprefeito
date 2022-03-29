package com.devidea.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.devidea.business.AgressorBusiness;
import com.devidea.business.AgressorDrogaBusiness;
import com.devidea.business.AtendimentoBusiness;
import com.devidea.business.AtendimentoServicoBusiness;
import com.devidea.business.BairroBusiness;
import com.devidea.business.CidadeBusiness;
import com.devidea.business.DrogaBusiness;
import com.devidea.business.ImagemBusiness;
import com.devidea.business.LocalFileBusiness;
import com.devidea.business.LocalOcorrenciaBusiness;
import com.devidea.business.MembroFamiliaBusiness;
import com.devidea.business.MulherBusiness;
import com.devidea.business.MulherDrogaBusiness;
import com.devidea.business.OcorrenciaBusiness;
import com.devidea.business.OcorrenciaViolenciaBusiness;
import com.devidea.business.OrientacaoSexualBusiness;
import com.devidea.business.PaisBusiness;
import com.devidea.business.RegiaoBusiness;
import com.devidea.business.ServicoBusiness;
import com.devidea.business.TempoGestacaoBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.business.ViolenciaCategoriaBusiness;
import com.devidea.business.ViolenciaTipoBusiness;
import com.devidea.data.repository.AtendimentoRepository;
import com.devidea.model.AgressorDroga;
import com.devidea.model.Atendimento;
import com.devidea.model.AtendimentoServico;
import com.devidea.model.Bairro;
import com.devidea.model.Droga;
import com.devidea.model.ListagemPojo;
import com.devidea.model.MembroFamilia;
import com.devidea.model.MulherDroga;
// IMPORTS_CONTROLLER
import com.devidea.model.OcorrenciaViolencia;
import com.devidea.model.Servico;
import com.devidea.model.UserGroup;
import com.devidea.model.Usuario;
import com.devidea.model.ViolenciaTipo;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Stateless
public class AtendimentoController implements AtendimentoBusiness{
	
	@EJB
	private AtendimentoRepository atendimentoRepository;


	// VARIABLES_CONTROLLER

	@Override
	public Atendimento create(Atendimento entity) {
		
		entity = atendimentoRepository.create(entity);
		
		
		return entity;
	}


	@Override
	public Atendimento update(Atendimento entity) {
		
		Atendimento resposta = atendimentoRepository.update(entity);
		
		return resposta;
	}

	@Override
	public Atendimento findById(Integer id) {
		return atendimentoRepository.findById(id);
	}

	@Override
	public List<Atendimento> findAll() {
		return atendimentoRepository.findAll();
	}

	@Override
	public List<Atendimento> findAll(Integer page) {
		return atendimentoRepository.findAll(page);
	}

	@Override
	public List<Atendimento> findAllFast() {
		return atendimentoRepository.findAllFast();
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Atendimento> atendimentoList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(atendimentoList != null) {	
			
			String reportName = "atendimentoList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(atendimentoList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	
	@Override
	public byte[] generatePDF(Integer atendimentoId, HttpServletRequest servletRequest) throws JRException, IOException {
		byte[] reportBytes = null;

		Atendimento atendimento = this.findById(atendimentoId);
		if(atendimento != null) {
			
			String reportName = PDFUtil.ATENDIMENTO_NAME;
			
			String realPath = servletRequest.getSession().getServletContext().getRealPath("/");
			realPath = realPath.replace("web.war", "ejb.jar");
			
			String reportLocation = realPath + PDFUtil.JRXML_PATH;
			String jasperLocation = realPath + PDFUtil.JASPER_PATH;
			String softwareLogoPath = realPath + PDFUtil.SOFTWARE_LOGO_PATH;
			String devideaLogoPath = realPath + PDFUtil.DEVIDEA_LOGO_PATH;
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("logo_image_path", softwareLogoPath);
			parameters.put("logo_devidea_path", devideaLogoPath);
			parameters.put("report_jasper_path", jasperLocation);
			
			List<Atendimento> items = new ArrayList<Atendimento>();
			items.add(atendimento);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}
	
	@Override
	public byte[] auxilioLegalPDF(Integer atendimentoId, HttpServletRequest servletRequest) throws JRException, IOException {
		byte[] reportBytes = null;
		return reportBytes;
	}


	@Override
	public List<Servico> findServicosByAtendimento(Atendimento atendimento) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Atendimento> findByExample(Atendimento example, Integer listPage, Integer listPageSize) {
		// TODO Auto-generated method stub
		return atendimentoRepository.findByExample(example, listPage, listPageSize);
	}


	// METHODS_CONTROLLER
}
