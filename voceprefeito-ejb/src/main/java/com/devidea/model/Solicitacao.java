package com.devidea.model;

import java.io.Serializable;

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

import com.devidea.enumeration.Turno;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="solicitacao")
@SequenceGenerator(name = "seq_solicitacao", sequenceName = "seq_solicitacao", allocationSize = 1)
public class Solicitacao extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_solicitacao")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;
    
    @Column(name="telefone", nullable=false)
    @Expose
    private String telefone;
    
    @Column(name="endereco")
    @Expose
    private String endereco;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bairro_id", referencedColumnName="id", nullable=true)
    @Expose
    private Bairro bairro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cidade_id", referencedColumnName="id", nullable=true)
    @Expose
    private Cidade cidade;
    
    @Column(name="numero")
    @Expose
    private String numero;
    
    @Column(name="complemento")
    @Expose
    private String complemento;
    
    @Column(name="descricao")
    @Expose
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="turno")
    private Turno turno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motivo_solicitacao_id", referencedColumnName="id", nullable=false)
    @Expose
    private MotivoSolicitacao motivoSolicitacao;
    
}
