package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.OrientacaoSexual;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface OrientacaoSexualBusiness {
	
	public OrientacaoSexual create(OrientacaoSexual entity);
	public OrientacaoSexual findById(Integer id);
	public List<OrientacaoSexual> findAll();
	public List<OrientacaoSexual> findAll(Integer page);
	public List<OrientacaoSexual> findAllFast();
	public OrientacaoSexual update(OrientacaoSexual entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<OrientacaoSexual> list) throws JRException, IOException;
	public List<OrientacaoSexual> findByExample(OrientacaoSexual example, Integer... page);
	// METHODS_BUSINESS
}
