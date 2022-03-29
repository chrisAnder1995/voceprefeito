package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import com.devidea.model.Droga;
import com.devidea.model.Mulher;
import com.devidea.model.MulherDroga;
import com.devidea.model.Usuario;

import net.sf.jasperreports.engine.JRException;
// IMPORTS_BUSINESS

@Local
public interface MulherDrogaBusiness {
	
	public MulherDroga create(MulherDroga entity);
	public MulherDroga findById(Integer id);
	public List<MulherDroga> findAll();
	public List<MulherDroga> findAll(Integer page);
	public List<MulherDroga> findAllFast();
	public MulherDroga update(MulherDroga entity);
	public void deleteByMulher(Mulher mulher);
	public byte[] listagemPDF(HttpServletRequest httpServletResquest, Usuario usuario, List<MulherDroga> list) throws JRException, IOException;
	public List<MulherDroga> findByExample(MulherDroga example, Integer... page);
	public List<Droga> findDrogasByMulher(Mulher mulher);
	// METHODS_BUSINESS
}
