package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.OcorrenciaRepository;
import com.devidea.model.Ocorrencia;

// IMPORTS_DAO

@Stateless
public class OcorrenciaDao extends GenericDao<Ocorrencia> implements OcorrenciaRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
