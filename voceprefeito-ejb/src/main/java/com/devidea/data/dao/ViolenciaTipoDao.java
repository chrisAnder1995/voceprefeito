package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devidea.data.repository.ViolenciaTipoRepository;
import com.devidea.model.ViolenciaTipo;

// IMPORTS_DAO

@Stateless
public class ViolenciaTipoDao extends GenericDao<ViolenciaTipo> implements ViolenciaTipoRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
