package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Agressor;
import com.devidea.model.AgressorDroga;
// IMPORTS_REPOSITORY
import com.devidea.model.Droga;

@Local
public interface AgressorDrogaRepository extends GenericRepository<AgressorDroga>{
	// METHODS_REPOSITORY
	void deleteByAgressor(Agressor agressor);
	
	List<Droga> findDrogasByAgressor(Agressor agressor);
}
