package com.devidea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.devidea.enumeration.Deficiencia;
import com.devidea.enumeration.Escolaridade;
import com.devidea.enumeration.EstadoCivil;
import com.devidea.enumeration.Etnia;
import com.devidea.enumeration.Parentesco;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="agressor")
@SequenceGenerator(name = "seq_agressor", sequenceName = "seq_agressor", allocationSize = 1)
public class Agressor extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_agressor")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="estado_civil")
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="etnia")
    private Etnia etnia;

    @Column(name="idade")
    @Expose
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="parentesco")
    private Parentesco parentesco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orientacao_sexual_id", referencedColumnName="id")
    @Expose
    private OrientacaoSexual orientacaoSexual;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="deficiencia")
    private Deficiencia deficiencia;

    @Column(name="desempregado")
    @Expose
    private Boolean desempregado;

    @Column(name="profissao")
    @Expose
    private String profissao;

    @Column(name="ocupacao")
    @Expose
    private String ocupacao;
	
	@Column(name = "renda")
	@Expose
	private BigDecimal renda;

    @Column(name="local_trabalho")
    @Expose
    private String localTrabalho;

    @Column(name="carteira_assinada")
    @Expose
    private Boolean carteiraAssinada;

    @Column(name="aposentado")
    @Expose
    private Boolean aposentado;

    @Column(name="usa_drogas")
    @Expose
    private Boolean usaDrogas;

    @Column(name="usa_medicamentos")
    @Expose
    private Boolean usaMedicamentos;

    @Column(name="medicamentos")
    @Expose
    private String medicamentos;

    @Column(name="antecedentes_criminais")
    @Expose
    private Boolean antecedentesCriminais;

    @Column(name="violencia_contra_familiares")
    @Expose
    private Boolean violenciaContraFamiliares;

    @Column(name="violencia_contra_outros")
    @Expose
    private Boolean violenciaContraOutros;

    @Column(name="foi_preso")
    @Expose
    private Boolean foiPreso;    

    @Column(name="motivo_prisao")
    @Expose
    private String motivoPrisao;
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="escolaridade")
    private Escolaridade escolaridade;
    
    @Transient
    @Expose
    private List<Droga> drogas;
    
    public String getDrogasString() {
    	String drogas = "";
		
		if (getDrogas() != null) {
			for (Droga droga : getDrogas()) {
				if (!drogas.equals("")) {
					drogas += ", ";
				}
				drogas += droga.getNome();
			}
		}
		
		return drogas;
    }
}
