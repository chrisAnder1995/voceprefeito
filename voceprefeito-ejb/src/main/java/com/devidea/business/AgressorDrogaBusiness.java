package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Agressor;
import com.devidea.model.AgressorDroga;
import com.devidea.model.Droga;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface AgressorDrogaBusiness {
	
	public AgressorDroga create(AgressorDroga entity);
	public AgressorDroga findById(Integer id);
	public List<AgressorDroga> findAll();
	public List<AgressorDroga> findAll(Integer page);
	public List<AgressorDroga> findAllFast();
	public AgressorDroga update(AgressorDroga entity);
	public void deleteByAgressor(Agressor agressor);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<AgressorDroga> list) throws JRException, IOException;
	public List<AgressorDroga> findByExample(AgressorDroga example, Integer... page);
	public List<Droga> findDrogasByAgressor(Agressor agressor);
	}
