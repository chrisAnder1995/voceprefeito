package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.AgressorDrogaRepository;
import com.devidea.model.Agressor;
import com.devidea.model.AgressorDroga;
import com.devidea.model.Droga;

// IMPORTS_DAO

@Stateless
public class AgressorDrogaDao extends GenericDao<AgressorDroga> implements AgressorDrogaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;
	@SuppressWarnings("unchecked")
	@Override
	public List<Droga> findDrogasByAgressor(Agressor agressor) {
		Criteria criteria = this.session().createCriteria(AgressorDroga.class);
		criteria.add(Restrictions.eq("agressor", agressor));
		
		List<AgressorDroga> lista = (List<AgressorDroga>) criteria.list();
		
		List<Droga> servicos = new ArrayList<Droga>();
		
		for (AgressorDroga item : lista) {
			servicos.add(item.getDroga());
		}
		
		return servicos;
	}

	@Override
	public void deleteByAgressor(Agressor agressor) {
		String hql = "delete from AgressorDroga where agressor_id = :agressor_id";
		this.session().createQuery(hql).setInteger("agressor_id", agressor.getId()).executeUpdate();
	}
}
