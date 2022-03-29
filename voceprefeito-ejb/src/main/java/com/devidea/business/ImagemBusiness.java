package com.devidea.business;

import java.util.List;

import javax.ejb.Local;

import com.devidea.model.Imagem;

@Local
public interface ImagemBusiness {
	
	public Imagem create(Imagem entity);
	public Imagem findById(Integer id);
	public List<Imagem> findAll();
	public Imagem update(Imagem entity);
	public void delete(Imagem entity);
}
