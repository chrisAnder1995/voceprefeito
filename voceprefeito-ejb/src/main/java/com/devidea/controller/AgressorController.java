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
import com.devidea.business.AgressorBusiness;
import com.devidea.business.AgressorDrogaBusiness;
import com.devidea.data.repository.AgressorRepository;
import com.devidea.model.Agressor;
import com.devidea.model.AgressorDroga;
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
import com.devidea.business.PaisBusiness;
import com.devidea.model.Politica;
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
public class AgressorController implements AgressorBusiness{
	
	@EJB
	private AgressorRepository agressorRepository;

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
	private PaisBusiness paisBusiness;

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
	private MembroFamiliaBusiness membroFamiliaBusiness;

	@EJB
	private MulherBusiness mulherBusiness;

	@EJB
	private TempoGestacaoBusiness tempoGestacaoBusiness;

	@EJB
	private OcorrenciaBusiness ocorrenciaBusiness;

	@EJB
	private AtendimentoBusiness atendimentoBusiness;
	
	@EJB
	private AgressorDrogaBusiness agressorDrogaBusiness;

	// VARIABLES_CONTROLLER

	@Override
	public Agressor create(Agressor entity) {
		entity.setAtivo(true);
		entity = agressorRepository.create(entity);
		
		for (Droga droga : entity.getDrogas()) {
			AgressorDroga ad = new AgressorDroga();
			ad.setAgressor(entity);
			ad.setDroga(droga);
			ad.setAtivo(true);
			agressorDrogaBusiness.create(ad);
		}
		
		return entity;
	}

	@Override
	public Agressor update(Agressor entity) {
		// UPDATE_CONTROLLER_BEFORE
		entity = agressorRepository.update(entity);
		// UPDATE_CONTROLLER_AFTER
		return entity;
	}

	@Override
	public Agressor findById(Integer id) {
		return agressorRepository.findById(id);
	}

	@Override
	public List<Agressor> findAll() {
		return agressorRepository.findAll();
	}

	@Override
	public List<Agressor> findAll(Integer page) {
		return agressorRepository.findAll(page);
	}

	@Override
	public List<Agressor> findAllFast() {
		return agressorRepository.findAll();
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Agressor> agressorList)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(agressorList != null) {	
			
			String reportName = "agressorList";
			
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
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(agressorList);
			reportBytes = PDFUtil.generateJasperReportPDF(reportLocation, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<Agressor> findByExample(Agressor example, Integer... page) {
		return agressorRepository.findByExample(example, page);
	}

	// METHODS_CONTROLLER
}
