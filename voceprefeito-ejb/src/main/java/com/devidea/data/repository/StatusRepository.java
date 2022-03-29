package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Politica;
// IMPORTS_REPOSITORY
import com.devidea.model.Status;

@Local
public interface StatusRepository extends GenericRepository<Status>{
	// METHODS_REPOSITORY
}
