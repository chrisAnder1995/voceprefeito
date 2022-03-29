package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.MulherDrogaRepository;
import com.devidea.model.Droga;
import com.devidea.model.Mulher;
import com.devidea.model.MulherDroga;

// IMPORTS_DAO

@Stateless
public class MulherDrogaDao extends GenericDao<MulherDroga> implements MulherDrogaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;
	
	@Override
	public void deleteByMulher(Mulher mulher) {
		String hql = "delete from MulherDroga where mulher_id = :mulher_id";
		this.session().createQuery(hql).setInteger("mulher_id", mulher.getId()).executeUpdate();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Droga> findDrogasByMulher(Mulher mulher) {
		Criteria criteria = this.session().createCriteria(MulherDroga.class);
		criteria.add(Restrictions.eq("mulher", mulher));
		
		List<MulherDroga> lista = (List<MulherDroga>) criteria.list();
		
		List<Droga> servicos = new ArrayList<Droga>();
		
		for (MulherDroga item : lista) {
			servicos.add(item.getDroga());
		}
		
		return servicos;
	}
}
