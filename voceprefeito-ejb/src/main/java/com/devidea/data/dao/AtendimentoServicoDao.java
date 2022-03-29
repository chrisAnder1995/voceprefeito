package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.AtendimentoServicoRepository;
import com.devidea.model.Atendimento;
import com.devidea.model.AtendimentoServico;
import com.devidea.model.Servico;

// IMPORTS_DAO

@Stateless
public class AtendimentoServicoDao extends GenericDao<AtendimentoServico> implements AtendimentoServicoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> findServicosByAtendimento(Atendimento atendimento) {
		Criteria criteria = this.session().createCriteria(AtendimentoServico.class);
		criteria.add(Restrictions.eq("atendimento", atendimento));
		
		List<AtendimentoServico> lista = (List<AtendimentoServico>) criteria.list();
		
		List<Servico> servicos = new ArrayList<Servico>();
		
		for (AtendimentoServico as : lista) {
			servicos.add(as.getServico());
		}
		
		return servicos;
	}

	@Override
	public void deleteByAtendimento(Atendimento atendimento) {
		String hql = "delete from AtendimentoServico where atendimento_id = :atendimento_id";
		this.session().createQuery(hql).setInteger("atendimento_id", atendimento.getId()).executeUpdate();
	}
}
