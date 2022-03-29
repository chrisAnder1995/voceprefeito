package com.devidea.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
@MappedSuperclass
public abstract class GenericEntity implements Serializable {

	private static final long serialVersionUID = -3474241642047963322L;

	@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data_cadastro")
    @Expose
    private Date dataCadastro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_cadastro_id", referencedColumnName="id", nullable=true)
    @Expose(serialize = false)
    private Usuario usuarioCadastro;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data_atualizacao")
    @Expose
    private Date dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_atualizacao_id", referencedColumnName="id", nullable=true)
    @Expose(serialize = false)
    private Usuario usuarioAtualizacao;

    @Column(name="ativo", nullable=true)
    @Expose
    private Boolean ativo;
    
    @Expose
    @Transient
    private Integer listSize;
    
    @Expose
    @Transient
    private Integer listPage;
    
    @Expose
    @Transient
    private Integer listPageSize;
    
    @Expose
    @Transient
    private String listOrder;
    
    @Expose
    @Transient
    private Boolean orderAsc;

}
