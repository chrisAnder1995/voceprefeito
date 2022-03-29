package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.ConfiguracaoRepository;
import com.devidea.model.Configuracao;

// IMPORTS_DAO

@Stateless
public class ConfiguracaoDao extends GenericDao<Configuracao> implements ConfiguracaoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
