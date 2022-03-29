package com.devidea.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import com.devidea.business.UserGroupBusiness;
import com.devidea.data.repository.UserGroupRepository;
import com.devidea.model.UserGroup;
import com.devidea.model.Usuario;
import com.devidea.util.PDFUtil;
import com.devidea.util.PhotoUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// IMPORTS_CONTROLLER

@Stateless
public class UserGroupController implements UserGroupBusiness{
	
	@EJB
	private UserGroupRepository userGroupRepository;

	// VARIABLES_CONTROLLER

	public UserGroup create(UserGroup entity) {
		// CREATE_CONTROLLER
		return userGroupRepository.create(entity);
	}

	public UserGroup update(UserGroup entity) {
		// UPDATE_CONTROLLER
		return userGroupRepository.update(entity);
	}

	@Override
	public UserGroup findById(Integer id) {
		return userGroupRepository.findById(id);
	}

	@Override
	public List<UserGroup> findAll() {
		List<UserGroup> UserGroups = userGroupRepository.findAll();

		Collections.sort(UserGroups, new Comparator<UserGroup>() {
			@Override
			public int compare(UserGroup o1, UserGroup o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
		});
		
		return UserGroups;
	}

	@Override
	public List<UserGroup> findAllFast() {
		List<UserGroup> UserGroups = userGroupRepository.findAllFast();
		
		return UserGroups;
	}

	@Override
	public List<UserGroup> findAll(Integer page) {
		return userGroupRepository.findAll(page);
	}

	@Override
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<UserGroup> UserGroups)
			throws JRException, IOException {
		byte[] reportBytes = null;
		
		if(UserGroups != null) {	
			
			String reportName = "UserGroups";
			
			String realPath = httpServletResquest.getSession().getServletContext().getRealPath("/");
			realPath = realPath.replace("web.war", "ejb.jar");
			
			String reportUserGroup = realPath + PDFUtil.JRXML_PATH;
			String jasperUserGroup = realPath + PDFUtil.JASPER_PATH;
			//String softwareLogoPath = realPath + PDFUtil.SOFTWARE_LOGO_PATH;
			String devideaLogoPath = realPath + PDFUtil.DEVIDEA_LOGO_PATH;
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("user_name", usuario.getFullName());
			
			parameters.put("logo_devidea_path", devideaLogoPath);
			parameters.put("report_jasper_path", jasperUserGroup);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(UserGroups);
			reportBytes = PDFUtil.generateJasperReportPDF(reportUserGroup, reportName, parameters, dataSource);
		}
		return reportBytes;
	}

	@Override
	public List<UserGroup> findByExample(UserGroup example, Integer... page) {
		return userGroupRepository.findByExample(example, page);
	}
	// METHODS_CONTROLLER



}
