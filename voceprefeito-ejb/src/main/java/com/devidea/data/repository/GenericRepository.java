package com.devidea.data.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import org.hibernate.criterion.Criterion;

@Local
public interface GenericRepository<T> extends Serializable{

	public static final String PERSISTENCE_UNIT = "voceprefeitoDS";
	
	public T create(T entity);
	public T findById(Integer id);
	public List<T> findAll();
	public List<T> findAll(Integer page);
	public T update(T entity);
	public void delete(T entity);
	public List<T> findByExample(T entity, List<Criterion> criterions, Integer... page);
	public List<T> findByExample(T entity, Integer... page);
	List<T> findAllFast();
	Long getSqlSize(String sql);
}
