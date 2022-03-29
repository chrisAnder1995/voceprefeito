package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.ImagemRepository;
import com.devidea.model.Imagem;

@Stateless
public class ImagemDao extends GenericDao<Imagem> implements ImagemRepository, Serializable{

	private static final long serialVersionUID = -4423287677842042038L;
	
	@Override
	public Imagem create(Imagem entity) {
		if(entity.getTipo() == null) {
			entity.setTipo("jpg");
		}
		return super.create(entity);
	}
}
