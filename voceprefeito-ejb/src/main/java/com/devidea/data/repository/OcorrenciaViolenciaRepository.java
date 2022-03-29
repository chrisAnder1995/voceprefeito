package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Ocorrencia;
import com.devidea.model.OcorrenciaViolencia;
// IMPORTS_REPOSITORY
import com.devidea.model.ViolenciaTipo;

@Local
public interface OcorrenciaViolenciaRepository extends GenericRepository<OcorrenciaViolencia>{
	// METHODS_REPOSITORY
	public List<OcorrenciaViolencia> findByOcorrenciaList(List<Ocorrencia> ocorrencias);

	public List<ViolenciaTipo> findViolenciasByOcorrencia(Ocorrencia ocorrencia);

	public void deleteByOcorrencia(Ocorrencia ocorrencia);
}
