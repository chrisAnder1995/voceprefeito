package com.devidea.data.repository;

import javax.ejb.Local;
import java.util.List;

import com.devidea.model.Atendimento;
// IMPORTS_REPOSITORY
import com.devidea.model.ListagemPojo;

@Local
public interface AtendimentoRepository extends GenericRepository<Atendimento>{

//	List<Atendimento> findByListagem(ListagemPojo listagem);

}
