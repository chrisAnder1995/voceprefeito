package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.OcorrenciaViolenciaRepository;
import com.devidea.model.Ocorrencia;
import com.devidea.model.OcorrenciaViolencia;
import com.devidea.model.ViolenciaTipo;

// IMPORTS_DAO

@Stateless
public class OcorrenciaViolenciaDao extends GenericDao<OcorrenciaViolencia> implements OcorrenciaViolenciaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	@SuppressWarnings("unchecked")
	public List<OcorrenciaViolencia> findByOcorrenciaList(List<Ocorrencia> ocorrencias){
		List<Integer> listIds = new ArrayList<Integer>();
		for (Ocorrencia ocorrencia : ocorrencias) {
			listIds.add(ocorrencia.getId());
		}
		
		Criteria criteria = this.session().createCriteria(OcorrenciaViolencia.class);
		criteria.add(Restrictions.in("ocorrencia", listIds.toArray()));
		criteria.add(Restrictions.eq("ativo", true));
		
		return (List<OcorrenciaViolencia>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViolenciaTipo> findViolenciasByOcorrencia(Ocorrencia ocorrencia) {
		Criteria criteria = this.session().createCriteria(OcorrenciaViolencia.class);
		criteria.add(Restrictions.eq("ocorrencia", ocorrencia));
		
		List<OcorrenciaViolencia> lista = (List<OcorrenciaViolencia>) criteria.list();
		
		List<ViolenciaTipo> violencias = new ArrayList<ViolenciaTipo>();
		
		for (OcorrenciaViolencia item : lista) {
			violencias.add(item.getViolenciaTipo());
		}
		
		return violencias;
	}

	@Override
	public void deleteByOcorrencia(Ocorrencia ocorrencia) {
		String hql = "delete from OcorrenciaViolencia where ocorrencia_id = :ocorrencia_id";
		this.session().createQuery(hql).setInteger("ocorrencia_id", ocorrencia.getId()).executeUpdate();
	}
}
