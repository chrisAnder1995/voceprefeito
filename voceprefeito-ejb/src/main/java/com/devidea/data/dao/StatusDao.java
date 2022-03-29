package com.devidea.data.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.devidea.data.repository.StatusRepository;
import com.devidea.model.Status;

// IMPORTS_DAO

@Stateless
public class StatusDao extends GenericDao<Status> implements StatusRepository, Serializable{

	private static final long serialVersionUID = -2914289890271982059L;

	// VARIABLES_DAO

	// METHODS_DAO
}
