package com.devidea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.devidea.enumeration.Deficiencia;
import com.devidea.enumeration.Escolaridade;
import com.devidea.enumeration.Estado;
import com.devidea.enumeration.EstadoCivil;
import com.devidea.enumeration.Etnia;
import com.devidea.enumeration.Moradia;
import com.devidea.enumeration.TempoGestacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="mulher")
@SequenceGenerator(name = "seq_mulher", sequenceName = "seq_mulher", allocationSize = 1)
public class Mulher extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_mulher")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Column(name="idade")
    @Expose
    private Integer idade;

    @Column(name="nome_mae")
    @Expose
    private String nomeMae;

    @Column(name="endereco")
    @Expose
    private String endereco;

    @Column(name="numero")
    @Expose
    private String numero;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bairro_id", referencedColumnName="id", nullable=true)
    @Expose
    private Bairro bairro;

    @Column(name="complemento")
    @Expose
    private String complemento;

    @Column(name="ponto_referencia")
    @Expose
    private String pontoReferencia;

    @Column(name="telefone1")
    @Expose
    private String telefone1;

    @Column(name="telefone2")
    @Expose
    private String telefone2;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data_nascimento")
    @Expose
    private Date dataNascimento;

    @Column(name="naturalidade")
    @Expose
    private String naturalidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="nacionalidade_id", referencedColumnName="id", nullable=true)
    @Expose
    private Politica nacionalidade;
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="tempo_gestacao")
    private TempoGestacao tempoGestacao;    
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="estado_civil")
    private EstadoCivil estadoCivil;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="religiao_id", referencedColumnName="id", nullable=true)
    @Expose
    private Regiao religiao;   
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="escolaridade")
    private Escolaridade escolaridade;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="etnia")
    private Etnia etnia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orientacao_sexual_id", referencedColumnName="id")
    @Expose
    private OrientacaoSexual orientacaoSexual;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="deficiencia")
    private Deficiencia deficiencia;

    @Column(name="rg")
    @Expose
    private String rg;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="rg_uf")
    private Estado rgUf;

    @Column(name="rg_orgao")
    @Expose
    private String rgOrgao;

    @Column(name="cpf")
    @Expose
    private String cpf;
    
    @Column(name="usa_drogas")
    @Expose
    private Boolean usaDrogas;

    @Column(name="usa_medicamentos")
    @Expose
    private Boolean usaMedicamentos;

    @Column(name="medicamentos")
    @Expose
    private String medicamentos;

    @Column(name="desempregada")
    @Expose
    private Boolean desempregada;

    @Column(name="profissao")
    @Expose
    private String profissao;
    
    @Column(name="local_trabalho")
    @Expose
    private String localTrabalho;
	
	@Column(name = "renda_individual")
	@Expose
	private BigDecimal rendaIndividual;

    @Column(name="carteira_assinada")
    @Expose
    private Boolean carteiraAssinada;

    @Column(name="aposentada")
    @Expose
    private Boolean aposentada;
    
    @Column(name="cancelamento")
    @Expose
    private Boolean cancelamento;

    @Column(name="riscoDeVida")
    @Expose
    private Boolean riscoDeVida;

    @Column(name="sosMulher")
    @Expose
    private Boolean sosMulher;

    @Column(name="numeroSosMulher")
    @Expose
    private String numeroSosMulher;
	
	@Column(name = "outras_rendas_domiciliares")
	@Expose
	private BigDecimal outrasRendasDomiciliares;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="moradia")
    private Moradia moradia;

    @Column(name="observacao", columnDefinition="TEXT")
    @Expose
    private String observacao;

    @Transient
    @Expose
    private List<MembroFamilia> membrosFamilia;
    
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
    
    public String getEndereco() {
    	if (endereco != null) {
    		return endereco;
    	} else {
    		return "";
    	}
    }
    
    public String getNumero() {
    	if (numero != null) {
    		return numero;
    	} else {
    		return "";
    	}
    }
    
    public String getComplemento() {
    	if (complemento != null) {
    		return complemento;
    	} else {
    		return "";
    	}
    }
}
