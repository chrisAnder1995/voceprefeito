package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.LocalFileRepository;
import com.devidea.model.LocalFile;

@Stateless
public class LocalFileDao extends GenericDao<LocalFile> implements LocalFileRepository, Serializable{

	private static final long serialVersionUID = -4423287677842042038L;
	
	@Override
	public LocalFile create(LocalFile entity) {
		if(entity.getTipo() == null) {
			entity.setTipo("jpg");
		}
		return super.create(entity);
	}
}
