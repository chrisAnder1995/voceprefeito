package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Mulher;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface MulherBusiness {
	
	public Mulher create(Mulher entity);
	public Mulher findById(Integer id);
	public List<Mulher> findAll();
	public List<Mulher> findAll(Integer page);
	public List<Mulher> findAllFast();
	public Mulher update(Mulher entity);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<Mulher> list) throws JRException, IOException;
	public List<Mulher> findByExample(Mulher example, Integer... page);
	// METHODS_BUSINESS
}
