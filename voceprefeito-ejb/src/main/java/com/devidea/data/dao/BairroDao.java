package com.devidea.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.devidea.data.repository.BairroRepository;
import com.devidea.model.Bairro;

// IMPORTS_DAO

@Stateless
public class BairroDao extends GenericDao<Bairro> implements BairroRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> findAllFast(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id AS id, e.nome AS nome, e.regiao AS regiao ")
		.append("FROM Bairro e ")
		.append("WHERE e.ativo = true ");
		
		sql.append("ORDER BY e.nome ");
		
		Query q = this.session().createQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		List<Bairro> result = (List<Bairro>) q.list();
		return result;
	}
}
