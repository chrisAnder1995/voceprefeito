package com.devidea.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.devidea.data.repository.UserGroupRepository;
import com.devidea.model.UserGroup;

// IMPORTS_DAO

@Stateless
public class UserGroupDao extends GenericDao<UserGroup> implements UserGroupRepository, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1170255718389602730L;

	/**
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findAllFast(){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id AS id, e.description AS description, e.totalAccess AS totalAccess ")
		.append("FROM UserGroup e ")
		.append("WHERE e.ativo = true ");
		
		sql.append("ORDER BY e.id ");
		
		Query q = this.session().createQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		List<UserGroup> result = (List<UserGroup>) q.list();
		return result;
	}

	


	// VARIABLES_DAO

	// METHODS_DAO
}
