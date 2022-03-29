package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.UserGroup;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;


@Local
public interface UserGroupBusiness {
	
	public UserGroup create(UserGroup entity);
	public UserGroup update(UserGroup entity);
	public UserGroup findById(Integer id);
	public List<UserGroup> findAll(Integer page);
	public List<UserGroup> findAllFast();
	public List<UserGroup> findAll();
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<UserGroup> list) throws JRException, IOException;
	public List<UserGroup> findByExample(UserGroup example, Integer... page);
	// METHODS_BUSINESS
}