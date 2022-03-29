package com.devidea.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.devidea.enumeration.ViolenciaCategoria;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListagemPojo {
	@Expose
	private List<LocalOcorrencia> locaisOcorrencia;
	@Expose
	private Date dataCadastroInicio;
	@Expose
	private Date dataCadastroFim;
	@Expose
	private Date dataOcorrenciaInicio;
	@Expose
	private Date dataOcorrenciaFim;
	@Expose
	private Cidade cidade;
	@Expose
	private Bairro bairro;
	@Expose
	private ViolenciaCategoria violenciaCategoria;
	@Expose
	private int idadeInicio;
	@Expose
	private int idadeFim;
	@Expose
	private BigDecimal rendaInicio;
	@Expose
	private BigDecimal rendaFim;
}
