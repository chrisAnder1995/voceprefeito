package com.devidea.model;

import java.io.Serializable;

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

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="atendimentoservico")
@SequenceGenerator(name = "seq_atendimentoservico", sequenceName = "seq_atendimentoservico", allocationSize = 1)
public class AtendimentoServico extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_atendimentoservico")
    @Expose
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="atendimento_id", referencedColumnName="id", nullable=false)
    @Expose
    private Atendimento atendimento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="servico_id", referencedColumnName="id", nullable=false)
    @Expose
    private Servico servico;
}
