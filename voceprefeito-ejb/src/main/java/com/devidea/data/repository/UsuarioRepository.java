package com.devidea.data.repository;

import java.util.List;

import javax.ejb.Local;

import com.devidea.model.Usuario;


@Local
public interface UsuarioRepository extends GenericRepository<Usuario>{
	public Usuario find(String email, String password);
	public Usuario findByEmail(String email);
	public void preCadastro(String classe, String sequence, String nome);
	public List<Usuario> consultarUsuario(Usuario entity);
}
