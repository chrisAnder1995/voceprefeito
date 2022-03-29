package com.devidea.model;

import com.devidea.enumeration.*;
import com.devidea.model.jsonAdapter.*;

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="violencia_categoria")
@SequenceGenerator(name = "seq_violencia_categoria", sequenceName = "seq_violencia_categoria", allocationSize = 1)
public class ViolenciaCategoria extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_violencia_categoria")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

// JAVA_INSERT

// METHODS_INSERT
}
