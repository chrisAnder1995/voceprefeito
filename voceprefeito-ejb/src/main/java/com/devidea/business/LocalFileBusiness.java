package com.devidea.business;

import java.util.List;

import javax.ejb.Local;

import com.devidea.model.LocalFile;

@Local
public interface LocalFileBusiness {
	
	public LocalFile create(LocalFile entity);
	public LocalFile findById(Integer id);
	public List<LocalFile> findAll();
	public LocalFile update(LocalFile entity);
	public void delete(LocalFile entity);
}
