package com.devidea.data.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.AtendimentoRepository;
import com.devidea.model.Atendimento;
import com.devidea.model.GenericEntity;
import com.devidea.model.ListagemPojo;
import com.devidea.model.LocalOcorrencia;

// IMPORTS_DAO

@Stateless
public class AtendimentoDao extends GenericDao<Atendimento> implements AtendimentoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;
	
	private Class<Atendimento> entity;

	@SuppressWarnings("unchecked")
	public List<Atendimento> findByListagem(ListagemPojo listagem) {
		String hql = "select at from Atendimento as at"
				+ " join at.ocorrencia as oc "
				+ " join at.mulher as mu "
				+ " left join oc.bairro as br "
				+ " left join br.cidade as cd "
				+ " left join oc.localOcorrencia as lo "
				+ " where at.ativo = true ";
		
		String where = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		

		if (listagem.getViolenciaCategoria() != null) {
			where += " and oc in (select o from OcorrenciaViolencia as ov join ov.violenciaTipo as vt join ov.ocorrencia as o where vt.violenciaCategoria = '" + listagem.getViolenciaCategoria().name() + "') ";
		}
		if (listagem.getCidade() != null) {
			where += " and cd.id = " + listagem.getCidade().getId();
		}
		if (listagem.getBairro() != null) {
			where += " and br.id = " + listagem.getBairro().getId();
		}
		if (listagem.getIdadeInicio() > 0) {
			where += " and mu.idade >= " + listagem.getIdadeInicio();
		}
		if (listagem.getIdadeFim() > 0) {
			where += " and mu.idade <= " + listagem.getIdadeFim();
		}
		if (listagem.getDataCadastroInicio() != null) {
			where += " and at.dataHora >= '" + formatter.format(listagem.getDataCadastroInicio()) + "'";
		}
		if (listagem.getDataCadastroFim() != null) {
			where += " and at.dataHora <= '" + formatter.format(listagem.getDataCadastroFim()) + "'";
		}
		if (listagem.getDataOcorrenciaInicio() != null) {
			where += " and oc.data >= '" + formatter.format(listagem.getDataOcorrenciaInicio()) + "'";
		}
		if (listagem.getDataOcorrenciaFim() != null) {
			where += " and oc.data <= '" + formatter.format(listagem.getDataOcorrenciaFim()) + "'";
		}
		if (listagem.getRendaInicio() != null) {
			where += " and mu.rendaIndividual >= " + listagem.getRendaInicio();
		}
		if (listagem.getRendaFim() != null) {
			where += " and mu.rendaIndividual <= " + listagem.getRendaFim();
		}
		
		if (listagem.getLocaisOcorrencia() != null) {
			String locais = "";
			for (LocalOcorrencia lc : listagem.getLocaisOcorrencia()) {
				if (locais != "") {
					locais += ", ";
				}
				locais += lc.getId();
			}
			
			where += " and lo.id in (" + locais + ") ";
		}
		
		String order = " order by mu.nome";
		
		Query query = this.session().createQuery(hql + where + order);
		
		List<Atendimento> lista = (List<Atendimento>) query.list();
		
		return lista;
	}

	
}
