package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Droga;
import com.devidea.model.Mulher;
import com.devidea.model.MulherDroga;
// IMPORTS_REPOSITORY

@Local
public interface MulherDrogaRepository extends GenericRepository<MulherDroga>{
	// METHODS_REPOSITORY
	void deleteByMulher(Mulher mulher);
	List<Droga> findDrogasByMulher(Mulher mulher);
}
