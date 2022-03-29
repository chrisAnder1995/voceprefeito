package com.devidea.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

import com.devidea.enumeration.Parentesco;
import com.devidea.enumeration.Sexo;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="membrofamilia")
@SequenceGenerator(name = "seq_membrofamilia", sequenceName = "seq_membrofamilia", allocationSize = 1)
public class MembroFamilia extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_membrofamilia")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="sexo")
    private Sexo sexo;
    
    @Column(name="idade")
    @Expose
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="parentesco")
    private Parentesco parentesco;

    @Column(name="ocupacao")
    @Expose
    private String ocupacao;
	
	@Column(name = "renda")
	@Expose
	private BigDecimal renda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mulher_id", referencedColumnName="id", nullable=false)
    @Expose(deserialize=false, serialize=false)
    private Mulher mulher;
}
