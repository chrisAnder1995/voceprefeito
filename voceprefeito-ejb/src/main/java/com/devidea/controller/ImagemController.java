package com.devidea.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.devidea.business.ImagemBusiness;
import com.devidea.data.repository.ImagemRepository;
import com.devidea.model.Imagem;
import com.devidea.util.PhotoUtil;

@Stateless
public class ImagemController implements ImagemBusiness{
	
	@EJB
	private ImagemRepository imagemRepository;

	@Override
	public Imagem create(Imagem entity) {
		String savedPhoto = "";
		try {
			savedPhoto = PhotoUtil.savePhoto(entity.getBase64(), entity.getNome(), entity.getTipo());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setNome(savedPhoto);
		entity.setBase64("");
		return imagemRepository.create(entity);
	}

	@Override
	public Imagem findById(Integer id) {
		return imagemRepository.findById(id);
	}

	@Override
	public List<Imagem> findAll() {
		return imagemRepository.findAll();
	}

	@Override
	public Imagem update(Imagem entity) {
		String savedPhoto = "";
		try {
			savedPhoto = PhotoUtil.savePhoto(entity.getBase64(), entity.getNome(), entity.getTipo());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setNome(savedPhoto);
		return imagemRepository.update(entity);
	}
	
	public void delete(Imagem entity) {
		this.imagemRepository.delete(entity);
	}
}
