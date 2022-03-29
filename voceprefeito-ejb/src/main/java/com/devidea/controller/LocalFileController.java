package com.devidea.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.devidea.business.LocalFileBusiness;
import com.devidea.data.repository.LocalFileRepository;
import com.devidea.model.LocalFile;
import com.devidea.util.PhotoUtil;

@Stateless
public class LocalFileController implements LocalFileBusiness{
	
	@EJB
	private LocalFileRepository localFileRepository;

	@Override
	public LocalFile create(LocalFile entity) {
		String savedPhoto = "";
		try {
			savedPhoto = PhotoUtil.savePhoto(entity.getBase64(), entity.getNome(), entity.getTipo());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setNome(savedPhoto);
		entity.setBase64("");
		return localFileRepository.create(entity);
	}

	@Override
	public LocalFile findById(Integer id) {
		return localFileRepository.findById(id);
	}

	@Override
	public List<LocalFile> findAll() {
		return localFileRepository.findAll();
	}

	@Override
	public LocalFile update(LocalFile entity) {
		String savedPhoto = "";
		try {
			savedPhoto = PhotoUtil.savePhoto(entity.getBase64(), entity.getNome(), entity.getTipo());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setNome(savedPhoto);
		return localFileRepository.update(entity);
	}
	
	public void delete(LocalFile entity) {
		this.localFileRepository.delete(entity);
	}
}
