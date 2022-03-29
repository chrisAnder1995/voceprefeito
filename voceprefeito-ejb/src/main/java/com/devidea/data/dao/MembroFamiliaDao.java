package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.MembroFamiliaRepository;
import com.devidea.model.MembroFamilia;
import com.devidea.model.Mulher;

// IMPORTS_DAO

@Stateless
public class MembroFamiliaDao extends GenericDao<MembroFamilia> implements MembroFamiliaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;
	@Override
	public void deleteByMulher(Mulher mulher) {
		String hql = "delete from MembroFamilia where mulher_id = :mulher_id";
		this.session().createQuery(hql).setInteger("mulher_id", mulher.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembroFamilia> findByMulher(Mulher mulher) {
		Criteria criteria = this.session().createCriteria(MembroFamilia.class);
		criteria.add(Restrictions.eq("mulher", mulher));
		
		List<MembroFamilia> lista = (List<MembroFamilia>) criteria.list();
		
		return lista;
	}
}
