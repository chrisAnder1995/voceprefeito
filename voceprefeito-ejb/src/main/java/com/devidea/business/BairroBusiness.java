package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Bairro;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface BairroBusiness {
	
	public Bairro create(Bairro entity);
	public Bairro findById(Integer id);
	public List<Bairro> findAll();
	public List<Bairro> findAll(Integer page);
	public List<Bairro> findAllFast();
	public Bairro update(Bairro entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Bairro> list) throws JRException, IOException;
	public List<Bairro> findByExample(Bairro example, Integer... page);
	// METHODS_BUSINESS
}
