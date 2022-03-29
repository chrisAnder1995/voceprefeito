package com.devidea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.devidea.enumeration.ViolenciaCategoria;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="atendimento")
@SequenceGenerator(name = "seq_atendimento", sequenceName = "seq_atendimento", allocationSize = 1)
public class Atendimento extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_atendimento")
    @Expose
    private Integer id;

    @Column(name="sugestao_um", columnDefinition="TEXT")
    @Expose
    private String sugestao;
    
    @Column(name="sugestao_dois", columnDefinition="TEXT")
    @Expose
    private String sugestaoDois;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id", referencedColumnName="id", nullable=true)
    @Expose
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="politica_um_id", referencedColumnName="id", nullable=true)
    @Expose
    private Politica politicaUm;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="politica_dois_id", referencedColumnName="id", nullable=true)
    @Expose
    private Politica politicaDois;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="politicas_tres_id", referencedColumnName="id", nullable=true)
    @Expose
    private Politica politicaTres;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="servico_um_id", referencedColumnName="id", nullable=true)
    @Expose
    private Servico servicoUm;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="servico_dois_id", referencedColumnName="id", nullable=true)
    @Expose
    private Servico servicoDois;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="servico_tres_id", referencedColumnName="id", nullable=true)
    @Expose
    private Servico servicoTres;
    
    
}
