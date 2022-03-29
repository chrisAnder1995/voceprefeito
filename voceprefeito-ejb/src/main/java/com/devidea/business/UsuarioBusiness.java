package com.devidea.business;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;

import com.devidea.model.Atendimento;
import com.devidea.model.Usuario;
import com.devidea.model.UsuarioEmail;
import com.devidea.model.transferObject.ClientMailTO;
import com.devidea.model.transferObject.SignInTO;

@Local
public interface UsuarioBusiness {
	
	public Usuario create(Usuario entity) throws IOException;
	public Usuario findById(Integer id);
	public List<Usuario> findAll();
	public Usuario update(Usuario entity) throws IOException;
	public Usuario login(String email, String password);
	public Usuario findByEmail(String email);
	public List<Usuario> findByExample(Usuario example, Integer... page);
	public void preCadastro(String classe, String sequence, String nome);
	public List<Usuario> findAllFast();
	public List<Usuario> consultarUsuario(Usuario entity);
	
	
	
}
