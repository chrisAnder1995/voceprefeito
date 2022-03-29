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
import com.devidea.business.SolicitacaoBusiness;
import com.devidea.data.repository.SolicitacaoRepository;
import com.devidea.model.Solicitacao;
import com.devidea.model.LocalFile;
import com.devidea.model.Usuario;
import com.devidea.util.PDFUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.devidea.business.ImagemBusiness;
import com.devidea.model.Imagem;
import com.devidea.business.UsuarioBusiness;
import com.devidea.model.Usuario;
import com.devidea.business.ViolenciaTipoBusiness;
import com.devidea.model.ViolenciaTipo;
import com.devidea.business.ViolenciaCategoriaBusiness;
import com.devidea.model.ViolenciaCategoria;
import com.devidea.business.ServicoBusiness;
import com.devidea.model.Servico;
import com.devidea.business.RegiaoBusiness;
import com.devidea.model.Regiao;
import com.devidea.business.OrientacaoSexualBusiness;
import com.devidea.model.OrientacaoSexual;
import com.devidea.business.DrogaBusiness;
import com.devidea.model.Droga;
import com.devidea.business.CidadeBusiness;
import com.devidea.model.Cidade;
import com.devidea.business.BairroBusiness;
import com.devidea.model.Bairro;
import com.devidea.business.LocalOcorrenciaBusiness;
import com.devidea.model.LocalOcorrencia;
import com.devidea.business.AgressorBusiness;
import com.devidea.model.Agressor;
import com.devidea.business.MembroFamiliaBusiness;
import com.devidea.model.MembroFamilia;
import com.devidea.business.MulherBusiness;
import com.devidea.model.Mulher;
import com.devidea.business.TempoGestacaoBusiness;
import com.devidea.model.TempoGestacao;
import com.devidea.business.OcorrenciaBusiness;
import com.devidea.model.Ocorrencia;
import com.devidea.business.AtendimentoBusiness;
import com.devidea.model.Atendimento;
// IMPORTS_CONTROLLER

@Stateless
public class SolicitacaoController implements SolicitacaoBusiness{
	
	@EJB
	private SolicitacaoRepository solicitacaoRepository;

	@EJB
	private LocalFileBusiness localFileBusiness;

	@EJB
	private ImagemBusiness imagemBusiness;

	@EJB
	private UsuarioBusiness usuarioBusiness;

	@EJB
	private ViolenciaTipoBusiness violenciaTipoBusiness;

	@EJB
	private ViolenciaCategoriaBusiness violenciaCategoriaBusiness;

	@EJB
	private ServicoBusiness servicoBusiness;

	@EJB
	private RegiaoBusiness religiaoBusiness;

	@EJB
	private OrientacaoSexualBusiness orientacaoSexualBusiness;

	@EJB
	private DrogaBusiness drogaBusiness;

	@EJB
	private CidadeBusiness cidadeBusiness;

	@EJB
	private BairroBusiness bairroBusiness;

	@EJB
	private LocalOcorrenciaBusiness localOcorrenciaBusiness;

	@EJB
	private AgressorBusiness agressorBusiness;

	@EJB
	private MembroFamiliaBusiness membroFamiliaBusiness;

	@EJB
	private MulherBusiness mulherBusiness;

	@EJB
	private TempoGestacaoBusiness tempoGestacaoBusiness;

	@EJB
	private OcorrenciaBusiness ocorrenciaBusiness;

	@EJB
	private AtendimentoBusiness atendimentoBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public Solicitacao create(Solicitacao entity) {
		entity = solicitacaoRepository.create(entity);
		return entity;
	}

	@Override
	public Solicitacao update(Solicitacao entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = solicitacaoRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public Solicitacao findById(Integer id) {
		return solicitacaoRepository.findById(id);
	}

	@Override
	public List<Solicitacao> findAll() {
		return solicitacaoRepository.findAll();
	}

	@Override
	public List<Solicitacao> findAll(Integer page) {
		return solicitacaoRepository.findAll(page);
	}

	@Override
	public List<Solicitacao> findAllFast() {
		return solicitacaoRepository.findAll();
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Solicitacao> solicitacaoList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(solicitacaoList != null) {	
			
			String reportName = "solicitacaoList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(solicitacaoList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<Solicitacao> findByExample(Solicitacao example, Integer... page) {
		return solicitacaoRepository.findByExample(example, page);
	}

	// METHODS_CONTROLLER
}
