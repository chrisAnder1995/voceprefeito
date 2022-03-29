package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.SolicitacaoRepository;
import com.devidea.model.Solicitacao;

// IMPORTS_DAO

@Stateless
public class SolicitacaoDao extends GenericDao<Solicitacao> implements SolicitacaoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

}
