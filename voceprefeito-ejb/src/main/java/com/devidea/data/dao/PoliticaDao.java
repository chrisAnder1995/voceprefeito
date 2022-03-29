package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.PoliticaRepository;
import com.devidea.model.Politica;

// IMPORTS_DAO

@Stateless
public class PoliticaDao extends GenericDao<Politica> implements PoliticaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
