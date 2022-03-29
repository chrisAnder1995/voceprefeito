package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.MembroFamilia;
import com.devidea.model.Mulher;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface MembroFamiliaBusiness {
	
	public MembroFamilia create(MembroFamilia entity);
	public MembroFamilia findById(Integer id);
	public List<MembroFamilia> findAll();
	public List<MembroFamilia> findAll(Integer page);
	public List<MembroFamilia> findAllFast();
	public MembroFamilia update(MembroFamilia entity);
	public void deleteByMulher(Mulher mulher);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<MembroFamilia> list) throws JRException, IOException;
	public List<MembroFamilia> findByExample(MembroFamilia example, Integer... page);
	public List<MembroFamilia> findByMulher(Mulher mulher);
	// METHODS_BUSINESS
}
