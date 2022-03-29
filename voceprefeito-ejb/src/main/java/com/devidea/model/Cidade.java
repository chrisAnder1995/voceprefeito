package com.devidea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devidea.enumeration.Estado;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="cidade")
@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
public class Cidade extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 2691413916387841395L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_cidade")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="estado")
    private Estado estado;
}