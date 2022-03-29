package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.MotivoSolicitacaoRepository;
import com.devidea.model.MotivoSolicitacao;

// IMPORTS_DAO

@Stateless
public class MotivoSolicitacaoDao extends GenericDao<MotivoSolicitacao> implements MotivoSolicitacaoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
