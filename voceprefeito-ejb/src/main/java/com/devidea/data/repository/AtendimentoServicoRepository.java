package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Atendimento;
import com.devidea.model.AtendimentoServico;
// IMPORTS_REPOSITORY
import com.devidea.model.Servico;

@Local
public interface AtendimentoServicoRepository extends GenericRepository<AtendimentoServico>{
	// METHODS_REPOSITORY
	void deleteByAtendimento(Atendimento entity);
	
	List<Servico> findServicosByAtendimento(Atendimento atendimento);
}
