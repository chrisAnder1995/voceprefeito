package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.MembroFamilia;
// IMPORTS_REPOSITORY
import com.devidea.model.Mulher;

@Local
public interface MembroFamiliaRepository extends GenericRepository<MembroFamilia>{
	// METHODS_REPOSITORY
	void deleteByMulher(Mulher mulher);
	List<MembroFamilia> findByMulher(Mulher mulher);
}
